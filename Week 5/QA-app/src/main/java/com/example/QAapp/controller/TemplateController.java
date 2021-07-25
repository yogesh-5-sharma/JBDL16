package com.example.QAapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

    @GetMapping
    public String welcomePage() {
        return "Hello Users to our site";
    }
}
