import java.util.UUID;
import java.util.List;

/**
 * Represents a job applicant. This is the base class for inheritance.
 */
public class Applicant {
    private String applicantId;
    private String name;
    private String email;
    private int yearsOfExperience;
    private List<String> skills;
    protected static int applicantCount = 0;

    public Applicant(String name, String email, int yearsOfExperience, List<String> skills) {
        this.applicantId = "A-" + UUID.randomUUID().toString().substring(0, 6);
        this.name = name;
        this.email = email;
        this.yearsOfExperience = yearsOfExperience;
        this.skills = skills;
        applicantCount++;
    }

    // Getters
    public String getApplicantId() {
        return this.applicantId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getYearsOfExperience() {
        return this.yearsOfExperience;
    }

    public List<String> getSkills() {
        return this.skills;
    }

    public static int getApplicantCount() {
        return applicantCount;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Evaluates the applicant's profile. This method will be overridden by subclasses.
     * @param job The job the applicant is being evaluated for.
     * @return An integer score based on the evaluation.
     */
    public int evaluate(Job job) {
        // Base evaluation logic
        int score = 0;
        if (this.yearsOfExperience >= 2) {
            score += 20;
        }
        return score;
    }

    /**
     * Prints a formatted summary of the applicant's profile.
     */
    public void printApplicantDetails() {
        System.out.println("Applicant ID: " + this.applicantId);
        System.out.println("Name: " + this.name);
        System.out.println("Email: " + this.email);
        System.out.println("Years of Experience: " + this.yearsOfExperience);
        System.out.println("Skills: " + String.join(", ", this.skills));
    }
}
