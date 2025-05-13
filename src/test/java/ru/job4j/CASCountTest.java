package ru.job4j;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CASCountTest {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                casCount.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                casCount.increment();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(casCount.get()).isEqualTo(1000);
    }
}