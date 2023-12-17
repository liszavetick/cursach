package com.englishschool.controller.main;

import com.englishschool.model.Purchased;
import com.englishschool.model.Subs;
import com.englishschool.model.enums.Role;
import com.englishschool.model.enums.Status;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesIndex(Model model) {
        AddAttributes(model);
        model.addAttribute("teachers", teachersRepo.findAll());
    }

    protected void AddAttributesTeacherEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("teacher", teachersRepo.getReferenceById(id));
    }

    protected void AddAttributesSubs(Model model) {
        AddAttributes(model);
        model.addAttribute("subs", subsRepo.findAll());
        model.addAttribute("teachers", teachersRepo.findAll());
    }

    protected void AddAttributesSubsMy(Model model) {
        AddAttributes(model);
        model.addAttribute("purchased", getUser().getPurchased());
    }

    protected void AddAttributesStats(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
    }

    protected void AddAttributesSubsSearch(Model model, String name, String category, String sort) {
        AddAttributes(model);

        List<Subs> subs = subsRepo.findAllByNameContainingAndSpecContaining(name, category);

        subs.sort(Comparator.comparing(Subs::getModules));

        if (Objects.equals(sort, "max")) Collections.reverse(subs);

        model.addAttribute("subs", subs);
        model.addAttribute("teachers", teachersRepo.findAll());
    }

    protected void AddAttributesProgress(Model model) {
        AddAttributes(model);
        List<Purchased> purchasedList = purchasedRepo.findAllByStatus(Status.IN_PROGRESS);
        purchasedList.addAll(purchasedRepo.findAllByStatus(Status.DONE));
        model.addAttribute("purchased", purchasedList);
    }

    protected void AddAttributesSubEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("sub", subsRepo.getReferenceById(id));
    }
}
