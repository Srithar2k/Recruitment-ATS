import java.util.UUID;

/**
 * Represents an application for a job by a specific applicant.
 * Demonstrates encapsulation for managing the application stage and score.
 */
public class Application {
    private String applicationId;
    private Applicant applicant;
    private Job job;
    private String stage; // e.g., "Applied", "Screening", "Interview", "Offer"
    private int score;
    private String remarks;
    private static int applicationCount = 0;

    public Application(Applicant applicant, Job job) {
        this.applicationId = "APP-" + UUID.randomUUID().toString().substring(0, 6);
        this.applicant = applicant;
        this.job = job;
        this.stage = "Applied";
        this.score = 0;
        this.remarks = "Initial Application";
        applicationCount++;
    }

    // Getters
    public String getApplicationId() {
        return this.applicationId;
    }

    public Applicant getApplicant() {
        return this.applicant;
    }

    public Job getJob() {
        return this.job;
    }

    public String getStage() {
        return this.stage;
    }

    public int getScore() {
        return this.score;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public static int getApplicationCount() {
        return applicationCount;
    }

    // Setters
    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
