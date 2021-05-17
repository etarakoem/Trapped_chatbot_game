import java.util.*;
import java.io.*;

public class mainGameAn{
    public static void main(String[] args){
        messagePhrase intro = new messagePhrase("rightPath.txt");
        messagePhrase options = new messagePhrase("options_an.txt");
        messagePhrase cases = new messagePhrase("tempcase.txt");

        intro.messagePrint("Introduction");

        editFile(options, intro, cases);

    }

    public static void commandMessage(messagePhrase intro, messagePhrase options, messagePhrase cases){
        System.out.println("==================");
        System.out.println("Main menu:");
        System.out.println("Input 'a' for " + intro.getFileName()+ ", 'b' for " + options.getFileName() + " or 'c' for " + cases.getFileName());
        System.out.println("input 'q' to quit");
        System.out.println("==================");
        System.out.println("Your input: ");
    }

    public static void editFile(messagePhrase intro, messagePhrase options, messagePhrase cases){
        commandMessage(intro,options,cases);
        Scanner commandInput = new Scanner(System.in);
        String userInput = commandInput.nextLine();
        while (!userInput.equalsIgnoreCase("q"))
        {
            switch(userInput.charAt(0))
            {
                case 'a':
                intro.menu();
                break;
                case 'b':
                options.menu();
                break;
                case 'c':
                cases.menu();
                break;
                case 'q':
                break;
                default:
                System.out.println("Can't find user input");
                break;
            }
            commandMessage(intro,options,cases);
            userInput = commandInput.nextLine();
        }
    }
}