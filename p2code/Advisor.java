package p2code;

import java.util.ArrayList;

public class Advisor extends User
{
    String firstName;
    String lastName;
    String Email;
    String phoneNumber;
    ArrayList<Student> students;
    String advisorID;
    public Advisor()
    {
        firstName="";
        lastName="";
        Email="";
        phoneNumber="";
        ArrayList<Student> students = new ArrayList<Student>();
        advisorID="";
    }
    public Advisor(String firstName,String lastName,String Email,String number,
    ArrayList<Student> students,String advisorID)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.Email=Email;
        this.phoneNumber=number;
        this.students=students;
        this.advisorID=advisorID;
    }
    public void viewStudents()
    {
        int size=students.size();
        for(int i=0;i<size;i++)
        {   
            System.out.println(students.get(i));
        }
    }
}
