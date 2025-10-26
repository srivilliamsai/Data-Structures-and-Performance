package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import textgen.MyLinkedList;

/**
 * Comprehensive JUnit tests for MyLinkedList implementation
 * Used in UCSD Data Structures & Performance Module 4 assignment.
 */
public class LinkedListJUnitTest {

    private MyLinkedList<String> shortList;
    private MyLinkedList<Integer> emptyList;
    private MyLinkedList<Integer> longerList;
    private MyLinkedList<Integer> list1;

    @Before
    public void setUp() {
        shortList = new MyLinkedList<>();
        shortList.add("A");
        shortList.add("B");

        emptyList = new MyLinkedList<>();

        longerList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            longerList.add(i);
        }

        list1 = new MyLinkedList<>();
        list1.add(65);
        list1.add(21);
        list1.add(42);
    }

    @Test
    public void testGet() {
        assertEquals("A", shortList.get(0));
        assertEquals("B", shortList.get(1));
        for (int i = 0; i < 10; i++) {
            assertEquals((Integer) i, longerList.get(i));
        }

        try {
            shortList.get(-1);
            fail("Index -1 should throw exception");
        } catch (IndexOutOfBoundsException e) {}

        try {
            shortList.get(2);
            fail("Index 2 should throw exception");
        } catch (IndexOutOfBoundsException e) {}
    }

    @Test
    public void testRemove() {
        assertEquals((Integer) 65, list1.remove(0));
        assertEquals((Integer) 21, list1.get(0));
        assertEquals(2, list1.size());

        assertEquals("B", shortList.remove(1));
        assertEquals(1, shortList.size());

        try {
            emptyList.remove(0);
            fail("Removing from empty should throw exception");
        } catch (IndexOutOfBoundsException e) {}
    }

    @Test
    public void testAddEnd() {
        emptyList.add(99);
        assertEquals((Integer) 99, emptyList.get(0));
        assertEquals(1, emptyList.size());

        shortList.add("C");
        assertEquals("C", shortList.get(2));
        assertEquals(3, shortList.size());

        try {
            shortList.add(null);
            fail("Adding null should throw NullPointerException");
        } catch (NullPointerException e) {}
    }

    @Test
    public void testAddAtIndex() {
        shortList.add(0, "Z");
        assertEquals("Z", shortList.get(0));
        assertEquals("A", shortList.get(1));
        assertEquals(3, shortList.size());

        list1.add(1, 99);
        assertEquals((Integer) 99, list1.get(1));
        assertEquals((Integer) 21, list1.get(2));

        try {
            list1.add(-1, 50);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}

        try {
            list1.add(100, 50);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}

        try {
            list1.add(1, null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {}
    }

    @Test
    public void testSet() {
        String old = shortList.set(0, "Z");
        assertEquals("A", old);
        assertEquals("Z", shortList.get(0));

        int oldVal = list1.set(2, 88);
        assertEquals(42, oldVal);
        assertEquals((Integer) 88, list1.get(2));

        try {
            list1.set(-1, 10);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}

        try {
            list1.set(5, 10);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}

        try {
            list1.set(0, null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {}
    }

    @Test
    public void testSize() {
        assertEquals(2, shortList.size());
        assertEquals(3, list1.size());
        assertEquals(10, longerList.size());

        shortList.add("X");
        assertEquals(3, shortList.size());

        shortList.remove(0);
        assertEquals(2, shortList.size());
    }
}
