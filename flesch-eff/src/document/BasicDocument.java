package document;

import java.util.List;

/**
 * A class that represents a text document.
 * It offers methods to count the number of words, sentences, and syllables
 * in the document, but it re-calculates these values each time.
 * @author UC San Diego Intermediate MOOC team
 */
public class BasicDocument extends Document {

	/**
	 * Create a new BasicDocument object
	 * @param text The text of the document.
	 */
	public BasicDocument(String text) {
		super(text);
	}

	/**
	 * Get the number of words in the document.
	 * A "word" is defined as a contiguous string of alphabetic characters.
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords() {
		// A word is any token that matches the regex [a-zA-Z]+
		List<String> tokens = getTokens("[a-zA-Z]+");
		return tokens.size();
	}

	/**
	 * Get the number of sentences in the document.
	 * Sentences are marked by end-of-sentence punctuation (!, ., or ?).
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences() {
		// A sentence is ended by ! . or ?
		List<String> tokens = getTokens("[!?.]+");
		return tokens.size();
	}

	/**
	 * Get the number of syllables in the document.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables() {
		// We count syllables only in words
		List<String> tokens = getTokens("[a-zA-Z]+");
		int totalSyllables = 0;
		for (String word : tokens) {
			totalSyllables += countSyllables(word);
		}
		return totalSyllables;
	}

	/* The main method for testing this class.
	 * You are encouraged to add more tests.  */
	public static void main(String[] args) {
		testCase(new BasicDocument("This is a test.  How many???  Sentences are here..."),
				10, 10, 3);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("Sentence."), 2, 1, 1);
		testCase(new BasicDocument("Many words here, perhaps?"), 5, 5, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui minim labore adipisicing " +
				"minim sint cillum sint consectetur cupidatat."),
				30, 19, 1);
	}
}