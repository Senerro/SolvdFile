package com.solvd.mavenFile.stringHelpers;

import java.util.ArrayList;

public class StringContainer
{
    private ArrayList<Dictionary> dictionary = new ArrayList<>();
    public void add(String word)
    {
            Dictionary myWord = new Dictionary(word);
            if (!dictionary.contains(myWord))
            {
                dictionary.add(myWord);
            }
            else
            {
                for(int i =0; i < dictionary.size(); i++)
                {
                    if(dictionary.get(i).equals(myWord))
                    {
                        dictionary.get(i).increaseCount();
                    }
                }
            }
    }
    private boolean isEmpty()
    {
        return dictionary.isEmpty();
    }

    public int sizeUnique() {
        return dictionary.size();
    }
    public int size()
    {
        int result = 0;
        for (var element:dictionary)
        {
            result += element.count();
        }
        return result;
    }

    public ArrayList<Dictionary> dictionary() {
        return dictionary;
    }
}
