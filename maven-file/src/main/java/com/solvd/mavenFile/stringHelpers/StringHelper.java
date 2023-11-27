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
                charLisr.add((char)c);
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return charLisr;
    }

    public static ArrayList<Character> readFile(String fileName) throws IOException {
        var file = new File(fileName + ".txt");
        var lines = FileUtils.readFileToString(file, "UTF-8");

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

    public static String[] saveLetters(String str)
    {
        var dictionary = StringHelper.devineByLetters(str);
        String[] answer = new String[]{"default text is " + str +"\n", "Current text is ", ""};
        StringHelper.upCaseOfAllLetters(dictionary);
        var a = StringUtils.join(dictionary, " ");
        answer[1] += a;
        answer[2] += "Count of letters at whole is "+ (a.length()/2+1);
        return answer;

    }

    public static String[] saveUniqueWords(String str) throws IOException {
        int uniqueWordCounter = 0;

        var dictionary = StringHelper.devineByWords(str);
        var joinedDictionary = StringUtils.join(dictionary, " ");
        joinedDictionary = joinedDictionary.toLowerCase();
        joinedDictionary = " " + joinedDictionary;
        joinedDictionary = joinedDictionary + " ";
        String[] result = new String[]{"default text is " + str, "", ""};
        StringContainer container = new StringContainer();

        for(var element: dictionary) {

            if (element.length() >= 2)
            {
                String findable = " " + element + " ";
                int count = StringUtils.countMatches(joinedDictionary.toLowerCase(), findable.toLowerCase());
                if(count==1)
                {
                    uniqueWordCounter++;
                    result[1] += "[ " + element + " ]";
                }
                else
                {
                    container.add(element);
                }
            }
        }
        for (var element1: container.dictionary()) {
            result[1] += "[ " + element1.word() + " ] ";
        }
         result[2] += " Text has " + (uniqueWordCounter + container.dictionary().size()) + " unique word";
        StringHelper.writeInFile("Count of unique words", ".txt", result);
        return result;

    }

    public static String[] saveMatches(String[] dictionary, String word)
    {
        String[] answer = new String[]{"default text is " + StringUtils.join(dictionary, " "),
                " Searchable word is " + word, "Matches count is "};

        var str = StringUtils.join(dictionary, "./.");
        var strLow = str.toLowerCase();
        strLow = "." + strLow + ".";
        var findable = "." + word.toLowerCase() + ".";
        var result = StringUtils.countMatches(strLow, findable);
        answer[2] += result;

        return answer;
    }

    public static boolean CheckSpace(String str){
    return  StringAnalyzer.CheckSpace(str);
    }


    public static boolean CheckCymbols(String str)
    {
       return StringAnalyzer.CheckSymbols(str);
    }

    public static boolean CheckFigure(String str)
    {
        return StringAnalyzer.CheckFigure(str);
    }

    public static ArrayList<Character> ConvertFromStringToCharArrayList(String text)
    {
        ArrayList<Character> list= new ArrayList<>();
        var array = text.toCharArray();
        for (var element:array) {
            list.add(element);
        }
        return list;
    }
}
