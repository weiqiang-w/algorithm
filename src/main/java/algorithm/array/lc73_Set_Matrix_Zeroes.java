package algorithm.array;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 */
public class lc73_Set_Matrix_Zeroes {
    static void setZeroes(int[][] matrix) {
        int[] x = new int[matrix.length];
        int[] y = new int[matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j< matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    x[i] = 1;
                    y[j] = 1;
                }
            }
        }

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(x[i] > 0 || y[j] > 0){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 2, 0},
                {1, 4, 1 ,3},
                {1, 1, 1, 1}
        };
        int[][] matrix1 = {
                {0, 1},
        };
        setZeroes(matrix);
        System.out.println();
    }
}
