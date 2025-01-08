package com.iver.rjproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataSourceController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("additionalDataSourceCreated", additionalDataSourceCreated);
        return "index";
    }

    @GetMapping("/create-source")
    public String createDataSource() {
        return "redirect:/";
    }
}
