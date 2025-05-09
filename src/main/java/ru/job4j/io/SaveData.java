package ru.job4j.io;

import java.io.*;

public class SaveData {
    private final File file;

    public SaveData(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void saveContent(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
