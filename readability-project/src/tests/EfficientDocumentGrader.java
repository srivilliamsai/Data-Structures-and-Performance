package tests;

import document.EfficientDocument;

/** Tests the one-pass efficient version */
public class EfficientDocumentGrader {
    public static void main(String[] args) {
        EfficientDocument doc = new EfficientDocument("This is a simple test. It has two sentences!");
        System.out.println("Words: " + doc.getNumWords());
        System.out.println("Sentences: " + doc.getNumSentences());
        System.out.println("Syllables: " + doc.getNumSyllables());
        System.out.printf("Flesch: %.2f%n", doc.getFleschScore());
    }
}
