//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
//class Solution {
//    // Definition for a Node.
//    class Node {
//        public int val;
//        public List<Node> children;
//
//        public Node() {}
//
//        public Node(int _val) {
//            val = _val;
//        }
//
//        public Node(int _val, List<Node> _children) {
//            val = _val;
//            children = _children;
//        }
//    };
//    //执行用时 :1 ms, 在所有 Java 提交中击败了99.50%的用户
//    //内存消耗 :41.4 MB, 在所有 Java 提交中击败了5.07%的用户
//    ArrayList<Integer> array = new ArrayList<>();
//    public List<Integer> preorder(Node root) {
//        if(root==null) return array;
//        array.add(root.val);
//        for (Node node: root.children) {
//            preorder(node);
//        }
//        return array;
//    }
//
//}

//1,98.72;  40.7,16.67
// class Solution {
//     public List<Integer> preorder(Node root) {
//         ArrayList<Integer> res=new ArrayList<>();
//         narypre(root,res);
//         return res;
//     }

//     private void narypre(Node root,List<Integer> res){
//         if(root==null){
//             return;
//         }
//         res.add(root.val);
//         for(Node node:root.children){
//             narypre(node,res);
//         }
//     }
// }

//5,21.59;  40.6,16.67
class Solution{
    //非递归
    public List<Integer> preorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            //逆序是因为stack是先进后出，要实现所以子节点从左到右必须逆序入栈
            //不过这样会改变树结构
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
