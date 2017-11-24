package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(path = "/")
    public String home(final Model model) {
        model.addAttribute("user", "Max");
        return "home";
    }
}
