package application;

import document.*;

/**
 * MainApp: quick runner to test both document types.
 */
public class MainApp {

    public static void main(String[] args) {
        String text;
        if (args.length > 0)
            text = TextFileUtil.readFile(args[0]);
        else
            text = "This is a small test. It has two sentences!";

        Document basic = new BasicDocument(text);
        Document efficient = new EfficientDocument(text);

        System.out.println("=== BasicDocument ===");
        printStats(basic);

        System.out.println("\n=== EfficientDocument ===");
        printStats(efficient);
    }

    private static void printStats(Document d) {
        System.out.println("Words: " + d.getNumWords());
        System.out.println("Sentences: " + d.getNumSentences());
        System.out.println("Syllables: " + d.getNumSyllables());
        System.out.printf("Flesch Score: %.2f%n", d.getFleschScore());
    }
}
