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

    public static String[] HW_28_11_1(String str)
    {
        var dictionary = StringHelper.devineByLetters(str);
        String[] answer = new String[]{"default text is " + str +"\n", "Current text is ", ""};
        StringHelper.upCaseOfAllLetters(dictionary);
        var a = StringUtils.join(dictionary, " ");
        answer[1] += a;
        answer[2] += "Count of letters at whole is "+ (a.length()/2+1);
        return answer;

    }

    public static String[] HW_28_11_0(String str)
    {
        int uniqueWordCounter = 0;
        boolean isSpotted;

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
        return result;
    }

    public static String[] HW_28_11_2(String[] dictionary, String word)
    {
        String[] answer = new String[]{"default text is " + StringUtils.join(dictionary, " "),
                " Searchable word is " + word, "Matches count is "};

        var a = StringUtils.join(dictionary, " ");
        var b = a.toLowerCase();
        b = " " + b + " ";
        var findable = " " + word.toLowerCase() + " ";
        var result = StringUtils.countMatches(b, findable);
        answer[2] += result;

        return answer;
    }

    public static boolean CheckSpace(String str){
    return  StringAnalyzer.CheckSpace(str);
    }
    public static ArrayList<Character> toCharacterListFromString(String str)
    {
        ArrayList<Character> characters = new ArrayList<>();
        var array = str.toCharArray();
        for (var element:array)
        {
            characters.add(element);
        }
        return characters;
    }

    public static boolean CheckCymbols(String str)
    {
       return StringAnalyzer.CheckSymbols(str);
    }

    public static boolean CheckFigure(String str)
    {
        return StringAnalyzer.CheckFigure(str);
    }


    public static void writeInFile(String fileName, String s, ArrayList<Character> file1) {
    }
}
