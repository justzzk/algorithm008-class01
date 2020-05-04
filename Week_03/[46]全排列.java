//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//这种方法也是回溯，但是做的操作不是添加元素，是交换位置
//1，99.82；  40.5，7.32
// class Solution {
//   public void backtrack(int n,
//                         ArrayList<Integer> output,
//                         List<List<Integer>> res,
//                         int first) {
//     // 所有数都填完了
//     if (first == n)
//       res.add(new ArrayList<Integer>(output));
//     for (int i = first; i < n; i++) {
//       // 动态维护数组
//       Collections.swap(output, first, i);
//       // 继续递归填下一个数
//       backtrack(n, output, res, first + 1);
//       // 撤销操作
//       Collections.swap(output, first, i);
//     }
//   }

//   public List<List<Integer>> permute(int[] nums) {
//     List<List<Integer>> res = new LinkedList();

//     ArrayList<Integer> output = new ArrayList<Integer>();
//     for (int num : nums)
//       output.add(num);

//     int n = nums.length;
//     backtrack(n, output, res, 0);
//     return res;
//   }
// }


//基本的递归回溯
//使用LinkedList：3，44.4； 40.7，7.32
//也有2，80.66；  40.4，7.32
// class Solution{
//     public List<List<Integer>> permute(int[] nums){
//         List<List<Integer>> res=new LinkedList<>();
//         boolean[] visited=new boolean[nums.length];
//         permutation(res,new LinkedList<Integer>(),nums,visited,0);
//         return res;
//     }

//     //参数含义：
//     //res：返回的结果列表
//     //perm：一条结果
//     //nums：可取元素数组
//     //visited：已访问数组
//     //level：层数
//     private void permutation(List<List<Integer>> res,LinkedList<Integer> perm,int[] nums,boolean[] visited,int level){
//         if(level==nums.length){
//             res.add(new LinkedList<Integer>(perm));
//         }
//         //使用循环逐个添加元素
//         for(int i=0;i<nums.length;i++){
//             //如果之前已访问/已添加，则跳过
//             if(visited[i]){
//                 continue;
//             }
//             perm.add(nums[i]);
//             visited[i]=true;
//             //递归
//             permutation(res,perm,nums,visited,level+1);
//             //回溯
//             visited[i]=false;
//             perm.removeLast();
//         }
//     }
// }


//dont use level
class Solution{

    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res=new LinkedList<>();
        boolean[] visited=new boolean[nums.length];
        permutation(res,new LinkedList<Integer>(),nums,visited);
        return res;
    }

    //参数含义：
    //res：返回的结果列表
    //perm：一条结果
    //nums：可取元素数组
    //visited：已访问数组
    //level：层数
    private void permutation(List<List<Integer>> res,LinkedList<Integer> perm,int[] nums,boolean[] visited){
        if(perm.size()==nums.length){
            res.add(new LinkedList<Integer>(perm));
        }
        //使用循环逐个添加元素
        for(int i=0;i<nums.length;i++){
            //如果之前已访问/已添加，则跳过
            if(visited[i]){
                continue;
            }
            perm.add(nums[i]);
            visited[i]=true;
            //递归
            permutation(res,perm,nums,visited);
            //回溯
            visited[i]=false;
            perm.removeLast();
        }
    }
}



//使用ArrayList：1，99.82； 40.2，7.32
//也有  2，80.66； 40.2，7.32
// class Solution{

//     public List<List<Integer>> permute(int[] nums){
//         List<List<Integer>> res=new ArrayList<>();
//         boolean[] visited=new boolean[nums.length];
//         permutation(res,new ArrayList<Integer>(),nums,visited,0);
//         return res;
//     }

//     //参数含义：
//     //res：返回的结果列表
//     //perm：一条结果
//     //nums：可取元素数组
//     //visited：已访问数组
//     //level：层数
//     private void permutation(List<List<Integer>> res,ArrayList<Integer> perm,int[] nums,boolean[] visited,int level){
//         if(level==nums.length){
//             res.add(new ArrayList<Integer>(perm));
//         }
//         //使用循环逐个添加元素
//         for(int i=0;i<nums.length;i++){
//             //如果之前已访问/已添加，则跳过
//             if(visited[i]){
//                 continue;
//             }
//             perm.add(nums[i]);
//             visited[i]=true;
//             //递归
//             permutation(res,perm,nums,visited,level+1);
//             //回溯
//             visited[i]=false;
//             perm.remove(perm.size()-1);
//         }
//     }
// }
//leetcode submit region end(Prohibit modification and deletion)
