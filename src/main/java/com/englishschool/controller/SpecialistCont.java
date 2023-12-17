package com.englishschool.controller;

import com.englishschool.controller.main.Attributes;
import com.englishschool.model.Teachers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/teachers")
public class SpecialistCont extends Attributes {
    @GetMapping("/add")
    public String TeacherAdd(Model model) {
        AddAttributes(model);
        return "addSpecialist";
    }

    @GetMapping("/delete/{id}")
    public String TeacherDelete(@PathVariable Long id) {
        teachersRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String TeacherAddNew(Model model, @RequestParam String name, @RequestParam String email, @RequestParam String ach, @RequestParam String tel, @RequestParam byte exp, @RequestParam String spec, @RequestParam MultipartFile file) {
        String res = "";
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "teachers/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributes(model);
                return "addSpecialist";
            }
        }

        teachersRepo.save(new Teachers(name, res, tel, ach, spec, exp, email));

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String TeacherEdit(Model model, @PathVariable Long id) {
        AddAttributesTeacherEdit(model, id);
        return "editSpecialist";
    }

    @PostMapping("/edit/{id}")
    public String TeacherEditOld(Model model, @RequestParam String name, @RequestParam String email, @RequestParam String ach, @RequestParam String tel, @RequestParam byte exp, @RequestParam String spec, @RequestParam MultipartFile file, @PathVariable Long id) {
        Teachers teacher = teachersRepo.getReferenceById(id);

        teacher.setSpec(spec);
        teacher.setName(name);
        teacher.setAch(ach);
        teacher.setExp(exp);
        teacher.setEmail(email);
        teacher.setTel(tel);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String res = "";
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "teachers/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                AddAttributesTeacherEdit(model, id);
                model.addAttribute("message", "Некорректный данные!");
                return "editSpecialist";
            }
            teacher.setFile(res);
        }

        teachersRepo.save(teacher);
        return "redirect:/";
    }
}
