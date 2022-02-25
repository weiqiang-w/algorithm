package algorithm.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class lc1991_Find_the_Middle_Index_in_Array {

    static int pivotIndex(int[] nums) {
        int sum = 0;
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (2 * leftSum + nums[i] == sum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] ints = {1, 7, 3, 6, 5, 6};
        int is = pivotIndex(ints);
        System.out.println(is);
    }
}
