package p3code;

import java.util.*;

public class test {
    public static void main(String[] args) {
        //generate 10 fake accounts on creation of SessionManager object and one predetermined for testing
        SessionManager sm = new SessionManager();
        
        System.out.println("Active sessions (userID):");
        sm.activeSessions.forEach(session -> {
            System.out.println(session.getUserID());
        });
        System.out.println();
        //login with invalid credentials

        try {
            Session s1 = sm.login("admin", "admin", AccountDatabase.Role.ADMIN);
        } catch (InvalidCredentialsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Active sessions (userID):");
        sm.activeSessions.forEach(session -> {
            System.out.println(session.getUserID());
        });
        System.out.println();


        //login with valid credentials
        //1234567,admin,pass123,Admin,blocked

        try {
            Session s2 = sm.login("admin", "pass123", AccountDatabase.Role.ADMIN);
            System.out.println("Session created");
        } catch (InvalidCredentialsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("Active sessions (userID):");
        sm.activeSessions.forEach(session -> {
            System.out.println(session.getUserID());
        });
        System.out.println();

        //test AccountDatabase.unblock(userID) and AccountDatabase.block(userID) with account
        //fw.write("2345678,advisor,pass234,ADVISOR,blocked");


        try {
            System.out.println("Unblock but not login ~ not in active sessions");
            AccountDatabase.unblock(2345678);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Active sessions (userID):");
        sm.activeSessions.forEach(session -> {
            System.out.println(session.getUserID());
        });
        System.out.println();
        
        try {;
            AccountDatabase.block(2345678);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Active sessions (userID):");
        sm.activeSessions.forEach(session -> {
            System.out.println(session.getUserID());
        });
        System.out.println();
    }
    
}
