//给定一个二叉树，返回它的中序 遍历。 
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
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
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
class Solution {

    //递归
    ArrayList<Integer> array=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null) return array;
        inorderTraversal(root.left);
        array.add(root.val);
        inorderTraversal(root.right);
        return array;
    }


    //迭代
    public List<Integer> inorderTraversal(TreeNode root) {
        //迭代
        //1,55.07; 37.6,5.79
        ArrayList<Integer> res=new ArrayList<>();
        if(root==null) {
            return res;
        }
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        while(cur!=null || !stack.isEmpty()){
            //先把cur的左节点全部入栈
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            cur=stack.pop();
            //作为中间节点取出
            res.add(cur.val);
            //每次取出一个节点都是作为中间节点取出，接下来迭代这个节点的right
            cur=cur.right;
        }
        return res;
    }

    //Morris（扩展）
    public static void inOrderMorris(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            //构建连接线
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            System.out.print(cur1.val + " ");
            cur1 = cur1.right;
        }
    }
}


//leetcode submit region end(Prohibit modification and deletion)
