package algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 */
public class lc350_Intersection_Of_Two_Arrays_2 {
    static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            int num = map.getOrDefault(i, 0) + 1;
            map.put(i, num);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int i : nums2) {
            int num = map.getOrDefault(i, 0);
            if (num > 0) {
                intersection[index++] = i;
                num--;
                if (num > 0) {
                    map.put(i, num);
                } else {
                    map.remove(i);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,9,5};
        int[] nums2 = new int[]{9,4,9,8,4};
        int[] intersect = intersect(nums1, nums2);
        System.out.println();

    }
}
