package Admin;

public class AdminAccount {
    private String username;
    private String password;

    public AdminAccount(String username,String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public String toString(){
        return username + "," + password;
    }
    public static AdminAccount fromString(String account){
        String[] acc_info = account.split(",");
        return new AdminAccount(acc_info[0],acc_info[1]);
    }
}
