package p3code;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import java.io.BufferedReader;
import java.time.Duration;
import java.util.*;

public class Session {

    private AccountDatabase.Role role;
    private int userID;
    private StudentAccount studentAccount;
    private SessionManager sessionManager;
    private LocalDateTime expirationTime;
    private Timer timer;

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
    }

    public int getUserID(){
        return userID;
    }

    public void logout() {
        //block account, need int userID
        try{
            AccountDatabase.block(userID);
            System.out.println("Session expired");
            sessionManager.activeSessions.remove(this);

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
        
    }
    
}
