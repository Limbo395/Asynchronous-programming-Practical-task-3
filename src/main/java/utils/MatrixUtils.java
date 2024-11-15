package main.java.utils;

import java.util.Random;

public class MatrixUtils {
    public static int[][] generateMatrix(int rows, int columns, int minValue, int maxValue) {
        Random random = new Random();
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = random.nextInt(maxValue - minValue + 1) + minValue;
            }
        }
        return matrix;
    }
}