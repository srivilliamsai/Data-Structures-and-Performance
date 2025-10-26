package textgen;

/**
 * Stand-alone node class used by MyLinkedList.
 * (You can use this file instead of the inner LLNode if your grader needs it separately.)
 */
public class MyLinkedListNode<E> {
    public E data;
    public MyLinkedListNode<E> prev;
    public MyLinkedListNode<E> next;

    public MyLinkedListNode(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
