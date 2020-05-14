//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组


//leetcode submit region begin(Prohibit modification and deletion)
//倒着
//1,99.84;  41.4,15.63
// class Solution {
//     public boolean canJump(int[] nums) {
//         if (nums == null || nums.length == 0) {
//             return false;
//         }
//         //pos表示需要到达的位置
//         int pos = nums.length - 1;
//         for (int i = nums.length - 2; i >= 0; i--) {
//             if (nums[i] + i >= pos) {
//                 pos = i;
//             }
//         }
//         return pos == 0;
//     }
// }

//正着
class Solution{
    public boolean canJump(int[] nums){
        //关键是更新maxpos，并比较是否>=length-1
        int target=nums.length-1;
        int maxpos=0;
        //i<target还是i<target+1,写后者是为了包括[0]这种情况，不然实际上判断到i==target-1就可以了
        //若写i<target,则return target==0?true:false;为了包含[0]这种情况
        //否则的话，如果i能走到这里，前面肯定已经可以到达了，不会i==target；
        //如果不能走到这里，i==target也不会通过if，所以也是安全的。
        for(int i=0;i<target+1;i++){
            if(i<=maxpos){
                maxpos=Math.max(maxpos,nums[i]+i);
                if(maxpos>=target){
                    return true;
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
