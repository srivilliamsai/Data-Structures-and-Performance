package spelling;

import java.util.*;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private String text;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        text = "";
        isWord = false;
    }

    public TrieNode(String text) {
        this();
        this.text = text;
    }

    public TrieNode insert(char c) {
        if (children.containsKey(c)) return children.get(c);
        TrieNode next = new TrieNode(text + c);
        children.put(c, next);
        return next;
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public Set<Character> getValidNextCharacters() {
        return children.keySet();
    }

    public boolean endsWord() {
        return isWord;
    }

    public void setEndsWord(boolean isWord) {
        this.isWord = isWord;
    }

    public String getText() {
        return text;
    }
}
