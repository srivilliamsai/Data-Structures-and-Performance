package spelling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryLoader {
    public static void loadDictionary(Dictionary dict, String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                dict.addWord(line.trim());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading dictionary: " + e.getMessage());
        }
    }
}
