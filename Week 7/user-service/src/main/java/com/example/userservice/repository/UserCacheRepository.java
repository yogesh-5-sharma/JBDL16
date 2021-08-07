package com.example.userservice.repository;

import com.example.userservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class UserCacheRepository {

    private static final String USER_PREFIX = "USER::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private String getKey(String id) {
        return USER_PREFIX + id;
    }

    public void saveUserById(User user) {
        if (user == null
                || user.getUserId() == null
                || user.getUserId().equals("")) {
            return;
        }

        redisTemplate.opsForValue().set(getKey(user.getUserId()), user, Duration.ofHours(1));
    }

    public User getUserById(String userId) {
        if(userId == null  || userId.equals("")) {
            return null;
        }

        return (User)redisTemplate.opsForValue().get(getKey(userId));
    }

}
