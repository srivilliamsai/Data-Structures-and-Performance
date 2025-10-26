package spelling;

import java.util.*;

/**
 * Word Path Tree: Uses BFS to find a path from one word to another.
 */
public class WPTree implements WordPath {

    private WPTreeNode root;
    private NearbyWords nw;

    /** Constructor used by the grader. */
    public WPTree(NearbyWords nw) {
        this.nw = nw;
        this.root = null;
    }

    @Override
    public List<String> findPath(String word1, String word2) {
        if (word1 == null || word2 == null) return null;

        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        if (word1.equals(word2)) return Arrays.asList(word1);

        Set<String> visited = new HashSet<>();
        Queue<WPTreeNode> queue = new LinkedList<>();

        this.root = new WPTreeNode(word1, null);
        visited.add(word1);
        queue.add(root);

        while (!queue.isEmpty()) {
            WPTreeNode curr = queue.remove();
            List<String> neighbors = nw.distanceOne(curr.getWord(), true);

            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    WPTreeNode child = curr.addChild(neighbor);
                    queue.add(child);

                    if (neighbor.equals(word2)) {
                        return child.buildPathToRoot();
                    }
                }
            }
        }

        return null;
    }

    /** Optional: prints queue contents for debugging */
    public void printQueue(List<WPTreeNode> list) {
        StringBuilder sb = new StringBuilder("[ ");
        for (WPTreeNode n : list) {
            sb.append(n.getWord()).append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /**
     * Inner class WPTreeNode — must be included in this file for submission!
     */
    private class WPTreeNode {
        private String word;
        private WPTreeNode parent;
        private List<WPTreeNode> children;

        public WPTreeNode(String word, WPTreeNode parent) {
            this.word = word;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        public WPTreeNode addChild(String childWord) {
            WPTreeNode child = new WPTreeNode(childWord, this);
            this.children.add(child);
            return child;
        }

        public List<String> buildPathToRoot() {
            List<String> path = new LinkedList<>();
            WPTreeNode current = this;
            while (current != null) {
                path.add(0, current.word); // add to beginning
                current = current.parent;
            }
            return path;
        }

        public String getWord() {
            return this.word;
        }

        public List<WPTreeNode> getChildren() {
            return this.children;
        }

        public WPTreeNode getParent() {
            return this.parent;
        }
    }
}
