package spelling;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

/**
 * W16 Spelling Suggestions
 * @author UCSD
 */
public class NearbyWords implements SpellingSuggest {

    private static final int THRESHOLD = 1000;

    // Dictionary to check for real words
    Dictionary dict;

    public NearbyWords(Dictionary dict) {
        this.dict = dict;
    }

    /**
     * Return list of Strings that are one modification away from s.
     */
    public List<String> distanceOne(String s, boolean wordsOnly) {
        List<String> retList = new ArrayList<>();
        insertions(s, retList, wordsOnly);
        substitutions(s, retList, wordsOnly);
        deletions(s, retList, wordsOnly);
        return retList;
    }

    /**
     * Add all strings that are one character substitution away from s.
     */
    public void substitutions(String s, List<String> currentList, boolean wordsOnly) {
        for (int index = 0; index < s.length(); index++) {
            for (char c = 'a'; c <= 'z'; c++) {
                StringBuilder sb = new StringBuilder(s);
                sb.setCharAt(index, c);
                String newWord = sb.toString();

                if (!currentList.contains(newWord)
                    && (!wordsOnly || dict.isWord(newWord))
                    && !newWord.equals(s)) {
                    currentList.add(newWord);
                }
            }
        }
    }

    /**
     * Add all strings that are one character insertion away from s.
     */
    public void insertions(String s, List<String> currentList, boolean wordsOnly) {
        for (int index = 0; index <= s.length(); index++) {
            for (char c = 'a'; c <= 'z'; c++) {
                StringBuilder sb = new StringBuilder(s);
                sb.insert(index, c);
                String newWord = sb.toString();

                if (!currentList.contains(newWord)
                    && (!wordsOnly || dict.isWord(newWord))
                    && !newWord.equals(s)) {
                    currentList.add(newWord);
                }
            }
        }
    }

    /**
     * Add all strings that are one character deletion away from s.
     */
    public void deletions(String s, List<String> currentList, boolean wordsOnly) {
        for (int index = 0; index < s.length(); index++) {
            StringBuilder sb = new StringBuilder(s);
            sb.deleteCharAt(index);
            String newWord = sb.toString();

            if (!currentList.contains(newWord)
                && (!wordsOnly || dict.isWord(newWord))
                && !newWord.equals(s)) {
                currentList.add(newWord);
            }
        }
    }

    /**
     * Spelling correction algorithm: return up to numSuggestions valid words
     * that are close (edit distance) to the input word.
     */
    @Override
    public List<String> suggestions(String word, int numSuggestions) {
        List<String> suggestions = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(word);
        visited.add(word);

        int explored = 0;

        while (!queue.isEmpty() && suggestions.size() < numSuggestions && explored < THRESHOLD) {
            String curr = queue.poll();
            explored++;

            List<String> neighbors = distanceOne(curr, true);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);

                    if (dict.isWord(neighbor)) {
                        suggestions.add(neighbor);
                        if (suggestions.size() >= numSuggestions) {
                            break;
                        }
                    }
                }
            }
        }

        return suggestions;
    }

    /** Main method to test NearbyWords class. */
    public static void main(String[] args) {
        Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "data/dict.txt");
        NearbyWords w = new NearbyWords(d);

        String testWord = "tailo";
        List<String> suggestions = w.suggestions(testWord, 10);
        System.out.println("Spelling suggestions for \"" + testWord + "\": " + suggestions);
    }
}
