import lib.*;

public class Main {
    public static void main(String[] args) {
        new Login();
        //new Register();
        //new Concert();
        //new Zone();
        //new StandZone();
        //new SittingZone();

        Account a1 = new Account("Tee", "123");
        AccountManager am1 = new AccountManager();
        String s = am1.PasswordSecure("Teelol5562652");
        System.out.println(s);
    }
}
