package com.example.springbeansdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class TemplateController {

//    @GetMapping("/home")
//    public String homepage(@RequestParam String name, Model model) {
//
//        User user = new User("Yogesh", 50);
//        model.addAttribute("myuser", user);
//
//        model.addAttribute("myname", name);
//
//        List<User> userList = Arrays.asList(
//                new User("ABC", 50),
//                new User("DEF", 60),
//                new User("XYZ", 70)
//        );
//        model.addAttribute("userList", userList);
//
//        return "homepage";
//    }

    @GetMapping("/home")
    public ModelAndView homepage(@RequestParam(required = false, defaultValue = "MNO") String name) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("homepage");

        User user = new User("Yogesh", 50);
        mv.addObject("myuser", user);
        mv.addObject("myname", name);

        return mv;
    }
}
