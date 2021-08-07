package com.example.kafkademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public void sendMessage(@RequestParam String message, @RequestParam String key) {
        messageService.sendMessage(message, key);
    }
}
