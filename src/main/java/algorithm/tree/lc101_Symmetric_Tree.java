package algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class lc101_Symmetric_Tree {
    static boolean isSymmetric(TreeNode root) {
        return isSymmetric1(root, root);
    }

    static boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }

    static boolean isSymmetric1(TreeNode u, TreeNode v) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(u);
        queue.offer(v);
        while (!queue.isEmpty()) {
            u = queue.poll();
            v = queue.poll();
            if (u == null && v == null) {
                continue;
            }
            if (u == null || v == null || u.val != v.val) {
                return false;
            }
            queue.offer(u.left);
            queue.offer(v.right);

            queue.offer(u.right);
            queue.offer(v.left);
        }
        return true;
    }


    public static void main(String[] args) {
        TreeNode treeNode6 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, treeNode5, treeNode6);
        TreeNode treeNode1 = new TreeNode(2, treeNode3, treeNode4);
        TreeNode treeNode = new TreeNode(1, treeNode1, treeNode2);
        boolean symmetric = isSymmetric(treeNode);
        System.out.println(symmetric);
    }
}