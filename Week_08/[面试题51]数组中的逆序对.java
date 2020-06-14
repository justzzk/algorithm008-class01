//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
//


//leetcode submit region begin(Prohibit modification and deletion)
// public class Solution {

//     public int reversePairs(int[] nums) {
//         int len = nums.length;

//         if (len < 2) {
//             return 0;
//         }

//         int[] copy = new int[len];
//         for (int i = 0; i < len; i++) {
//             copy[i] = nums[i];
//         }

//         int[] temp = new int[len];
//         return reversePairs(copy, 0, len - 1, temp);
//     }

//     /**
//      * nums[left..right] 计算逆序对个数并且排序
//      *
//      * @param nums
//      * @param left
//      * @param right
//      * @param temp
//      * @return
//      */
//     private int reversePairs(int[] nums, int left, int right, int[] temp) {
//         if (left == right) {
//             return 0;
//         }

//         int mid = left + (right - left) / 2;
//         int leftPairs = reversePairs(nums, left, mid, temp);
//         int rightPairs = reversePairs(nums, mid + 1, right, temp);

//         // 如果整个数组已经有序，则无需合并，注意这里使用小于等于
//         if (nums[mid] <= nums[mid + 1]) {
//             return leftPairs + rightPairs;
//         }

//         int crossPairs = mergeAndCount(nums, left, mid, right, temp);
//         return leftPairs + rightPairs + crossPairs;
//     }

//     /**
//      * nums[left..mid] 有序，nums[mid + 1..right] 有序
//      *
//      * @param nums
//      * @param left
//      * @param mid
//      * @param right
//      * @param temp
//      * @return
//      */
//     private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
//         for (int i = left; i <= right; i++) {
//             temp[i] = nums[i];
//         }

//         int i = left;
//         int j = mid + 1;

//         int count = 0;
//         for (int k = left; k <= right; k++) {
//             // 有下标访问，得先判断是否越界
//             if (i == mid + 1) {
//                 nums[k] = temp[j];
//                 j++;
//             } else if (j == right + 1) {
//                 nums[k] = temp[i];
//                 i++;
//             } else if (temp[i] <= temp[j]) {
//                 // 注意：这里是 <= ，写成 < 就不对，请思考原因
//                 nums[k] = temp[i];
//                 i++;
//             } else {
//                 nums[k] = temp[j];
//                 j++;

//                 // 在 j 指向的元素归并回去的时候，计算逆序对的个数，只多了这一行代码
//                 count += (mid - i + 1);
//             }
//         }
//         return count;
//     }
// }


//暴力
//超时
// class Solution{
//     public int reversePairs(int[] nums) {
//         int count=0;
//         for(int i=0;i<nums.length-1;i++){
//             for(int j=i+1;j<nums.length;j++){
//                 if(nums[i] > nums[j]){
//                     count++;
//                 }
//             }
//         }
//         return count;
//     }
// }


//归并排序
//36,84.30;  49.9,100
class Solution{
    public int reversePairs(int[] nums){
        if(nums==null || nums.length==0) return 0;
        return mergeSort(nums,0,nums.length-1);
    }

    private int mergeSort(int[] nums,int left,int right){
        if(left >= right) return 0;

        int mid=left+(right-left)/2;
        int count=mergeSort(nums,left,mid)+mergeSort(nums,mid+1,right);

        if(nums[mid]>nums[mid+1]){
            int[] cache=new int[right-left+1];
            int i=left;  //index of [left,mid]
            int j=mid+1; //index of [mid+1,right]
            int c=0;  //index of cache
            while(i<=mid ){
                //只要[left,mid]没有遍历完，count就有加的可能
                while(j<=right && nums[j]<nums[i]){
                    cache[c++]=nums[j++];
                    count+=mid-i+1;
                }
                cache[c++]=nums[i++];
            }
            while(j<=right) cache[c++]=nums[j++];
            System.arraycopy(cache,0,nums,left,right-left+1);
        }
        return count;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
