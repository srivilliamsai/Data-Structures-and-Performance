package document;

import java.util.*;
import java.util.regex.*;

/**
 * An abstract class representing a text document.
 * Provides core methods for text processing and analysis.
 */
public abstract class Document {
    private String text;

    /** Create a new document from the given text */
    protected Document(String text) {
        this.text = text;
    }

    /** Return the tokens that match the regex pattern from the text */
    protected List<String> getTokens(String pattern) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);

        while (m.find()) {
            tokens.add(m.group());
        }
        return tokens;
    }

    /** Abstract methods for subclasses to implement */
    public abstract int getNumWords();
    public abstract int getNumSentences();
    public abstract int getNumSyllables();

    /** Return text of this document */
    public String getText() {
        return this.text;
    }

    /**
     * Compute and return the Flesch readability score.
     * Formula: 206.835 - 1.015*(words/sentences) - 84.6*(syllables/words)
     */
    public double getFleschScore() {
        double words = getNumWords();
        double sentences = getNumSentences();
        double syllables = getNumSyllables();

        if (words == 0 || sentences == 0) return 0.0;

        return 206.835 - (1.015 * (words / sentences))
                       - (84.6 * (syllables / words));
    }

    /**
     * Count syllables in a given word.
     * Rules:
     * - Contiguous vowel groups (aeiouy) count as one syllable.
     * - A trailing 'e' is silent unless it's the only vowel.
     */
    protected static int countSyllables(String word) {
        int numSyllables = 0;
        boolean newSyllable = true;
        String vowels = "aeiouy";
        char[] cArray = word.toCharArray();

        for (int i = 0; i < cArray.length; i++) {
            char c = Character.toLowerCase(cArray[i]);

            // Handle silent 'e' at the end
            if (i == cArray.length - 1 && c == 'e' && newSyllable && numSyllables > 0) {
                numSyllables--;
            }

            if (newSyllable && vowels.indexOf(c) >= 0) {
                newSyllable = false;
                numSyllables++;
            } else if (vowels.indexOf(c) < 0) {
                newSyllable = true;
            }
        }

        return numSyllables;
    }
}
