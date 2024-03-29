package com.solvd.mavenFile;

import com.solvd.mavenFile.exception.MyCustomeException;
import com.solvd.mavenFile.file.FileWorker;
import com.solvd.mavenFile.stringHelpers.StringHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Scanner;
public class Main {

    public static Logger log = LogManager.getLogger(Main.class);
    static Scanner input = new Scanner(System.in);
    public static void readFromFileForUniqueWords(String fileName, String fileFormat, String fileText) throws IOException {
        StringHelper.writeInFile(fileName, fileFormat, fileText);
        var characterArrayList = StringHelper.readFile(fileName, fileFormat);
        var str = StringHelper.transformation(characterArrayList);
        var resultText = StringHelper.saveUniqueWords(str);

        for (var element:resultText) {
            log.info(element);
        }
    }
    public static void readFromFileForLetters(String fileName, String fileFormat, String fileText) throws IOException {
        {
            StringHelper.writeInFile(fileName, fileFormat, fileText);
            var charactersLetterList = StringHelper.readFile(fileName);
            var str = StringHelper.transformation(charactersLetterList);
            var textResult = StringHelper.saveLetters(str);
            StringHelper.writeInFile("File with up-case letters", fileFormat, textResult);
            for (var element:textResult) {
                log.info(element);
            }
        }
    }

    public static void readFromFileForSearchingTheMatches(String fileName, String fileFormat, String fileText) throws IOException, MyCustomeException {        //reading from the file and searching matches
            StringHelper.writeInFile(fileName, fileFormat, fileText);
            var charactersLetterList = StringHelper.readFile(fileName);
            var str = StringHelper.transformation(charactersLetterList);
            var dictionary = StringHelper.devineByWords(str);
            var word = getWordFromConsole();
            var textResult = StringHelper.saveMatches(dictionary, word);
            StringHelper.writeInFile("File with information about count of matches", fileFormat, textResult);
            for (var element:textResult) {
                log.info(element);
            }
    }
    public static String getWordFromConsole() throws MyCustomeException {
        String anwser = "";
        log.info("Enter your word");
            try {
                anwser = input.nextLine();
                if (StringUtils.isNumeric(anwser))
                    throw new MyCustomeException("Answer should be string, not number");
                if (StringUtils.length(anwser) < 2)
                    throw new MyCustomeException("Entered word was so little");
                if (StringHelper.CheckCymbols(anwser))
                    throw new MyCustomeException("You entered 1 or more special characters");
                if (StringHelper.CheckFigure(anwser))
                    throw new MyCustomeException("You entered one or more figure");
                if (StringHelper.CheckSpace(anwser))
                    throw new MyCustomeException("You entered a text, not single world");
            }
            catch (MyCustomeException ex)
            {
                log.warn(ex.getMessage());
                getWordFromConsole();
            }

        return anwser;
    }

    public static void main(String[] args) throws IOException, MyCustomeException {

        String[] str = new String[]{"========="};
        StringHelper.writeInFile("Result", ".txt", str);
        boolean isWorking = true;
        do {
            log.info("Are you going to do smth or quit?");
            log.info("[1] continue");
            log.info("[2] quit]");
            String answer = input.nextLine();
            switch (answer)
            {
                case "1": Start();break;
                case "2": isWorking = false;break;
                default:log.warn("Uncorerct unswer"); main(str);
            }
        }
        while (isWorking);
        input.close();
        System.exit(200);
    }

    private static void Start() throws MyCustomeException, IOException {

        log.info("Are you going to work with file or console");
        log.info("1: File");
        log.info("2: Console");
            String answer = input.nextLine();
            switch (answer)
            {
                case "1": WorkWithFile();  break;
                case "2": WorkWithConsole(); break;
                default: log.info("uncorrected"); Start();
            }
         FileWorker.saveSession();
    }

    private static void WorkWithConsole() throws MyCustomeException, IOException {
        log.info("Enter your text");
        String text = input.nextLine();
        String fileFormat = ".txt";
        readFromConsoleForUniqueWords(text, fileFormat);
        readFromConsoleForLetters(text, fileFormat);
        readFromConsoleForSearchingTheMatches(fileFormat, text);
    }

    private static void readFromConsoleForSearchingTheMatches(String fileFormat, String text) throws MyCustomeException, IOException {
        var word = getWordFromConsole();
        var dictionary = StringHelper.devineByWords(text);
        var textResult = StringHelper.saveMatches(dictionary, word);
        StringHelper.writeInFile("File with information about count of matches", fileFormat, textResult);
        for (var element:textResult) {
            log.info(element);
        }
    }

    private static void readFromConsoleForLetters(String text, String fileFormat) throws IOException {
        var textResult = StringHelper.saveLetters(text);
        StringHelper.writeInFile("File with up-case letters", fileFormat, textResult);
        for (var element:textResult) {
            log.info(element);
        }
    }

    private static void readFromConsoleForUniqueWords(String text, String fileFormar) throws IOException {
        var a = StringHelper.ConvertFromStringToCharArrayList(text);
        var str = StringHelper.transformation(a);
        var resultText = StringHelper.saveUniqueWords(str);
        StringHelper.writeInFile("Count of unique words", fileFormar, resultText);
        for (var element:resultText) {
            log.info(element);
        }
    }

    private static void WorkWithFile() throws IOException, MyCustomeException {
        String fileName = "file";
        String fileFormat = ".txt";
        String fileText = "Some text and other something interesting some.";
        readFromFileForUniqueWords(fileName, fileFormat, fileText);
        readFromFileForLetters(fileName, fileFormat, fileText);
        readFromFileForSearchingTheMatches(fileName, fileFormat, fileText);
    }
}