package test.java.tests;

import main.java.forkjoin.WorkDealingTask;
import main.java.utils.MatrixUtils;
import main.java.utils.TimerUtils;

import java.util.concurrent.ForkJoinPool;

public class WorkDealingTest {
    public static void main(String[] args) {
        System.out.println("Running Work Dealing Test...");

        // Генерація тестової матриці
        int[][] matrix = MatrixUtils.generateMatrix(100, 100, 1, 100);

        // Використання ForkJoinPool
        ForkJoinPool pool = ForkJoinPool.commonPool();
        WorkDealingTask task = new WorkDealingTask(matrix, 0, matrix.length);

        // Замір часу виконання
        long executionTime = TimerUtils.measure(() -> pool.invoke(task));
        int result = task.join();

        // Результати
        System.out.println("Minimum value (Work Dealing): " + result);
        System.out.println("Execution time: " + executionTime + " ns");
    }
}