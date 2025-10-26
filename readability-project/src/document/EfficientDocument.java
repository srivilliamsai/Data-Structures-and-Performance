package document;

/**
 * EfficientDocument makes a single pass through the text to count
 * words, sentences, and syllables efficiently (no per-word regex).
 */
public class EfficientDocument extends Document {

    private int numWords;
    private int numSentences;
    private int numSyllables;

    /** Construct and immediately process the text */
    public EfficientDocument(String text) {
        super(text);
        processText();
    }

    /** Walk through the text once to count all features */
    private void processText() {
        String text = getText();
        int length = text.length();
        boolean inWord = false;
        boolean inSentence = false;
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);

            // detect letters
            if (Character.isLetter(c)) {
                if (!inWord) inWord = true;
                currentWord.append(c);
                inSentence = true;
            } else {
                // punctuation or space
                if (inWord) {
                    numWords++;
                    numSyllables += countSyllables(currentWord.toString());
                    currentWord.setLength(0);
                    inWord = false;
                }

                if (c == '.' || c == '!' || c == '?') {
                    numSentences++;
                    inSentence = false;
                }
            }
        }

        // handle trailing word/sentence
        if (inWord) {
            numWords++;
            numSyllables += countSyllables(currentWord.toString());
        }
        if (inSentence) {
            numSentences++;
        }
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
}
