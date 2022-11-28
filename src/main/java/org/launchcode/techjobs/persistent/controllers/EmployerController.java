package org.launchcode.techjobs.persistent.controllers;


import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {
    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("title", "Employers");
        model.addAttribute("employers", employerRepository.findAll());
        for (Employer employer : employerRepository.findAll()){
            System.out.println(employer.getId());
        }
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }
        employerRepository.save(newEmployer);
        return "redirect:";
    }

    //#4 displayViewEmployer will be in charge of rendering a page to view the contents of an individual employer object.
    // It will make use of that employer object’s id field to grab the correct information from employerRepository.
    // optEmployer is currently initialized to null.
    // Replace this using the .findById() method with the right argument to look for the given employer object from the data layer.
    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
/*Original*/
//        Optional optEmployer = null;
//        if (optEmployer.isPresent()) {
//            Employer employer = (Employer) optEmployer.get();
//            model.addAttribute("employer", employer.getId());
//            return "employers/view";
//        } else {
//            return "redirect:../";
//        }

        if (employerId >= 0 ) {

            model.addAttribute("employer", employerRepository.findById(employerId));
            return "employers/view";
        } else {
            return "redirect:../";
        }

    }
}
