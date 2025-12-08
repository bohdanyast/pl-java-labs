package task2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CircularBuffer<String> buffer1 = new CircularBuffer<>(10);
        CircularBuffer<String> buffer2 = new CircularBuffer<>(10);

        for (int i = 0; i < 5; i++) {
            Thread producer = new Thread(new Producer(buffer1, i + 1));
            producer.setDaemon(true);
            producer.start();
        }

        for (int i = 0; i < 2; i++) {
            Thread translator = new Thread(new Consumer(buffer1, buffer2, i + 1));
            translator.setDaemon(true);
            translator.start();
        }
        
        for (int i = 0; i < 100; i++) {
            String message = buffer2.remove();
            System.out.println(message);
        }
    }


}