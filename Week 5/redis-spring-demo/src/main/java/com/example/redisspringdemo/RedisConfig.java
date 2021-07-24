package com.example.redisspringdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    // create your configurations and return connection factory
    @Bean
    RedisConnectionFactory getRedisFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();

        redisStandaloneConfiguration.setHostName("redis-14082.c278.us-east-1-4.ec2.cloud.redislabs.com");
        redisStandaloneConfiguration.setPort(14082);
        redisStandaloneConfiguration.setPassword("ho6X2HwtMKvSjov19ySrCqpqX8WhUFd5");

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    // gives a redis template, specifying the serializer for both key and value/list/hash
//    @Bean
//    RedisTemplate<String, Object> getRedisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        JdkSerializationRedisSerializer objectSerializer = new JdkSerializationRedisSerializer();
//
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//
//        redisTemplate.setValueSerializer(objectSerializer);
//
//        redisTemplate.setHashValueSerializer(objectSerializer);
//
//        redisTemplate.setConnectionFactory(getRedisFactory());
//
//        return redisTemplate;
//    }

    @Bean
    RedisTemplate<Object, Object> getRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer objectSerializer = new JdkSerializationRedisSerializer();

        redisTemplate.setKeySerializer(objectSerializer);

        redisTemplate.setValueSerializer(objectSerializer);

        redisTemplate.setHashValueSerializer(objectSerializer);

        redisTemplate.setConnectionFactory(getRedisFactory());

        return redisTemplate;
    }

    @Bean
    ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
