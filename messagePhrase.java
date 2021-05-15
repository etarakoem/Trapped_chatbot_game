import java.util.*;
import java.io.*;

public class messagePhrase {
    private String file;

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
        String begin, end;
        begin = "<Begin tag> ";
        end = "<end tag> ";
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
                    String name = line.substring(begin.length(), line.length() - 1);
                    if (name.equals(find)) 
                    {
                        result.add(name);
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
}

