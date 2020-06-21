//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
//初次尝试，对一些组合写了很多层判断，最终没有写出
//不可行
// class Solution {
//     public int numDecodings(String s) {
//         int length=s.length();
//         if(s==null || length==0 ){
//             return 0;
//         }
//         //加一个出现连续0的处理
//         for(int i=0;i<s.length();i++){
//             if(i==0 && s.charAt(i)=='0'){
//                 return 0;
//             }
//             if(i>0 && s.charAt(i-1)=='0' && s.charAt(i)=='0'){
//                 return 0;
//             }
//         }
//         return decode(s,0);
//     }
//     private int decode(String s,int start){
//         if(start == s.length()){
//             return 1;
//         }
//         if(start==s.length()-1){
//             //只有一个
//             return 1;
//         }
//         if(s.charAt(start)=='0'){
//             return 0;
//         }
//         //>=2个
//         //这里检测start+1是否为0，即10或20；
//         //如果是就只能分两位，也保证了递归子数组start不可能是0
//         if(s.charAt(start+1)=='0'){
//             return decode(s,start+2);
//         }else{
//             //如果前2位小于等于26，可以分两位，不然只能分1位
//             int sum=(s.charAt(start)-'0')*10+(s.charAt(start+1)-'0');
//             if(sum<=26 ){
//                 return decode(s,start+1)+decode(s,start+2);
//             }else{
//                 return decode(s,start+1);
//             }
//         }
//     }
// }

//纯递归
//1341,5.01;  38.4,7.69
// class Solution{
//     public int numDecodings(String s) {
//         return s.length()==0 ? 0: numDecodings(0,s);
//     }
//     private int numDecodings(int p, String s) {
//         int n = s.length();
//         if(p == n) return 1;
//         if(s.charAt(p) == '0') return 0;
//         int res = numDecodings(p+1,s);
//         if( p < n-1 && (s.charAt(p)=='1'|| (s.charAt(p)=='2'&& s.charAt(p+1)<'7')))
//             res += numDecodings(p+2,s);
//         return res;
//     }
// }


//常规dp数组
//1,100;   37.8,7.69
// class Solution {
//     public int numDecodings(String s) {
//         char[] nums = s.toCharArray();
//         int len = nums.length;
//         int[] dp = new int[len+1];  // dp[i] 表示从第i+1个数到第n个数的所有方案数
//         dp[len] = 1;
//         dp[len-1]=s.charAt(len-1)=='0'?0:1;
//         // 从右往左
//         for(int i = len-2; i >= 0; i--) {
//             // 注意判断0字符
//             // 当开始位为0字符时不满足任意一个字母的解析，跳过，dp[i]=0;
//             if (nums[i] == '0') continue;
//             int num = 0;
//             //已经跳过i为0的情况了，所以直接计算前两位是否小于等于26即可
//             if((nums[i]-'0')*10+(nums[i+1]-'0') <= 26) dp[i]=dp[i+1]+dp[i+2];
//             else dp[i]=dp[i+1];
//         }
//         return dp[0];
//     }
// }

//常数空间
//1,100;  38.1,7.69
class Solution{
    public int numDecodings(String s){
        if(s==null || s.length()==0) return 0;
        char[] schar=s.toCharArray();
        int len=schar.length;
        int end=1,cur=0;
        //最后一位如果是0，就没有解
        if(schar[len-1]!='0') cur=1;
        for(int i=len-2;i>=0;i--){
            //end肯定是要更新为cur的，所以保存end后更新
            int tmp=end;
            end=cur;
            //只要是0，就无法解
            if(schar[i]=='0') cur=0;
            else{
                //不是0，就有解；如果两位数大于26，就无法有跨两位的解，cur仍然等于cur
                //可以有跨两位的解，就加上end的值
                int sum=(schar[i]-'0')*10+(schar[i+1]-'0');
                if(sum<=26) cur+=tmp;
            }
        }
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
