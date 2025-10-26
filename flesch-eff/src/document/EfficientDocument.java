package document;

import java.util.List;

/**
 * A document that computes its properties efficiently in one pass.
 */
public class EfficientDocument extends Document {

    private int numWords;
    private int numSyllables;
    private int numSentences;

    public EfficientDocument(String text) {
        super(text);
        processText();
    }

    /** Make one pass through tokens to compute counts. */
    private void processText() {
        List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
        numWords = 0;
        numSyllables = 0;
        numSentences = 0;

        boolean lastTokenWasWord = false;

        for (String token : tokens) {
            if (isWord(token)) {
                numWords++;
                numSyllables += countSyllables(token);
                lastTokenWasWord = true;
            } else {
                numSentences++;
                lastTokenWasWord = false;
            }
        }

        // Handle text that ends without punctuation
        if (lastTokenWasWord) {
            numSentences++;
        }

        // Handle empty document case
        if (numWords == 0) numSentences = 0;
    }

    @Override
    public int getNumWords() {
        return numWords;
    }

    @Override
    public int getNumSentences() {
        return numSentences;
    }

    @Override
    public int getNumSyllables() {
        return numSyllables;
    }

    public static void main(String[] args) {
        testCase(new EfficientDocument("This is a test.  How many???  Sentences are here..."),
                12, 9, 3);

        testCase(new EfficientDocument(""), 0, 0, 0);

        testCase(new EfficientDocument("Sentence."), 2, 1, 1);

        testCase(new EfficientDocument("Many words here, perhaps?"),
                6, 4, 1);

        testCase(new EfficientDocument(
            "Lorem ipsum dolor sit amet, qui minim labore adipisicing " +
            "minim sint cillum sint consectetur cupidatat."),
            33, 15, 1);
    }
}
