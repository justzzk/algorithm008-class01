//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//33,18.49;  40.8,12.5
// class Solution {
//     public List<List<Integer>> permuteUnique(int[] nums) {
//         //用set去重,之后转为list
//         HashSet<List<Integer>> set=new HashSet<>();
//         boolean[] visited=new boolean[nums.length];
//         permutation(set,new ArrayList<Integer>(),nums,visited,0);
//         return new ArrayList<List<Integer>>(set);
//     }

//     private void permutation(HashSet<List<Integer>> set,ArrayList<Integer> list,int[] nums,boolean[] visited,int level){
//         if(level==nums.length){
//             //注意这里是保存一个新的list
//             set.add(new ArrayList<Integer>(list));
//             return;
//         }

//         //使用for循环遍历添加元素
//         for(int i=0;i<nums.length;i++){
//             if(visited[i]){
//                 continue;
//             }
//             list.add(nums[i]);
//             visited[i]=true;
//             permutation(set,list,nums,visited,level+1);
//             //回溯
//             list.remove(list.size()-1);
//             visited[i]=false;
//         }
//     }
// }


//33,18.49;  40.8,12.5
//不使用level
// class Solution {
//     public List<List<Integer>> permuteUnique(int[] nums) {
//         //用set去重,之后转为list
//         HashSet<List<Integer>> set=new HashSet<>();
//         boolean[] visited=new boolean[nums.length];
//         permutation(set,new ArrayList<Integer>(),nums,visited);
//         return new ArrayList<List<Integer>>(set);
//     }

//     private void permutation(HashSet<List<Integer>> set,ArrayList<Integer> list,int[] nums,boolean[] visited){
//         if(list.size()==nums.length){
//             //注意这里是保存一个新的list
//             set.add(new ArrayList<Integer>(list));
//             return;
//         }

//         //使用for循环遍历添加元素
//         for(int i=0;i<nums.length;i++){
//             if(visited[i]){
//                 continue;
//             }
//             list.add(nums[i]);
//             visited[i]=true;
//             permutation(set,list,nums,visited);
//             //回溯
//             list.remove(list.size()-1);
//             visited[i]=false;
//         }
//     }
// }

//1，100；  40.9，12.5
//针对重复出现的元素，排序后，只要与前一个元素相同的就不用排序，直接continue就好了
// public class Solution {
//     public List<List<Integer>> permuteUnique(int[] nums) {
//         List<List<Integer>> res = new ArrayList<List<Integer>>();
//         if(nums==null || nums.length==0) return res;
//         boolean[] used = new boolean[nums.length];
//         List<Integer> list = new ArrayList<Integer>();
//         Arrays.sort(nums);
//         dfs(nums, used, list, res);
//         return res;
//     }

//     public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
//         if(list.size()==nums.length){
//             res.add(new ArrayList<Integer>(list));
//             return;
//         }
//         for(int i=0;i<nums.length;i++){
//             if(used[i]) continue;
//             if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) continue;
//             used[i]=true;
//             list.add(nums[i]);
//             dfs(nums,used,list,res);
//             used[i]=false;
//             list.remove(list.size()-1);
//         }
//     }
// }


//2，83.51；   40.7，12.5
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        ArrayList<Integer> _nums = new  ArrayList<Integer>();
        for(int num : nums) _nums.add(num);
        calPermut(res, _nums, 0);
        return res;
    }
    public void calPermut(List<List<Integer>> res, List<Integer> _nums, int start){

        if(start == _nums.size()){ res.add(new ArrayList<Integer>(_nums));}
        else{
            Set<Integer> appeared = new HashSet<>();
            for(int i = start; i < _nums.size(); i++){
                //用向set中添加元素是否成功跳过重复元素
                //总之只要重复元素就不需要进行排列
                if(appeared.add(_nums.get(i))){
                    Collections.swap(_nums, i, start);
                    calPermut(res, _nums, start + 1);
                    Collections.swap(_nums, start, i);
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
