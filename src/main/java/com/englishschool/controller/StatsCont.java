package com.englishschool.controller;

import com.englishschool.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsCont extends Attributes {

    @GetMapping
    public String stats(Model model) {
        AddAttributesStats(model);
        return "stats";
    }
}
