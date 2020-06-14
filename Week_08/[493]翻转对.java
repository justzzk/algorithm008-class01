//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法


//leetcode submit region begin(Prohibit modification and deletion)
//count同时合并数组
// class Solution{
//     public int reversePairs(int[] nums){
//         if(nums==null || nums.length==0) return 0;
//         return mergeSort(nums,0,nums.length-1);
//     }

//     private int mergeSort(int[] nums,int left,int right){
//         //递归中止条件
//         if(left >= right) return 0;

//         int mid= left+(right-left)/2;
//         int count=mergeSort(nums,left,mid)+ mergeSort(nums,mid+1,right);

//         int[] cache=new int[right-left+1];

//         //策略是，每次迭代，[mid+1,right]增加1个，[left,mid]不断后移，
//         //直到i，是翻转对，这时，i到mid都是相对这个j的翻转对
//         //如何填写cache呢，还是以这次迭代的j为标准，将小于nums[j]的都写到cache后，
//         //将nums[j]写入，本次迭代可以结束
//         int i=left;   //指示相对本j出现翻转对的[left,mid]索引
//         int t=left;  //写cache时的[left,mid]索引
//         int j=mid+1;   //[mid+1,right]的索引
//         int c=0;    //cache的索引
//         while(j<=right){
//             //先找翻转对或者cache都可以，二者是独立的
//             //这里的一个问题是，nums[j]是int最大值，那么扩大两倍就会越界，所以需要转为long
//             while(i<=mid && nums[i] <= 2*(long)nums[j]) i++;
//             //退出上一个while，说明i指示的num是相对j的翻转对,
//             //因为两个数组都已排序，所以i之后的都是相对j的翻转对
//             count+=mid-i+1;

//             //下一个while写cache
//             while(t<=mid && nums[t]<=nums[j]) cache[c++]=nums[t++];
//             //把nums[j]写上
//             cache[c++]=nums[j++];
//         }
//         //退出for的原因是nums[mid+1,right]走完了,只可能nums[left,mid]还有剩余
//         while(t<=mid) cache[c++]=nums[t++];

//         //将排序结果写会原数组
//         System.arraycopy(cache,0,nums,left,right-left+1);
//         //返回结果
//         return count;

//     }
// }

//先记录比较结果，后直接合并
//这个不需要cache
//78,29.19;  48.3,100
class Solution{
    public int reversePairs(int[] nums){
        if(nums==null || nums.length==0) return 0;
        return mergeSort(nums,0,nums.length-1);
    }

    private int mergeSort(int[] nums,int left,int right){
        if(left >= right) return 0;

        int mid=left+(right-left)/2;
        int count=mergeSort(nums,left,mid)+mergeSort(nums,mid+1,right);

        int i=left;  //[left,mid]
        int j=mid+1;  //[mid+1,right]
        //比较的角度，可以以i也可以以j，上面的是以j，移动i
        //这个以i，移动j
        // while(i<=mid){
        //     while(j<=right && nums[i]>2*(long)nums[j]) j++;
        //     //nums[j]不是翻转对
        //     count+=j-(mid+1);
        //     i++;
        // }

        //以j
        while(j<=right){
            while(i<=mid && nums[i]<=2*(long)nums[j]) i++;
            //i也是对与j的翻转对
            count+=mid-i+1;
            j++;
        }
        Arrays.sort(nums,left,right+1);
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
