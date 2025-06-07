package ru.job4j.exam;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpamChecker {
    boolean isSpam(List<Filter> filters, String text) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        for (Filter filter : filters) {
            if (executor.submit(() -> filter.getKeys()
                    .stream()
                    .map(text::contains)
                    .reduce((aBoolean, aBoolean2) -> aBoolean || aBoolean2))
                    .get()
                    .get()) {
                executor.shutdownNow();
                return true;
            }
        }
        return false;
    }
}
