//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 说明： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 
// Related Topics 堆 哈希表


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
//
//执行用时 :28 ms, 在所有 Java 提交中击败了25.65%的用户
//内存消耗 :42.4 MB, 在所有 Java 提交中击败了9.41%的用户
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i,map.getOrDefault(i,0)+1);
        }

        PriorityQueue<Integer> heap=new PriorityQueue<>((n1, n2)->map.get(n2)-map.get(n1));
//        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(map::get));

        heap.addAll(map.keySet());

        List<Integer> list= new LinkedList<>();
        while(list.size()!=k) list.add(heap.poll());
        return list;
    }

    public static void main(String[] args) {
        int[] nums={1,1,1,2,2,3,4};
        System.out.println(new Solution().topKFrequent(nums,2));
    }
}



//基于桶排序求解「前 K 个高频元素」
//执行用时 :18 ms, 在所有 Java 提交中击败了80.44%的用户
//内存消耗 :42.6 MB, 在所有 Java 提交中击败了8.12%的用户
//class Solution {
//    public List<Integer> topKFrequent(int[] nums, int k) {
//        List<Integer> res = new ArrayList();
//        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
//        HashMap<Integer,Integer> map = new HashMap();
//        for (int i : nums) {
//            map.put(i,map.getOrDefault(i,0)+1);
//        }
//
//        //桶排序
//        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
//        List<Integer>[] list = new List[nums.length+1];
//        for(int key : map.keySet()){
//            // 获取出现的次数作为下标
//            int i = map.get(key);
//            if(list[i] == null){
//                list[i] = new ArrayList();
//            }
//            list[i].add(key);
//        }
//
//        // 倒序遍历数组获取出现顺序从大到小的排列
//        for(int i = list.length - 1;i >= 0 && res.size() < k;i--){
//            if(list[i] == null) continue;
//            res.addAll(list[i]);
//        }
//        return res;
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
