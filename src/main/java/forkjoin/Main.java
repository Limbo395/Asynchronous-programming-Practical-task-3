package main.java.forkjoin;

import main.java.utils.MatrixUtils;
import  main.java.utils.TimerUtils;

import java.util.concurrent.ForkJoinPool;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter matrix size (rows and columns):");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        System.out.println("Enter value range (min and max):");
        int minValue = scanner.nextInt();
        int maxValue = scanner.nextInt();

        int[][] matrix = MatrixUtils.generateMatrix(rows, columns, minValue, maxValue);

        ForkJoinPool pool = ForkJoinPool.commonPool();

        // Work Stealing
        WorkStealingTask stealingTask = new WorkStealingTask(matrix, 0, rows);
        long stealingTime = TimerUtils.measure(() -> pool.invoke(stealingTask));
        System.out.println("Minimum value (Work Stealing): " + stealingTask.join());
        System.out.println("Execution time (Work Stealing): " + stealingTime + " ns");

        // Work Dealing
        WorkDealingTask dealingTask = new WorkDealingTask(matrix, 0, rows);
        long dealingTime = TimerUtils.measure(() -> pool.invoke(dealingTask));
        System.out.println("Minimum value (Work Dealing): " + dealingTask.join());
        System.out.println("Execution time (Work Dealing): " + dealingTime + " ns");

        scanner.close();
    }
}