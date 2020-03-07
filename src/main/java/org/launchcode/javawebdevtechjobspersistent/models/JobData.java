package org.launchcode.javawebdevtechjobspersistent.models;

import java.util.ArrayList;

// This is a change made in sandbox.

/**
 * Created by LaunchCode
 */
public class JobData {


    /**
     * Returns the results of searching the Jobs data by field and search term.
     *
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param column Job field that should be searched.
     * @param value Value of the field to search for.
     * @param allJobs The list of jobs to search.
     * @return List of all jobs matching the criteria.
     */
    public static ArrayList<Job> findByColumnAndValue(String column, String value, Iterable<Job> allJobs) {

        ArrayList<Job> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Job>) allJobs;
        }

        if (column.equals("all")){
            results = findByValue(value, allJobs);
            return results;
        }
        for (Job job : allJobs) {

            String aValue = getFieldValue(job, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            }
        }

        return results;
    }

    public static String getFieldValue(Job job, String fieldName){
        String theValue = new String();
        if (fieldName.equals("name")){
            theValue = job.getName();
//            return theValue;
        } else if (fieldName.equals("employer")) {
//            theValue = job.getEmployer().toString();  // TODO: Tell John: This Starter Code is still incorrect!
            theValue = job.getEmployer().getName();     // TODO: FINAL Corrected Starter Code
        } else {
//            theValue = job.toString();                // Old, incorrect starter code
//            theValue = job.getSkills().toString();    // TODO: Tell John: This Starter Code is still incorrect!
            for (Skill skill : job.getSkills()){        // TODO: FINAL Corrected Starter Code
                theValue = skill.getName();
            }
        }

        return theValue;
    }

    /**
     * Search all Job fields for the given term.
     *
     * @param value The search term to look for.
     * @param allJobs The list of jobs to search.
     * @return      List of all jobs with at least one field containing the value.
     */
    public static ArrayList<Job> findByValue(String value, Iterable<Job> allJobs) {


        ArrayList<Job> results = new ArrayList<>();

        for (Job job : allJobs) {

            if (job.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
//            } else if (job.getEmployer().toLowerCase().contains(value.toLowerCase())) {      // Old incorrect starter code
//            } else if (job.getEmployer().toString().toLowerCase().contains(value.toLowerCase())) { // TODO: Corrected starter code: still not working!
            } else if (job.getEmployer().getName().toLowerCase().contains(value.toLowerCase())) {   // TODO: FIXED! Tell John and all that additional correction to Starter Code needed
                results.add(job);

//            } else if (job.getSkills().contains(value.toLowerCase())) {                      // Old incorrect starter code
//            } else if (job.getSkills().toString().toLowerCase().contains(value.toLowerCase())) {  // TODO: still needs correction
             } for (Skill skill : job.getSkills()){                                                 // TODO: FIXED! Tell John and all that additional correction to Starter Code needed
                if (skill.getName().toLowerCase().contains(value.toLowerCase())) {
                    results.add(job);
                }

//            } else if (job.toString().toLowerCase().contains(value.toLowerCase())) {         //Old incorrect starter code
//                results.add(job);                                                            //Old incorrect starter code
            }

        }

        return results;
    }


}

