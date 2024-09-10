package p4code;

import java.util.*;
import java.io.*;
import java.time.*;

public class SessionManager {
    static ArrayList<Session> activeSessions; //only ACTIVE sessions are stored here
    AccountDatabase database;
    
    SessionManager(){
        activeSessions = new ArrayList<Session>();
        mockAccountDatabase();
    }

    Session login(String loginName, String password, AccountDatabase.Role role) throws InvalidCredentialsException {
        LocalDateTime expirationTime = LocalDateTime.now().plusSeconds(5); //session expires in 10 seconds
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

    void removeSession(Session s){
        activeSessions.remove(s);
    }

    void mockAccountDatabase(){
        //String line;
        try {
            FileWriter fw = new FileWriter("AccountDatabase.csv",false);
            fw.write("userID,loginName,password,role,status,name,DOB,gender,phoneNumber,academicHistory\n");
            for (int i=0; i<10; i++){ //ten fake accounts
                String id="";
                String username="";
                String pass="";
                int role;
                String status="blocked";
                String line="";
                for (int j=0; j<7; j++){ //generate 7 random digits for ID
                    int random = (int)(Math.random() * 10);
                    id+=random;
                }
                for (int j=0; j<5; j++){ //generate 5 random letters for name
                    char random = (char)(Math.random() * 26 + 'a');
                    username+=random;
                }
                for (int j=0; j<5; j++){ //generate 5 random letters for password
                    int random = 33 + (int)(Math.random() * 93);
                    pass+=(char)random;
                }
                role = (int)(Math.random() * 3) +1;

                String roleString  = "";
                
                if (role == 1){
                    roleString = "ADMIN";
                } else if (role == 2){
                    roleString = "ADVISOR";
                } else {
                    roleString = "STUDENT";
                }
                line += id+","+username+","+pass+","+roleString+","+status;

                if (role == 3){
                    int month = (int)(Math.random() * 13)+1;
                    int day = (int)(Math.random() * 28)+1; //arbitrarily up to 28 for testing purposes
                    int year = (int)(Math.random()*51)+1960; //arbitrarily ages 14-64
                    String dob = month + "/" + day + "/" + year;
                    
                    int random = (int)(Math.random()*2);
                    char g='M';
                    if (random==1){
                        g='F';
                    }

                    String phoneNum="";
                    for (int j=0;j<3;j++){
                        phoneNum+= (int)(Math.random()*10);
                    }
                    phoneNum+="-";
                    for (int j=0;j<3;j++){
                        phoneNum+=(int)(Math.random()*10);
                    }
                    phoneNum+="-";
                    for (int j=0; j<4;j++){
                        phoneNum+=(int)(Math.random()*10);
                    }

                    ArrayList<String> courses = new ArrayList<>(Arrays.asList("COMPSC", "HIST", "MATH", "ENG", "PSYCH"));
                    ArrayList<String> courseCodes = new ArrayList<>(Arrays.asList("101","102","103"));
                    ArrayList<String> fname = new ArrayList<>(Arrays.asList("Emily","Bob","Mochi","Branch"));
                    ArrayList<String> lname = new ArrayList<>(Arrays.asList("Smith","Johnson","Lee","Po"));
                    String AcadHist = "";

                    for (int j=0; j<(int)(Math.random()*5)+1; j++){ //generate 1-5 courses
                        int random1 = (int)(Math.random()*courses.size());
                        int random2 = (int)(Math.random()*courseCodes.size());
                        if (!AcadHist.contains(courses.get(random1) + courseCodes.get(random2))){
                            AcadHist+= " " + courses.get(random1)+courseCodes.get(random2);
                        }
                    }

                    int rand = (int)(Math.random()*4);
                    String first = fname.get(rand);
                    int rand2 = (int)(Math.random()*4);
                    String last = lname.get(rand2);

                    line+=","+first+" "+last+","+dob+","+g+","+phoneNum+","+AcadHist+"\n";

                    fw.write(line);


                    String full = first + " " + last;

                    StudentAccount newStudent = new StudentAccount(full, dob , g, phoneNum, AcadHist);
                    AccountDatabase.students.put(Integer.parseInt(id), newStudent);
                } else {
                    line+="\n";
                    fw.write(line);
                }


            }
            
            fw.write("1234567,admin,pass123,ADMIN,blocked\n"); //for testing purposes
            fw.write("2345678,advisor,pass234,ADVISOR,blocked\n"); //for testing purposes
            fw.write("3456789,student,pass345,STUDENT,blocked, Jerica Steph, 05/16/2003, F, 555-555-5555, MATH101 CMPSC101\n"); //for testing purposes
            fw.close();

            database = new AccountDatabase("AccountDatabase.csv");


        } catch (IOException e) {
            e.printStackTrace(); //try to see what happened
        }
    }   
}
