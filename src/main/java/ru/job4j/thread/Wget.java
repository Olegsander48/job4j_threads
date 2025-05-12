package ru.job4j.thread;

import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        var startAt = System.currentTimeMillis();
        var file = new File("tmp.xml");
        try (var input = new URL(url).openStream();
             var output = new FileOutputStream(file)) {
            System.out.println("Open connection: " + (System.currentTimeMillis() - startAt) + " ms");
            var dataBuffer = new byte[512];
            int bytesRead;
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                var downloadAt = System.nanoTime();
                output.write(dataBuffer, 0, bytesRead);
                long downloadTime = System.nanoTime() - downloadAt;
                System.out.println("Read " + bytesRead + " bytes : " + (downloadTime) + " nano.");
                double downloadSpeed = (double) (bytesRead * 1_000_000) / downloadTime;
                System.out.printf("Download speed %.2f bytes/ms\n", downloadSpeed);
                if (downloadSpeed > speed) {
                    Thread.sleep(Math.round((downloadSpeed / speed) - 1));
                    System.out.println("thread stopped for " + Math.round((downloadSpeed / speed) - 1) + "ms");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println(Files.size(file.toPath()) + " bytes");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        checkConsoleArguments(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

    private static void checkConsoleArguments(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected number of arguments: 2, but was: " + args.length);
        }
        UrlValidator validator = new UrlValidator();
        if (!validator.isValid(args[0])) {
            throw new IllegalArgumentException("Wrong format of url.");
        }
    }
}
