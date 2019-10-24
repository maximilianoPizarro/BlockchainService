package com.blockchain.app.web.rest;

import com.blockchain.app.service.BlockchainService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BlockchainResource {

    private final Logger log = LoggerFactory.getLogger(BlockchainResource.class);

    private final BlockchainService blockchainService;

    public BlockchainResource(BlockchainService blockchainService){
        this.blockchainService=blockchainService;
    }
    

    
}
