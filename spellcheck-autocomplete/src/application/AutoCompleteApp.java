package application;

import java.io.*;
import java.util.*;
import spelling.*;

/**
 * Simple console app to test AutoCompleteDictionaryTrie
 * Example: java -cp bin application.AutoCompleteApp data/words.small.txt hel 5
 */
public class AutoCompleteApp {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Usage: java application.AutoCompleteApp <dictionaryFile> <prefix> <numCompletions>");
            return;
        }

        String dictFile = args[0];
        String prefix = args[1];
        int num = Integer.parseInt(args[2]);

        AutoCompleteDictionaryTrie trie = new AutoCompleteDictionaryTrie();

        try (BufferedReader br = new BufferedReader(new FileReader(dictFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                trie.addWord(line.trim());
            }
        }

        System.out.println("Loaded " + trie.size() + " words.");
        System.out.println("Completions for \"" + prefix + "\":");
        List<String> completions = trie.predictCompletions(prefix, num);
        for (String s : completions)
            System.out.println(" - " + s);
    }
}
