//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// '.' 匹配任意单个字符
//'*' 匹配零个或多个前面的那一个元素
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
// Related Topics 字符串 动态规划 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//dp
//2,99.68;   38.3,35.29
class Solution{
    public boolean isMatch(String s,String p){
        if (s == null || p == null) {
            return false;
        }
        char[] schar=s.toCharArray();
        char[] pchar=p.toCharArray();
        int slen=s.length();
        int plen=p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;//dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
        //初始化第一行
        // for (int i = 0; i < plen; i++) { // here's the p's length, not s's
        //     if (pchar[i] == '*' && dp[0][i - 1]) {
        //         dp[0][i + 1] = true; // here's y axis should be i+1
        //     }
        // }
        //也可以
        for (int i = 0; i < plen; i++) { // here's the p's length, not s's
            if (pchar[i] == '*' ) {
                dp[0][i + 1] = dp[0][i - 1]; // here's y axis should be i+1
            }
        }
        //第一列表示使用空串匹配s，肯定全是false
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                if (pchar[j-1] == '.' || pchar[j-1] == schar[i-1]) {
                    dp[i][j] = dp[i-1][j-1];
                }
                if (pchar[j-1] == '*') {
                    if (pchar[j-2] != schar[i-1] && pchar[j-2] != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        //pchar[i-2]可以匹配上schar[i-1]
                        dp[i][j] = (dp[i][j-1] || dp[i-1][j] || dp[i][j - 2]);
//                         /*
//                         dp[i][j] = dp[i-1][j] // 多个字符匹配的情况
//                         or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
//                         or dp[i][j] = dp[i][j-2] // 没有匹配的情况
//                             */
                    }
                }
            }
        }
        return dp[slen][plen];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
