package sample;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableThread());
        thread.start();

        try {
            thread.join(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread thread2 = new Thread(new RunnableThread());
        thread2.start();
    }
}
