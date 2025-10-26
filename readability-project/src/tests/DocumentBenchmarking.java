package tests;

import document.*;
import application.TextFileUtil;

/**
 * Compares Basic vs Efficient performance for different text sizes.
 */
public class DocumentBenchmarking {

    public static void main(String[] args) {
        final int TRIALS = 100;
        final int INCREMENT = 20000;
        final int NUM_STEPS = 10;
        final int START = 50000;

        String filename = "data/sample.txt";

        System.out.println("NumChars\tBasicTime\tEfficientTime");

        for (int numToCheck = START;
             numToCheck < NUM_STEPS * INCREMENT + START;
             numToCheck += INCREMENT) {

            String text = getText(filename, numToCheck);

            long startBasic = System.nanoTime();
            for (int i = 0; i < TRIALS; i++) {
                Document d = new BasicDocument(text);
                d.getFleschScore();
            }
            long endBasic = System.nanoTime();

            long startEff = System.nanoTime();
            for (int i = 0; i < TRIALS; i++) {
                Document d = new EfficientDocument(text);
                d.getFleschScore();
            }
            long endEff = System.nanoTime();

            double basicTime = (endBasic - startBasic) / 1e9;
            double effTime = (endEff - startEff) / 1e9;

            System.out.printf("%d\t%.3f\t%.3f%n", numToCheck, basicTime, effTime);
        }
    }

    private static String getText(String filename, int numChars) {
        String fullText = TextFileUtil.readFile(filename);
        return fullText.length() > numChars ?
                fullText.substring(0, numChars) : fullText;
    }
}
