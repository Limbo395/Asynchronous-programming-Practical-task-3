package main.java.utils;

public class TimerUtils {
    public static long measure(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        return System.nanoTime() - startTime;
    }
}