//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
// 
//
// 两个字符串完全匹配才算匹配成功。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
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
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
// 
//
// 示例 3: 
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
// 
//
// 示例 4: 
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
// 
//
// 示例 5: 
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false 
// Related Topics 贪心算法 字符串 动态规划 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//dp
//37，43.60；  40.1，50
// class Solution {
//     public boolean isMatch(String s, String p) {
//         // dp[i][j]表示s截止到第i个位置(s[i-1])的子串与p截止到第j个位置(p[j-1])的子串是否匹配
//         boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
//         // dp[0][0]表示 空s 和 空p 匹配
//         dp[0][0] = true;
//         // for循环的开始条件是i=1,也就是没考虑 空s 的匹配情况，dp[0][0]只考虑了 空s匹配空p
//         // 但 空s 也可以匹配 *,**,而且对于 *abc*, 空s的匹配情况可以简化
//         // 这里对空s的匹配情况进行初始化
//         for (int j = 1; j <= p.length(); j++) {
//             // s匹配*,相当于s匹配空；s匹配**相当于s匹配*；s匹配*cb*相当于s匹配*cb
//             if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j-1];
//         }
//         // 注意 i,j表示的是s和p中第几个字符，对应的索引索引是i-1,j-1
//         for (int i = 1; i <= s.length(); i++) {
//             for (int j = 1; j <= p.length(); j++) {
//                 // 根据模式串的当前位置的字符来分类讨论
//                 // 当前位置是 *
//                 // * 可以匹配任意字符包括空字符，所以考虑 dp[i-1][j-1],dp[i-1][j],dp[i][j-1]这前面已处理过的三个结果
//                 // 但其实 dp[i-1][j-1]不用考虑，因为考虑dp[i-1][j]时也会执行这个if，还是会考虑到dp[i-1][j-1]
//                 if (p.charAt(j - 1) == '*') {
//                     dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
//                 // 当前位置是 ？,可以匹配任意单个字符，所以和前一个位置结果一样
//                 // p当前位置字符和s当前字符一样，所以跟前一个位置匹配结果一样
//                 } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
//                     dp[i][j] = dp[i - 1][j - 1];
//                 }
//             }
//         }
//         // 返回
//         return dp[s.length()][p.length()];
//     }
// }


//17,77.93;  40,53.57
class Solution {
    public boolean isMatch(String s, String p) {
        char[] schar=s.toCharArray();
        char[] pchar=p.toCharArray();
        int slen=schar.length;
        int plen=pchar.length;
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;
        for (int j = 1; j <= plen; j++) {
            if (pchar[j - 1] == '*') dp[0][j] = dp[0][j-1];
        }
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                if (pchar[j - 1] == '*') {
                    //当前pat的字符是*，可以不匹配，即dp[i][j - 1]；
                    //可以匹配当前字符也可以匹配上，即dp[i - 1][j-1]；
                    //也可以匹配多次，即dp[i - 1][j]，相当于*跳过了当前i，检查与上一个i的匹配情况
                    //并且dp[i-1][j]的结果包含了dp[i - 1][j-1]，
                    //因为当前为*，不对*进行匹配就是dp[i - 1][j-1]，所以可以省略dp[i - 1][j-1]
                    // dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i-1][j - 1];
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (pchar[j - 1] == '?' || schar[i - 1] == pchar[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[slen][plen];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
