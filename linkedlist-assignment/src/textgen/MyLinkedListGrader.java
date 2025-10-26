package textgen;

/**
 * Simplified grader to mimic Coursera’s hidden test logic.
 * You can run this to verify all major operations behave correctly.
 */
public class MyLinkedListGrader {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        // Add test
        for (int i = 1; i <= 5; i++) list.add(i);
        System.out.println("Initial: " + toString(list));

        // Add at index
        list.add(2, 99);
        System.out.println("After add(2,99): " + toString(list));

        // Remove test
        list.remove(3);
        System.out.println("After remove(3): " + toString(list));

        // Set test
        list.set(0, 77);
        System.out.println("After set(0,77): " + toString(list));

        // Get test
        System.out.println("Get index 2: " + list.get(2));

        // Size test
        System.out.println("Final size: " + list.size());
    }

    private static <E> String toString(MyLinkedList<E> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
