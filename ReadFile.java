import java.io.*;
import java.util.*;

public class ReadFile {
    private Scanner x;

    public void openFile(){
        try{
            x = new Scanner(new File("words.txt"));
        }
        catch(Exception e){
            System.out.println("oops, couldn't find my dictionary!");
        }
    }
    public void readFile(){
        while(x.hasNext()){
            String a = x.next();
            System.out.printf(a + "\n");
        }
    }
    public void closeFile(){
        x.close();
    }
    //create a hashset out of txt file
    //this will be used to check user input
    public Set<String> createSet(){
        Set<String> words = new HashSet<>();
        try{
            x = new Scanner(new File("words.txt"));
        }
        catch(Exception e){
            System.out.println("oops, couldn't find my dictionary!");
        }
        while(x.hasNext()){
            String a = x.next();
            words.add(a);
        }
        return words;
    }
    //create a List out of txt file
    //this will be used to generate the initial word
    public ArrayList<String> createList(){
        ArrayList<String> nineLetterWords = new ArrayList<>();
        try{
            x = new Scanner(new File("words.txt"));
        }
        catch(Exception e){
            System.out.println("oops, couldn't find my dictionary!");
        }
        while(x.hasNext()){
            String a = x.next();
            if(a.length() == 9){
                nineLetterWords.add(a);
            }
        }
        return nineLetterWords;
    }
}
