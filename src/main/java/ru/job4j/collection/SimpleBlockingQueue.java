package ru.job4j.collection;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int total;

    public SimpleBlockingQueue(int total) {
        this.total = total;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() >= total) {
            wait();
        }
        queue.offer(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T element = queue.poll();
        notifyAll();
        return element;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
