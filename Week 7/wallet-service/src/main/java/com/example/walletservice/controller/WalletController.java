package com.example.walletservice.controller;

import com.example.walletservice.models.Wallet;
import com.example.walletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @Autowired
    WalletService walletService;

    @GetMapping("/wallet/{userId}")
    public Wallet getWalletByUserId(@PathVariable String userId) {
        return walletService.getWalletByUserid(userId);
    }
}
