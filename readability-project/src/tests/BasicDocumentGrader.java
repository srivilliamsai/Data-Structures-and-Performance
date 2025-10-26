package tests;

import document.BasicDocument;

/** Small grader for BasicDocument correctness */
public class BasicDocumentGrader {
    public static void main(String[] args) {
        BasicDocument doc = new BasicDocument("The quick brown fox jumps. Over the lazy dog!");
        System.out.println("Words: " + doc.getNumWords());
        System.out.println("Sentences: " + doc.getNumSentences());
        System.out.println("Syllables: " + doc.getNumSyllables());
        System.out.printf("Flesch: %.2f%n", doc.getFleschScore());
    }
}
