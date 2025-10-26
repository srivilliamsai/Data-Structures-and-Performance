package document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An abstract class that represents a document.
 * Provides methods to count words, sentences, and syllables.
 * Used by BasicDocument and EfficientDocument.
 * 
 * @author UCSD
 */
public abstract class Document {

    private String text;

    /** Create a new document with the given text. */
    protected Document(String text) {
        this.text = text;
    }

    /** Returns tokens that match a regex pattern from the document text. */
    protected List<String> getTokens(String pattern) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);

        while (m.find()) {
            tokens.add(m.group());
        }
        return tokens;
    }

    /**
     * Count syllables in a word using standard rules:
     * - Groups of vowels count as 1 syllable
     * - Trailing 'e' is silent unless part of “le” pattern
     * - 'y' counts as vowel except if first letter
     * - Words ending with “es” or “ed” after consonant add back a syllable if needed
     */
    protected int countSyllables(String word) {
        word = word.toLowerCase();
        int count = 0;
        boolean newSyllable = true;
        String vowels = "aeiouy";

        char[] cArray = word.toCharArray();

        for (int i = 0; i < cArray.length; i++) {
            if (i == cArray.length - 1 && cArray[i] == 'e' && newSyllable && count > 0) {
                // Skip silent 'e' at the end
            } else if (vowels.indexOf(cArray[i]) >= 0) {
                if (newSyllable) {
                    count++;
                    newSyllable = false;
                }
            } else {
                newSyllable = true;
            }
        }

        if (count == 0) {
            count = 1;
        }
        return count;
    }

    /** Check if a token is a word (starts with a letter). */
    protected boolean isWord(String tok) {
        if (tok == null || tok.isEmpty()) return false;
        char c = tok.charAt(0);
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /** Compute the Flesch readability score. */
    public double getFleschScore() {
        double words = (double) getNumWords();
        double sentences = (double) getNumSentences();
        double syllables = (double) getNumSyllables();

        if (words == 0 || sentences == 0) return 0.0;
        return 206.835 - 1.015 * (words / sentences)
                       - 84.6 * (syllables / words);
    }

    // Abstract methods
    public abstract int getNumWords();
    public abstract int getNumSentences();
    public abstract int getNumSyllables();

    public String getText() { return this.text; }

    /** Simple test harness */
    public static boolean testCase(Document doc, int syllables, int words, int sentences) {
        System.out.println("Testing " + doc.getClass().getName() + "...");
        boolean passed = true;
        int syllFound = doc.getNumSyllables();
        int wordsFound = doc.getNumWords();
        int sentFound = doc.getNumSentences();

        if (syllFound != syllables) {
            System.out.println("\nIncorrect syllables. Found " + syllFound + ", expected " + syllables);
            passed = false;
        }
        if (wordsFound != words) {
            System.out.println("\nIncorrect words. Found " + wordsFound + ", expected " + words);
            passed = false;
        }
        if (sentFound != sentences) {
            System.out.println("\nIncorrect sentences. Found " + sentFound + ", expected " + sentences);
            passed = false;
        }
        System.out.println(passed ? "TEST PASSED.\n" : "TEST FAILED.\n");
        return passed;
    }
}
