package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        var process = new char[]{'-', '\\', '|', '/'};
        System.out.println("Start loading ... ");
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process[index++]);
            if (index > 3) {
                index = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("\n!!Thread interrupted!!");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
