package application;

import java.io.*;

/** Utility to read a text file fully into a String (UTF-8 safe). */
public class TextFileUtil {

    public static String readFile(String filename) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return sb.toString();
    }
}
