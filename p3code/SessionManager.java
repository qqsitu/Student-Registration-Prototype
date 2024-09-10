package p3code;

import java.util.*;
import java.io.*;
import java.time.*;

public class SessionManager {
    ArrayList<Session> activeSessions; //only ACTIVE sessions are stored here
    AccountDatabase database;
    
    SessionManager(){
        activeSessions = new ArrayList<Session>();
        mockAccountDatabase();
    }

    Session login(String loginName, String password, AccountDatabase.Role role) throws InvalidCredentialsException {
        LocalDateTime expirationTime = LocalDateTime.now().plusSeconds(5); //session expires in 5 seconds
        if (database.checkCredentials(loginName, password, role)) {
            String userID = database.findUserID(loginName, password, role);
            Session session = new Session(role, this, expirationTime, userID);
            System.out.println("Logging in... user "+userID);
            activeSessions.add(session);
            return session;
        } else {
            throw new InvalidCredentialsException("Invalid credentials.");
            //P3.3.a "...put the account on hold by connecting to the account database."
        }
    }

    void mockAccountDatabase(){
        //String line;
        try {
            FileWriter fw = new FileWriter("AccountDatabase.csv",false); //true : append to file
            fw.write("userID,loginName,password,role,status\n");
            for (int i=0; i<10; i++){ //ten fake accounts
                String id="";
                String name="";
                String pass="";
                String role="";
                String status="blocked";
                for (int j=0; j<7; j++){ //generate 7 random digits for ID
                    int random = (int)(Math.random() * 10);
                    id+=random;
                }
                for (int j=0; j<5; j++){ //generate 5 random letters for name
                    char random = (char)(Math.random() * 26 + 'a');
                    name+=random;
                }
                for (int j=0; j<5; j++){ //generate 5 random letters for password
                    int random = 33 + (int)(Math.random() * 93);
                    pass+=(char)random;
                }
                int roleNum = (int)(Math.random() * 3);
                if (roleNum == 0){
                    role = "ADMIN";
                } else if (roleNum == 1){
                    role = "ADVISOR";
                } else {
                    role = "STUDENT";
                }
                fw.write(id+","+name+","+pass+","+role+","+status+"\n");

                database = new AccountDatabase("AccountDatabase.csv");
            }
            fw.write("1234567,admin,pass123,ADMIN,blocked\n"); //for testing purposes
            fw.write("2345678,advisor,pass234,ADVISOR,blocked\n"); //for testing purposes

            fw.close();
        } catch (IOException e) {
            e.printStackTrace(); //try to see what happened
        }
    }   
}
