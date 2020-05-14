//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组


//leetcode submit region begin(Prohibit modification and deletion)
//一遍遍的从开头更新pos，直到pos==0
//297,14.35;  41.9,5
class Solution {
    public int jump(int[] nums) {
        int target=nums.length-1;
        int start=0;
        int steps=0;
        while(target!=0){
            for(int i=0;i<target;i++){
                if(i+nums[i]>=target){
                    target=i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}


//遍历一遍，更新maxpos
//2，94.93；  41.2，5
class Solution{
    public int jump(int[] nums){
        int maxpos=0;
        int steps=0;
        int end=0;
        //end记录上一个step可以到达的最远位置
        //因为最后一个位置就是目标位置，所以不需要遍历最后一个位置
        for(int i=0;i<nums.length-1;i++){
            // for(int i=0;i<nums.length;i++){
            maxpos=Math.max(maxpos,i+nums[i]);
            //到达了上一个step的最远
            //只要在i还没到end，就不需要steps++；因为都在上一跳范围内，没有确保找到上个step的最远距离
            if(i==end){
                end=maxpos;
                steps++;
                if(end>=nums.length-1){
                    return steps;
                }
            }
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
