package p4code;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import javax.print.DocFlavor.READER;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.*;

public class Session {

    private AccountDatabase.Role role;
    private int userID;
    private StudentAccount studentAccount;
    private SessionManager sessionManager;
    private LocalDateTime expirationTime;
    private Timer timer;
    private boolean active;
    private boolean modified;

    Session(AccountDatabase.Role role, SessionManager sessionManager, LocalDateTime expirationTime, String userID) {
        //schedule auto-logout that shall further call logout() after expiration time if session is still active
        this.role = role;
        this.sessionManager = sessionManager;
        this.expirationTime = expirationTime;
        this.userID = Integer.parseInt(userID);      

        timer = new Timer();
        long delay = Duration.between(LocalDateTime.now(), expirationTime).toMillis();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logout();
            }
        }, delay);
        active = true;
    }

    public AccountDatabase.Role getRole() {
        return role;
    }

    public int getOwner(){
        return userID;
    }

    public int getUserID(){
        return userID;
    }

    public void logout() {
        //block account, need int userID
        try{
            AccountDatabase.block(userID);
            System.out.println("Session expired");
            sessionManager.removeSession(this);

            //print active sessions for testing
            System.out.println("Active sessions (userID):");
            sessionManager.activeSessions.forEach(session -> {
            System.out.println(session.getUserID());
            });
            System.out.println();

            timer.cancel();

            
        } catch (AccountNotFoundException e){
            e.printStackTrace();
        }

        active = false;
        
    }

    public boolean getStatus()
    {
        return active;
    }
    
    public void setModified(boolean b){
        modified = b;
    }

    public void setPassword(String newPass) throws AccountNotFoundException{
        try {
            boolean found = false;
            File file = new File("AccountDatabase.csv");
            BufferedReader br = new BufferedReader(new FileReader("AccountDatabase.csv"));
            // Scanner scanner = new Scanner(file);
            
            File tempfile = new File("temp.csv");
            PrintWriter writer = new PrintWriter(tempfile);
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] account = line.split(",");
                if (String.valueOf(userID).equals(account[0])) {
                    account[2]=newPass;
                    found = true;
                }
                writer.println(account[0] + "," + account[1] + "," + account[2] + "," + account[3] + "," + account[4]);
            }
            writer.close();
            br.close();

            if (!found) {
                throw new AccountNotFoundException("Account not found.");
            }
            file.delete();
            tempfile.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
            // shows error up till that point
        }
    }

    public void setLoginName(String newLoginName) throws AccountNotFoundException {
        try {
            boolean found = false;
            File file = new File("AccountDatabase.csv");
            BufferedReader br = new BufferedReader(new FileReader("AccountDatabase.csv"));
            // Scanner scanner = new Scanner(file);
            
            File tempfile = new File("temp.csv");
            PrintWriter writer = new PrintWriter(tempfile);
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] account = line.split(",");
                if (String.valueOf(userID).equals(account[0])) {
                    account[1]=newLoginName;
                    found = true;
                }
                writer.println(account[0] + "," + account[1] + "," + account[2] + "," + account[3] + "," + account[4]);
            }
            writer.close();
            br.close();

            if (!found) {
                throw new AccountNotFoundException("Account not found.");
            }
            file.delete();
            tempfile.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
            // shows error up till that point
        }

}
}