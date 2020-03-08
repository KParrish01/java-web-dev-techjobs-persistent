package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs",jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers",employerRepository.findAll());  // Added in Part 3 "Updating HomeController
        model.addAttribute("skills", skillRepository.findAll());       // Instead of DTO GetMapping
        return "add";
    }


    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model) {
//                                    Errors errors, Model model/*, @RequestParam int employer*/, @RequestParam @NotEmpty(message = "At least one skill is required.") List<Integer> skills) {  // Can avoid extra RequestParam since variables are already bound by the Model - that way we can use bound validation
//                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {   // starter code

        if (errors.hasErrors()) {
            model.addAttribute("employers",employerRepository.findAll());  // Added in Part 3 "Updating HomeController
            model.addAttribute("skills", skillRepository.findAll());
            model.addAttribute("errorMsg", "Entry needed!");
            return "add";
        }

        jobRepository.save(newJob);
        model.addAttribute("job",newJob);

//        model.addAttribute("employerId",employerId); // could not get it to work with employerId in Thymeleaf 'add' form
//        model.addAttribute("employerId", employer);
        model.addAttribute("employerId", newJob.getEmployer().getId());   // employer here is name="employer" that th:field creates in 'add.html' (switched for employerId in starter code)

//        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills); // TODO: This worked with RequestParam skills but didn't take advantage of model binding for validation

        List<Skill> skillObjs = new ArrayList<>();
        for (Skill skillObj : newJob.getSkills()){
            skillRepository.findById(skillObj.getId());
            skillObjs.add(skillObj);
            newJob.setSkills(skillObjs);
        }

        newJob.setSkills(skillObjs);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> optJob = jobRepository.findById(jobId);     //similar passage in SkillController or EmployerController
            if (optJob.isPresent()) {
                Job job = (Job) optJob.get();
                model.addAttribute("job", job);
                model.addAttribute("job.skills", skillRepository.findAll());
                return "view";
            } else {
                return "redirect:";
            }

    }
}
