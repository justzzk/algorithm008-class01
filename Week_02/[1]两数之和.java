//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
//1. 直接法：从第一个元素开始循环验证其后的数字和，直到找到第一组解；
//2. 将求和变换为在数组中寻找每一个元素需要的值是否存在，就是查找问题，使用hash提高效率；
//执行用时 :103 ms, 在所有 Java 提交中击败了5.07%的用户
//内存消耗 :39.4 MB, 在所有 Java 提交中击败了5.07%的用户

import java.util.HashMap;
import java.util.Map;
// class Solution {
//     public int[] twoSum(int[] nums, int target) {
//         int[] ans=new int[2];
//         for(int i=0;i<nums.length;i++){
//             for(int j=i+1;j<nums.length;j++){
//                 if(nums[i]+nums[j]==target){
//                     ans[0]=i;
//                     ans[1]=j;
//                     return ans;
//                 }
//             }
//         }
//         return ans;
//     }
// }



//执行用时 :3 ms, 在所有 Java 提交中击败了95.21%的用户
//内存消耗 :41.4 MB, 在所有 Java 提交中击败了5.07%的用户
//一遍hashmap
class Solution{
    public int[] twoSum(int[] nums,int target){

        Map<Integer,Integer> map=new HashMap();
        for(int i=0;i<nums.length;i++){
            int other=target-nums[i];
            if(map.containsKey(other)){
                return new int[]{map.get(other),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No answer");
    }

}
//leetcode submit region end(Prohibit modification and deletion)
