package document;

import java.util.*;

/**
 * BasicDocument implements Document for simple text analysis.
 * Counts words, sentences, and syllables using regex and helper methods.
 */
public class BasicDocument extends Document {

    /** Construct a BasicDocument with given text */
    public BasicDocument(String text) {
        super(text);
    }

    /** Count words as contiguous alphabetic strings */
    @Override
    public int getNumWords() {
        List<String> tokens = getTokens("[a-zA-Z]+");
        return tokens.size();
    }

    /** Count sentences separated by '.', '!', or '?' */
    @Override
    public int getNumSentences() {
        List<String> tokens = getTokens("[^.!?]+");
        return tokens.size();
    }

    /** Count syllables using helper from Document */
    @Override
    public int getNumSyllables() {
        List<String> tokens = getTokens("[a-zA-Z]+");
        int totalSyllables = 0;
        for (String word : tokens) {
            totalSyllables += countSyllables(word);
        }
        return totalSyllables;
    }
}
