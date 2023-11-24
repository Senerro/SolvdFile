package com.solvd.mavenFile.file;



import com.solvd.mavenFile.stringHelpers.StringHelper;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWorker
{
    public static void add(String fileName, String fileFormat, String text)
    {
        try(FileWriter writer = new FileWriter(fileName+fileFormat, false))
        {

            writer.write(text);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public static void add(String fileName2, String fileFormat, List<String> lines) throws IOException {

        File file = new File("src/main/resources/"+fileName2+fileFormat);
        FileUtils.writeLines(file, "UTF-8", lines);
    }
    public  static ArrayList<Character> get(String fileName, String fileFormat)
    {
        ArrayList<Character> charLisr = new ArrayList<>();
        try(FileReader reader = new FileReader(fileName+fileFormat))
        {
            int c;

            while((c=reader.read())!=-1){

                System.out.print((char)c);
                charLisr.add((char)c);
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return charLisr;
    }


}
