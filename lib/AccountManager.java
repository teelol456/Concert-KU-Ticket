package lib;

import java.io.*;
import java.util.*;

public class AccountManager {
    private List<Account> accounts = new ArrayList<>();
    private String filename = "./File/Register.csv";

    public AccountManager(){
        LoadAccount();
    }

    public void LoadAccount(){
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            f = new File(filename);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line; 
            while ((line = br.readLine()) != null) { 
                accounts.add(Account.fromString(line));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close(); 
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void SaveAccount(Account account){
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            f = new File(filename);
            fw = new FileWriter(f,true);
            bw = new BufferedWriter(fw);

            bw.write(account.toString()+"\n");
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {   
            System.out.println("An error occurred.");
        } finally {     
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        accounts.add(account);
    }
    
    public Account getAccount(String username){
        for (Account a : accounts) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return null;
    }

    public boolean hasAccount(String username){
        for(Account a : accounts){
            if(a.getUsername().equals(username)){
                return true;
            }
        }
        return false;

    }

    public String PasswordSecure(String password){
        String invalid = "";

        if (password==null ||password.length()<8) {
            return "Password must be more than 8 characters.";
        }

        boolean lower = false; boolean upper = false;
        boolean digit = false;

        for(char c : password.toCharArray()){
            if (Character.isLowerCase(c)) lower = true;
            if (Character.isUpperCase(c)) upper = true;
            if (Character.isDigit(c)) digit = true;
        }
        if(!lower) invalid += "Password must have lower characters. ";
        if(!upper) invalid += "Password must have upper characters. ";
        if(!digit) invalid += "Password must have Digit characters. ";

        return invalid;
    }

    public void printlist(){
        for (Account a : accounts){
            System.out.println(a.toString());
        }
    }
}