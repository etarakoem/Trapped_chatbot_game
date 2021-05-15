import java.util.ArrayList;

public class mainGame{
    public static void main(String[] args){
        messagePhrase intro = new messagePhrase();
        // ArrayList <String> introduction = new intro.getMessages("Introduction");
        intro.getCurrentFile();
        intro.messagePrint("Introduction");
    }
}