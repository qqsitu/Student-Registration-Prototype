package p2code;

public class Student extends User
{  
    String FirstName;
    String LastName;
    String DOB;
    String Email;
    String PhoneNumber;
    String StudentID;
    String Address;
    String EmergencyFirstName;
    String EmergencyLastName;
    String EmergencyEmail;
    String EmergencyNumber;
    String EmergencyRelationship;

    public Student()
    {
        FirstName="";
        LastName="";
        DOB="";
        Email="";
        PhoneNumber="";
        StudentID="";
        Address="";
        accessLevel=1;
    }  
  
    public Student(String FirstName, String LastName, String DOB, String StringEmail, String Phonenumber,String ID,String Address, String EmergencyFirstName, String EmergencyLastName, String EmergencyEmail, String EmergencyNumber, String EmergencyRelationship)
    {
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.DOB=DOB;
        this.Email=StringEmail;
        this.PhoneNumber=Phonenumber;
        this.StudentID=ID;  
        this.Address=Address;
        this.EmergencyFirstName=EmergencyFirstName;
        this.EmergencyLastName=EmergencyLastName;
        this.EmergencyEmail=EmergencyEmail;
        this.EmergencyNumber=EmergencyNumber;
        this.EmergencyRelationship=EmergencyRelationship;
        accessLevel=1;
    }

    public String toString()
    {
        return "*****\nName: "+FirstName+" " + LastName +"\nDate of Birth: "+DOB+"\nEmail: "+Email+"\nPhone Number: "+PhoneNumber+"\nStudent ID: "+StudentID+"\nAddress: "+Address+"\n------\nEmergency Contact: "+EmergencyFirstName+" "+EmergencyLastName+"\n Email: "+EmergencyEmail+"\nPhone Number: "+EmergencyNumber+"\nRelationship: "+EmergencyRelationship+"\n*****\n";
    }
    

}
