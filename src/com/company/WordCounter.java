package com.company;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Scanner;

public class WordCounter
{
    public static void main(String[] args)
    {
        if (args.length > 0 && args[0].equals("-h"))
        {
            System.out.println("You can run a program with:");
            System.out.println("WordCounter [inputFileName.txt] [outputFileName.txt]    - define input and output files");
            System.out.println("WordCounter [inputFileName.txt]                         - define output file later");
            System.out.println("WordCounter                                             - define input and output files later");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        String readerName;
        String writerName;

        readerName = args.length > 0 ? args[0] : scanner.next();
        writerName = args.length > 1 ? args[1] : scanner.next();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(readerName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(writerName));

            DecimalFormat dF = new DecimalFormat( "#.#####" );
            HashMap<String, Integer> map = new HashMap<>();
            TreeSet<Word> treeSet = new TreeSet<>();

            String line;
            double wordAmount = 0;
            int lineLength;
            int i;

            while((line = reader.readLine()) != null)
            {
                i = 0;
                lineLength = line.length();
                while (i < lineLength)
                {
                    StringBuilder word = new StringBuilder();
                    for (;i < lineLength && Character.isLetterOrDigit(line.charAt(i)); ++i)
                    {
                        word.append(line.charAt(i));
                    }
                    for (;i < lineLength && !Character.isLetterOrDigit(line.charAt(i)); ++i);
                    if (word.length() != 0)
                    {
                        ++wordAmount;
                        map.merge(word.toString(), 1, Integer::sum);
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : map.entrySet())
            {
                treeSet.add(new Word(entry.getKey(), entry.getValue()));
            }
            for (Word word : treeSet)
            {   
                writer.write(word.getString() + ", " + word.getCount() + ", " + dF.format(word.getCount() / wordAmount * 100) + "%\n");
            }
            reader.close();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
