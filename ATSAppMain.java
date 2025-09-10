import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class to demonstrate the functionality of the ATS.
 * Tests all classes and OOPS concepts.
 */
public class ATSAppMain {
    public static void main(String[] args) {
        ATSService atsService = new ATSService();

        // 1. Post some jobs
        System.out.println("--- Posting Jobs ---");
        Job softwareJob = new Job("Software Engineer", "Engineering", "New York");
        Job marketingJob = new Job("Marketing Manager", "Marketing", "London");
        atsService.postJob(softwareJob);
        atsService.postJob(marketingJob);

        // 2. Add different types of applicants (Polymorphism)
        System.out.println("\n--- Adding Applicants ---");
        Applicant techApp1 = new TechApplicant("Alice", "alice@example.com", 5, 
                                            List.of("Java", "Spring", "SQL", "Cloud"), "Backend Development");
        Applicant nonTechApp1 = new NonTechApplicant("Bob", "bob@example.com", 8, 
                                                List.of("Project Management", "Strategy", "Budgeting"), "Business");
        Applicant techApp2 = new TechApplicant("Charlie", "charlie@example.com", 2,
                                                List.of("Python", "Data Science", "Machine Learning"), "Data Science");
        Applicant nonTechApp2 = new NonTechApplicant("David", "david@example.com", 4, 
                                                List.of("Content Creation", "Social Media"), "Communications");

        atsService.addApplicant(techApp1);
        atsService.addApplicant(nonTechApp1);
        atsService.addApplicant(techApp2);
        atsService.addApplicant(nonTechApp2);
        
        // 3. Accept applications
        System.out.println("\n--- Accepting Applications ---");
        atsService.acceptApplication(techApp1, softwareJob);
        atsService.acceptApplication(nonTechApp2, softwareJob);
        atsService.acceptApplication(nonTechApp1, marketingJob);
        atsService.acceptApplication(techApp2, softwareJob);
        atsService.acceptApplication(nonTechApp1, softwareJob); // Bob applies for a tech job

        // 4. Advance stages
        System.out.println("\n--- Advancing Application Stages ---");
        // Find Charlie's application ID to advance it
        List<Application> charlieApps = atsService.rankByScore(softwareJob.getJobId());
        if (!charlieApps.isEmpty()) {
            Application charlieApp = charlieApps.stream()
                                                .filter(app -> "Charlie".equals(app.getApplicant().getName()))
                                                .findFirst().orElse(null);
            if (charlieApp != null) {
                atsService.advanceStage(charlieApp.getApplicationId(), "Interview");
                atsService.advanceStage(charlieApp.getApplicationId(), "Offer");
            }
        }

        // 5. Print job pipeline
        atsService.printJobPipeline(softwareJob.getJobId());
        atsService.printJobPipeline(marketingJob.getJobId());

        // 6. Demonstrate rankings
        System.out.println("\n--- Ranking Candidates for Software Engineer Job ---");
        List<Application> rankedSoftwareCandidates = atsService.rankByScore(softwareJob.getJobId());
        rankedSoftwareCandidates.forEach(app -> {
            System.out.printf("  - %s (Score: %d, Stage: %s)\n", 
                              app.getApplicant().getName(), 
                              app.getScore(), 
                              app.getStage());
        });

        // 7. Demonstrate Method Overloading
        System.out.println("\n--- Demonstrating Method Overloading ---");
        List<Applicant> skilledApplicants = atsService.searchApplicants("Java");
        System.out.println("Applicants with 'Java' skill: " + skilledApplicants.stream().map(Applicant::getName).collect(Collectors.toList()));
        
        List<Applicant> experiencedApplicants = atsService.searchApplicants(5);
        System.out.println("Applicants with >= 5 years of experience: " + experiencedApplicants.stream().map(Applicant::getName).collect(Collectors.toList()));

        List<Applicant> keywordApplicants = atsService.searchApplicants("dav", "name");
        System.out.println("Applicants with 'dav' in their name: " + keywordApplicants.stream().map(Applicant::getName).collect(Collectors.toList()));
    }
}
