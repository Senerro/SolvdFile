package com.solvd.mavenFile.stringHelpers;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;

public class Dictionary {
    private final String word;
    private int count;
    public Dictionary(String word)
    {
      //  this.word = WordUtils.capitalize(word);
        this.word = word.toUpperCase();
        count = 1;
    }
    public String word()
    {
        return this.word;
    }

    public void increaseCount() {
        count++;
    }
    public int count()
    {
        return count;
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(word, this.getClass());
    }
    @Override
    public boolean equals(Object object)
    {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        if (this.word.equals(((Dictionary) object).word()))
                return true;
        return false;
    }
}
