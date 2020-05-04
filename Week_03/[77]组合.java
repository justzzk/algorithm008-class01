//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//最基本的递归
//96，7.75；  42.6，7.41
// class Solution {

//     private List<List<Integer>> res=new LinkedList<>();

//     public List<List<Integer>> combine(int n, int k) {
//         combination(new LinkedList<Integer>(),n,k,0);
//         return res;
//     }

//     //这个先根据分层来做试试
//     //参数含义：list是保存递归组合结果，n最大值，k最大层数，level本层层数
//     private void combination(LinkedList<Integer> list,int n,int k,int level){
//         //当层数达到最大层数k，添加并退出
//         if(level==k){
//             res.add(list);
//             return ;
//         }

//         //保存传入的list状态，用于再循环中恢复
//         LinkedList<Integer> tmp=list;
//         //否则循环添加新的数字，
//         int start=(list.isEmpty()?0:list.peekLast());
//         for(;start < n;start++){
//             LinkedList<Integer> newlist=new LinkedList<>(tmp);
//             newlist.add(start+1);
//             combination(newlist,n,k,level+1);
//         }
//     }
// }

//优化的递归，
//2，99.13；  43.1，7.41
//主要优化点在如何处理list赋值是引用的问题以及如何保存for中list的状态以及减少for循环的次数
class Solution{
    public List<List<Integer>> combine(int n, int k){
        List<List<Integer>> res=new LinkedList<>();
        combination(res,new LinkedList<Integer>(), n, k, 1);
        return res;
    }

    //使用k做层次标识
    private void combination(List<List<Integer>> res,
                             LinkedList<Integer> comb,
                             int n,
                             int k,
                             int start){
        if(k==0){
            //这里注意是创建一个新的list，如果直接add(list),就是添加了引用
            res.add(new LinkedList<>(comb));
            return;
        }
        //这里不需要用tmp保存状态
        //并且起点直接使用参数，不用重新获取
        //这里将最后一个添加的i，n-k+1，比如n=4，k=2，第一层只能添加1、2、3，4不能添加到第一层，因为第二层要加下一个数，而4是最后一个
        for(int i=start; i<=n-k+1; i++){
            comb.add(i);
            //注意这里的start不是start+1，因为本层的start一直是1，而每次循环的start要根据i变化
            // combination(res,comb,n,k-1,start+1);
            combination(res,comb,n,k-1,i+1);
            //这里通过删除上两步add的数字实现状态返回
            comb.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
