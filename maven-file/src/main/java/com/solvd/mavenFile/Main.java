package com.solvd.mavenFile;

import com.solvd.mavenFile.stringHelpers.StringHelper;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        String fileName = "file";
        String fileFormat = ".txt";
        String fileText = "Some   Text1!!!, SomeText2, some ";

        StringHelper.writeInFile(fileName, fileFormat, fileText);
        var characterArrayList = StringHelper.readFile(fileName, fileFormat);
        var str = StringHelper.transformation(characterArrayList);
        var dictionary = StringHelper.Devine(str);
        var container = StringHelper.enterWords(dictionary);
        System.out.println(container.size());
    }
}