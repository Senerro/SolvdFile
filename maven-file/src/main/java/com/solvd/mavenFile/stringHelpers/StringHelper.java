package com.solvd.mavenFile.stringHelpers;

import com.solvd.mavenFile.file.FileWorker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringHelper
{
    public static String transformation(ArrayList<Character> list, int a)
    {

               StringAnalyzer.purgeSpecials(list);
               StringAnalyzer.purgeSpaces(list);
        return StringAnalyzer.toStringTransformation(list);
    }
    public static String transformation(ArrayList<Character> list)
    {

                StringAnalyzer.purgeSpecials(list);
                StringAnalyzer.purgeSpaces(list);
        return StringAnalyzer.toStringTransformation(list);
    }

    public static String[] devineByWords(String str)
    {
        return StringUtils.split(str, " ");
    }
    public static ArrayList<Character> devineByLetters(String str2)
    {
        var words = devineByWords(str2);
        var wordsList = transformationStringArrayToStringArrayList(words);
        var letters = devineWordsByLetters(wordsList);
        return letters;
    }

    public static ArrayList<Character> devineWordsByLetters(List<String> lines)
    {
        ArrayList<Character> collection = new ArrayList<>();
        for (var element:lines) {
            var charArray = element.toCharArray();
            for (var chr:charArray) {
                collection.add(chr);
            }

        }
        return collection;
    }

    public static StringContainer enterIntoContainer(String[] dictionary)
    {
        StringContainer container = new StringContainer();
        for (var element:dictionary) {
            container.add(element);
        }
        return container;

    }
    public static StringContainer enterIntoContainer(ArrayList<Character> dictionary)
    {
        StringContainer container = new StringContainer();
        for (var element:dictionary) {
            container.add(element.toString());
        }
        return container;
    }
    public static void writeInFile(String fileName, String fileFormat, String text)
    {
        FileWorker.add(fileName, fileFormat, text);
    }
    public static void writeInFile(String fileName2, String fileFormat, String[] dictionary) throws IOException {
        List<String> lines = new ArrayList<>();
        for (var element: dictionary) {
            lines.add(element);
        }
        FileWorker.add(fileName2, fileFormat, lines);
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

    public static ArrayList<Character> readFile(String fileName) throws IOException {
        File file = new File(fileName + ".txt");
        String lines = FileUtils.readFileToString(file, "UTF-8");

        return toCharacterArrayTransformation(lines);
    }

    private static ArrayList<Character> toCharacterArrayTransformation(String lines)
    {
        ArrayList<Character> charLisr = new ArrayList<>();
        var a = lines.toCharArray();
        for (var element:a) {
            charLisr.add(element);
        }
        return charLisr;
    }


    public static ArrayList<Character> addSpacesAfterLetters(ArrayList<Character> letters)
    {
        for(int i = 0; i < letters.size()-1; i++)
        {
            var a = letters.get(i);
            var b = letters.get(i+1);
            if(letters.get(i) == ' ')
                continue;
            if(letters.get(i+1) != ' ')
            {
                letters.add(i+1, ' ');
            }
        }
        return letters;
    }

    public static ArrayList<Character> upCaseOfAllLetters(ArrayList<Character> letters)
    {

        for(int i = 0; i < letters.size(); i++)
        {
                var c  = StringUtils.capitalize(letters.get(i).toString()).toCharArray()[0];

            letters.set(i, c);
        }
        return letters;
    }




   private static ArrayList<String> transformationStringArrayToStringArrayList(String[] str)
   {
       ArrayList<String> list = new ArrayList<>();
       for (var element:str)
       {
           list.add(element);
       }
       return list;
   }

    public static String[] HW_28_11_1(ArrayList<Character> dictionary, StringContainer container)
    {
        String[] answer = new String[]{"default text is " + dictionary +"\n", "Current text is ", ""};

        var a = StringUtils.join(dictionary, " ");
        answer[1] += a;
        answer[2] += "Count of letters at whole is "+ (a.length()/2+1);
        return answer;

    }

    public static String[] HW_28_11_0(String[] dictionary, String str)
    {
        int uniqueWordCounter = 0;
        boolean isSpotted;
        var joinedDictionary = StringUtils.join(dictionary, " ");
        String[] result = new String[]{"default text is " + joinedDictionary, "", ""};


        for(var element: dictionary)
        {
            isSpotted = StringUtils.containsIgnoreCase(joinedDictionary, element);
            int matches = StringUtils.countMatches(joinedDictionary, element);
            if(isSpotted)
            {
                var removedJoinedDictionary = StringUtils.remove(str, element);
                isSpotted = StringUtils.containsIgnoreCase(removedJoinedDictionary, element);
                if(!isSpotted)
                {
                    uniqueWordCounter++;
                    result[1] += "[ " + element + " ] ";
                }
            }
        }

         result[2] += " Text has " + uniqueWordCounter + " unique word";
        return result;
    }
}
