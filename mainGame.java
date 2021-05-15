import java.util.ArrayList;

public class mainGame{
    public static void main(String[] args){
        messagePhrase intro = new messagePhrase("test.txt");

        // ArrayList <String> introduction = new intro.getMessages("Introduction");
        // intro.getCurrentFile();
        // System.out.println("Start printing");
        intro.messagePrint("Introduction");

        intro.messagePrint("Level");
        // System.out.println("end printing");
    }
}