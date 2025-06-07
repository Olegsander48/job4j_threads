package ru.job4j.exam;

import java.util.List;

public class Filter {
    private List<String> keys;

    public Filter(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getKeys() {
        return keys;
    }
}
