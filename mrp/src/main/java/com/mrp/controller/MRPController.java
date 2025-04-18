package com.mrp.controller;


import com.mrp.dto.RecipeComponentDTO;
import com.mrp.entities.RecipeComponent;
import com.mrp.service.MRPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MRPController {
    private final MRPService service;

    @Autowired
    public MRPController(MRPService service) { this.service = service; }
    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping("/requirement")
    public String showRequirement(@RequestParam int bicycles, Model model) {
        model.addAttribute("bicycles", bicycles);
        model.addAttribute("requirement", service.calculateTotalRequirement(bicycles));
        return "requirement";
    }

    @GetMapping("/procurement")
    public String showProcurement(@RequestParam int bicycles, Model model) {
        Map<String, Integer> required = service.calculateTotalRequirement(bicycles);
        model.addAttribute("bicycles", bicycles);
        model.addAttribute("procurement", service.calculateProcurement(required));
        return "procurement";
    }

    @GetMapping("/max")
    public String showMaxBuildable(Model model) {
        model.addAttribute("maxBicycles", service.calculateMaxBicyclesPossible());
        return "max";
    }

    @PostMapping("/add-inventory")
    public String addInventory(@RequestParam String partName, @RequestParam int quantity) {
        service.addInventory(partName, quantity);
        return "redirect:/";
    }

    @PostMapping("/add-part")
    public String addPart(@RequestParam String partName) {
        service.addPart(partName);
        return "redirect:/";
    }

    @PostMapping("/add-recipe")
    public String addRecipe(@ModelAttribute RecipeComponentDTO dto) {
        service.addRecipeComponent(dto);
        return "redirect:/";
    }

}

