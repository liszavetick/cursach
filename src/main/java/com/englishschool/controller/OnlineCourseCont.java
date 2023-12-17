package com.englishschool.controller;

import com.englishschool.controller.main.Attributes;
import com.englishschool.model.Purchased;
import com.englishschool.model.Subs;
import com.englishschool.model.enums.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/subs")
public class OnlineCourseCont extends Attributes {
    @GetMapping
    public String Subs(Model model) {
        AddAttributesSubs(model);
        return "subs";
    }

    @GetMapping("/my")
    public String SubsMy(Model model) {
        AddAttributesSubsMy(model);
        return "mySubs";
    }

    @PostMapping("/search")
    public String SubsSearch(Model model, @RequestParam String name, @RequestParam String category, @RequestParam String sort) {
        AddAttributesSubsSearch(model, name, category, sort);
        return "subs";
    }

    @GetMapping("/my/progress/{id}")
    public String SubMyProgress(@PathVariable Long id) {
        Purchased purchased = purchasedRepo.getReferenceById(id);
        purchased.setCount((byte) (purchased.getCount() + 1));
        if (purchased.getCount() >= purchased.getSub().getModules()) {
            purchased.setStatus(Status.DONE);
        }
        purchasedRepo.save(purchased);
        return "redirect:/subs/my";
    }

    @GetMapping("/my/delete/{id}")
    public String SubMyDelete(@PathVariable Long id) {
        Purchased purchased = purchasedRepo.getReferenceById(id);
        purchasedRepo.delete(purchased);
        return "redirect:/subs/my";
    }

    @PostMapping("/buy/{id}")
    public String SubBuy(@RequestParam Long teacherId, @PathVariable Long id) {
        Subs sub = subsRepo.getReferenceById(id);
        subsRepo.save(sub);
        purchasedRepo.save(new Purchased(sub, getUser(), teachersRepo.getReferenceById(teacherId)));
        return "redirect:/subs";
    }

    @GetMapping("/add")
    public String SubAdd(Model model) {
        AddAttributes(model);
        return "addSub";
    }

    @GetMapping("/edit/{id}")
    public String SubEdit(Model model, @PathVariable Long id) {
        AddAttributesSubEdit(model, id);
        return "editSub";
    }

    @GetMapping("/delete/{id}")
    public String SubDelete(@PathVariable Long id) {
        subsRepo.deleteById(id);
        return "redirect:/subs";
    }

    @PostMapping("/add")
    public String subsAddNew(Model model, @RequestParam String name, @RequestParam MultipartFile file, @RequestParam String spec, @RequestParam byte modules, @RequestParam String time, @RequestParam String complexity, @RequestParam String description) {
        String res = "";
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "subs/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributes(model);
                return "addSub";
            }
        }

        subsRepo.save(new Subs(name, res, spec, modules, time, complexity, description));

        return "redirect:/subs";
    }

    @PostMapping("/edit/{id}")
    public String SubEditOld(Model model, @RequestParam String name, @RequestParam MultipartFile file, @RequestParam String spec, @RequestParam byte modules, @RequestParam String time, @RequestParam String complexity, @RequestParam String description, @PathVariable Long id) {
        Subs sub = subsRepo.getReferenceById(id);

        sub.setName(name);
        sub.setSpec(spec);
        sub.setModules(modules);
        sub.setTime(time);
        sub.setComplexity(complexity);
        sub.setDescription(description);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String res = "";
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "subs/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesSubEdit(model, id);
                return "editSub";
            }
            sub.setFile(res);
        }

        subsRepo.save(sub);

        return "redirect:/subs";
    }
}
