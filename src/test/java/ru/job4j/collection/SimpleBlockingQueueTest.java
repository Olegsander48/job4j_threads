package ru.job4j.collection;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimpleBlockingQueueTest {
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        SimpleBlockingQueue<String> simpleBlockingQueue = new SimpleBlockingQueue<>(5);
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    simpleBlockingQueue.poll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    simpleBlockingQueue.offer("New value");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(simpleBlockingQueue.isEmpty()).isTrue();
    }
}