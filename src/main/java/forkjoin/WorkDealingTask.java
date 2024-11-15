package main.java.forkjoin;

import java.util.concurrent.RecursiveTask;

public class WorkDealingTask extends RecursiveTask<Integer> {
    private final int[][] matrix;
    private final int startRow, endRow;

    public WorkDealingTask(int[][] matrix, int startRow, int endRow) {
        this.matrix = matrix;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    protected Integer compute() {
        if (endRow - startRow <= 1) {
            return calculateMinInRows(matrix, startRow, endRow);
        }

        // Divide tasks equally
        int mid = (startRow + endRow) / 2;
        WorkDealingTask leftTask = new WorkDealingTask(matrix, startRow, mid);
        WorkDealingTask rightTask = new WorkDealingTask(matrix, mid, endRow);

        invokeAll(leftTask, rightTask); // Execute both tasks in parallel

        return Math.min(leftTask.join(), rightTask.join()); // Combine results
    }

    private int calculateMinInRows(int[][] matrix, int startRow, int endRow) {
        int min = Integer.MAX_VALUE;
        for (int i = startRow; i < endRow; i++) {
            for (int value : matrix[i]) {
                if (value < min) {
                    min = value;
                }
            }
        }
        return min;
    }
}