package p2code;

public class User {
    int accessLevel;
    String username;
    String password;

    public User(){
        accessLevel=0;
        username="";
        password="";
    }

    public User(int accessLevel, String username, String password){
        this.accessLevel=accessLevel;
        this.username=username;
        this.password=password;
    }
}
