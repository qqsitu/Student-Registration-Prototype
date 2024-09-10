package p4code;

public class StudentAccount
{
    //info 
   String name;
   String DOB;
   char gender;
    //contact info 
   String phoneNumber;

    //academicHistorylist
    String academicHistoryList;

    public StudentAccount(String n,String D,char g,String phoneNum,String AcadHist)
    {
        name=n;
        DOB=D;
        gender=g;
        phoneNumber=phoneNum;
        academicHistoryList="History: " + AcadHist;
    }
    public String getName(Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        return name;
       
    }
    public void setName(String n,Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        name=n;
    }
    
    public String getDOB(Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        return DOB;
    }
    public void setDOB(String DateOfBirth,Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        DOB=DateOfBirth;
    }

    public char getGender(Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        return gender;
    }
    public void setGender(char g,Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        gender=g;
    }

    public String getPhoneNumber(Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        return phoneNumber;
    }
    public void setPhoneNumber(String pNum,Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        phoneNumber=pNum;
    }

    public String getacadHist(Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        return academicHistoryList;
    }
    public void setAcadHist(String acadHist,Session s) throws ExpiredSessionException, AccessViolationException
    {
        testExcept(s);
        academicHistoryList=acadHist;
    }

    // Remove the duplicate method declaration
    public void testExcept(Session s) throws ExpiredSessionException, AccessViolationException
    {  
        
        for( Session sessions : SessionManager.activeSessions)
       {
            if(!sessions.equals(s))
            {
                throw new ExpiredSessionException("Expired Session Exception");
            } 
       }
       if(!(s.getRole().equals(AccountDatabase.Role.ADMIN)||s.getRole().equals(AccountDatabase.Role.ADVISOR)))
       {
            throw new AccessViolationException("Access Violation Exception");
       }
    }
}



