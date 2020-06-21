//给定一个无序的整数数组，找到其中最长上升子序列的长度。 
//
// 示例: 
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4 
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 
//
// 说明: 
//
// 
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 
// 你算法的时间复杂度应该为 O(n2) 。 
// 
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗? 
// Related Topics 二分查找 动态规划


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //执行用时 :14 ms, 在所有 Java 提交中击败了58.52%的用户
    //内存消耗 :37.5 MB, 在所有 Java 提交中击败了5.05%的用户
    //O(n^2)
    public int lengthOfLIS(int[] nums) {
        //对空的判断很重要
        int length=nums.length;
        if(length==0) return 0;
        int ans=1;
        int[] dp=new int[length];
        Arrays.fill(dp,1);
        for(int i=1;i<length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            ans=Math.max(ans,dp[i]);
        }
        return ans;
    }

    //dp/贪心+二分查找
    //执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗 :37.8 MB, 在所有 Java 提交中击败了5.05%的用户
//    class Solution {
//        public int lengthOfLIS(int[] nums) {
//            int[] tails = new int[nums.length];
//            int res = 0;
//            for(int num : nums) {
//                int i = 0, j = res;
//                while(i < j) {
//                    int m = (i + j) / 2;
//                    if(tails[m] < num) i = m + 1;
//                    else j = m;
//                }
//                tails[i] = num;
//                if(res == j) res++;
//            }
//            return res;
//        }
//    }
    //使用二分优化的另一种
    //优化:一旦前面有两个dp值一样了，比如dp[i] = dp[j],并缺nums[i] > nums[j] ，那就只要考虑第j个就可以了
    //启示：同样的dp值，存一个坐标，这个坐标对应的nums[index]值最小。
    //怎么做？对于每个dp值，保存对应的nums[i]的值
    //序列是单调上升的，在单调上升中找最后一个比自己小的数用二分法 lon2n，二分法很重要
    //执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗 :37.8 MB, 在所有 Java 提交中击败了5.05%的用户
//    public int lengthOfLIS(int[] nums) {
//        if (nums == null ||nums.length == 0) return 0;
//        int n = nums.length;
//        int[] a = new int[n];
//        int res = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int idx = Arrays.binarySearch(a, 0, res, nums[i]);
//            if (idx < 0) {
//                idx = -idx - 1;
//            }
//            //把这个nums[i]放在插入位置上
//            a[idx] = nums[i];
//            if (idx == res) {
//                res++;
//            }
//        }
//        return res;
//    }

    //
    //利用更简单的API TreeSet的Ceiling方法，应该是logN？？但是会退化，如果数据太多的话
    //TreeSet.ceiling(x)方法可以直接找出set中大于x的最小数字，如果不存在则返回null。
    //1、如果这个数字存在，则删除这个数字，然后把x插入set中，相当于代替该数字。
    //2、如果这个数字不存在，说明x大于set中任何数字，直接把x插入set中。
    //最后返回set的大小即可。
//    public int lengthOfLIS(int[] nums) {
//        TreeSet<Integer> set = new TreeSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            Integer c = set.ceiling(nums[i]);
//            //如果set中没有大于nums[i]的最小数字，那么就符合最长上升子序列，加入
//            //如果有，把让那个移除那个数字，换做这个nums[i]，因为nums[i]更小
//            //其实这边先判断 c!=null代码会更为简洁一点
//            if (c == null) {
//                set.add(nums[i]);
//            } else {
//                set.remove(c);
//                set.add(nums[i]);
//            }
//        }
//        return set.size();
//    }

//
//    public static void main(String[] args) {
//        int[] nums={10,9,2,5,3,7,101,18};
//        System.out.println(new Solution().lengthOfLIS(nums));
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
