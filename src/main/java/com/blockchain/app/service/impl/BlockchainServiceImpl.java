package com.blockchain.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.blockchain.app.domain.Stamp;
import com.blockchain.app.domain.Stamp2;
import com.blockchain.app.domain.StampResponse;
import com.blockchain.app.domain.StampVerify;
import com.blockchain.app.domain.StampVerify2;
import com.blockchain.app.domain.StampVerifyResponse;
import com.blockchain.app.domain.StampVerifyResponse2;
import com.blockchain.app.service.BlockchainService;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;

/**
 * Servicios consumir tsa y tsa2 apis de Blockchain Federal Argentina.
 * 
 * @autor maximiliano.pizarro.5@gmail.com
 **/

@Service
public class BlockchainServiceImpl implements BlockchainService {

	private final Logger log = LoggerFactory.getLogger(BlockchainServiceImpl.class);

	@Value("${app.blockchain.tsa_api}")
	private String urlBfa;

	@Value("${app.blockchain.tsang_api}")
	private String urlBfa2;

	@Value("${app.blockchain.tsa_api_stamp}")
	private String urlBfaStamp;

	@Value("${app.blockchain.tsang_api_stamp}")
	private String urlBfaStamp2;

	@Value("${app.blockchain.tsa_api_verify}")
	private String urlBfaVerify;

	@Override
	public String hashDocumento(String documento) throws UnsupportedEncodingException {
		byte data[] = documento.getBytes("UTF8");
		log.debug("Json Documento = [" + documento + "]");
		String string = Hashing.sha256().hashBytes(data).toString();
		log.debug("SHA256 generado = [" + string + "]");
		return string;
	}
	
	@Override
	public String hashFile(MultipartFile multipartfile) throws IOException {
		String string = Hashing.sha256().hashBytes(multipartfile.getBytes()).toString();
		log.debug("SHA256 pdf generado = [" + string + "]");
		return string;
	}


	// TSA
	@Override
	public StampResponse altaBloque(String hash) throws ParseException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(urlBfaStamp);
		httppost.addHeader("content-type", "application/json");
		StringEntity params = new StringEntity(new Gson().toJson(new Stamp(hash)));
		log.debug(urlBfa + " Alta Blockchain=[ hash: " + hash + " ]");
		httppost.setEntity(params);
		HttpResponse response = httpclient.execute(httppost);
		return new Gson().fromJson(EntityUtils.toString(response.getEntity()), StampResponse.class);
	}
	
	@Override
	public StampVerifyResponse verificarBloque(String file_hash, String rd) throws ParseException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(urlBfaVerify);
		httppost.addHeader("content-type", "application/json");
		StringEntity params = new StringEntity(new Gson().toJson(new StampVerify(file_hash, rd)));
		log.debug(urlBfa + " Verificación Blockchain=[ { file_hash:" + file_hash + ", rd_temporal:" + rd + "} ]");
		httppost.setEntity(params);
		HttpResponse response = httpclient.execute(httppost);
		return new Gson().fromJson(EntityUtils.toString(response.getEntity()), StampVerifyResponse.class);
	}

	// TSA NG
	@Override
	public int altaBloqueTsaNG(List<String> hashes) throws ParseException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		hashes.stream().forEach((p) -> {
			log.debug(urlBfaStamp2 + " Alta Blockchain=[ hash: " + p + " ]");
		});
		HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(new Stamp2(hashes)), headers);
		String response = restTemplate.postForObject(urlBfaStamp2, entity, String.class);
		if (response.toString().compareTo("success") == 0) {
			return 200;
		} else {
			return 400;
		}

	}

	@Override
	public int altaBloqueTsaNG(String hash) throws ParseException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(new Stamp2(hash)), headers);
		String response = restTemplate.postForObject(urlBfaStamp2, entity, String.class);
		if (response.toString().compareTo("success") == 0) {
			return 200;
		} else {
			return 400;
		}
	}

	@Override
	public StampVerify2 verificarBloqueTsaNg(String hash) throws ParseException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(urlBfa2 + "/verify/" + hash);
		log.debug(urlBfa + " Verificación Blockchain=[ hash:" + hash + " ]");
		HttpResponse response = httpclient.execute(httpget);
		return new Gson().fromJson(EntityUtils.toString(response.getEntity()), StampVerify2.class);
	}

}
