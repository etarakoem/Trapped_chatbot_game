import java.util.*;
import java.io.*;

public class messagePhrase {
    private String file, option;
    final String begin= "<Begin tag> ";
    final String end = "<end tag> ";

    public messagePhrase(){
        this.file = "test.txt";
    }

    public messagePhrase(String file){
        this.file = file;
    }

    public void setFile(String fileName){
        this.file = fileName;
    }
    public void getCurrentFile(){
        System.out.println(this.file);
    }

    public String getFileName(){
        return this.file;
    }
    public void messagePrint(String find){
        try
        {
            ArrayList<String> array = getMessages(find);
            for (String part: array) 
            System.out.println(part);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMessages(String find) throws FileNotFoundException
    {
        ArrayList<String> result = new ArrayList<String>();
        try
        {
            // Extract from file then get into the Name tag
            Scanner lines = new Scanner(new File(file));
            while (lines.hasNextLine()) 
            {
                String line = lines.nextLine();
                if (line.contains(begin))
                {
                    String name = line.substring(begin.length(), line.length());
                    if  (name.equals(find))
                    {
                        // result.add(name);
                        String nextLine = lines.nextLine();
                        while (!nextLine.contains(end)) 
                        {
                            result.add(nextLine);
                            nextLine = lines.nextLine();
                        }
                        if (nextLine.contains(end))
                        {
                            return result;
                        }
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("can't find any");
        return result;
    }


    public void addOptions(String name, int number){
        try 
        {
            FileWriter file = new FileWriter(this.file, true);

            if (number == -1){
                System.out.println("Long name/options for file: ");
                Scanner input = new Scanner(System.in);
                String options = input.nextLine();
                file.write(begin + options + "\n");
                
                System.out.println("Long phrase to add in: ");
                
                options = input.nextLine();
                while (!options.equalsIgnoreCase("d"))
                {
                    if (!options.equalsIgnoreCase("d")) file.write(options + "\n");   
                    options = input.nextLine();
                }

                file.write(end + "\n");
                file.close();
                return;
            }

            file.write("\n");
            file.write(begin + name + "\n");

            if (number!=0){
                Scanner input = new Scanner(System.in);
                for (int i = 1; i < number+1; i++){
                    System.out.print("Option for: " + i +"): ");
                    String options = input.nextLine();
                    file.write(i + ") " + options);
                    file.write("\n");
                }
            }

            file.write(end + "\n");
            file.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void menuMessage(){
        System.out.println("==================");
        System.out.println("Edit Options menu:");
        System.out.println("Input 'a <name> <number of options>' to add options for '"+ this.file+"'");
        System.out.println("Input 'b <name>' to add options with blank spaces for '"+ this.file+"'");
        System.out.println("Input 'c' to add long name/options with blank spaces for '"+ this.file+"'");
        System.out.println("input 'q' to quit");
        System.out.println("==================");
        System.out.println("Your input:");
    }

    public void menu()
    {
        menuMessage();
        Scanner commandInput = new Scanner(System.in);
        String userInput = commandInput.next();
        while (!userInput.equalsIgnoreCase("q")){
            if (userInput.equalsIgnoreCase("a")){
                String name = commandInput.next();
                if (commandInput.hasNextInt()){
                    int number = commandInput.nextInt();
                    addOptions(name, number);
                    System.out.println("Succesfully add "+ name +" and "+number+" lines into "+ this.file);
                }
                else{
                    addOptions(name, 0);
                }
            }
            else if (userInput.equalsIgnoreCase("b")){
                String name = commandInput.next();
                addOptions(name, 0);
                System.out.println("Succesfully add "+ name +" into "+ this.file);
            }
            else if (userInput.equalsIgnoreCase("c"))
            {
                addOptions("", -1);
            }
            userInput = commandInput.nextLine();
            System.out.println("What next? 'q' to return");
        }
        if (userInput.equalsIgnoreCase("q")) System.out.println("(Return to menu)...");
    }

}

