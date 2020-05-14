//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
//
// 你可以假设数组中不存在重复的元素。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 示例 1: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
// 
//
// 示例 2: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1 
// Related Topics 数组 二分查找


//leetcode submit region begin(Prohibit modification and deletion)
// class Solution {
//     public int search(int[] nums, int target) {
//         int lo = 0, hi = nums.length - 1;
//         while (lo <= hi) {
//             int mid = lo + (hi - lo) / 2;
//             if (nums[mid] == target) {
//                 return mid;
//             }

//             // 先根据 nums[0] 与 target 的关系判断目标值是在左半段还是右半段
//             if (target >= nums[0]) {
//                 // 目标值在左半段时，若 mid 在右半段，则将 mid 索引的值改成 inf
//                 if (nums[mid] < nums[0]) {
//                     nums[mid] = Integer.MAX_VALUE;
//                 }
//             } else {
//                 // 目标值在右半段时，若 mid 在左半段，则将 mid 索引的值改成 -inf
//                 if (nums[mid] >= nums[0]) {
//                     nums[mid] = Integer.MIN_VALUE;
//                 }
//             }

//             if (nums[mid] < target) {
//                 lo = mid + 1;
//             } else {
//                 hi = mid - 1;
//             }
//         }
//         return -1;
//     }
// }

class Solution{
    public int search(int[] nums, int target){
        int length=nums.length;
        int left=0;
        int right=length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }
            //根据mid和nums[0]的大小关系以及mid与target的大小关系
            if(nums[mid]>=nums[left]){
                //mid在左半
                if(target<nums[mid] && target>=nums[left]){
                    //将区间缩减至0 ～mid-1
                    right=mid-1;
                }else{
                    //mid+1,right
                    left=mid+1;
                }
            }else{
                //mid在右半
                if(target>nums[mid] && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
