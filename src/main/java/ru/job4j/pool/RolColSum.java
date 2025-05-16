package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RolColSum {
    public static Sums[] sum(int[][] matrix) {
        Sums[] total = new Sums[matrix.length];
        for (int t = 0; t < total.length; t++) {
            int rowSum = 0;
            int colSum = 0;
            for (int i = 0; i < matrix.length; i++) {
                rowSum += matrix[t][i];
                colSum += matrix[i][t];
            }
            total[t] = new Sums(rowSum, colSum);
        }
        return total;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Sums[] total = new Sums[matrix.length];
        for (int t = 0; t < total.length; t++) {
            final int index = t;
            total[t] = CompletableFuture.supplyAsync(() -> {
                int rowSum = 0;
                int colSum = 0;
                for (int i = 0; i < matrix.length; i++) {
                    rowSum += matrix[index][i];
                    colSum += matrix[i][index];
                }
                return new Sums(rowSum, colSum);
            }, executor).get();
        }
        return total;
    }
}
