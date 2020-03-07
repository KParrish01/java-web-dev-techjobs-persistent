package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

//    @Id
//    @GeneratedValue
//    private int id;
//
//    private String name;

//    private String employer;
    @ManyToOne
    @NotNull(message="Employer is required.")
    @Valid
    private Employer employer;

//    private String skills;
    @ManyToMany
    @Valid
    public List<Skill> skills = new ArrayList<>();

    public Job() {
    }

//    public Job(String anEmployer, String someSkills) {
//    public Job(Employer anEmployer, String someSkills) {
public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

//    public String getEmployer() {
//        return employer;
//    }
//
//    public void setEmployer(String employer) {
//        this.employer = employer;
//    }


    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

//    public String getSkills() {
//        return skills;
//    }


    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }


//    public String getSkills() {
//        return skills;
//    }
//
//    public void setSkills(String skills) {
//        this.skills = skills;
//    }

    //    public void setSkills(List<Skill> skills) {
//        this.skills = skills;
//    }


//    public List<Integer> getSkills() {
//        return skills;
//    }
//
//    public void setSkills(List<Integer> skills) {
//        this.skills = skills;
//    }

}
