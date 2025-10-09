package lib;

public class Account {
    private String username;
    private String password;

    public Account(String username,String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public String toString(){
        return username + "," + password;
    }
    public static Account fromString(String account){
        String[] acc_info = account.split(",");
        return new Account(acc_info[0],acc_info[1]);
    }
}