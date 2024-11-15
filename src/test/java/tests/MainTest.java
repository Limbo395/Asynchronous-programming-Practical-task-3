package test.java.tests;

import main.java.forkjoin.WorkDealingTask;
import main.java.forkjoin.WorkStealingTask;
import main.java.utils.MatrixUtils;
import main.java.utils.TimerUtils;

import java.util.concurrent.ForkJoinPool;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("Running Integration Test...");

        // Генерація тестової матриці
        int[][] matrix = MatrixUtils.generateMatrix(100, 100, 1, 100);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        // Work Stealing
        WorkStealingTask stealingTask = new WorkStealingTask(matrix, 0, matrix.length);
        long stealingTime = TimerUtils.measure(() -> pool.invoke(stealingTask));
        int stealingResult = stealingTask.join();
        System.out.println("Minimum value (Work Stealing): " + stealingResult);
        System.out.println("Execution time (Work Stealing): " + stealingTime + " ns");

        // Work Dealing
        WorkDealingTask dealingTask = new WorkDealingTask(matrix, 0, matrix.length);
        long dealingTime = TimerUtils.measure(() -> pool.invoke(dealingTask));
        int dealingResult = dealingTask.join();
        System.out.println("Minimum value (Work Dealing): " + dealingResult);
        System.out.println("Execution time (Work Dealing): " + dealingTime + " ns");

        System.out.println("Integration Test Complete.");
    }
}