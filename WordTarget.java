import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class WordTarget {
    public static boolean newGame = false;
    //declare variables word set and word list and the word
    public Scanner scanner;
    public static Set<String> words;
    public static ArrayList<String> nineLetterWords;
    public String theWord;
    public String theScrambledWord;
    private ArrayList<String> answers = new ArrayList<>();
    public void updateAnswers(String s){
        answers.add(s);
    }
    //methods for creating word set and list
    public static void initiateNineLetterWords(){
        nineLetterWords = new ArrayList<>();
        ReadFile r = new ReadFile();
        nineLetterWords = new ArrayList<>();
        nineLetterWords = r.createList();
    }
    public void Welcome(){
        System.out.println();
        System.out.println("Welcome to Word Target!");
        System.out.println();
    }
    public void instructions(){
        System.out.println("Come up with as many words as possible, using  " +
                "the letter in the grid, containing FOUR letters or more.");
        System.out.println("There is at least one nine letter word");
        System.out.println("Words MUST contain the MIDDLE letter.");
        System.out.println();
        System.out.println("Type your answers then press [enter]");
        System.out.println("To see the nine letter word type 9");
        System.out.println("To see your answers so far type: 1");
        System.out.println("To see all possible answers type: 5");
        System.out.println("To start a new game, type \"new\"" +
                "your current game will be lost \n" +
                " for ever");
        System.out.println("Enter * to quit");
    }
    //create "words"-a list of all english words in the main method
    public static void initiateWords(){
        ReadFile r = new ReadFile();
        words = new HashSet<>();
        words = r.createSet();
    }
    public void initiateTheWord(){
        theWord = getWord();
    }
    public void scrambleTheWord(){
        theScrambledWord = scramble(theWord);
    }
    public Set<String>getWords(){
        return words;
    }

    //main
    public static void main(String []args) {
        int lineCount = 0;
        WordTarget t = new WordTarget();
        Scanner scan = new Scanner(System.in);
        initiateWords();
        initiateNineLetterWords();
        t.initiateTheWord();
        t.Welcome();
        t.instructions();
        System.out.println();
        t.scrambleTheWord();
        System.out.println();
        t.gridify(t.theScrambledWord);
        System.out.println();
        ArrayList<String> answers = t.allAnswers();
        //get user input
        String input = scan.next();
        input = input.toLowerCase();
        //validate etc
        while(!(input.equals("*"))){
            if(input.equals("new")){
                newGame = true;
                System.out.println("Are you sure you want to start a new game?");
                System.out.println("type \"yes\" to confirm new game," +
                        " or anything else to resume");
                input = scan.next();
                if(input.equals("yes")){
                    t.initiateTheWord();
                    t.Welcome();
                    t.instructions();
                    t.scrambleTheWord();
                    System.out.println();
                    t.gridify(t.theScrambledWord);
                    answers = t.allAnswers();
                }
            //if anything other than new is entered, game resumes
            }
            if(input.equals("5")){
                System.out.println((answers));
                System.out.println();
                System.out.println("There are " + answers.size() +
                        " possible answers");
                t.gridify(t.theScrambledWord);
                System.out.println();
            }
            else if(input.equals("1")){
                System.out.println(t.answers);
                lineCount+=2;
            }
            else if(input.equals("9")){
                System.out.println(t.theWord);
                lineCount+=2;
            }
            else if(t.answers.contains(input)){
                System.out.println("you already gave that answer");
                lineCount+=2;
            }
            else if(input.equals(t.theWord)){
                System.out.println("WOOHOO! you are AMAZING," +
                        " YOU FOUND THE NINE LETTER WORD");
            }
            else if(t.check(input)){
                    //random int between 0 and 4
                    int n = (int) Math.floor(Math.random() * 5);
                    if(n == 0){
                        System.out.println("nice!");
                    }
                    else if(n == 1){
                        System.out.println("great!");
                    }
                    else if(n == 2){
                        System.out.println("good word!");
                    }
                    else if(n == 3){
                        System.out.println("nice one!");
                    }
                    else if(n == 4){
                        System.out.println("wow nice job!");
                    }
                    t.updateAnswers(input);
                    lineCount +=3;
            }
            //the following is an else statement to add to
            // line count when an invalid word is entered
            else{
                lineCount+=3;
            }
            if(lineCount > 20){
                System.out.println();
                t.gridify(t.theScrambledWord);
                System.out.println();
                lineCount = 0;
            }
            System.out.println();
            input = scan.next();
            input = input.toLowerCase();
        }
    }
    public Set<String> createSet(){
        Set<String> words = new HashSet<>();
        try{
            scanner = new Scanner(new File("words.txt"));
        }
        catch(Exception e){
            System.out.println("oops, couldn't find my dictionary!");
        }
        while(scanner.hasNext()){
            String a = scanner.next();
            words.add(a);
        }
        return words;
    }
    //create a List out of txt file
    //this will be used to generate the initial word
    public ArrayList<String> createList(){
        ArrayList<String> nineLetterWords = new ArrayList<>();
        try{
            scanner = new Scanner(new File("words.txt"));
        }
        catch(Exception e){
            System.out.println("oops, couldn't find my dictionary!");
        }
        while(scanner.hasNext()){
            String a = scanner.next();
            if(a.length() == 9){
                nineLetterWords.add(a);
            }
        }
        return nineLetterWords;
    }
    public String getWord(){
        ReadFile d = new ReadFile();
        ArrayList<String> wordz = new ArrayList<>();
        wordz = d.createList();
        //create a random number between 0 and the length of the list
        Random rand = new Random();
        int num = rand.nextInt(wordz.size());
        theWord = wordz.get(num);
        return theWord;
    }
    public String scramble(String w){
        String scrambledWord = "";
        //scramble word
        ArrayList<Character> chars = new ArrayList<Character>(w.length());
        for ( char c : w.toCharArray() ) {
            chars.add(c);
        }
        Collections.shuffle(chars);
        char[] shuffled = new char[chars.size()];
        for ( int i = 0; i < shuffled.length; i++ ) {
            shuffled[i] = chars.get(i);
        }
        scrambledWord = new String(shuffled);
        return scrambledWord;
    }
    //the following method displays a nine letter word in a grid-like fashion
    public void gridify(String word){
        String wordCaps = word.toUpperCase();
        //we know word will be nine letters
        System.out.println(wordCaps.charAt(0) + " | " + wordCaps.charAt(1)
                + " | " + wordCaps.charAt(2));
        System.out.println("----------");
        System.out.println(wordCaps.charAt(3) + " | "
                + wordCaps.charAt(4) + " | " + wordCaps.charAt(5));
        System.out.println("----------");
        System.out.println(wordCaps.charAt(6) + " | "
                + wordCaps.charAt(7) + " | " + wordCaps.charAt(8));
    }
    public boolean check(String word) {
        //create a boolean to indicate if the word is valid or not
        //the check method is currently incomplete
        //it gives a false negative(shows invalid answers as valid,
      // when a given word repeats a letter that only exists
        // once on the grid
        //the following will attempt to solve this
        //create a Character arrayList to store letters of the Word
        int answerOccurances = 0;
        int wordoccurances = 0;
        //
        boolean tooMany = false;
        boolean isWord = true;
        boolean isValid = true;
        boolean containsMiddle = false;
        boolean fourOrMore = true;
        boolean tooLong = false;
        boolean isAll = isWord && isValid && containsMiddle && fourOrMore && !tooLong && !tooMany;
        //create an array of characters to see if any characters in the word entered by the user
        //are not in the word target
        ArrayList<Character> wordChars = new ArrayList<>();
        for(int i = 0; i<theWord.length();i++){
            wordChars.add(theWord.charAt(i));
        }
        // create arraylist of answer letters
        ArrayList<Character> answerChars = new ArrayList<>();
        for(int i = 0; i< word.length();i++){
            answerChars.add(word.charAt(i));
        }
        //find out if any letters occur more often in the answer than in the grid
//        while(!tooMany){
            for(int i = 0; i <answerChars.size(); i++){
                answerOccurances = Collections.frequency(answerChars,
                        answerChars.get(i));
                wordoccurances = Collections.frequency(wordChars,
                        answerChars.get(i));
                if(answerOccurances > wordoccurances){
                    tooMany = true;
                }
            }
        char[] letters = new char[word.length()];
        //fill array using loop;
        for (int i = 0; i < word.length(); i++) {
            letters[i] = word.charAt(i);
        }
        //compare letters in user word the the word
        for (int i = 0; i < letters.length; i++) {
                if (theWord.indexOf(letters[i]) == -1) {
                    isValid = false;
                }
        }
        if(word.length() > 9){
            tooLong = true;
        }
        if (!(words.contains(word))) {
            isWord = false;
        }
        for(int k = 0; k < letters.length; k++){
            if(letters[k] == theScrambledWord.charAt(4)){
                containsMiddle = true;
            }
        }
        if(newGame){
            newGame = false;
            return noOutputCheck(word);
        }
        if(!isValid){
            System.out.println(word + "  contains letters not on the grid");
        }
        else if(tooMany){
            System.out.println("You have used a letter too many times");
        }
        else if(!isWord){
            System.out.println(word + " is not a word");
        }
        else if(word.length() < 4){
            fourOrMore = false;
            System.out.println("words must contain at least four letters");
        }
        else if(!containsMiddle){
            System.out.println(word + " does not contain the middle letter");
        }
        isAll = isWord && isValid && containsMiddle && fourOrMore && !tooLong && !tooMany;
        return isAll;
    }
    public boolean noOutputCheck(String word) {
        int answerOccurances = 0;
        int wordoccurances = 0;
        //create a boolean to indicate if the word is valid or not
        boolean tooMany = false;
        boolean isWord = true;
        boolean isValid = true;
        boolean containsMiddle = false;
        boolean fourOrMore = true;
        boolean tooLong = false;
        boolean isAll = isWord && isValid && containsMiddle && fourOrMore && !tooLong && !tooMany;
        //create an array of characters to see if any charactes in the word entered by the user
        //are not in the word target
        ArrayList<Character> wordChars = new ArrayList<>();
        for(int i = 0; i<theWord.length();i++){
            wordChars.add(theWord.charAt(i));
        }
        // create arraylist of answer letters
        ArrayList<Character> answerChars = new ArrayList<>();
        for(int i = 0; i< word.length();i++){
            answerChars.add(word.charAt(i));
        }
        //find out if any letters occur more often in the answer than in the grid
        for(int i = 0; i <answerChars.size(); i++){
                answerOccurances = Collections.frequency(answerChars, answerChars.get(i));
                wordoccurances = Collections.frequency(wordChars, answerChars.get(i));
                if(answerOccurances > wordoccurances){
                    tooMany = true;
                }
            }
        //
        char[] letters = new char[word.length()];
        //fill array using loop;
        for (int i = 0; i < word.length(); i++) {
            letters[i] = word.charAt(i);
        }
        //compare letters in user word the the word
        for (int i = 0; i < letters.length; i++) {
            if (theWord.indexOf(letters[i]) == -1) {
                isValid = false;
            }
        }
        if(word.length() > 9){
            tooLong = true;
        }
        if (!(words.contains(word))) {
            isWord = false;
        }
        for(int k = 0; k < letters.length; k++){
            if(letters[k] == theScrambledWord.charAt(4)){
                containsMiddle = true;
            }
        }
        if(!isValid){
//            System.out.println(word + "  contains letters not on the grid");
        }
        else if(!isWord){
//            System.out.println(word + " is not a word");
        }
        else if(word.length() < 4){
            fourOrMore = false;
//            System.out.println("words must contain at least four letters");
        }
        else if(!containsMiddle){
//            System.out.println(word + " does not contain the middle letter");
        }
        isAll = isWord && isValid && containsMiddle && fourOrMore && !tooLong && !tooMany;
        return isAll;
    }
    public ArrayList<String> allAnswers(){
        ArrayList<String> allPossible = new ArrayList<>();
        //use an iterator to iterate through Hashset
        Iterator<String> it = words.iterator();
        while(it.hasNext()){
            String a = it.next();
            if(noOutputCheck(a)){
                allPossible.add(a);
            }
        }
//        System.out.println(allPossible.size());
        return allPossible;
    }
    //function to solve unknown word word target
    public void solveUnknown(){
        //create scanner to get letters
        boolean isWord = false;
        String newArrangement = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the letters as a single \"word\" with no spaces ");
        String scrambled = scan.next();
        ArrayList<Character> scrammedList = new ArrayList<>();
        for(int i = 0; i < scrambled.length();i++){
            scrammedList.add(scrambled.charAt(i));
        }
        while(!isWord){
            Collections.shuffle(scrammedList);
            char[] lettersArray = new char[scrammedList.size()];
            for( int i = 0; i < lettersArray.length; i++ ){
                lettersArray[i] = scrammedList.get(i);
            }
            newArrangement = new String(lettersArray);
            if(words.contains(newArrangement)){
                isWord = true;
            }
        }
        System.out.println(newArrangement);
    }
}


