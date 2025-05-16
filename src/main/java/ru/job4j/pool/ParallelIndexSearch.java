package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T element;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T[] array, T element, int from, int to) {
        this.array = array;
        this.element = element;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            return search(array, element, from, to);
        }
        int middle = (from + to) / 2;
        ParallelIndexSearch<T> task1 = new ParallelIndexSearch<>(array, element, from, middle);
        ParallelIndexSearch<T> task2 = new ParallelIndexSearch<>(array, element, middle + 1, to);
        task1.fork();
        task2.fork();
        return task1.join() != -1 ? task1.join() : task2.join();
    }

    public Integer search() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, element, 0, array.length - 1));
    }

    public int search(T[] array, T element, int from, int to) {
        for (int i = from; i < to; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}