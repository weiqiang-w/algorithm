package algorithm.tree;


import java.util.HashMap;

/**
 *
 */
public class lc226_Invert_Binary_Tree {

    static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }


    public static void main(String[] args) {
//        TreeNode treeNode6 = new TreeNode(3);
//        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(7);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(6,treeNode3,treeNode4);
        TreeNode treeNode1 = new TreeNode(4,null,null);
        TreeNode treeNode = new TreeNode(5, treeNode1, treeNode2);
        boolean validBST = isValidBST(treeNode);
        System.out.println();
    }

    static boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        boolean flag = true;
        TreeNode l =  root.left;
        TreeNode r = root.right;
        if(r!=null){
            flag = root.val < r.val;
        }
        if(l!=null){
            flag = l.val < root.val;
        }
        if(!flag){
            return flag;
        }

        return isValidBST(root.right) && isValidBST(root.left);
    }
}
