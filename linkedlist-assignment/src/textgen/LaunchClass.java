package textgen;

/**
 * LaunchClass is a simple wrapper used by Coursera’s autograder.
 * It runs MyLinkedListGrader by default.
 */
public class LaunchClass {
    public static void main(String[] args) {
        System.out.println("=== Running Linked List Demo ===");
        MyLinkedListGrader.main(args);
        System.out.println("=== End of Test ===");
    }
}
