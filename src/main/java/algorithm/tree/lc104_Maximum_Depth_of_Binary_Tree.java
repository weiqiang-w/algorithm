package algorithm.tree;

/**
 */
public class lc104_Maximum_Depth_of_Binary_Tree {
    static int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }else {
            return Math.max(maxDepth(root.right),maxDepth(root.left)) + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(Math.min(1,1));
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, treeNode5, treeNode6);
        TreeNode treeNode1 = new TreeNode(1, treeNode3, treeNode4);
        TreeNode treeNode = new TreeNode(0, treeNode1, treeNode2);
        System.out.println(maxDepth(treeNode));
    }


}
