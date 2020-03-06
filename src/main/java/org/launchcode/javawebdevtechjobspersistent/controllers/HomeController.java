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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired           // TODO Part 3: This was added in 'Update HomeController' 1. - shouldn't this be 'JobRepository' below?
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired         // TODO Part 3: See comment above...
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
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }


    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
//                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
                                    Errors errors, Model model, @RequestParam int employer, @RequestParam List<Integer> skills) {
//                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Skill> skills) {
//                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam Employer employer, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
            model.addAttribute("title", "Errors present - try again: Add Job");

            return "add";
        }
        /* Ad code here to list chosen job - see coding-events as example*/
//        model.addAttribute(new Job(employer, skills)); // This can't be it as either we need to change skills in Job model class to List<Integer> or change RequestParam above
        jobRepository.save(newJob);               // TODO: Check
        model.addAttribute("job",newJob);

//        model.addAttribute("employerId",employerId); // could not get it to work with employerId in Thymeleaf 'add' form
        model.addAttribute("employerId",employer);   // employer here is name="employer" that th:field creates in 'add.html' (switched for employerId in starter code)

//        employerRepository.findById(employerId);  // TODO: Check
//        employerRepository.save(new Employer());  // TODO: Checkcheck
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
//      model.addAttribute("job",jobRepository.findById(jobId));
////      jobRepository.findAll();

        //below taken from SkillController or EmployerController: TODO: Fix below to work with template/view.html

        Optional<Job> optJob = jobRepository.findById(jobId);
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
