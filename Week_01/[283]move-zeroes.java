//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针


//leetcode submit region begin(Prohibit modification and deletion)
//执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
//内存消耗 :42.3 MB, 在所有 Java 提交中击败了5.00%的用户
class Solution {
//    public void moveZeroes(int[] nums) {
//        int index=0;
//        for (int i = 0; i < nums.length; i++) {
//            if(nums[i]!=0){
//                nums[index]=nums[i];
//                index++;
//            }
//        }
//        for (int i = index; i < nums.length; i++) {
//            nums[i]=0;
//        }
//    }


    //执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
//内存消耗 :42.8 MB, 在所有 Java 提交中击败了5.00%的用户
    public void moveZeroes(int[] nums) {

        int slow=0;
        int fast=0;
        int tmp=0;
//        while (fast<nums.length){
//            if(nums[fast]!=0 ){
//                if(fast!= slow) {
//                    tmp = nums[slow];
//                    nums[slow] = nums[fast];
//                    nums[fast] = tmp;
//                }
//                slow++;
//            }
//            fast++;
//        }
        for(;fast<nums.length;fast++){
            if(nums[fast]!=0){
                if(fast!=slow){
                    tmp = nums[slow];
                    nums[slow] = nums[fast];
                    nums[fast] = tmp;
                }
                slow++;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
