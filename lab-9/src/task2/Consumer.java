package task2;

public class Consumer implements Runnable {
    private final CircularBuffer<String> circularBuffer1;
    private final CircularBuffer<String> circularBuffer2;
    private final int id;

    public Consumer(CircularBuffer<String> circularBuffer1, CircularBuffer<String> circularBuffer2, int id) {
        this.circularBuffer1 = circularBuffer1;
        this.circularBuffer2 = circularBuffer2;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String message = circularBuffer1.remove();
                String translatedMessage = "Потік № " + id + " переклав повідомлення " + message + "\n";
                circularBuffer2.add(translatedMessage);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
