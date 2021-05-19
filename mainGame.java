import java.util.*;
import java.io.*;

public class mainGame{
    public static void main(String[] args){
        messagePhrase quote = new messagePhrase("easterQuote.txt");
        messagePhrase options = new messagePhrase("easterOptions.txt");
        messagePhrase cases = new messagePhrase("tempcase.txt");
        if (editQ()) editFile(quote, options, cases);
        gameLoop(quote, options, true);
        
        // gameOver();
    }
    public static boolean editQ(){
        System.out.println("edit file? y/n");
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        if (command.equals("y")) return true;
        return false;
    }

    public static void popUp(messagePhrase quotes, messagePhrase options, String message){
        if (!quotes.nameCheck(message) || !options.nameCheck(message)){
            System.out.println("No info, return to previous");
            return;
        }
        quotes.messagePrint(message);
        options.messagePrint(message);
    }

    public static void gameLoop(messagePhrase quotes, messagePhrase options, boolean gameRestart){
        popUp(quotes, options, "Greetings");
        String input = options.takeUserInput();
        if (input.equals("q")) {
            gameOver();
            return;
        }
        // System.out.println("input "+input);
        String choice = options.pickOptions("Greetings", input);
        gameRestart = false;
        while (!gameRestart){
            popUp(quotes, options, choice);
            input = options.takeUserInput();
            choice = options.pickOptions(choice, input);
            System.out.println("User choose: "+ input);
            if (input.equals("q")){
                gameRestart = true; 
            }
            
            // Close condition
        }
        gameOver();
    }
        
    public static String userResponse(messagePhrase options, messagePhrase quotes,String quote, String choice){
        // First 2 parameter is for library, last one is to search.

        // Give the quote
        System.out.println("==================");
        quotes.messagePrint(quote);
        System.out.println("------------------");
        
        // Scanner input = new Scanner(System.in);
        // String userInput = input.nextLine();
        // switch(userInput)
        // {
        //     case "a": return 
        // }
        System.out.println("------------------");
        System.out.println("==================");
        return "q";
    }
    
    public static void gameOver(){
        System.out.println("GameOver!");
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