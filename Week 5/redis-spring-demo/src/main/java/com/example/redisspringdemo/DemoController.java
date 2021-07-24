package com.example.redisspringdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/setkeystring")
    public void setKeyString(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        logger.info("add key:{} with value:{}", key, value);
    }

    @GetMapping("/getkeystring")
    public Object getKeyString(@RequestParam String key) {
        Object value = redisTemplate.opsForValue().get(key);
        logger.info("get key:{} with value:{}", key, value);
        return value;
    }

    @PostMapping("/setkeyperson")
    public void setKeyPerson(@RequestParam String key, @RequestBody Person person) {
        redisTemplate.opsForValue().set(key, person, Duration.ofSeconds(15));
        logger.info("add key:{} with value:{}", key, person);
    }

    @GetMapping("/getkeyperson")
    public Person getKeyPerson(@RequestParam String key) {
        Person value = (Person) redisTemplate.opsForValue().get(key);
        logger.info("get key:{} with value:{}", key, value);
        return value;
    }

    @PostMapping("setpersonperson")
    public void setPersonPerson(@RequestBody Person person) {
        Person key = new Person(100, "admin", "USA");
        redisTemplate.opsForValue().set(key, person);
    }

    @PostMapping("/getpersonperson")
    public Person getPersonPerson() {
        Person key = new Person(90, "admin", "USA");
        return (Person) redisTemplate.opsForValue().get(key);
    }

    // -------------------------X-------------------------------

    @PostMapping("/lpush")
    public void lPush(@RequestParam String key, @RequestBody Person person) {
        redisTemplate.opsForList().leftPush(key, person);
        logger.info("lpush on key:{} with value:{}", key, person);
    }

    @PostMapping("/rpush")
    public void rPush(@RequestParam String key, @RequestBody Person person) {
        redisTemplate.opsForList().rightPush(key, person);
        logger.info("rpush on key:{} with value:{}", key, person);
    }

    @GetMapping("lrange")
    public List<Person> lRange(@RequestParam String key, @RequestParam int start, @RequestParam int end) {
        List<Object> result = redisTemplate.opsForList().range(key, start, end);
        return result.stream().map(object -> (Person)object).sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
    }

    @PostMapping("/lpop")
    public Person lPop(@RequestParam String key) {
        return (Person) redisTemplate.opsForList().leftPop(key);
    }

    @PostMapping("/rpop")
    public Person rPop(@RequestParam String key) {
        return (Person) redisTemplate.opsForList().rightPop(key);
    }

    // ---------------------------X---------------------------------

    @PostMapping("/hmset")
    public void hmset(@RequestParam String key, @RequestBody Person person) {

//        Map<String, Object> mappedPerson = new HashMap<>();
//        mappedPerson.put("id", person.getId());
//        // country
//        // name

//        ObjectMapper objectMapper = new ObjectMapper();

        Map mappedPerson = objectMapper.convertValue(person, Map.class);

        redisTemplate.opsForHash().putAll(key, mappedPerson);
    }

    @GetMapping("/hmget")
    public Person hmget(@RequestParam String key) {
        Map mappedPerson = redisTemplate.opsForHash().entries(key);

        Person person = objectMapper.convertValue(mappedPerson, Person.class);

        logger.info("get person:{}", mappedPerson);
        return person;
    }

    // -------------------------------X-----------------------------
    // perform operations on set
}
