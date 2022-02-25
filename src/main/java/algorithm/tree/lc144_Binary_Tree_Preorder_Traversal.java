package algorithm.tree;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class lc144_Binary_Tree_Preorder_Traversal {

    static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        post(result, root);
        return result;
    }

    static void pre(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        pre(result, root.left);
        pre(result, root.right);
    }

    static void mid(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        mid(result, root.left);
        result.add(root.val);
        mid(result, root.right);
    }

    static void post(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        post(result, root.left);
        post(result, root.right);
        result.add(root.val);
    }


    public static void main(String[] args) {
//        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode1 = new TreeNode(1);
//        TreeNode treeNode2 = new TreeNode(2, treeNode3, null);
//        TreeNode root = new TreeNode(1, treeNode2, null);
        List<Integer> integers = preorderTraversal(new TreeNode(3,treeNode1,treeNode2));
        System.out.println();
    }
}
