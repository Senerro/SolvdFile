package com.solvd.mavenFile;

import com.solvd.mavenFile.stringHelpers.StringHelper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "file";
        String fileName2 = "file2";
        String fileFormat = ".txt";
        String fileText = "Some   Text1!!!, SomeText2, some ";

        //Reading from file and calculate unique words
        {
            StringHelper.writeInFile(fileName, fileFormat, fileText);
            var characterArrayList = StringHelper.readFile(fileName, fileFormat);
            var str = StringHelper.transformation(characterArrayList);
            var dictionary = StringHelper.devineByWords(str);
            var container = StringHelper.enterIntoContainer(dictionary);
            var resultText = StringHelper.HW_28_11_0(dictionary, str);
            StringHelper.writeInFile("Count of unique words", fileFormat, resultText);

        }

        {
            //reading from file and calculate unique and non-unique letters
            StringHelper.writeInFile(fileName2, fileFormat, fileText);
            var charactersLetterList = StringHelper.readFile(fileName2);
            var str2 = StringHelper.transformation(charactersLetterList);
            var dictionary = StringHelper.devineByLetters(str2);
            var container = StringHelper.enterIntoContainer(dictionary);
            StringHelper.upCaseOfAllLetters(dictionary);
            var textResult = StringHelper.HW_28_11_1(dictionary, container);
            StringHelper.writeInFile("File with up-case letters", fileFormat, textResult);

            System.out.println(container.sizeUnique());
            System.out.println(container.size());
        }

        {

        }

        {

        }
    }
}