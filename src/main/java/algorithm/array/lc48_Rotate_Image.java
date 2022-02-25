package algorithm.array;

/**
 *
 */
public class lc48_Rotate_Image {
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = matrix[length - i - 1][j];
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = matrix[length - i - 1][length - j - 1];
            }
        }
    }
}
