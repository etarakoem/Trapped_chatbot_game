import java.util.*;

import java.io.*;

public class messagePhrase {
    private String file;
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

    public boolean nameCheck(String nameCompare)
    {
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
                    if  (name.equals(nameCompare))
                        return true;
                }
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public void addOptions(String name, int number){
        try 
        {
            FileWriter targetFile = new FileWriter(this.file, true);

            if (number == -1){
                customMenu("Adding Long name, then adding Long quote, many lines. When finish, input in 'd'");
                System.out.println("Long name/options for file: ");
                Scanner input = new Scanner(System.in);
                String options = input.nextLine();
                targetFile.write(begin + options + "\n");
                
                System.out.println("Long phrase to add in: ");
                
                options = input.nextLine();
                while (!options.equalsIgnoreCase("d"))
                {
                    if (!options.equalsIgnoreCase("d")) targetFile.write(options + "\n");   
                    options = input.nextLine();
                }

                targetFile.write(end + "\n");
                targetFile.close();
                return;
            }

            targetFile.write("\n");
            targetFile.write(begin + name + "\n");

            if (number!=0){
                Scanner input = new Scanner(System.in);
                for (int i = 1; i < number+1; i++){
                    System.out.print("Option for: " + i +"): ");
                    String options = input.nextLine();
                    targetFile.write(i + ") " + options);
                    targetFile.write("\n");
                }
            }

            targetFile.write(end + "\n");
            targetFile.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void customMenu(String message){
        System.out.println("==================");
        System.out.println(message);
        System.out.println("==================");
    }
    public void menuMessage(String a, String b, String c){
        System.out.println("==================");
        System.out.println("Edit Options menu:");
        System.out.println("Input 'a <number of options>' to add options for '"+ a+"'");
        System.out.println("Input 'b <name>' to add options with blank spaces for '"+ b+"'");
        System.out.println("Input 'c' to add long name/options with blank spaces for '"+ c+"'");
        System.out.println("input 'q' to quit");
        System.out.println("==================");
        System.out.println("Your input:");
    }

    public void menu()
    {
        menuMessage(this.file,this.file,this.file);
        Scanner commandInput = new Scanner(System.in);
        String userInput = commandInput.next();
        while (!userInput.equalsIgnoreCase("q")){
            if (userInput.equalsIgnoreCase("a")){
                System.out.println("Long name/options for file: ");
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                if (commandInput.hasNextInt()){
                    int number = commandInput.nextInt();
                    addOptions(name, number);
                    System.out.println("\n Succesfully add "+ name +" and "+number+" lines into "+ this.file + "\n");
                }
                else{
                    addOptions(name, 0);
                }
            }
            else if (userInput.equalsIgnoreCase("b")){
                String name = commandInput.next();
                addOptions(name, 0);
                System.out.println("\n Succesfully add "+ name +" into "+ this.file + "\n");
            }
            else if (userInput.equalsIgnoreCase("c"))
            {
                addOptions("", -1);
            }
            userInput = commandInput.nextLine();

            System.out.println("Press 'q' to return");
        }
        if (userInput.equalsIgnoreCase("q")) System.out.println("(Return to menu)...");
    }

    public void inGameOptions(){
        System.out.println("==================");
        System.out.println("Choose '1','2','3' for options");
        System.out.println("Any other keys to quit");
        System.out.println("==================");
    }
    public String takeUserInput(){
        try{
            System.out.println("Hey, type your input here: ");
            Scanner input = new Scanner(System.in);
            String userinput = input.nextLine();
            if ((userinput.length()==1)&&(Integer.parseInt(userinput)<4) && (Integer.parseInt(userinput)>0))
            return userinput;
        }
        catch(NumberFormatException e)
        {
           System.out.println("Quitting...");
        }
        return "q";
    }

    // Print out full name title
    public String pickOptions(String quote,String option){
        try{
            messagePrint(quote);
            inGameOptions();
            ArrayList<String> quotes = getMessages(quote);
            String choices = option+")";
            for (String opt:quotes){
                if (opt.contains(choices)){
                    return opt.substring(3, opt.length());
                }
            }
        }
        catch(FileNotFoundException e){e.printStackTrace();}

        return "q";
    }

}

