package com.blockchain.app.service;

import com.blockchain.app.domain.StampResponse;
import com.blockchain.app.domain.StampVerify2;
import com.blockchain.app.domain.StampVerifyResponse;

import org.apache.http.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Servicios consumir tsa y tsa2 api de Blockchain Federal Argentina.
 * 
 * @autor maximiliano.pizarro.5@gmail.com
 **/

public interface BlockchainService {

    //Genera un hash a partir de un String
    public String hashDocumento(String documento) throws UnsupportedEncodingException;
    
    //Genera un hash a partir de un archivo
    public String hashFile(MultipartFile multipartfile)throws IOException;

    //TSA
    public StampResponse altaBloque(String hash) throws UnsupportedEncodingException, ParseException, IOException;
    
    public StampVerifyResponse verificarBloque(String file_hash, String rd) throws ParseException, IOException;

    //TSA NG
    int altaBloqueTsaNG(String hash) throws ParseException, IOException;

    public int altaBloqueTsaNG(List<String> hashes) throws ParseException, IOException; 
    
    public StampVerify2 verificarBloqueTsaNg(String hash) throws ParseException, IOException;
}
