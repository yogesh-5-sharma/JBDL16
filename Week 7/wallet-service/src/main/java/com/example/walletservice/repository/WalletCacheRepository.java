package com.example.walletservice.repository;

import com.example.walletservice.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class WalletCacheRepository {

    private static final String WALLET_PREFIX = "WALLET::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private String getKey(String userId) {
        return WALLET_PREFIX + userId;
    }

    public void saveWalletByUserid(Wallet wallet) {
        if(wallet == null || wallet.getUserId() == null  || wallet.getUserId().equals("")) {
            return;
        }

        redisTemplate.opsForValue().set(getKey(wallet.getUserId()), wallet, Duration.ofMinutes(30));
    }

    public Wallet getWalletByUserId(String userId) {
        if(userId == null  || userId.equals("")) {
            return null;
        }

        return (Wallet) redisTemplate.opsForValue().get(getKey(userId));
    }
}
