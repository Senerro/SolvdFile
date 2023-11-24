package com.solvd.mavenFile;

import com.solvd.mavenFile.exception.MyCustomeException;
import com.solvd.mavenFile.stringHelpers.StringHelper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static Logger log = LogManager.getLogger(Main.class);

    static Scanner input  = new Scanner(System.in);

    public static void readFromFileForUniqueWords(String fileName, String fileFormat, String fileText) throws IOException {
        StringHelper.writeInFile(fileName, fileFormat, fileText);
        var characterArrayList = StringHelper.readFile(fileName, fileFormat);
        var str = StringHelper.transformation(characterArrayList);
        var resultText = StringHelper.HW_28_11_0(str);
        StringHelper.writeInFile("Count of unique words", fileFormat, resultText);
        for (var element:resultText) {
            log.info(element);
        }
    }
    public static void readFromFileForLetters(String fileName, String fileFormat, String fileText) throws IOException {
        {
            //reading from file and calculate unique and non-unique letters
            StringHelper.writeInFile(fileName, fileFormat, fileText);
            var charactersLetterList = StringHelper.readFile(fileName);
            var str = StringHelper.transformation(charactersLetterList);
            var textResult = StringHelper.HW_28_11_1(str);
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
            var textResult = StringHelper.HW_28_11_2(dictionary, word);
            StringHelper.writeInFile("File with information about count of matches", fileFormat, textResult);
            for (var element:textResult) {
                log.info(element);
            }
    }
    public static String getWordFromConsole() throws MyCustomeException {
        String anwser = "";

        log.info("Enter your word");
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

            anwser = rd.readLine();

            try {
                Integer.parseInt(anwser);
                throw new MyCustomeException("Answer is number");

            } catch (NumberFormatException e)
            {
                log.debug("answer is not numeric");
            }


            if(anwser.length()==1)
                throw new MyCustomeException("Entered word was so little");
            if(StringHelper.CheckCymbols(anwser))
            {
                throw new MyCustomeException("You entered 1 or more special characters");
            }
            if(StringHelper.CheckFigure(anwser))
            {
                throw new MyCustomeException("You entered one or more figure");
            }

            if(StringHelper.CheckSpace(anwser))
            {
                throw new MyCustomeException("You entered a text, not single world");
            }
        }
        catch(MyCustomeException | IOException e)
        {
          log.info(e.getMessage());
          getWordFromConsole();
        }

        return anwser;
    }

    public static void main(String[] args) throws IOException, MyCustomeException {

        boolean isWorking = true;
        do {
            log.info("Are you going to do smth or quit?");
            log.info("[1] continue");
            log.info("[2] quit]");
            int answer = input.nextInt();

            switch (answer)
            {
                case 1: Start();break;
                case 2: isWorking = false;
            }
        }
        while (isWorking);
        System.exit(200);
    }

    private static void Start() throws MyCustomeException, IOException {
        boolean isCorrect = false;
        log.info("Are you going to work with file or console");
        log.info("1: File");
        log.info("2: Console");

        int answer = input.nextInt();
            switch (answer)
            {
                case 1: WorkWithFile();  break;
                case 2: WorkWithConsole(); break;
                default: log.info("uncorrect"); Start();
            }
            saveSession();
    }

    private static void saveSession() throws IOException {
        /*try {
            StringHelper.readFile("src/main/resources/Result");
        }
        catch (IOException e)
        {*/
            String[] str = new String[]{"========="};
            StringHelper.writeInFile("Result", ".txt", str);
            log.debug("result file was written");
       /* }
        finally
        {*/
            writeDownAllResults();
        /*}*/
    }

    private static void writeDownAllResults() throws IOException {
        var fileName = "src/main/resources/Result.txt";
        File toFile = new File(fileName);
        List<String> lines = getSessionResolt(fileName);
        lines.addAll(getSessionResolt("src/main/resources/Count of unique words.txt"));
        lines.add("\n");
        lines.addAll(getSessionResolt("src/main/resources/File with up-case letters.txt"));
        lines.add("\n");
        lines.addAll(getSessionResolt("src/main/resources/File with information about count of matches.txt"));
        lines.add("\n");
        lines.add("===============");


        FileUtils.writeLines(toFile, lines);
    }

    private static List<String> getSessionResolt(String fileRef) throws IOException {

        File fromFile = new File(fileRef);

       // FileUtils.copyFile(fromFile, toFile);
        //Files.copy(fromFile.toPath(), toFile.toPath());
            List<String> lines = FileUtils.readLines(fromFile, StandardCharsets.UTF_8);
            /*FileUtils.writeLines(toFile, lines);*/
            return lines;
    }

    private static void WorkWithConsole() throws MyCustomeException, IOException {
        log.info("Enter your text");

        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        String fileName = "file";
        String fileFormat = ".txt";
        readFromConsoleForUniqueWords(text, fileFormat);
        readFromConsoleForLetters(text, fileFormat);
        readFromConsoleForSearchingTheMatches(fileFormat, text);

    }

    private static void readFromConsoleForSearchingTheMatches(String fileFormat, String text) throws MyCustomeException, IOException {
        var word = getWordFromConsole();
        var dictionary = StringHelper.devineByWords(text);
        var textResult = StringHelper.HW_28_11_2(dictionary, word);
        StringHelper.writeInFile("File with information about count of matches", fileFormat, textResult);
        for (var element:textResult) {
            log.info(element);
        }
    }

    private static void readFromConsoleForLetters(String text, String fileFormat) throws IOException {
        var textResult = StringHelper.HW_28_11_1(text);
        StringHelper.writeInFile("File with up-case letters", fileFormat, textResult);
        for (var element:textResult) {
            log.info(element);
        }
    }

    private static void readFromConsoleForUniqueWords(String text, String fileFormar) throws IOException {
        var resultText = StringHelper.HW_28_11_0(text);
        StringHelper.writeInFile("Count of unique words", fileFormar, resultText);
        for (var element:resultText) {
            log.info(element);
        }
    }

    private static String writeDownText(String wordFromConsole) throws MyCustomeException {
        String word = "";
        word = getWordFromConsole();

        if(!word.isEmpty())
        {
            wordFromConsole+=(word);
            wordFromConsole+="/";
            writeDownText(wordFromConsole);
        }
       return wordFromConsole;
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