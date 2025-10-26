package textgen;

import java.util.AbstractList;

public class MyLinkedList<E> extends AbstractList<E> {

	protected LLNode<E> head;
	protected LLNode<E> tail;
    private int size;

    public MyLinkedList() {
        head = new LLNode<>(null);
        tail = new LLNode<>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public E get(int index) {
        return getNode(index).data;
    }

    public boolean add(E element) {
        add(size, element);
        return true;
    }

    public void add(int index, E element) {
        if (element == null) throw new NullPointerException();
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        LLNode<E> nextNode = (index == size) ? tail : getNode(index);
        LLNode<E> prevNode = nextNode.prev;
        LLNode<E> newNode = new LLNode<>(element);

        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = nextNode;
        nextNode.prev = newNode;
        size++;
    }

    public E remove(int index) {
        LLNode<E> node = getNode(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.data;
    }

    public E set(int index, E element) {
        if (element == null) throw new NullPointerException();
        LLNode<E> node = getNode(index);
        E old = node.data;
        node.data = element;
        return old;
    }

    public int size() {
        return size;
    }

    private LLNode<E> getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        LLNode<E> current;

        if (index < size / 2) {
            current = head.next;
            for (int i = 0; i < index; i++) current = current.next;
        } else {
            current = tail.prev;
            for (int i = size - 1; i > index; i--) current = current.prev;
        }

        return current;
    }
}

class LLNode<E> {
    E data;
    LLNode<E> prev;
    LLNode<E> next;

    public LLNode(E data) {
        this.data = data;
    }
}
