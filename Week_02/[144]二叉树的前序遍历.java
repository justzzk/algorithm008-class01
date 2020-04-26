//给定一个二叉树，返回它的 前序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树


//leetcode submit region begin(Prohibit modification and deletion)

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {

    public ArrayList<Integer> array=new ArrayList<>();
    //递归
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return array;
        array.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return array;
    }

    //迭代
    public List<Integer> preorderTraversal(TreeNode root){
        //迭代
        //1,54.08;  38.2,6.38
        ArrayList<Integer> res=new ArrayList<>();
        if(root==null) {
            return res;
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        TreeNode cur;
        while(!stack.isEmpty()){
            cur=stack.pop();
            res.add(cur.val);
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }
        }
        return res;
    }
//
//    //Morris
//    public static void preorderTraversal(TreeNode head) {
//        if (head == null) {
//            return;
//        }
//        TreeNode cur1 = head;
//        TreeNode cur2 = null;
//        while (cur1 != null) {
//            cur2 = cur1.left;
//            if (cur2 != null) {
//                while (cur2.right != null && cur2.right != cur1) {
//                    cur2 = cur2.right;
//                }
//                if (cur2.right == null) {
//                    cur2.right = cur1;
//                    System.out.print(cur1.val + " ");
//                    cur1 = cur1.left;
//                    continue;
//                } else {
//                    cur2.right = null;
//                }
//            } else {
//                System.out.print(cur1.val + " ");
//            }
//            cur1 = cur1.right;
//        }
//    }
//    //另一个Morris
//    public List<Integer> preorderTraversal(TreeNode root) {
//        LinkedList<Integer> output = new LinkedList<>();
//
//        TreeNode node = root;
//        while (node != null) {
//            if (node.left == null) {
//                output.add(node.val);
//                node = node.right;
//            }
//            else {
//                TreeNode predecessor = node.left;
//                while ((predecessor.right != null) && (predecessor.right != node)) {
//                    predecessor = predecessor.right;
//                }
//
//                if (predecessor.right == null) {
//                    output.add(node.val);
//                    predecessor.right = node;
//                    node = node.left;
//                }
//                else{
//                    predecessor.right = null;
//                    node = node.right;
//                }
//            }
//        }
//        return output;
//    }

}

//leetcode submit region end(Prohibit modification and deletion)
