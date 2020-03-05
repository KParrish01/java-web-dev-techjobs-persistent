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
    @JoinColumn(name="employer_id") // or "employer.id" ? <<< which parameter to join on? employer or employer.id or employer_id or jobs_id (all are columns in job table)? TODO: Check what this should look like...
    private List<Job> jobs = new ArrayList<>(); // <<<<TODO: Part 3 - Check to make sure this is correct at Part 3 'Add a jobs Field to Employer' 1.

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

    @Override
    public String toString() {
        return location;
    }
}
