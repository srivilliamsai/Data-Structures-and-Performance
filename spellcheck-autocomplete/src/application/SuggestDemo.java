package application;

import java.io.*;
import java.util.*;
import spelling.*;

/**
 * Simple spell suggestion demo using NearbyWords.
 * Example: java -cp bin application.SuggestDemo data/words.txt spel 10
 */
public class SuggestDemo {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Usage: java application.SuggestDemo <dictionaryFile> <word> <numSuggestions>");
            return;
        }

        String dictFile = args[0];
        String word = args[1];
        int num = Integer.parseInt(args[2]);

        DictionaryHashSet dict = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(dict, dictFile);
        NearbyWords nw = new NearbyWords(dict);

        System.out.println("Loaded " + dict.size() + " words.");
        System.out.println("Suggestions for \"" + word + "\":");
        List<String> suggestions = nw.suggestions(word, num);
        for (String s : suggestions)
            System.out.println(" - " + s);
    }
}
