package com.solvd.mavenFile.stringHelpers;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringAnalyzer {

    public static ArrayList<Character> purgeSpaces(ArrayList<Character> list)
    {

        if(!isContainSpace(list))
            return list;

        char searchable = ' ';
        for (int i = 0; i <list.size()-1; i++) {
             var a = list.get(i);
             var b = list.get(i+1);
             if (list.get(i) == searchable && list.get(i + 1) == searchable) {

                 list.remove(i);
                 i--;
             }
        }
        if (list.get(list.size()-1) == searchable)
            list.remove(list.size()-1);
         return list;
    }
    public static List<Character> purgeSpecials(ArrayList<Character> list)
    {
        ArrayList<Character> cymbols = new ArrayList<>(Arrays.asList(
                ',', '.', '&', '"', '?', '!', '@', '#', '$', '%', '^', '*', '(', ')', '+', '-', '/', '|', '<', '>', '\n', '\t', '\''));
        list.removeAll(cymbols);
        return list;
    }

    private static boolean isContainSpace(ArrayList<Character> list)
    {
        return list.contains(' ');
    }
    public static ArrayList<Character> downReqistre(ArrayList<Character> list)
    {
        return list;
    }
    public static String toStringTransformation(ArrayList<Character> list)
    {
        StringBuilder returnString = new StringBuilder();
        for (var element:list) {
           returnString.append(element);
        }
        return returnString.toString();
    }

}
