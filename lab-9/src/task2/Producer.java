package task2;

public class Producer implements Runnable {
    private final CircularBuffer<String> circularBuffer;
    private final int id;

    public Producer(CircularBuffer<String> buffer, int id) {
        this.circularBuffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                String message = "Потік № " + id + " згенерував повідомлення " + (i + 1) + "\n";
                circularBuffer.add(message);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
