package spelling;

import java.util.List;

/**
 * An interface for providing suggestions for autocompletion.
 * UCSD Data Structures & Performance – Module 5
 */
public interface AutoComplete {

    /**
     * Return a list of up to numCompletions words that complete the given prefix.
     * If there are fewer than numCompletions valid completions, return all of them.
     *
     * @param prefix The text to complete.
     * @param numCompletions The maximum number of completions to return.
     * @return A list of words that start with the given prefix.
     */
    public List<String> predictCompletions(String prefix, int numCompletions);
}
