package textgen;

/**
 * Standalone demonstration showing basic usage.
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        MyLinkedList<String> names = new MyLinkedList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        System.out.println("Original List:");
        print(names);

        names.add(1, "Zara");
        System.out.println("\nAfter adding Zara at index 1:");
        print(names);

        names.remove(2);
        System.out.println("\nAfter removing index 2:");
        print(names);

        names.set(1, "Tom");
        System.out.println("\nAfter setting index 1 to Tom:");
        print(names);

        System.out.println("\nElement at index 0: " + names.get(0));
        System.out.println("List size: " + names.size());
    }

    private static <E> void print(MyLinkedList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
}
