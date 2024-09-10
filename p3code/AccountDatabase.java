package p3code;

import java.io.*;
import java.util.*;

public class AccountDatabase {
    static File database;

    public enum Role {
        ADMIN, ADVISOR, STUDENT
    }

    // private Map<Integar, String> accounts = new Hashmap<>();

    public AccountDatabase(String filename) {
        database = new File(filename);
    }
    
    boolean checkCredentials(String loginName, String password, Role role) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("AccountDatabase.csv"));
            while ((line = br.readLine()) != null) {
                String[] account = line.split(",");
                if (account[1].equals(loginName) && account[2].equals(password) && Role.valueOf(account[3]) == role) {
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    String findUserID(String loginName, String password, Role role) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("AccountDatabase.csv"));
            while ((line = br.readLine()) != null) {
                String[] account = line.split(",");
                if (account[1].equals(loginName) && account[2].equals(password) && Role.valueOf(account[3]) == role) {
                    return account[0];
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    static void block(int userID) throws AccountNotFoundException{
        //need to take off active sessions list
        boolean found = false;

        try {
            File tempFile = new File("temp.csv");
            if (tempFile.exists()) {
                tempFile.delete();
            }
            PrintWriter writer = new PrintWriter(tempFile);

            Scanner scanner = new Scanner(database);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] account = line.split(",");
                if (Integer.parseInt(account[0]) == userID) { 
                    account[4] = "blocked"; 
                    found = true;
                }
                writer.println(account[0] + "," + account[1] + "," + account[2] + "," + account[3] + "," + account[4]);
            }
            scanner.close();
            writer.close();
    
            if (!found) {
                throw new AccountNotFoundException("Account not found.");
            }

            if (database.delete()) {
                tempFile.renameTo(database);
            } else {
                System.out.println("Error deleting file.");
            }
            
            System.out.println("Account blocked.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       

    }

    static void unblock(int userID) throws AccountNotFoundException{
        try {
            boolean found = false;

            String filename = "AccountDatabase.csv";
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            File tempFile = new File("temp.csv");
            PrintWriter writer = new PrintWriter(tempFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] account = line.split(",");
                if (Integer.parseInt(account[0]) == userID) { 
                    account[4] = "active"; 
                    found = true;
                }
                writer.println(account[0] + "," + account[1] + "," + account[2] + "," + account[3] + "," + account[4]);
            }
            scanner.close();
            writer.close();

            if (!found) {
                throw new AccountNotFoundException("Account not found.");
            }
            file.delete();
            tempFile.renameTo(file);
        
            System.out.println("Account unblocked.");

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    

}
        
        // incomplete p4
    // public StudentAccount createAccount(Session session, String loginName, String password) throws DuplicateRecordException, AccessViolationException, ExpiredSessionException {
    //     if (accountExists(loginName)) {
    //         throw new DupicateRecordException("Account already exists.");
    //     } else if(/* ?????? */) {
    //         throw new AccessViolationException("Access violation.");
    //     } else if(/* ?????? */) {
    //         throw new ExpiredSessionException("Session expired.");
    //     }
            
    //     int userID = generateuserID();
    //     StudentAccount newAccount = new StudentAccount(userID, loginName, password, Role.STUDENT); 
    //     return newAccount;
    // }

    // void viewAccount(Session session) {
            
    // }
        
    // boolean passwordCheck(String password) {
            
    // }
        
    // void passwordCheckInstruction() {
    //     System.out.println("Password must contain at least 8 letters.");
    // }
        
    // boolean changePassword(Session session, String newPassword) {
    //     if (!passwordCheck(newPassword)) {
    //         return false;
    //     } else {
    //         session.getOwner().setPassword(newPassword);
    //         // temrinate program go here
    //         return true;
    //     }
    // }
        
    // boolean changeLoginName(Session session, String newLoginName) {
    //     if (!session.getOwner().getRole().equals(Role.ADMIN)) {
    //         return false;
    //     } else {
    //         session.getOwner().setLoginName(mewLoginName);
    //         return true;
    //     }
    // }

    // }