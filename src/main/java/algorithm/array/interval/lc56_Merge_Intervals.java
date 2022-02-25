package algorithm.array.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class lc56_Merge_Intervals {

    public int[][] merge(int[][] intervals) {
        List<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int[] interval : intervals) {
            int l = interval[0];
            int r = interval[1];
            if (res.size() == 0 || res.get(res.size() - 1)[1] < l) {
                res.add(new int[] {l, r});
            } else {
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1] , r);
            }
        }

        return res.toArray(new int[res.size()][0]);
    }
}
