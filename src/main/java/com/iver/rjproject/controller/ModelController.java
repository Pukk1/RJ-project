package com.iver.rjproject.controller;

import com.iver.rjproject.model.DomainModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ModelController {

    private final DomainModel domainModel;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("model", domainModel);
        return "index";
    }

    @PatchMapping("/parameters")
    public String index(
            Model model,
            @RequestParam(required = false) Integer cpuGenerationSpeed,
            @RequestParam(required = false) Integer pcGenerationSpeed,
            @RequestParam(required = false) Integer makersCount
    ) {
        domainModel.setCpuGenerationSpeed(cpuGenerationSpeed);
        domainModel.setPcGenerationSpeed(pcGenerationSpeed);
        domainModel.setMakersCount(makersCount);
        model.addAttribute("model", domainModel);
        return "index";
    }
}
