import java.util.Arrays;
import java.util.Objects;
import java.util.RandomAccess;


/**
 * This class is a simplified version of usual Arraylist.
 * It has much fewer methods, because most of them are not very popular to use among us, beginners.
 * In fact, it does the basic things we need from ArrayList, like set(), get(), size(), add(), remove(), etc.
 * @param <T> objects of any type
 */
public class MyArrayList<T> implements MyList<T>, RandomAccess {
    private Object[] list; // Array to keep every item
    private int size; // Its size
    private static final int START_ARRAYLIST_SIZE = 10; // Start size
    private static final int ARRAYLIST_SIZE_INCREASING_COEFFICIENT = 2; // Coefficient for resizing

    public MyArrayList() {
        // Setting size and an array
        list = new Object[START_ARRAYLIST_SIZE];
        size = 0;
    }

    /**
     * Function needed for resizing the array.
     * If we have to add more items than current size, then we double the size, but copy all the values into new array,
     * keeping the size, so we won't get null at isEmpty items.
     */
    private void resize() {
        // Setting a new array of doubled size
        Object[] copyList = new Object[list.length * ARRAYLIST_SIZE_INCREASING_COEFFICIENT];

        // Copying the elements into new list, keeping the size and setting it as main
        System.arraycopy(list, 0, copyList, 0, size);
        list = copyList;
    }

    /**
     * Adds item to the end of the list
     * @param item item to add
     */
    @Override
    public void add(T item) {
        // Resizing if size is not enough
        if (list.length == size) {
            resize();
        }
        list[size++] = item;
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
     * Adds item to the list at the given index
     * @param index index where to add
     * @param item item to add
     * @throws IndexOutOfBoundsException if the index is not in range of [0; size]
     */
    @Override
    public void add(int index, T item) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        // Adding 1 to size for resizing
        size++;
        resize();

        // Moving all the elements, starting at index, on 1 step on right
        for (int i = size; i > index; i--) {
            Object temp = list[i];
            list[i] = list[i-1];
            list[i-1] = temp;
        }

        list[index] = item;
    }

    /**
     * Returns an item by index
     * @param index index where to search the item
     * @return an item at given index
     * @throws IndexOutOfBoundsException if the index is not in range of [0; size)
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return (T) list[index];
    }

    /**
     * Returns the index of first appearance of item in list
     * @param item item to find first appearance of
     * @return index of first appearance of item
     */
    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(list[i], item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes an item from list by given index
     * @param index index of removing
     * @return deleted item
     * @throws IndexOutOfBoundsException if the index is not in range of [0; size)
     */
    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        Object deletedObj = list[index];
        list[index] = null;

        for (int i = index; i < size; i++) {
            list[i] = list[i+1];
        }

        size--;
        return (T) deletedObj;
    }

    /**
     * Sets another item at a given index, instead of a previous one
     * @param index index where to set
     * @param item item to set
     * @throws IndexOutOfBoundsException if the index is not in range of [0; size)
     */
    @Override
    public void set(int index, T item) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        list[index] = item;
    }

    /**
     * Returns the size of array
     * @return size of array
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String listStr = "";
        for(int i = 0; i < size; i++) {
            String space = (i == size - 1) ? "" : ", ";
            listStr += list[i] + space;
        }
        return "[" + listStr + "]";
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(list, size);
    }
}
