//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法


//leetcode submit region begin(Prohibit modification and deletion)
//执行用时 :1 ms, 在所有 Java 提交中击败了100.00%的用户
//内存消耗 :41.6 MB, 在所有 Java 提交中击败了50.78%的用户
//class Solution {
//    public int majorityElement(int[] nums) {
//        int ret = nums[0];
//        int count = 1;
//        for(int num : nums) {
//            if(num != ret) {
//                count--;
//                if(count == 0) {
//                    count = 1;
//                    ret = num;
//                }
//            }
//            else
//                count++;
//        }
//        return ret;
//    }
//}


//占比超过一半，就是求第K大的问题，利用这一点可以使用快排只对前k个排序，不必全排序，时间复杂度On
//执行用时 :1 ms, 在所有 Java 提交中击败了100.00%的用户
//内存消耗 :41.4 MB, 在所有 Java 提交中击败了57.61%的用户
//class Solution {
//    public int majorityElement(int[] nums) {
//        return quickSearch(nums, 0, nums.length - 1, nums.length / 2);
//    }
//
//    private int quickSearch(int[] nums, int lo, int hi, int k) {
//        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于n/2就返回；
//        int j = partition(nums, lo, hi);
//        if (j == k) {
//            return nums[j];
//        }
//        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
//        return j > k? quickSearch(nums, lo, j - 1, k): quickSearch(nums, j + 1, hi, k);
//    }
//
//    // 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
//    private int partition(int[] nums, int lo, int hi) {
//        int v = nums[lo];
//        int i = lo, j = hi + 1;
//        while (true) {
//            while (++i <= hi && nums[i] < v);
//            while (--j >= lo && nums[j] > v);
//            if (i >= j) {
//                break;
//            }
//            int t = nums[j];
//            nums[j] = nums[i];
//            nums[i] = t;
//        }
//        nums[lo] = nums[j];
//        nums[j] = v;
//        return j;
//    }
//}
//


//排序后返回中间的，因为超过一半所以排序后肯定在中间
//执行用时 :2 ms, 在所有 Java 提交中击败了85.34%的用户
//内存消耗 :41.9 MB, 在所有 Java 提交中击败了40.07%的用户
//时间复杂度：\O(nlogn)。将数组排序的时间复杂度为O(nlogn)。
//空间复杂度：O(logn)。如果使用语言自带的排序算法，需要使用 O(log n) 的栈空间。如果自己编写堆排序，则只需要使用 O(1) 的额外空间。
//class Solution {
//    public int majorityElement(int[] nums) {
//        Arrays.sort(nums);
//        return nums[nums.length/2];
//    }
//}


//Boyer-Moore 投票算法 时间On，空间O1
//执行用时 :3 ms, 在所有 Java 提交中击败了53.66%的用户
//内存消耗 :45.5 MB, 在所有 Java 提交中击败了5.07%的用户
//class Solution {
//    public int majorityElement(int[] nums) {
//        int count = 0;
//        Integer candidate = null;
//
//        for (int num : nums) {
//            if (count == 0) {
//                candidate = num;
//            }
//            count += (num == candidate) ? 1 : -1;
//        }
//
//        return candidate;
//    }
//}

//执行用时 :23 ms, 在所有 Java 提交中击败了19.49%的用户
//内存消耗 :47.1 MB, 在所有 Java 提交中击败了5.07%的用户
//hashmap On，On
//class Solution {
//    public int majorityElement(int[] nums) {
//        int length=nums.length;
//        if(length==1) return nums[0];
//        int goal=(int)length/2;
//        HashMap<Integer,Integer> map=new HashMap<>();
//        for(int i=0;i<nums.length;i++){
//            int tmp=nums[i];
//            if(map.containsKey(tmp)){
//                if(map.get(tmp)+1 > goal){
//                    return tmp;
//                }else{
//                    map.put(tmp,map.get(tmp)+1);
//                }
//            }else{
//                map.put(tmp,1);
//            }
//        }
//        return -1;
//    }
//}

//分治算法：我们使用经典的分治算法递归求解，直到所有的子问题都是长度为 1 的数组。
// 长度为 1 的子数组中唯一的数显然是众数，直接返回即可。如果回溯后某区间的长度大于 1，我们必须将左右子区间的值合并。
// 如果它们的众数相同，那么显然这一段区间的众数是它们相同的值。否则，我们需要比较两个众数在整个区间内出现的次数来决定该区间的众数。
// ，分治的思想是可取的，本题中不合适，时间nlogn，空间logn
//class Solution {
//    private int countInRange(int[] nums, int num, int lo, int hi) {
//        int count = 0;
//        for (int i = lo; i <= hi; i++) {
//            if (nums[i] == num) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    private int majorityElementRec(int[] nums, int lo, int hi) {
//        // base case; the only element in an array of size 1 is the majority
//        // element.
//        if (lo == hi) {
//            return nums[lo];
//        }
//
//        // recurse on left and right halves of this slice.
//        int mid = (hi-lo)/2 + lo;
//        int left = majorityElementRec(nums, lo, mid);
//        int right = majorityElementRec(nums, mid+1, hi);
//
//        // if the two halves agree on the majority element, return it.
//        if (left == right) {
//            return left;
//        }
//
//        // otherwise, count each element and return the "winner".
//        int leftCount = countInRange(nums, left, lo, hi);
//        int rightCount = countInRange(nums, right, lo, hi);
//
//        return leftCount > rightCount ? left : right;
//    }
//
//    public int majorityElement(int[] nums) {
//        return majorityElementRec(nums, 0, nums.length-1);
//    }
//}

//位运算
//思路：由于众数数组中出现次数大于n/2 ，
//那么众数对应的二进制每一个为1的位数出现的次数一定大于n/2，由此可以得出众数
//执行用时 :7 ms, 在所有 Java 提交中击败了38.42%的用户
//内存消耗 :41.5 MB, 在所有 Java 提交中击败了58.77%的用户
class Solution{
    public int majorityElement(int[] nums) {
        int result = 0, k = nums.length >> 1;
        for (int j = 0; j < 32; j++) {
            int count = 0;
            for (int num : nums) {
                count += num >> j & 1;
                if (count > k) {
                    result += 1 << j;
                    break;
                }
            }
        }
        return result;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
