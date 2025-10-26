package spelling;

import java.util.*;

class WPTreeNode {

    private String word;
    private WPTreeNode parent;
    private List<WPTreeNode> children;

    public WPTreeNode(String word, WPTreeNode parent) {
        this.word = word;
        this.parent = parent;
        this.children = new LinkedList<>();
    }

    public WPTreeNode addChild(String word) {
        WPTreeNode child = new WPTreeNode(word, this);
        this.children.add(child);
        return child;
    }

    public List<String> buildPathToRoot() {
        LinkedList<String> path = new LinkedList<>();
        WPTreeNode curr = this;
        while (curr != null) {
            path.addFirst(curr.getWord());
            curr = curr.getParent();
        }
        return path;
    }

    public String getWord() {
        return this.word;
    }

    public WPTreeNode getParent() {
        return this.parent;
    }

    public List<WPTreeNode> getChildren() {
        return this.children;
    }
}
