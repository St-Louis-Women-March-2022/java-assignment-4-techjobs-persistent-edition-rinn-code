package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("title", "Skills");
        model.addAttribute("skills", skillRepository.findAll());
    for (Skill skill : skillRepository.findAll()){
        System.out.println(skill.getId());
        }
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }


    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        }
        skillRepository.save(newSkill);
        return "redirect:";
    }

    //#4 displayViewSkill will be in charge of rendering a page to view the contents of an individual skill object.
    // It will make use of that skill object’s id field to grab the correct information from skillRepository.
    // optSkill is currently initialized to null.
    // Replace this using the .findById() method with the right argument to look for the given skill object from the data layer.
    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {
/*Original*/
//        Optional optSkill = null;
//        if (optSkill.isPresent()) {
//            Skill skill = (Skill) optSkill.get();
//            model.addAttribute("skill", skill);
//            return "skills/view";
//        } else {
//            return "redirect:../";
//        }
        if (skillId >= 0 ) {

            model.addAttribute("skill", skillRepository.findById(skillId));
            return "skills/view";
        } else {
            return "redirect:../";
        }
    }

}
