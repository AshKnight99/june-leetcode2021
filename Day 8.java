/*
Construct Binary Tree from Preorder and Inorder Traversal
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]
 

Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] P, int[] I) {
        Map<Integer, Integer> M = new HashMap<>();
        for (int i = 0; i < I.length; i++)
            M.put(I[i], i);
        return splitTree(P, I, M, 0, 0, I.length - 1);
    }
    
    private TreeNode splitTree(int[] P, int[] I, Map<Integer, Integer> M, int pix, int ileft, int iright) {
        int rval = P[pix], imid = M.get(rval);
        TreeNode root = new TreeNode(rval);            
        if (imid > ileft)
            root.left = splitTree(P, I, M, pix + 1, ileft, imid - 1);
        if (imid < iright)
            root.right = splitTree(P, I, M, pix+imid-ileft + 1, imid + 1, iright);
        return root;
    }
}