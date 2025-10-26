package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * A class for timing the different Document implementations
 * @author UC San Diego Intermediate MOOC team
 */

public class DocumentBenchmarking {


	public static void main(String[] args) {

		// Run each test 50 times
		int numTrials = 50;

		// The text file to test on
		// (You may need to change this file path to match your project setup)
		String textFile = "data/warAndPeace.txt";

		// The amount of characters to read from the file
		int[] charSizes = {5000, 10000, 15000, 20000, 25000, 30000, 35000, 40000, 45000, 50000};

		// Print the table header
		System.out.println("NumberOfChars\tBasicTime\tEfficientTime");

		// To avoid file I/O overhead in the timing loop,
		// read the largest amount of text we'll need ONCE.
		String largeText = getStringFromFile(textFile, charSizes[charSizes.length - 1]);


		for (int numChars : charSizes) {

			// 1. Get the substring for this trial size
			String text = largeText.substring(0, numChars);

			// 2. Initialize timers
			long basicTotalTime = 0;
			long efficientTotalTime = 0;

			// 3. Time BasicDocument
			for (int i = 0; i < numTrials; i++) {
				System.gc(); // Request garbage collection to minimize noise
				long startTime = System.nanoTime();
				BasicDocument doc = new BasicDocument(text);
				doc.getFleschScore(); // Includes constructor + 3 O(N) get calls
				long endTime = System.nanoTime();
				basicTotalTime += (endTime - startTime);
			}

			// 4. Time EfficientDocument
			for (int i = 0; i < numTrials; i++) {
				System.gc(); // Request garbage collection
				long startTime = System.nanoTime();
				EfficientDocument doc = new EfficientDocument(text);
				doc.getFleschScore(); // Includes constructor (and O(N) processText) + 3 O(1) get calls
				long endTime = System.nanoTime();
				efficientTotalTime += (endTime - startTime);
			}

			// 5. Calculate average times
			double avgBasicTime = basicTotalTime / (double) numTrials;
			double avgEfficientTime = efficientTotalTime / (double) numTrials;

			// 6. Print the row for the table
			System.out.println(numChars + "\t" + avgBasicTime + "\t" + avgEfficientTime);
		}

	}

	/**
	 * Get a string from a file.
	 * @param filename The name of the file
	 * @param n The number of characters to read
	 * @return A string containing the first n characters of the file
	 */
	public static String getStringFromFile(String filename, int n) {
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			int c;
			for (int i = 0; i < n && (c = br.read()) != -1; i++) {
				s.append((char) c);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Unable to open " + filename);
			e.printStackTrace();
		}
		return s.toString();
	}
}