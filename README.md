Recruitment ATS (Applicant Tracking System)

  Project Overview
  
  This is a console-based Applicant Tracking System (ATS) developed in Java to manage the hiring workflow. The system is built using Object-Oriented Programming (OOP) principles, demonstrating key concepts such as inheritance, polymorphism, encapsulation, and method overloading.
   
   The application allows users to:
   
        Post job openings.
        Add applicants with unique skills and experience.
        Manage job applications by tracking their progress through different stages.
        Rank candidates based on a scoring system.
        Search for applicants using different criteria.
  
  OOPS Concepts Demonstrated
  
  This project serves as a practical demonstration of core OOP concepts in a real-world context:
        
        Inheritance: The TechApplicant and NonTechApplicant classes inherit from the base Applicant class, allowing for specialized behavior and data while reusing common attributes.
        
        Polymorphism: The ATSService class processes all applicants as Applicant references, but the evaluate() method call correctly executes the specialized logic defined in the TechApplicant or NonTechApplicant subclasses.
        
        Encapsulation: Critical data, such as application scores and stages, is kept private within the Application class, ensuring that it can only be accessed or modified through controlled methods.
        
        Method Overloading: The ATSService class features multiple searchApplicants() methods, allowing users to search for applicants by skill, years of experience, or a keyword.
  
  Project Structure
  
  The project is organized into the following classes:
        
        Job.java: Represents a job posting with attributes like title, department, and status.
        
        Applicant.java: The base class for all applicants, containing general information.
        
        TechApplicant.java: Extends Applicant with specialized properties and evaluation logic for technical roles.
        
        NonTechApplicant.java: Extends Applicant with specialized properties and evaluation logic for non-technical roles.
        
        Application.java: Maps an applicant to a specific job and tracks the application's stage, score, and remarks.
        
        ATSService.java: The core service class that manages all jobs, applicants, and applications. It contains the business logic for the system.
        
        ATSAppMain.java: The main executable class that serves as a demonstration and test harness for the entire system.
  
  How to Run
  
  1) Clone the Repository: Clone this repository to your local machine using Git.
      
  2) Open in IDE: Open the project in a Java IDE like Eclipse or IntelliJ IDEA.
      
  3) Run the Main Class: Navigate to ATSAppMain.java and run it as a Java Application. The program's output will be displayed in the console.
  
  Expected Output
  
  When you run the ATSAppMain.java file, you will see a console-based log detailing the entire workflow, including job postings, applicant data, application stages, and candidate rankings.
