package ru.job4j.pool;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParallelIndexSearchTest {
    @Test
    public void whenSearchNasaThenIndex4() {
        String[] strings = {"F", ".", ",", "gh", "nasa", "yu", "me", "i", "7"};
        ParallelIndexSearch<String> indexSearch = new ParallelIndexSearch<>(strings, "nasa", 0, strings.length);
        assertThat(indexSearch.search()).isEqualTo(4);
    }

    @Test
    public void whenSearchOlegThenIndex11() {
        String[] strings = {"F", ".", ",", "gh", "nasa", "yu", "me", "i", "7", "2345", "sasha", "oleg", "m5", "nasa", "p"};
        ParallelIndexSearch<String> indexSearch = new ParallelIndexSearch<>(strings, "oleg", 0, strings.length);
        assertThat(indexSearch.search()).isEqualTo(11);
    }

    @Test
    public void whenSearchM5ThenIndex12() {
        String[] strings = {"F", ".", ",", "gh", "nasa", "yu", "me", "i", "7", "2345", "sasha", "oleg", "m5", "nasa", "p",
                            "Ferrari", ".exe", ", ", "google", "mac", "kfc", "you", "i9", "RX7", "23-45-57", "Anatoliy",
                            "RR", "e63s", "m5", "m5", "www", ".rar", "Q", "67", "|||", "@", "$$$$$"};
        ParallelIndexSearch<String> indexSearch = new ParallelIndexSearch<>(strings, "m5", 0, strings.length);
        assertThat(indexSearch.search()).isEqualTo(12);
    }

    @Test
    public void whenSearchQThenIndex32() {
        String[] strings = {"F", ".", ",", "gh", "nasa", "yu", "me", "i", "7", "2345", "sasha", "oleg", "m5", "nasa", "p",
                            "Ferrari", ".exe", ", ", "google", "mac", "kfc", "you", "i9", "RX7", "23-45-57", "Anatoliy",
                            "RR", "e63s", "m5", "m5", "www", ".rar", "Q", "67", "|||", "@", "$$$$$"};
        ParallelIndexSearch<String> indexSearch = new ParallelIndexSearch<>(strings, "Q", 0, strings.length);
        assertThat(indexSearch.search()).isEqualTo(32);
    }

    @Test
    public void whenSearchAAAThenIndexMinus1() {
        String[] strings = {"F", ".", ",", "gh", "nasa", "yu", "me", "i", "7", "2345", "sasha", "oleg", "m5", "nasa", "p",
                            "Ferrari", ".exe", ", ", "google", "mac", "kfc", "you", "i9", "RX7", "23-45-57", "Anatoliy",
                            "RR", "e63s", "m5", "m5", "www", ".rar", "Q", "67", "|||", "@", "$$$$$"};
        ParallelIndexSearch<String> indexSearch = new ParallelIndexSearch<>(strings, "AAA", 0, strings.length);
        assertThat(indexSearch.search()).isEqualTo(-1);
    }

    @Test
    public void whenSearch4444444ThenIndex33() {
        Integer[] nums = {1, 67, 45, 3, 90, 1234, 5, 56, 333, 789, 0, 103, 605, 4007, 6008, 12344, 51, 12, 456, 340, 5673,
                      3, 7777, 312, 45, 76454, 2342, 23423, 7518, 178253, 818453, 82457, 82554, 4444444, 3000000, 11,
                      134, 845643};
        ParallelIndexSearch<Integer> indexSearch = new ParallelIndexSearch<>(nums, 4444444, 0, nums.length);
        assertThat(indexSearch.search()).isEqualTo(33);
    }

    @Test
    public void whenSearch1000000ThenIndexMinus1() {
        Integer[] nums = {1, 67, 45, 3, 90, 1234, 5, 56, 333, 789, 0, 103, 605, 4007, 6008, 12344, 51, 12, 456, 340, 5673,
                3, 7777, 312, 45, 76454, 2342, 23423, 7518, 178253, 818453, 82457, 82554, 4444444, 3000000, 11,
                134, 845643};
        ParallelIndexSearch<Integer> indexSearch = new ParallelIndexSearch<>(nums, 1000000, 0, nums.length);
        assertThat(indexSearch.search()).isEqualTo(-1);
    }

    @Test
    public void whenSearch1000000ThenIndex27() {
        Double[] nums = {1.24, 67.4, 45.2, 3.3, 90.009, 1234.56, 5.67, 56.9, 333.12, 789.0, 0.3428, 103.345, 605.00009,
                4007.452, 6008.111, 12344.123123123123, 51.56, 12.3408, 456.678, 340.34, 5673.78435, 3.313, 7777.6,
                312.67234, 45.743, 76454.99, 2342.089, 23423.3455, 7518.555, 178253.12, 818453.234, 82457.675, 82554.342};
        ParallelIndexSearch<Double> indexSearch = new ParallelIndexSearch<>(nums, 23423.3455, 0, nums.length);
        assertThat(indexSearch.search()).isEqualTo(27);
    }

    @Test
    public void whenSearch1000point0ThenIndexMinus1() {
        Double[] nums = {1.24, 67.4, 45.2, 3.3, 90.009, 1234.56, 5.67, 56.9, 333.12, 789.0, 0.3428, 103.345, 605.00009,
                4007.452, 6008.111, 12344.123123123123, 51.56, 12.3408, 456.678, 340.34, 5673.78435, 3.313, 7777.6,
                312.67234, 45.743, 76454.99, 2342.089, 23423.3455, 7518.555, 178253.12, 818453.234, 82457.675, 82554.342};
        ParallelIndexSearch<Double> indexSearch = new ParallelIndexSearch<>(nums, 1000.0, 0, nums.length);
        assertThat(indexSearch.search()).isEqualTo(-1);
    }

}