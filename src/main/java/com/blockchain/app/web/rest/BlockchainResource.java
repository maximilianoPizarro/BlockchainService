package com.blockchain.app.web.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap.SimpleEntry;

import com.blockchain.app.domain.StampResponse;
import com.blockchain.app.domain.StampVerify2;
import com.blockchain.app.domain.StampVerifyResponse;
import com.blockchain.app.domain.StampVerifyResponse2;
import com.blockchain.app.service.BlockchainService;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	 * POST /tsa2/stamp : Stamp Metadata tsa2
	 *
	 * @param String metadata
	 * @return StampResponse
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */    
    @PostMapping("/tsa2/stamp")
    public ResponseEntity<SimpleEntry> tsangStamp(@RequestParam("metadata") String metadata)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Stamp Metadata TSA = "+metadata);
        return ResponseEntity.ok().body(new SimpleEntry<String, Integer> ("status",blockchainService.altaBloqueTsaNG(blockchainService.hashDocumento(metadata))));
    }

    /**
	 * POST /tsa2/stamp/multipartfile : Stamp MultipartFile tsa2
	 *
	 * @param MultipartFile multipartfile
	 * @return StampResponse
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */
    @PostMapping("/tsa2/stamp/multipartfile")
    public ResponseEntity<SimpleEntry> tsangStamp(MultipartFile multipartfile)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Stamp File TSA = "+multipartfile.getOriginalFilename());            
        return ResponseEntity.ok().body(new SimpleEntry<String, Integer> ("status",blockchainService.altaBloqueTsaNG(blockchainService.hashFile(multipartfile))));
    }

    /**
	 * GET /tsa/verify : Hash & Rd temporary
	 *
	 * @param String hash
     * @param String Rd Temporary
	 * @return StampVerifyResponse
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */
    @GetMapping("/tsa/verify")
    public ResponseEntity<StampVerifyResponse> tsaVerify(@RequestParam("hash") String hash,@RequestParam("rd") String rd)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Verify Hash ="+hash +" , RD="+rd);
        return ResponseEntity.ok().body(blockchainService.verificarBloque(hash, rd));
    }

    /**
	 * GET /tsa2/verify : Hash
	 *
	 * @param String hash
     * @param String Rd Temporary
	 * @return StampVerifyResponse
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */
    @GetMapping("/tsa2/verify")
    public ResponseEntity<StampVerify2> tsa2Verify(@RequestParam("hash") String hash)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Verify Hash ="+hash);
        return ResponseEntity.ok().body(blockchainService.verificarBloqueTsaNg(hash));
    }


    /**
	 * GET /hash/sha256 : Hash RequestParam
	 *
	 * @param String metadata
	 * @return SimpleEntry
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */
    @GetMapping("/hash/sha256")
    public ResponseEntity<SimpleEntry> hash(@RequestParam("metadata") String metadata)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Generando SHA256 ");            
        return ResponseEntity.ok().body(new SimpleEntry<String, String> ("hash",blockchainService.hashDocumento(metadata)));
    }

    /**
	 * GET /hash/sha256/multipartfile : Hash MultipartFile
	 *
     * @param MultipartFile multipartfile
	 * @return SimpleEntry
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 * @throws IOException
	 */
    @GetMapping("/hash/sha256/multipartfile")
    public ResponseEntity<SimpleEntry> hashMultipartFile(MultipartFile multipartfile)
            throws UnsupportedEncodingException, ParseException, IOException {
            log.debug("Generando SHA256 ");            
        return ResponseEntity.ok().body(new SimpleEntry<String, String> ("hash",blockchainService.hashFile(multipartfile)));
    }
}
