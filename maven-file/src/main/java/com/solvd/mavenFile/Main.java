package com.solvd.mavenFile;

import com.solvd.mavenFile.exception.MyCustomeException;
import com.solvd.mavenFile.stringHelpers.StringHelper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner input  = new Scanner(System.in);
    public static void main(String[] args) throws IOException, MyCustomeException {
        String fileName = "file";
        String fileName2 = "file2";
        String fileName3 = "file3";
        String fileFormat = ".txt";
        String fileText = "Text text text Text Text Text";

        //Reading from file and calculate unique words
        {
            StringHelper.writeInFile(fileName, fileFormat, fileText);
            var characterArrayList = StringHelper.readFile(fileName, fileFormat);
            var str = StringHelper.transformation(characterArrayList);
//            var dictionary = StringHelper.devineByWords(str);
//            var container = StringHelper.enterIntoContainer(dictionary);
            var resultText = StringHelper.HW_28_11_0(str);
            StringHelper.writeInFile("Count of unique words", fileFormat, resultText);
            for (var element:resultText) {
                System.out.println(element);
            }
        }

        {
            //reading from file and calculate unique and non-unique letters
            StringHelper.writeInFile(fileName2, fileFormat, fileText);
            var charactersLetterList = StringHelper.readFile(fileName);
            var str = StringHelper.transformation(charactersLetterList);
            var textResult = StringHelper.HW_28_11_1(str);
            StringHelper.writeInFile("File with up-case letters", fileFormat, textResult);
            for (var element:textResult) {
                System.out.println(element);
            }
        }

        {
            //reading from the file and searching matches
            StringHelper.writeInFile(fileName3, fileFormat, fileText);
            var charactersLetterList = StringHelper.readFile(fileName);
            var str = StringHelper.transformation(charactersLetterList);
            var dictionary = StringHelper.devineByWords(str);
            var word = getWordForEqual();
            var textResult = StringHelper.HW_28_11_2(dictionary, word);
            StringHelper.writeInFile("File with information about count of matches", fileFormat, textResult);
            for (var element:textResult) {
                System.out.println(element);
            }

        }

    }

    private static String getWordForEqual() throws MyCustomeException {
        String anwser = "";
        System.out.println("Enter your word");
        try {
            anwser = input.nextLine();
            try {
                Integer.parseInt(anwser);
                throw new MyCustomeException("Answer is number");

            } catch (NumberFormatException e)
            {
                System.out.println("answer is not numeric");
            }

           // anwser = StringHelper.transformation(StringHelper.toCharacterListFromString(anwser));
            if(anwser.length()<=1)
                throw new MyCustomeException("Entered word was so little");
            if(StringHelper.CheckCymbols(anwser))
            {
                throw new MyCustomeException("You entered 1 or more special characters");
            }
            if(StringHelper.CheckFigure(anwser))
            {
                throw new MyCustomeException("You entered one or more Figure");
            }

            if(StringHelper.CheckSpace(anwser))
            {
                throw new MyCustomeException("You entered a text, not single world");
            }


        }
        catch(MyCustomeException e)
        {
          System.out.println(e.getMessage());
          getWordForEqual();
        }

        return anwser;
    }

}