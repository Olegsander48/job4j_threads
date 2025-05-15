package ru.job4j.pool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;

class ThreadPoolTest {
    @Test
    public void whenCreate30Jobs() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < 30; i++) {
            threadPool.work(counter::incrementAndGet);
        }
        Thread.sleep(5000);
        threadPool.shutdown();
        assertEquals(30, counter.get());
    }
}