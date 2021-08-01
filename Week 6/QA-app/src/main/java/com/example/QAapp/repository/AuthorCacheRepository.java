package com.example.QAapp.repository;

import com.example.QAapp.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class AuthorCacheRepository {

    private final String AUTHOR_PREFIX = "AUTHOR::";

    @Autowired 
    RedisTemplate<String, Object> redisTemplate;

    private String getKey(String username) {
        return AUTHOR_PREFIX + username;
    }

    public void saveAuthorByUsername(Author author) {
        if(author == null || author.getSystemUser() == null || author.getSystemUser().getUsername() == null) {
            return ;
        }
        String username = author.getSystemUser().getUsername();

        redisTemplate.opsForValue().set(getKey(username), author, Duration.ofHours(1));
    }

    public Author getAuthorByUsername(String username) {
        if (username == null || username.equals("")) {
            return null;
        }

        return (Author) redisTemplate.opsForValue().get(getKey(username));
    }

    public void deleteAuthorByUsername(String username) {
        if (username == null || username.equals("")) {
            return;
        }

        redisTemplate.delete(getKey(username));
    }
}
