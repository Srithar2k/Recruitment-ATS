import java.util.UUID;

/**
 * Represents a job posting within the ATS.
 * Demonstrates encapsulation by having private fields with public getters and setters.
 */
public class Job {
    private String jobId;
    private String title;
    private String department;
    private String location;
    private String status; // e.g., "Open", "Closed"
    private static int jobCount = 0;

    public Job(String title, String department, String location) {
        this.jobId = "J-" + UUID.randomUUID().toString().substring(0, 6);
        this.title = title;
        this.department = department;
        this.location = location;
        this.status = "Open";
        jobCount++;
    }

    // Getters
    public String getJobId() {
        return this.jobId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getLocation() {
        return this.location;
    }

    public String getStatus() {
        return this.status;
    }

    public static int getJobCount() {
        return jobCount;
    }

    // Setter
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Prints a formatted summary of the job.
     */
    public void printJobDetails() {
        System.out.println("Job ID: " + this.jobId);
        System.out.println("Title: " + this.title);
        System.out.println("Department: " + this.department);
        System.out.println("Location: " + this.location);
        System.out.println("Status: " + this.status);
    }
}
