package p2code;

import java.util.*;

public class Admin extends User
{
    String FirstName;
    String LastName;
    String Email;
    String PhoneNumber;
    String AdminID;
    public static ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Advisor> advisors = new ArrayList<Advisor>();
    
    public Admin() 
    {
    	this.FirstName="";
    	this.LastName="";
    	this.Email="";
    	this.PhoneNumber="";
    	this.AdminID="";
        accessLevel=3;

    }
    public Admin(String first, String last, String email, String num, String id)
    {
    	this.FirstName=first;
    	this.LastName=last;
    	this.Email=email;
    	this.PhoneNumber=num;
    	this.AdminID=id;
        accessLevel=3;
    }
    public void viewStudentProfile(String studentID)
    {
        for (int i = 0; i < students.size(); i++)
        {
            if (students.get(i).StudentID.equals(studentID))
            {
                System.out.println(students.get(i));
            }
        }
    }
    public void viewAdvisorProfile(String advisorID)
    {
        for (int i = 0; i < advisors.size(); i++)
        {
            if (advisors.get(i).advisorID.equals(advisorID))
            {
                System.out.println(advisors.get(i));
            }
        }
    }
}
