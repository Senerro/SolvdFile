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
                        dictionary.get(i).count();
                    }
                }
            }


    }
    private boolean isEmpty()
    {
        return dictionary.isEmpty();
    }

    public int size() {
        return dictionary.size();
    }
}
