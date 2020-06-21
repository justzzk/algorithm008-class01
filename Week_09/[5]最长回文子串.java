//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
//递归
//应该是可以的，但是超时
//应该可以维护一个全局变量，保存目前的最长，如果待判断数组长度小于已知最长，可以剪枝
// class Solution {
//     public String longestPalindrome(String s) {
//         return longestPali(s,0,s.length()-1);
//     }

//     private String longestPali(String s,int left,int right){
//         if(left==right){
//             return new String(s.substring(left,left+1));
//         }

//         if(isPali(s,left,right)){
//             return s.substring(left,right+1);
//         }else{
//             String leftstr=longestPali(s,left,right-1);
//             String rightstr=longestPali(s,left+1,right);
//             return leftstr.length()>rightstr.length()?leftstr:rightstr;
//         }
//     }

//     private boolean isPali(String s,int left,int right){
//         while(left < right){
//             if(s.charAt(left)!=s.charAt(right)){
//                 return false;
//             }else{
//                 left++;
//                 right--;
//             }
//         }
//         return true;
//     }
// }


//暴力，O(n^3)
//对暴力法的一点优化，在每个索引位置从最后开始，找到一个就返回
//111，42.93；  38，25.89
// public class Solution {

//     public String longestPalindrome(String s) {
//         int len = s.length();
//         if (len < 2) {
//             return s;
//         }

//         int maxLen = 1;
//         int begin = 0;
//         // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
//         char[] charArray = s.toCharArray();

//         // 枚举所有长度大于 1 的子串 charArray[i..j]
//         // for (int i = 0; i < len - 1; i++) {
//         //     for (int j = i + 1; j < len; j++) {
//         //         if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
//         //             maxLen = j - i + 1;
//         //             begin = i;
//         //         }
//         //     }
//         // }


//         //对暴力方法的一点优化
//         //??113,40.10;  38.3,25
//         //居然差不多，不应该啊，从大到小在找到一个就退出循环，
//         //从小到大是从i+1一直遍历到len-1，应该会有很大提升的
//         //这里有点优化，i 不必到len-1，必须大于manLen
//         //效果不明显
//         //103，44.91；  38.3，24.1
//         // for (int i = 0; i < len - 1; i++) {
//         for (int i = 0; len-i > maxLen; i++) {
//             //从后往前，找到了就退出本层循环
//             for (int j = len-1; j - i + 1 > maxLen ; j--) {
//                 if ( validPalindromic(charArray, i, j)) {
//                     maxLen = j - i + 1;
//                     begin = i;
//                     break;
//                 }
//             }
//         }

//         return s.substring(begin, begin + maxLen);
//     }

//     /**
//      * 验证子串 s[left..right] 是否为回文串
//      */
//     private boolean validPalindromic(char[] charArray, int left, int right) {
//         while (left < right) {
//             if (charArray[left] != charArray[right]) {
//                 return false;
//             }
//             left++;
//             right--;
//         }
//         return true;
//     }
// }


//二维dp,O(n^2)
//62，53.81；   42.1，15.18
class Solution{
    public String longestPalindrome(String s){
        if(s==null || s.length()<2) return s;

        int length=s.length();
        boolean[][] dp=new boolean[length][length];
        //[i,j]是比区间，所以i==j时dp=true；并且因为dp[i,j]依赖dp[i+1,j-1]的值，所以按列填写
        int maxLen=1,maxPos=0;
        char[] schar=s.toCharArray();
        for(int i=0;i<length;i++) dp[i][i]=true;
        for(int j=1;j<length;j++){
            for(int i=0;i<j;i++){
                if(schar[i]==schar[j]){
                    //如果[i,i+1]，会找[i+1,i]这里是不应该存在的，是false
                    if(j-i<3) dp[i][j]=true;
                    else dp[i][j]=dp[i+1][j-1];
                }
                if(dp[i][j] && j-i+1>maxLen){
                    maxLen=j-i+1;
                    maxPos=i;
                }
            }
        }
        return s.substring(maxPos,maxPos+maxLen);
    }
}



//中心扩散,O(n^2)
//不是特殊的算法，只是一个针对此题的思路
//可作为中心的索引范围是[0,len-2],每个中心可能是奇数长度的中心也可能是偶数长度的中心
//11ms,92.37;  37.8,26.78
// class Solution{
//     public String longestPalindrome(String s){
//         int length=s.length();
//         if(length < 2) return s;
//         String res="";
//         char[] schar=s.toCharArray();
//         int maxLen=1;
//         int maxCen=0;
//         for(int i=0; i<length-1; i++){
//             int leftstr=centerSpread(schar,i,i);
//             int rightstr=centerSpread(schar,i,i+1);
//             int thismax=Math.max(leftstr,rightstr);
//             if(thismax > maxLen){
//                 maxLen=thismax;
//                 maxCen=i;
//             }
//         }
//         //注意是，maxLen*1.0/2，比如len=3，ceil()是2；而maxLen/2的ceil是1
//         int start=maxCen-((int)Math.ceil(maxLen*1.0/2)-1);
//         return s.substring(start,start+maxLen);
//     }

//     private int centerSpread(char[] schar, int left,int right){
//         while(left >= 0 && right < schar.length ) {
//             if(schar[left] == schar[right]){
//                 left--;
//                 right++;
//             }else{
//                 break;
//             }
//         }
//         return right-left-1;
//     }
// }

//leetcode submit region end(Prohibit modification and deletion)
