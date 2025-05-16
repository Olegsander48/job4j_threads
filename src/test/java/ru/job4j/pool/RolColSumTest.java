package ru.job4j.pool;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class RolColSumTest {

    @Test
    public void whenSyncCountSum() {
        int[][] matrix = {{1, 2, 3, 4},
                          {2, 1, 3, 4},
                          {3, 4, 1, 2},
                          {2, 3, 5, 4}};
        Sums[] expected = {new Sums(10, 8), new Sums(10, 10),
                            new Sums(10, 12), new Sums(14, 14)};
        assertThat(RolColSum.sum(matrix)).isEqualTo(expected);
    }

    @Test
    public void whenASyncCountSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3, 4},
                {2, 1, 3, 4},
                {3, 4, 1, 2},
                {2, 3, 5, 4}};
        Sums[] expected = {new Sums(10, 8), new Sums(10, 10),
                new Sums(10, 12), new Sums(14, 14)};
        assertThat(RolColSum.asyncSum(matrix)).isEqualTo(expected);
    }

}