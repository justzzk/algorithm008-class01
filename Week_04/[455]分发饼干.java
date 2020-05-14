//假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干
//的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满
//足越多数量的孩子，并输出这个最大数值。 
//
// 注意： 
//
// 你可以假设胃口值为正。 
//一个小朋友最多只能拥有一块饼干。 
//
// 示例 1: 
//
// 
//输入: [1,2,3], [1,1]
//
//输出: 1
//
//解释: 
//你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
//虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
//所以你应该输出1。
// 
//
// 示例 2: 
//
// 
//输入: [1,2], [1,2,3]
//
//输出: 2
//
//解释: 
//你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
//你拥有的饼干数量和尺寸都足以让所有孩子满足。
//所以你应该输出2.
// 
// Related Topics 贪心算法


//leetcode submit region begin(Prohibit modification and deletion)
//按照思路最直接的实现
//直到找到满足当前胃口的饼干才继续，
//从程序中就可以看到可优化的地方：直接使用gindex，不需要cnt
//sindex++在两种情况都有，不应该以其为循环
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int cnt=0;
        int sindex=0;
        int gindex=0;
        while(sindex<s.length && gindex<g.length){
            while(sindex<s.length && s[sindex]<g[gindex]){
                sindex++;
            }
            if(sindex>=s.length) break;
            cnt++;
            sindex++;
            gindex++;
        }
        return cnt;
    }
}

//这种方法主体上遍历的是饼干数组，当当前饼干大小不小于当前胃口，
//才将朋友索引++，这样遍历完饼干数组后朋友的索引就是满足的总数
//    public int findContentChildren(int[] grid, int[] size) {
//        if (grid == null || size == null) return 0;
//        Arrays.sort(grid);
//        Arrays.sort(size);
//        int gi = 0, si = 0;
//        while (gi < grid.length && si < size.length) {
//            if (grid[gi] <= size[si]) {
//                gi++;
//            }
//            si++;
//        }
//        return gi;
//    }
//leetcode submit region end(Prohibit modification and deletion)
