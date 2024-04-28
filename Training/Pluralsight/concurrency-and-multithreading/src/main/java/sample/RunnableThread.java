package sample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            logger.info("current thread {}, i: {}", Thread.currentThread().getId(), i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
