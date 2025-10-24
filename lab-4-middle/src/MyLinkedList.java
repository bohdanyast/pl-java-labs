import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * It's a simplified realization of usual LinkedList. It doesn't implement any interfaces, but has basic methods to work.
 * Like getFirst, getLast, addFirst, addLast and other useful methods.
 * For reference, I took methods from w3schools spreadsheet, which, to my mind would be useful and pretty popular in
 * work.
 * @param <T> any type
 */
public class MyLinkedList<T> implements MyList<T> {
    private Node<T> head; // "head" of the list
    private Node<T> tail; // "tail" of the list
    private int size; // size of list

    /**
     * Class for creating the node of the list, which will contain its data and links to previous and next nodes
     * @param <T> any type
     */
    private static class Node<T>
    {
        T data; // data of node
        Node<T> next; // next neighbor
        Node<T> prev; // previous neighbor

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds item to the end of the list
     * @param item item to add
     */
    public void add(T item) {
        Node<T> newNode = new Node<>(item);

        if (head == null) { // If head doesn't exist, newNode will be the head
            head = newNode;
        } else { // Else we give links of newNode and tail for access
            newNode.prev = tail;
            tail.next = newNode;
        }

        tail = newNode; // In both cases, tail will be the newNode
        size++;
    }

    /**
     * Adds item at a given index
     * @param index index where to add
     * @param item item to add
     * @throws IndexOutOfBoundsException if index is not in [0; size]
     */
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> newNode = new Node<>(item);

        if (index == 0) { // Handling case, when adding to beginning of list
            if (size == 0) {
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
            }
            head = newNode;
        } else if (index == size) { // Handling case when adding to the end of the list
            newNode.prev = tail;
            if (tail != null) {
                tail.next = newNode;
            }
            tail = newNode;
        } else { // Handling adding between head and tail
            Node<T> currentNode = head;
            int currentIndex = 0;
            while (currentNode != null && currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }

            newNode.next = currentNode;
            newNode.prev = currentNode.prev;

            currentNode.prev.next = newNode;
            currentNode.prev = newNode;
        }
        size++;
    }

    @Override
    public void addAll(T[] c) {
        for (T item : c) {
            add(item);
        }
    }

    @Override
    public void addAll(int index, T[] c) {
        int cLen = c.length;

        for (int i = 0; i < cLen; i++) {
            add(index + i, c[i]);
        }
    }

    /**
     * Returns the item found by index
     * @param index index of desired item
     * @return the found item
     * @throws IndexOutOfBoundsException if index is not in [0, size)
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        int currentIndex = 0;
        Node<T> currentNode = head;

        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        return currentNode.data;
    }

    /**
     * Returns the index of first occurrence of item in list
     * @param item the index of first occurrence of item in list
     * @return index of first occurrence of item, if the ladder is in the list, -1 if no.
     */
    public int indexOf(Object item) {
        int currentIndex = 0;
        Node<T> currentNode = head;

        while (currentNode != null) {
            if (Objects.equals(currentNode.data, item)) {
                return currentIndex;
            }

            currentNode = currentNode.next;
            currentIndex++;

        }

        return -1;
    }

    /**
     * Removes the element by index
     * @param index index of desired item to remove
     * @return the removed element
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        T removedItemData;
        if (index == 0) { // Handling case when removing at beginning
            if (size == 1) { // If the list has only one element
                removedItemData = head.data;
                head = null;
                tail = null;
            } else {
                removedItemData = head.data;
                head = head.next;
                head.prev.next = null;
                head.prev = null;
            }
        } else if (index == size - 1) { // Handling case when adding to end
            removedItemData = tail.data;
            tail = tail.prev;
            tail.next.prev = null;
            tail.next = null;
        } else { // Handling case when adding between head and tail
            Node<T> currentNode = head;
            int currentIndex = 0;

            while (currentNode != null && currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }

            removedItemData = currentNode.data;

            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;

            currentNode.prev = null;
            currentNode.next = null;
        }

        size--;
        return removedItemData;
    }

    /**
     * Sets a new item on the place of the previous one
     * @param index index of item
     * @param item new item
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> currentNode = head;
        int currentIndex = 0;

        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        currentNode.data = item;
    }

    /**
     * Returns the size of list
     * @return the size of list
     */
    public int size() {
        return size;
    }


    @Override
    public String toString() {
        String structure = "";
        Node<T> currentNode = head;
        int currentIndex = 0;

        while (currentNode != null) {
            String space = (currentIndex == size - 1) ? "" : ", ";
            structure += currentNode.data + space;
            currentNode = currentNode.next;
            currentIndex++;
        }

        return "[" + structure + "]";
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

}
