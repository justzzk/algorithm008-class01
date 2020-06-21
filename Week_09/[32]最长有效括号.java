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
//暴力，单独检查每个子串是否有效
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
//     private boolean isValid(String s){
//         Stack<Character> stack=new Stack<>();
//         char[] schar=s.toCharArray();
//         for(char c:schar){
//             if(c=='(') stack.push(c);
//             else{
//                 if(stack.isEmpty()) return false;
//                 stack.pop();
//             }
//         }
//         return stack.isEmpty();
//     }
// }


//官方暴力，单独检查每一个子串是不是有效的
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

//常规解
//不过这个不是子串，是最长子序列长度 ()(()
// class Solution{
//     public int longestValidParentheses(String s){
//         if(s==null || s.length()==0) return 0;
//         int len=s.length();
//         int numLeft=0,count=0;
//         for(char c:s.toCharArray()){
//             if(c=='(') numLeft++;
//             else if(numLeft>0){
//                 count++;
//                 numLeft--;
//             }
//         }
//         return 2*count;
//     }
// }

//又想出一种不用dp的解,
//不行，原因在如果有嵌套，因为不知道是不是所有的（都可以匹配，
//所以不确定是不是应该将count清零，"()(())"
// class Solution{
//     public int longestValidParentheses(String s){
//         if(s==null || s.length()==0) return 0;
//         int len=s.length();
//         int numLeft=0,count=0,maxLen=0;
//         for(char c:s.toCharArray()){
//             if(c=='('){
//                 if(numLeft==1) count=0;
//                 else numLeft=1;
//             }else if(numLeft>0){
//                 count++;
//                 maxLen=Math.max(maxLen,2*count);
//                 numLeft--;
//             }
//         }
//         return maxLen;
//     }
// }

//dp方法没有想出来，看了之后觉得还是有些难的
//因为有效子串肯定是以）结尾的，所以(的dp=0；所以）的dp记录参与的有效子串的长度
//dp解法想的是，当遍历到）时，它是否有（可以匹配，如果有的话，它在哪里
//1.）的左边就是（，这时候的长度除了2，（前面是否就是有效子串，如果是，还要加上前面的长度；
//2.）的左边是），那么就要向前找是否有可匹配的（，就要跳过左边的）已经存在的有效子串长度，找有没有（，
//如果没有找到可匹配的（，就是0了，断掉
//其实第一种情况可以看成第2种的特例，所以两种放在一起讨论即可
//要是自己想的话，我觉得还是不容易想出来的
//因为自己的思路是，通过记录左右括号的数量来判断是否有效，
//像dp这样，直接找可匹配的（，再记录目前的有效长度，还是有些难的
//1,100;  40.1,9.52
class Solution{
    public int longestValidParentheses(String s){
        if(s==null || s.length()==0) return 0;
        char[] schar=s.toCharArray();
        int length=schar.length;
        int[] dp=new int[length];
        int max=0;
        for(int i=0;i<length;i++){
            if(schar[i]=='(') dp[i]=0;
            else if(i>0){
                //找可匹配的(
                if(i-dp[i-1]-1>=0 && schar[i-dp[i-1]-1]=='('){
                    dp[i]=dp[i-1]+2;
                    if(i-dp[i-1]-2>0) dp[i]+=dp[i-dp[i-1]-2];
                    max=Math.max(max,dp[i]);
                }
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
