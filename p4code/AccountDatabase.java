package p4code;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class AccountDatabase {
    static File database;
    enum Role {
        ADMIN, ADVISOR, STUDENT, USER
    }
    static Map<Integer, StudentAccount> students = new HashMap<>();

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
        File tempFile = null;

        try {
            tempFile = new File("temp.csv");
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
                writer.println(String.join(",", account));
            }
            scanner.close();
            writer.close();
    
            if (!found) {
                throw new AccountNotFoundException("Account not found.");
            }

            if (database.delete()) {
            } else {
                System.out.println("Error deleting file.");
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            tempFile.renameTo(database);
            System.out.println("Account blocked.");
        }
       

    }

    static void unblock(int userID) throws AccountNotFoundException{
        File tempFile = null;
        try {
            boolean found = false;

            String filename = "AccountDatabase.csv";
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            tempFile = new File("temp.csv");
            PrintWriter writer = new PrintWriter(tempFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] account = line.split(",");
                if (Integer.parseInt(account[0]) == userID) { 
                    account[4] = "active"; 
                    found = true;
                }
                writer.println(String.join(",", account));
            }
            scanner.close();
            writer.close();

            if (!found) {
                throw new AccountNotFoundException("Account not found.");
            }
            file.delete();
            tempFile.renameTo(file);
        
            System.out.println("Account blocked.");

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public static StudentAccount createAccount(Session session, String loginName, String password) throws DuplicateRecordException, AccessViolationException, ExpiredSessionException {
        if (!accountExists(loginName)) {
            throw new DuplicateRecordException("Account already exists.");
        } else if (session.getRole() != Role.ADMIN){
            throw new AccessViolationException("Access violation.");
        } else if (!session.getStatus()) {
            throw new ExpiredSessionException("Session expired.");
        }
            
        int userID = generateuserID();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = input.nextLine();
        System.out.println("Enter DOB (MM/DD/YYY): ");
        String DOB = input.nextLine();
        System.out.println("Enter Phone Number (XXX-XXX-XXXX): ");
        String phoneNumber = input.nextLine();
        System.out.println("Enter academic history (Course1, Course2, Course3, ...): ");
        String academicHistory = input.nextLine();
        int gender;
        do {
            System.out.println("Enter gender(1 for female, 2 for male): ");
            gender = input.nextInt();
        } while(gender!=1 || gender!=2);
        char g;
        if (gender==1) {
            g = 'f';
        } else if (gender==2) {
            g = 'm';
        } else {
            g = ' ';
        }
        StudentAccount newAccount = new StudentAccount(name, DOB, g, phoneNumber, academicHistory); 
        //name, DOB, gender, phoneNumber, academicHistory"
        input.close();
        try {
            FileWriter fw = new FileWriter(database,true); //true : append to file
            fw.write(userID+","+loginName+","+password+","+Role.STUDENT+",active\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace(); //try to see what happened
        }
        return newAccount;
    }

    private static int generateuserID() {
        String id = "";
        for (int j=0; j<7; j++){ //generate 7 random digits for ID
            int random = (int)(Math.random() * 10);
            id+=random;
        }
        return Integer.parseInt(id);
    }

    static boolean accountExists(String loginName) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("AccountDatabase.csv"));
            while ((line = br.readLine()) != null) {
                String[] account = line.split(",");
                if (account[1].equals(loginName)) {
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    void viewAccount(Session session) {
        int userID = session.getOwner();
        try {
            // bufferedreader --> input reader
            BufferedReader br = new BufferedReader(new FileReader("AccountDatabase.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] account = line.split(",");
                if (String.valueOf(userID).equals(account[0])) {
                    System.out.println(line);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            // shows error up till that point
        }

    }
        
    boolean passwordCheck(String password) {
        if (password.length()!=8){
            return false;
        } else {
            return true;
        }
    }
        
    void passwordCheckInstruction() {
        System.out.println("Password must contain at least 8 letters.");
    }
        
    boolean changePassword(Session session, String newPassword) throws AccountNotFoundException {
        if (!passwordCheck(newPassword)) {//if does not pass password limitation, return false
            return false;
        } else {
            try {
                session.setPassword(newPassword);
            } catch (AccountNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            session.setModified(true);
            return true;
        }
    }
        
    boolean changeLoginName(Session session, String newLoginName) throws AccountNotFoundException {
        if (!session.getRole().equals(Role.ADMIN)) {
            return false;
        } else {
            session.setLoginName(newLoginName);
            return true;
        }
    }

    

}
