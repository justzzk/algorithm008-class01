//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
//输出: false
// 
// Related Topics 双指针 字符串


//leetcode submit region begin(Prohibit modification and deletion)
//首尾双指针
//3,93.04;   39.9,7.14
// class Solution {
//     public boolean isPalindrome(String s) {
//         if(s==null || s.length()==0) return true;
//         s=s.toLowerCase();
//         char[] schar=s.toCharArray();
//         for(int start=0,end=schar.length-1;start<end;start++,end--){
//             while(start<end && !isLetterOrNum(schar[start]) ) start++;
//             while(start<end && !isLetterOrNum(schar[end])) end--;
//             if(schar[start]!=schar[end]) return false;
//         }
//         return true;
//     }
//     private boolean isLetterOrNum(char c){
//         return (c>=48 && c<=57) || (c>=65 && c<=90) || (c>=97 && c<=122);
//     }
// }

//6，39.16；  39.6，7.14
class Solution{
    public boolean isPalindrome(String s) {
        String str = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()){
            if(Character.isLetterOrDigit(c))    sb.append(c);
        }
        return sb.toString().equals(sb.reverse().toString());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
