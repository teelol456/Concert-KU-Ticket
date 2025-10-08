package Concert;

import java.io.*;
import java.util.*;

public class ConcertManager {
    private List<Concert> concerts = new ArrayList<>();
    private String filename = "./File/Concert.CSV";

    public ConcertManager(){
        LoadConcert();
    }

    public void LoadConcert(){
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            f = new File(filename);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line; 
            while ((line = br.readLine()) != null) { 
                concerts.add(Concert.fromString(line));
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
    public void SaveConcert(Concert concert){
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            f = new File(filename);
            fw = new FileWriter(f,true);
            bw = new BufferedWriter(fw);

            bw.write(concert.toString()+"\n");
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
        concerts.add(concert);
    }
    
    public Concert getConcert(String concertName){
        for (Concert c : concerts) {
            if (c.getConcertName().equals(concertName)) {
                return c;
            }
        }
        return null;
    }

    public Concert getConcert(int concertIndex){
        return concerts.get(concertIndex);
    }

    public List<Concert> getConcertList(){
        return concerts;
    }

    public boolean hasConcert(String concertName){
        for(Concert a : concerts){
            if(a.getConcertName().equals(concertName)){
                return true;
            }
        }
        return false;
    }


    public void printlist(){
        for (Concert c : concerts){
            System.out.println(c.toString());
        }
    }
}
