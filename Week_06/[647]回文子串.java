//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。 
//
// 示例 1: 
//
// 
//输入: "abc"
//输出: 3
//解释: 三个回文子串: "a", "b", "c".
// 
//
// 示例 2: 
//
// 
//输入: "aaa"
//输出: 6
//说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
// 
//
// 注意: 
//
// 
// 输入的字符串长度不会超过1000。 
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    //dp
    //17，24.04；   39.8，5.55
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        boolean[][] dp = buildDPForCountSubstrings(s);
        for (int j = 0; j < dp.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (dp[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }


    private boolean[][] buildDPForCountSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        //注意i 和j 的边界，只计算上半部分，j - i <= 1是为了处理边界，dp[i + 1][j - 1]是dp[i][j]砍头去尾后的是否是回文
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1]);
                }
            }
        }
        return dp;
    }


    //中心扩展
    //4，81.92；  37.5，5.55
    public int countSubstrings2nd(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            //以当前点i位置，向两边扩展,以i i+1位置向两边扩展
            result += countSegment(s, i, i);
            result += countSegment(s, i, i + 1);
        }
        return result;
    }


    public int countSegment(String s, int start, int end) {
        int count = 0;
        //start往左边跑，end往右边跑，注意边界
        while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++)) {
            count++;
        }
        return count;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
