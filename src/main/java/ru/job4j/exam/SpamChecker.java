package ru.job4j.exam;

import java.util.List;
import java.util.concurrent.*;

public class SpamChecker {
    boolean isSpam(List<Filter> filters, String text) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        List<Future<Boolean>> futures = filters.stream()
                .map(filter -> executor.submit(
                        () -> filter.getKeys()
                            .stream()
                            .map(text::contains)
                            .reduce((aBoolean, aBoolean2) -> aBoolean || aBoolean2).get()))
                .toList();
        for (Future<Boolean> future : futures) {
            if (future.get()) {
                executor.shutdownNow();
                return true;
            }
        }
        return false;
    }
}
