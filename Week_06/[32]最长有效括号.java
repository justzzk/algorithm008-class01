//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
//暴力
//超时，测试用例221/230
// class Solution {
//     public int longestValidParentheses(String s) {
//         int maxlen=0;
//         for(int i=0;i<s.length()-1;i++){
//             //有效的都是偶数长度，所以j步进为2,闭区间[i,j]
//             for(int j=i+1;j<s.length();j+=2){
//                 if(j-i+1 > maxlen && isValid(s.substring(i,j+1))){
//                     maxlen=j-i+1;
//                 }
//             }
//         }
//         return maxlen;
//     }

//     //一种不使用stack的判断有效字符串的方法
//     //也超时，测试用例223/230
//     private boolean isValid(String s){
//         char[] schar=s.toCharArray();
//         int sum=0;
//         for(char c:schar){
//             if(c=='('){
//                 sum++;
//             }else{
//                 sum--;
//                 //这一步很重要，刚才忘了
//                 //右括号不能比左括号
//                 if(sum<0){
//                     return false;
//                 }
//             }
//         }
//         return sum==0;
//     }

// private boolean isValid(String s){
//     Stack<Character> stack=new Stack<>();
//     char[] schar=s.toCharArray();
//     for(char c:schar){
//         if(c=='(') stack.push(c);
//         else{
//             if(stack.isEmpty()) return false;
//             stack.pop();
//         }
//     }
//     return stack.isEmpty();
// }
// }


//官方暴力
//超时
// public class Solution {
//     public boolean isValid(String s) {
//         Stack<Character> stack = new Stack<Character>();
//         for (int i = 0; i < s.length(); i++) {
//             if (s.charAt(i) == '(') {
//                 stack.push('(');
//             } else if (!stack.empty() && stack.peek() == '(') {
//                 stack.pop();
//             } else {
//                 return false;
//             }
//         }
//         return stack.empty();
//     }
//     public int longestValidParentheses(String s) {
//         int maxlen = 0;
//         for (int i = 0; i < s.length(); i++) {
//             for (int j = i + 2; j <= s.length(); j+=2) {
//                 if (isValid(s.substring(i, j))) {
//                     maxlen = Math.max(maxlen, j - i);
//                 }
//             }
//         }
//         return maxlen;
//     }
// }



//2,84.37;  39.7,9.52
class Solution{
    public int longestValidParentheses(String s){
        int length=s.length();
        int[] dp=new int[length];
        char[] schar=s.toCharArray();
        int max=0;
        for(int i=1;i<length;i++){
            if(schar[i]=='(') dp[i]=0;
            else{
                if(schar[i-1]=='(' || (i-dp[i-1]-1>=0 && schar[i-dp[i-1]-1]=='(')){
                    dp[i]=dp[i-1]+2;
                    if(i-dp[i-1]-2 >= 0) dp[i]+=dp[i-dp[i-1]-2];
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
