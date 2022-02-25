package algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 「双指针」，当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，
 * 那么就可以使用双指针的方法，将枚举的时间复杂度从 O(N^2) 减少至 O(N)
 */
public class lc15_Three_Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            //和上次数不相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + i;
            int k = length - 1;

            if (nums[i] + nums[j] + nums[k] == 0) {
                result.add(Arrays.asList(i, j, k));
            }

        }

        return result;
    }
}