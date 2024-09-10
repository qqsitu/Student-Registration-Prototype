package p2code;

import java.util.ArrayList;
import java.util.Scanner;
public class mainpage
{   
    public static void main(String[]args)
    {
        Scanner input=new Scanner(System.in);

        int choice0;
        do {
        System.out.println("Login as: 1. Admin 2. Student 3. Advisor");
        choice0=input.nextInt();
        input.nextLine();
        } while (choice0<1 || choice0>3);
        System.out.println("Enter your username: ");
        String username=input.nextLine();
        System.out.println("Enter your password: ");            
        String password=input.nextLine();
        System.out.println("...verifying...");


        switch (choice0){
            case 1:
                Admin();
                break;
            case 2:
                Student();
                break;
            case 3:
                Advisor();
                break;
            default:
                System.out.println("Invalid Login choice. Please try again.");
                break;
        }

        
        input.close();
        }

    public static void CreateNewStudentProfile()
    {
        Scanner input=new Scanner(System.in);
        System.out.println("enter your first name");
        String FirstName=input.nextLine();
        System.out.println("enter your last name");
        String LastName=input.nextLine();
        System.out.println("enter your Date of birth (MM/DD/YYYY)");
        String DOB=input.nextLine();
        System.out.println("enter your personal email");
        String PersonalEmail=input.nextLine();
        System.out.println("enter your phone number");
        String phoneNumber=input.nextLine();
        System.out.println("enter your address");
        String address=input.nextLine();
        System.out.println("Emergency Contact: ");
        System.out.println("First Name of Emergency Contact: ");
        String EmergencyFirstName=input.nextLine();
        System.out.println("Last Name of Emergency Contact: ");
        String EmergencyLastName=input.nextLine();
        System.out.println("Emergency Relationship: ");
        String EmergencyRelationship=input.nextLine();
        System.out.println("Emergency email");
        String EmergencyEmail=input.nextLine();
        System.out.println("Emergency phone number");
        String EmergencyNumber=input.nextLine();

        String studentID = randomID();
        System.out.println("The student ID is: " + studentID);
        Student student1=new Student(FirstName,LastName,DOB,PersonalEmail,phoneNumber, studentID, address, EmergencyFirstName,EmergencyLastName,EmergencyEmail,EmergencyNumber,EmergencyRelationship);
        System.out.println("Student profile for Student " + studentID + " has been created.");
        input.close();
    }

    public static void CreateNewAdvisorProfile()
    {
        Scanner input=new Scanner(System.in);
        System.out.println("enter your first name");
        String FirstName=input.nextLine();
        System.out.println("enter your last name");
        String LastName=input.nextLine();
        System.out.println("enter your personal email");
        String Email=input.nextLine();
        System.out.println("enter your phone number");
        String phoneNumber=input.nextLine();
        String advisorID = randomID();
        System.out.println("The advisor ID is: " + advisorID);
        Advisor advisor1=new Advisor(FirstName, LastName,Email, phoneNumber, new ArrayList<Student>(), advisorID);
        System.out.println("Advisor profile for Advisor " + advisorID + " has been created.");
        input.close();
    }

    public static void CreateNewAdminProfile()
    {
        Scanner input=new Scanner(System.in);
        System.out.println("enter your first name");
        String FirstName=input.nextLine();
        System.out.println("enter your last name");
        String LastName=input.nextLine();
            System.out.println("enter your personal email");
            String Email=input.nextLine();
            System.out.println("enter your phone number");
            String phoneNumber=input.nextLine();
            String adminID = randomID();
            System.out.println("The admin ID is: " + adminID);
            Admin admin1=new Admin(FirstName,LastName,Email,phoneNumber,adminID);
            System.out.println("Admin profile for Admin " + adminID + " has been created.");
            input.close();
        }

        public static String randomID()
        {
            String ID="";
            for(int i=0;i<9;i++)
            {
                ID+=(int)(Math.random()*10);
            }
            return ID;
        }

        public static void Admin(){
            //artifically logging in as admin
            Admin admin1 = new Admin();
            Scanner input=new Scanner(System.in);
            System.out.println("Welcome Admin Admin");
            int choice1;
            do{
            System.out.println("1. Create New Profile 2. View Profile 3. Update Profile 4. Delete Profile 5. Logout \n");
            choice1 = input.nextInt();
            input.nextLine();

            int choice2;
            switch (choice1){
                case 1:
                    do {
                    System.out.println("Which profile would you like to create? 1. Student 2. Advisor 3. Admin \n4. Back\n5. Logout\n");
                    choice2 = input.nextInt();
                    input.nextLine();

                    switch (choice2){
                        case 1:
                            CreateNewStudentProfile();
                            break;
                        case 2:
                            CreateNewAdvisorProfile();
                            break;
                        case 3:
                            CreateNewAdminProfile();
                            break;
                        case 4:
                            System.out.println("Going back...");
                            break;
                        case 5:
                            System.out.println("Logging out...");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    } 
                    } while (choice2 != 4 && choice2 <1 && choice2 >5);
                    break;

                case 2:
                    do {
                    System.out.println("Which profile would you like to view? 1. Student 2. Advisor\n 3. Back\n 4. Logout\n");
                    choice2 = input.nextInt();
                    input.nextLine();

                    switch (choice2){
                        case 1:
                            System.out.println("Enter Student ID: ");
                            String studentID = input.nextLine();
                            System.out.println("function will view student profile ");
                            //admin1.viewStudentProfile(studentID);
                            break;
                        case 2:
                            System.out.println("Enter Advisor ID: ");
                            String advisorID = input.nextLine();
                            System.out.println("function will view advisor profile ");
                            //admin1.viewAdvisorProfile(advisorID);
                            break;
                        case 3:
                            System.out.println("Going back...");
                            break;
                        case 4:
                            System.out.println("Logging out...");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    } 
                    } while (choice2 != 3 && choice2 <1 && choice2 >4);

                    break;
                case 3:
                do {
                    System.out.println("Which profile would you like to Update? 1. Student 2. Advisor\n 3. Back\n 4. Logout\n");
                    choice2 = input.nextInt();
                    input.nextLine();

                    switch (choice2){
                        case 1:
                            System.out.println("Enter Student ID: ");
                            String studentID = input.nextLine();
                            System.out.println("function will update student profile ");
                            break;
                        case 2:
                            System.out.println("Enter Advisor ID: ");
                            String advisorID = input.nextLine();
                            System.out.println("function will update advisor profile ");
                            break;
                        case 3:
                            System.out.println("Going back...");
                            break;
                        case 4:
                            System.out.println("Logging out...");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    } 
                    } while (choice2 != 3 && choice2 <1 && choice2 >4);
                    break;
                case 4:
                    do {
                        System.out.println("Which profile would you like to delete? 1. Student 2. Advisor\n 3. Back\n 4. Logout\n");
                        choice2 = input.nextInt();
                        input.nextLine();
    
                        switch (choice2){
                            case 1:
                                System.out.println("Enter Student ID: ");
                                String studentID = input.nextLine();
                                System.out.println("function will delete student profile ");
                                break;
                            case 2:
                                System.out.println("Enter Advisor ID: ");
                                String advisorID = input.nextLine();
                                System.out.println("function will delete advisor profile ");
                                break;
                            case 3:
                                System.out.println("Going back...");
                                break;
                            case 4:
                                System.out.println("Logging out...");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        } 
                        } while (choice2 != 3 && choice2 <1 && choice2 >4);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, pick again!");
            }
        } while (choice1!=5);
        input.close();
        
    }

     public static void Student(){
        Scanner input=new Scanner(System.in);
        System.out.println("Welcome Student Student");
        System.out.println("Please select from the following: ");
        int choice2;
        do {
        System.out.println("1. View Profile \n2. Update Profile \n 3. View Adacemic Record \n 4. Set Academic goals \n 5. Set Up Advisor Appointment 6.search for Courses \n 7. View Course recommendations \n 8. Register for courses \n9. Upload and Manage Documents \n10. Back \n11. Logout\n");
        choice2=input.nextInt();
        input.nextLine();

        switch(choice2)
        {
            case 1: 
                System.out.println("function will view student profile ");
                break;
            case 2:
                System.out.println("function will update student profile ");
                break;
            case 3:
                System.out.println("function will view academic record ");
                break;
            case 4:
                System.out.println("function will set academic goals");
                break;
            case 5:
                System.out.println("function will set up advisor appointment ");
                break;
            case 6:
                System.out.println("function search for available courses ");
                break;
            case 7:
                System.out.println("function will view course recommendations");
                break;
            case 8: 
                System.out.println("function will register for courses ");
                break;
            case 9: 
                System.out.println("function will upload and manage documents");
                break; 
            case 10:
                System.out.println("Going Back...");
                break;
            case 11:
                System.out.println("Logging out...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice, pick again!");
        }
        } while (choice2!=10);
        input.close();
    }
        
    public static void Advisor(){
        Scanner input = new Scanner(System.in);
    	
        System.out.println("Welcome Advisor Advisor");
        int choice3;
        do {
        System.out.println("1. View profile \n 2. Update profile \n 3. View Students \n 4. Schedule Meeting \n 5. Back \n 6. Logout\n");
        choice3 = input.nextInt();
        input.nextLine();
        
        switch (choice3) {
    	    case 1:
                System.out.println("This is where you can view your profile!");
                break;
        	case 2:
                System.out.println("This is where you can update your profile!");
                break;
    	    case 3:
                System.out.println("This is where you can locate the students!");
                break;
        	case 4:
                System.out.println("This is where you can schedule a meeting.");
                break;
            case 5:
                System.out.println("Going back...");
                break;
            case 6:
                System.out.println("Logging out...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice, pick again!");
        } 
        } while (choice3!=5);
        input.close();
    }
}
