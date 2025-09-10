import java.util.List;

/**
 * Represents a specialized technical applicant.
 * Demonstrates inheritance from the Applicant class.
 */
public class TechApplicant extends Applicant {
    private String specialty;
    private static int techApplicantCount = 0;

    public TechApplicant(String name, String email, int yearsOfExperience, List<String> skills, String specialty) {
        super(name, email, yearsOfExperience, skills);
        this.specialty = specialty;
        techApplicantCount++;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    public static int getTechApplicantCount() {
        return techApplicantCount;
    }

    /**
     * Custom evaluation logic for technical applicants.
     * Demonstrates method overriding.
     */
    @Override
    public int evaluate(Job job) {
        int baseScore = super.evaluate(job);
        int techScore = 0;

        // Additional scoring for technical skills relevant to the job title
        if (this.getSkills().contains("Java") && job.getTitle().toLowerCase().contains("software")) {
            techScore += 40;
        }
        if (this.getSkills().contains("Python") && job.getTitle().toLowerCase().contains("data science")) {
            techScore += 30;
        }
        
        return baseScore + techScore + (this.getYearsOfExperience() * 5);
    }
}
