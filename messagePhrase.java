package karahiko.play;
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
    public void messagePrint(String message){
        System.out.println(message);
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
            while (lines.hasNextLine()) {
                String line = lines.nextLine();
                if (line.contains(begin)) {
                    String name = line.substring(begin.length(), line.length() - 1);
                    if (name.equals(find)) {
                        result.add(name);
                        while (!line.contains(end)) {
                            String nextLine = lines.nextLine();
                            result.add(nextLine);
                        }
                        return result;
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

