package com.company;

public class Word implements Comparable<Word>
{
    private final String string;
    private final int count;

    Word(String string, int count)
    {
        this.string = string;
        this.count = count;
    }

    @Override
    public int compareTo(Word word)
    {
        if (word.count == this.count)
        {
            return this.string.compareTo(word.string);
        }
        return word.count - this.count;
    }

    public String getString()
    {
        return string;
    }
    public int getCount()
    {
        return count;
    }
}
