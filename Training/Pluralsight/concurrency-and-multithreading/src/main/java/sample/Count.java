package sample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Count {

    public static int counter = 0;

    static void incrementCounter() {
        //logger.info("before {} - current thread {}", counter, Thread.currentThread().getId());
        counter++;
        logger.info("after {} - current thread {}", counter, Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        while (counter < 9) {
            Thread t1 = new Thread(Count::incrementCounter);
            t1.start();
        }
    }
}
