//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
//定义方法判定字符串是不是回文串，再遇到不相等的两个时，检查删除左和删除右能不能得到回文串
//8,75.61;  40.5,6.67
class Solution{
    public boolean validPalindrome(String s){
        if(s==null || s.length()==0) return false;
        char[] schar=s.toCharArray();
        int length=schar.length;

        for(int left=0,right=length-1;left<right;left++,right--){
            if(schar[left]!=schar[right]){
                return isPalindrome(schar,left,right-1) || isPalindrome(schar,left+1,right);
            }
        }
        return true;
    }

    private boolean isPalindrome(char[] schar,int start,int end){
        for(;start<end;start++,end--){
            if(schar[start]!=schar[end]) return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
