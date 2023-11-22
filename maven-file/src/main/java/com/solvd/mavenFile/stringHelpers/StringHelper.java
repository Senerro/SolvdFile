package com.solvd.mavenFile.stringHelpers;

import com.solvd.mavenFile.file.FileWorker;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StringHelper
{
    public static String transformation(ArrayList<Character> list)
    {

               StringAnalyzer.purgeSpecials(list);
               StringAnalyzer.purgeSpaces(list);
        return StringAnalyzer.toStringTransformation(list);
    }
    public static String[] Devine(String str)
    {
        return StringUtils.split(str, " ");
    }

    public static StringContainer enterWords(String[] dictionary)
    {
        StringContainer container = new StringContainer();
        for (var element:dictionary) {
            container.add(element);
        }
        return container;

    }
    public static int CalculateUniqueWords(StringContainer container)
    {
        return container.size();
    }
    public static void writeInFile(String fileName, String fileFormat, String text)
    {
        FileWorker.add(fileName, fileFormat, text);
    }

    public static ArrayList<Character> readFile(String fileName, String fileFormat)
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
