package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message=" Location is required.")
    @Size(min = 1, max = 50, message = "Location must be 1 to 50 characters.")
    private String location;

//    @OneToMany(mappedBy = "employer")
    @OneToMany
    @JoinColumn(name="employer_id")
    private List<Job> jobs = new ArrayList<>();
//    private final List<Job> jobs = new ArrayList<>(); // <<<<TODO: Part 3 - Check if it needs to be final (I do not believe so from what I know now...)

    public Employer() {
    }

    public Employer(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public String toString() {
        return location;
    }
}
