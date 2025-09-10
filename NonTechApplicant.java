import java.util.List;

/**
 * Represents a specialized non-technical applicant.
 * Demonstrates inheritance from the Applicant class.
 */
public class NonTechApplicant extends Applicant {
    private String fieldOfStudy;
    private static int nonTechApplicantCount = 0;

    public NonTechApplicant(String name, String email, int yearsOfExperience, List<String> skills, String fieldOfStudy) {
        super(name, email, yearsOfExperience, skills);
        this.fieldOfStudy = fieldOfStudy;
        nonTechApplicantCount++;
    }

    public String getFieldOfStudy() {
        return this.fieldOfStudy;
    }
    
    public static int getNonTechApplicantCount() {
        return nonTechApplicantCount;
    }

    /**
     * Custom evaluation logic for non-technical applicants.
     * Demonstrates method overriding.
     */
    @Override
    public int evaluate(Job job) {
        int baseScore = super.evaluate(job);
        int nonTechScore = 0;
        
        // Additional scoring based on job department and field of study
        if (job.getDepartment().equalsIgnoreCase("Marketing") && this.fieldOfStudy.equalsIgnoreCase("Business")) {
            nonTechScore += 35;
        }
        
        return baseScore + nonTechScore + (this.getYearsOfExperience() * 3);
    }
}
