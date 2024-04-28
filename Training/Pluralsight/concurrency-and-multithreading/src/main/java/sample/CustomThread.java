package sample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomThread extends Thread {

    @Override
    public void run() {
        logger.info("Custom thread {}", Thread.currentThread().getId());
    }
}
