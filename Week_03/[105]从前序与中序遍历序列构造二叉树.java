//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// class Solution {
//     public TreeNode buildTree(int[] preorder, int[] inorder) {
//         int prelen=preorder.length;
//         int inlen=inorder.length;
//         if(preorder==null || inorder==null || prelen!=inlen || prelen==0){
//             return null;
//         }
//         return build(preorder,0,prelen-1,inorder,0,inlen-1);
//     }

//     //因为需要将数组分段，所以需要另写一个函数
//     //看了题解发现，修修补补果然是不需要存在的，只是边界不严谨
//     //其实基本思路是正确的，只是边界条件有问题
//     private TreeNode build(int[] preorder,int preleft,int preright,int[] inorder,int inleft,int inright){
//         if(preleft > preright || inleft > inright){
//             return null;
//         }
//         //子树根节点
//         TreeNode root=new TreeNode(preorder[preleft]);
//         //在inorder中找到这个根节点
//         //因为无序，只能遍历
//         //inindex是在inorder中root节点
//         int inindex=0;
//         for(int i=inleft;i<=inright;i++){
//             if(inorder[i]==root.val){
//                 inindex=i;
//                 break;
//             }
//         }
//         //在preorder中找到inorder中inindex-1的数，是左子树的结束
//         //没有考虑到inindex==0的情况
//         int preindex=0;
//         if(inindex==0){
//             preindex=0;
//         }else{
//             int leftend=inorder[inindex-1];
//             //leftend本来计划是左子树的右节点，但当右子树为空时，leftend是左子树根节点，这时候它的前一个才是左子树在preorder的结束点
//             if(leftend==preorder[preleft+1]){
//                 leftend=inorder[inindex-2];
//             }
//             for(int i=preleft;i<=preright;i++){
//                 if(preorder[i]==leftend){
//                     preindex=i;
//                     break;
//                 }
//             }
//         }
//         root.left=build(preorder,preleft+1,preindex,inorder,inleft,inindex-1);
//         root.right=build(preorder,preindex+1,preright,inorder,inindex+1,inright);
//         return root;
//     }
// }

//4,64.20;  40,70
// class Solution{
//     public TreeNode buildTree(int[] preorder, int[] inorder){
//         return build(preorder,0,inorder,0,inorder.length-1);
//     }

//     private TreeNode build(int[] preorder,int preStart,int[] inorder,int inStart,int inEnd){
//         if(preStart > preorder.length-1 || inStart > inEnd){
//             return null;
//         }
//         TreeNode root=new TreeNode(preorder[preStart]);
//         //找到inorder中的root
//         int inindex=0;
//         for(int i=inStart;i<=inEnd;i++){
//             if(inorder[i]==root.val){
//                 inindex=i;
//                 break;
//             }
//         }
//         //inorder的inStart~inindex-1是左子树，inindex+1～inEnd是右子树
//         root.left=build(preorder,preStart+1,inorder,inStart,inindex-1);
//         root.right=build(preorder,preStart+1+inindex-inStart,inorder,inindex+1,inEnd);
//         return root;
//     }
// }


//听说用HashMap可以节省时间
//2,97.98;  40.4,63.33
class Solution{
    public TreeNode buildTree(int[] preorder, int[] inorder){
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return build(preorder,0,inorder,0,inorder.length-1,map);
    }

    private TreeNode build(int[] preorder,int preStart,int[] inorder,int inStart,int inEnd,HashMap<Integer,Integer> map){
        if(preStart > preorder.length-1 || inStart > inEnd){
            return null;
        }
        TreeNode root=new TreeNode(preorder[preStart]);
        //找到inorder中的root
        // int inindex=0;
        // for(int i=inStart;i<=inEnd;i++){
        //     if(inorder[i]==root.val){
        //         inindex=i;
        //         break;
        //     }
        // }
        int inindex=map.get(root.val);
        //inorder的inStart~inindex-1是左子树，inindex+1～inEnd是右子树
        root.left=build(preorder,preStart+1,inorder,inStart,inindex-1,map);
        root.right=build(preorder,preStart+1+inindex-inStart,inorder,inindex+1,inEnd,map);
        return root;
    }
}


//leetcode submit region end(Prohibit modification and deletion)
