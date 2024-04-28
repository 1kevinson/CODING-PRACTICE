package sample;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class CallThread implements Callable<Object> {

    @Override
    public Object call() {
        logger.info("in callable thread");
        return new Object();
    }
}
