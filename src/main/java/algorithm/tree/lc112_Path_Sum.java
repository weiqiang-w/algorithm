package algorithm.tree;

/**
 *
 */
public class lc112_Path_Sum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.val == targetSum) {
            return true;
        }
        targetSum = targetSum - root.val;
        boolean b = hasPathSum(root.left, targetSum);
        boolean b1 = hasPathSum(root.right, targetSum);
        return b || b1;
    }
}
