package main.java.forkjoin;

import java.util.concurrent.RecursiveTask;

public class WorkStealingTask extends RecursiveTask<Integer> {
    private final int[][] matrix;
    private final int startRow, endRow;

    public WorkStealingTask(int[][] matrix, int startRow, int endRow) {
        this.matrix = matrix;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    protected Integer compute() {
        if (endRow - startRow <= 1) { // Small enough to process directly
            return calculateMinInRows(matrix, startRow, endRow);
        }

        // Split task into smaller tasks
        int mid = (startRow + endRow) / 2;
        WorkStealingTask firstHalf = new WorkStealingTask(matrix, startRow, mid);
        WorkStealingTask secondHalf = new WorkStealingTask(matrix, mid, endRow);

        firstHalf.fork(); // Process first half asynchronously
        int secondResult = secondHalf.compute(); // Process second half synchronously
        int firstResult = firstHalf.join(); // Wait for first half to finish

        return Math.min(firstResult, secondResult); // Combine results
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