//给出一个区间的集合，请合并所有重叠的区间。 
//
// 示例 1: 
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
// Related Topics 排序 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals==null || intervals.length==0){
            return new int[0][];
        }
        Arrays.sort(intervals,(o1,o2)->o1[0]-o2[0]);
        List<int[]> res=new LinkedList<>();
        int i=0;
        while(i < intervals.length){
            int left=intervals[i][0];
            int right=intervals[i][1];
            while(i<intervals.length-1 && right>=intervals[i+1][0]){
                right=Math.max(right,intervals[i+1][1]);
                // left=Math.min(intervals[i][0],intervals[i+1][0]);
                i++;
            }
            res.add(new int[]{left,right});
            i++;
        }
        return res.toArray(new int[0][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
