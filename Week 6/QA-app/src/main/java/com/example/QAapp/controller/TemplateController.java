package com.example.QAapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TemplateController {

    @GetMapping
    @ResponseBody
    public String welcomePage() {
        return "Hello Users to our site";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }
}
