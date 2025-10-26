package spelling;

import java.util.List;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;

/** * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode(); // This was the typo (was TaintNode)
		size = 0;
	}
	
	
	/** Insert a word into the trie.
	 * For the purposes of this assignment, everything should be converted to 
	 * lowercase before being inserted.
	 * * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes when necessary, and create new
	 * nodes when necessary.
	 * * @param word The word to add
	 * @return true if the word was added to the trie (it wasn't already there).
	 */
	public boolean addWord(String word)
	{
	    String wordL = word.toLowerCase();
	    TrieNode curr = root;
	    
	    for (char c : wordL.toCharArray()) {
	    	TrieNode next = curr.getChild(c);
	    	if (next == null) {
	    		next = curr.insert(c);
	    	}
	    	curr = next;
	    }
	    
	    // After the loop, curr is at the node for the last char
	    // If it's not already marked as a word, mark it and inc size
	    if (!curr.endsWord()) {
	    	curr.setEndsWord(true);
	    	size++;
	    	return true;
	    }
	    
	    // Word was already in the trie
	    return false;
	}
	
	/** * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of nodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, i.e. it ends at a
	 * node that is marked as ending a word.
	 * @param s The word to check
	 * @return true if the string is a word in the trie, false otherwise.
	 */
	public boolean isWord(String s) 
	{
		String sL = s.toLowerCase();
		TrieNode curr = root;
		
		for (char c : sL.toCharArray()) {
			curr = curr.getChild(c);
			if (curr == null) {
				// Path does not exist
				return false;
			}
		}
		
		// If we reached the end of the string,
		// return whether this node is marked as a word
		return curr.endsWord();
	}

	/** * Return a list, in order of increasing (string) length, of completions 
     * of the prefix.
     * * @param prefix The prefix to find completions for.
     * @param numCompletions The maximum number of completions to return.
     * @return A list of all words in the dictionary that start with the prefix, 
     * or an empty list if no words matching the prefix are found.
     */
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // List to store completions
    	 List<String> completions = new LinkedList<String>();
    	 
    	 String prefixL = prefix.toLowerCase();
    	 TrieNode curr = root;
    	 
    	 // 1. Navigate to the node representing the end of the prefix
    	 for (char c : prefixL.toCharArray()) {
    		 curr = curr.getChild(c);
    		 if (curr == null) {
    			 // Prefix not in trie, return empty list
    			 return completions;
    		 }
    	 }
    	 
    	 // 2. At this point, 'curr' is the prefix node.
    	 //    Perform BFS starting from this node.
    	 Queue<TrieNode> q = new LinkedList<TrieNode>();
    	 q.add(curr);
    	 
    	 while (!q.isEmpty()) {
    		 TrieNode node = q.remove();
    		 
    		 // 3. If this node represents a word, add it to the list
    		 if (node.endsWord()) {
    			 completions.add(node.getText());
    			 
    			 // Check if we have found enough completions
    			 if (numCompletions > 0 && completions.size() >= numCompletions) {
    				 break; // Stop the BFS loop
    			 }
    		 }
    		 
    		 // 4. Add all of its children to the queue
    		 Set<Character> childrenChars = node.getValidNextCharacters();
    		 for (char c : childrenChars) {
    			 q.add(node.getChild(c));
    		 }
    	 }
    	 
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
}