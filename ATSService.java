import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The core service class for the ATS. Manages jobs, applicants, and applications.
 * This class uses polymorphism, method overloading, and encapsulation.
 */
public class ATSService {
    private List<Job> jobs;
    private List<Applicant> applicants;
    private List<Application> applications;

    public ATSService() {
        this.jobs = new ArrayList<>();
        this.applicants = new ArrayList<>();
        this.applications = new ArrayList<>();
    }

    /**
     * Posts a new job to the system.
     * @param job The Job object to add.
     */
    public void postJob(Job job) {
        this.jobs.add(job);
        System.out.println("Job '" + job.getTitle() + "' posted successfully.");
    }

    /**
     * Adds a new applicant to the system.
     * Uses polymorphism to handle different applicant types.
     * @param applicant The Applicant object to add.
     */
    public void addApplicant(Applicant applicant) {
        this.applicants.add(applicant);
        System.out.println("Applicant '" + applicant.getName() + "' added successfully.");
    }

    /**
     * Accepts an application for a specific job.
     * @param applicant The applicant.
     * @param job The job they are applying for.
     */
    public void acceptApplication(Applicant applicant, Job job) {
        Application application = new Application(applicant, job);
        this.applications.add(application);
        System.out.println("Application submitted for " + applicant.getName() + " for job " + job.getTitle() + ".");
        
        // Automatically move to the screening stage and evaluate
        advanceStage(application.getApplicationId(), "Screening");
        int score = applicant.evaluate(job); // Polymorphism in action
        application.setScore(score);
        application.setRemarks("Initial automated score: " + score);
    }
    
    /**
     * Advances the stage of a specific application.
     * @param applicationId The ID of the application.
     * @param newStage The new stage (e.g., "Screening", "Interview", "Offer").
     */
    public void advanceStage(String applicationId, String newStage) {
        Application app = findApplicationById(applicationId);
        if (app != null) {
            app.setStage(newStage);
            System.out.println("Application for " + app.getApplicant().getName() + " advanced to stage: " + newStage);
        } else {
            System.out.println("Application with ID " + applicationId + " not found.");
        }
    }

    /**
     * Finds an application by its unique ID.
     * @param id The application ID.
     * @return The Application object or null if not found.
     */
    private Application findApplicationById(String id) {
        for (Application app : applications) {
            if (app.getApplicationId().equals(id)) {
                return app;
            }
        }
        return null;
    }
    
    // --- Method Overloading Examples ---

    /**
     * Searches for applicants by a specific skill.
     * @param skill The skill to search for.
     * @return A list of matching applicants.
     */
    public List<Applicant> searchApplicants(String skill) {
        System.out.println("Searching applicants by skill: " + skill);
        return applicants.stream()
                         .filter(applicant -> applicant.getSkills().contains(skill))
                         .collect(Collectors.toList());
    }

    /**
     * Searches for applicants by years of experience.
     * @param minYears The minimum years of experience.
     * @return A list of matching applicants.
     */
    public List<Applicant> searchApplicants(int minYears) {
        System.out.println("Searching applicants with at least " + minYears + " years of experience.");
        return applicants.stream()
                         .filter(applicant -> applicant.getYearsOfExperience() >= minYears)
                         .collect(Collectors.toList());
    }

    /**
     * Searches for applicants whose name or email contains a keyword.
     * @param keyword The keyword to search for.
     * @param searchField The field to search in ("name" or "email").
     * @return A list of matching applicants.
     */
    public List<Applicant> searchApplicants(String keyword, String searchField) {
        System.out.println("Searching applicants by keyword '" + keyword + "' in " + searchField + " field.");
        return applicants.stream()
                         .filter(applicant -> {
                             if (searchField.equalsIgnoreCase("name")) {
                                 return applicant.getName().toLowerCase().contains(keyword.toLowerCase());
                             } else if (searchField.equalsIgnoreCase("email")) {
                                 return applicant.getEmail().toLowerCase().contains(keyword.toLowerCase());
                             }
                             return false;
                         })
                         .collect(Collectors.toList());
    }

    /**
     * Ranks all applicants for a given job by their score in descending order.
     * @param jobId The job to rank applicants for.
     * @return A sorted list of applications.
     */
    public List<Application> rankByScore(String jobId) {
        System.out.println("Ranking applicants for job ID: " + jobId);
        List<Application> rankedList = applications.stream()
                                                    .filter(app -> app.getJob().getJobId().equals(jobId))
                                                    .collect(Collectors.toList());
        
        Collections.sort(rankedList, Comparator.comparingInt(Application::getScore).reversed());
        return rankedList;
    }

    /**
     * Prints the entire job pipeline for a specific job.
     * @param jobId The job to print the pipeline for.
     */
    public void printJobPipeline(String jobId) {
        Job job = jobs.stream().filter(j -> j.getJobId().equals(jobId)).findFirst().orElse(null);
        if (job == null) {
            System.out.println("Job not found.");
            return;
        }

        System.out.println("\n--- Job Pipeline for: " + job.getTitle() + " ---");
        List<Application> jobApps = applications.stream()
                                               .filter(app -> app.getJob().getJobId().equals(jobId))
                                               .collect(Collectors.toList());
        
        System.out.println("Total applications: " + jobApps.size());
        
        // Group applications by stage
        List<String> stages = List.of("Applied", "Screening", "Interview", "Offer");
        for (String stage : stages) {
            System.out.println("\n--- Stage: " + stage + " ---");
            List<Application> stageApps = jobApps.stream()
                                                 .filter(app -> app.getStage().equals(stage))
                                                 .collect(Collectors.toList());
            if (stageApps.isEmpty()) {
                System.out.println("  (No applicants in this stage)");
            } else {
                for (Application app : stageApps) {
                    System.out.printf("  - %s (Score: %d, Remarks: %s)\n", 
                                      app.getApplicant().getName(), 
                                      app.getScore(), 
                                      app.getRemarks());
                }
            }
        }
    }
}
