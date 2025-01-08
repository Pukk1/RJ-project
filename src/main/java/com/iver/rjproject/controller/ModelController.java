package com.iver.rjproject.controller;

import com.iver.rjproject.model.DomainModel;
import com.iver.rjproject.service.ChineseMakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ModelController {

    private final DomainModel domainModel;
    private final ChineseMakerService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("model", domainModel);
        return "index";
    }

    @PostMapping("/parameters")
    public String updateParameters(
            Model model,
            @RequestParam(required = false) Integer delay,
            @RequestParam(required = false) Integer makers
    ) {
        if (delay != null) {
            domainModel.setDelay(delay);
        }
        if (makers != null) {
            domainModel.setMakersCount(makers);
            service.setPoolSize(domainModel.getMakersCount());
        }
        model.addAttribute("model", domainModel);
        return "redirect:/";
    }
}
