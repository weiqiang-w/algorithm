package algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 */
public class lc102_Binary_Tree_Level_Order_Traversal {

    static List<List<Integer>> result = new ArrayList<>();

    static List<List<Integer>> levelOrder(TreeNode root) {
//        dfs(root, 0);
        bfs(root);
        return result;
    }

    static void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (result.size() == level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    static void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelCount; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, treeNode5, treeNode6);
        TreeNode treeNode1 = new TreeNode(1, treeNode3, treeNode4);
        TreeNode treeNode = new TreeNode(0, treeNode1, treeNode2);
        List<List<Integer>> lists = levelOrder(treeNode);
        System.out.println();
    }

}
