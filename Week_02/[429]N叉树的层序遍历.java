//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索


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


class Solution {
    // 4,60.45;  40.6,75
    // public List<List<Integer>> levelOrder(Node root) {
    //     List<List<Integer>> res=new ArrayList<>();
    //     if(root==null){
    //         return res;
    //     }
    //     ArrayDeque<Node> queue=new ArrayDeque<>();
    //     int count=0;
    //     Node cur;
    //     queue.addLast(root);
    //     while(!queue.isEmpty()){
    //         count=queue.size();
    //         List<Integer> levlist=new ArrayList<>();
    //         for (int i = 0; i < count; i++) {
    //             cur=queue.pollFirst();
    //             levlist.add(cur.val);
    //             // for (Node node:cur.children){
    //             //     queue.addLast(node);
    //             // }
    //             queue.addAll(cur.children);
    //         }
    //         res.add(levlist);
    //     }
    //     return res;
    // }

    //递归
    //1，100；  40.6，75
//    private List<List<Integer>> res=new ArrayList<>();
//    public List<List<Integer>> levelOrder(Node root) {
//        if(root!=null){
//            traverseByRe(root,0);
//        }
//        return res;
//    }
//
//    private void traverseByRe(Node root,int level){
//        if(res.size()<=level){
//            res.add(new ArrayList<>());
//        }
//        //将该节点加入所属层的队列
//        res.get(level).add(root.val);
//        //遍历子节点
//        //没有子节点也就不会进入迭代从而调用traverseNode
//        //所以不需要在traverseNode中进行null判断
//        for(Node node:root.children){
//            traverseByRe(node,level+1);
//        }
//    }

    //另一种形式的迭代
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<Node> previousLayer = Arrays.asList(root);

        while (!previousLayer.isEmpty()) {
            List<Node> currentLayer = new ArrayList<>();
            List<Integer> previousVals = new ArrayList<>();
            for (Node node : previousLayer) {
                previousVals.add(node.val);
                currentLayer.addAll(node.children);
            }
            result.add(previousVals);
            previousLayer = currentLayer;
        }

        return result;
    }
}



//leetcode submit region end(Prohibit modification and deletion)
