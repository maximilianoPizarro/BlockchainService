package com.blockchain.app.web.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap.SimpleEntry;

import com.blockchain.app.domain.StampResponse;
import com.blockchain.app.service.BlockchainService;
import com.google.gson.Gson;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class BlockchainResource {

    private final Logger log = LoggerFactory.getLogger(BlockchainResource.class);

    private final BlockchainService blockchainService;

    public BlockchainResource(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }
    
	/**
	 * POST /tsa/stamp : Stamp Metadata tsa1
	 *
	 * @param String metadata
	 * @return StampResponse
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */
    
    @PostMapping("/tsa/stamp")
    public ResponseEntity<StampResponse> tsaStamp(@RequestParam("metadata") String metadata)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Stamp Metadata TSA = "+metadata);
        return ResponseEntity.ok().body(blockchainService.altaBloque(blockchainService.hashDocumento(metadata)));
    }

    /**
	 * POST /tsa/stamp/multipartfile : Stamp MultipartFile tsa1
	 *
	 * @param MultipartFile multipartfile
	 * @return StampResponse
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */

    @PostMapping("/tsa/stamp/multipartfile")
    public ResponseEntity<StampResponse> tsaStamp(MultipartFile multipartfile)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Stamp File TSA = "+multipartfile.getOriginalFilename());
        return ResponseEntity.ok().body(blockchainService.altaBloque(blockchainService.hashFile(multipartfile)));
    }

    /**
	 * POST /tsa/stamp : Stamp Metadata tsa1
	 *
	 * @param String metadata
	 * @return StampResponse
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */
    
    @PostMapping("/tsa2/stamp")
    public ResponseEntity<String> tsangStamp(@RequestParam("metadata") String metadata)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Stamp Metadata TSA = "+metadata);
            SimpleEntry<String,Integer > response = new SimpleEntry<String, Integer> ("status",blockchainService.altaBloqueTsaNG(blockchainService.hashDocumento(metadata)));
        return ResponseEntity.ok().body(new Gson().toJson(response));
    }

    /**
	 * POST /tsa/stamp/multipartfile : Stamp MultipartFile tsa1
	 *
	 * @param MultipartFile multipartfile
	 * @return StampResponse
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */

    @PostMapping("/tsa2/stamp/multipartfile")
    public ResponseEntity<String> tsangStamp(MultipartFile multipartfile)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Stamp File TSA = "+multipartfile.getOriginalFilename());
            SimpleEntry<String,Integer > response = new SimpleEntry<String, Integer> ("status",blockchainService.altaBloqueTsaNG(blockchainService.hashFile(multipartfile)));
        return ResponseEntity.ok().body(new Gson().toJson(response));
    }
}
