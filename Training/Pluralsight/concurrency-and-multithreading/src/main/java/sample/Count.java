package sample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Count {

    public static int counter = 0;
    public static final Object lock = new Object();

    private static void incrementCounter() {
        synchronized (lock) {
            logger.info("before {} - current thread {}", counter, Thread.currentThread().getId());
            counter++;
            logger.info("after {} - current thread {}", counter, Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(Count::incrementCounter);
            t1.start();
        }
    }
}