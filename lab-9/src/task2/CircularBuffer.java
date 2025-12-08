package task2;

import java.util.*;

class CircularBuffer<T> {
    private final List<T> buffer;
    private int head = 0;
    private int tail = 0;
    private final int size;

    public CircularBuffer(int size) {
        this.size = size;
        buffer = new ArrayList<>(Collections.nCopies(size, null));
    }

    public synchronized void add(T item) throws InterruptedException {
        while ((tail + 1) % size == head) {
            wait();
        }
        buffer.set(tail, item);
        tail = (tail + 1) % size;
        notifyAll();
    }

    public synchronized T remove() throws InterruptedException {
        while (head == tail) {
            wait();
        }
        T item = buffer.get(head);
        head = (head + 1) % size;
        notifyAll();
        return item;
    }
}