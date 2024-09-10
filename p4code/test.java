package p4code;

public class test {
    public static void main(String[] args) {
        SessionManager sm = new SessionManager();
        //
        try {
            Session s3=sm.login("student","pass345",AccountDatabase.Role.STUDENT);
            Session s2 = sm.login("admin", "pass123", AccountDatabase.Role.ADMIN);
            StudentAccount student1 = AccountDatabase.createAccount(s2, "student1", "pass456");
            //test accessviolationexception
            System.out.println(student1.getacadHist(s2));
            System.out.println(student1.getacadHist(s3));
            //invalidcredentialsexception
            Session s4=sm.login("student","jdjfjfifjjf",AccountDatabase.Role.STUDENT);
            //duplicateRecordException
            Session s5=sm.login("student","pass345",AccountDatabase.Role.STUDENT);
          
            
        } catch (InvalidCredentialsException e) {
            System.out.println("invalid");
        } catch (DuplicateRecordException e) {
            System.out.println("This is a duplicate account");
        } catch (AccessViolationException e) {
            System.out.println("you do not have access to this account");
        } catch (ExpiredSessionException e) { 
            System.out.println("your session expired");
        }
        
        // try {
        //     Session s1 = sm.login("student", "pass345", AccountDatabase.Role.STUDENT);

        // } catch (InvalidCredentialsException e) {
        //     System.out.println(e.getMessage());
        // }

        //expired session exception
       
    }

        //sm.mockAccountDatabase(); //generate mock Account Database()
        ///Session s1 = sm.login("admin", "admin", AccountDatabase.Role.ADMIN);

}
    

