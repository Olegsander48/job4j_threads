package ru.job4j.pool;

import ru.job4j.collection.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int threadCount = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(threadCount);

    public ThreadPool() {
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(() -> {
                while (!tasks.isEmpty() || !Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
        try {
            this.tasks.offer(job);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}
