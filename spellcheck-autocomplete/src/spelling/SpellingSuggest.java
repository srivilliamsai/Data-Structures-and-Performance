package spelling;

import java.util.List;

/**
 * Interface defining spell-suggestion behavior.
 * Implemented by NearbyWords.
 */
public interface SpellingSuggest {
    /**
     * Return a list of spelling suggestions for the given word.
     * @param word The word to generate suggestions for
     * @param numSuggestions Maximum number of suggestions to return
     */
    public List<String> suggestions(String word, int numSuggestions);
}
