//实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入: "Hello"
//输出: "hello" 
//
// 示例 2： 
//
// 
//输入: "here"
//输出: "here" 
//
// 示例 3： 
//
// 
//输入: "LOVELY"
//输出: "lovely"
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
//0，100；  37.3，5.88
class Solution {
    public String toLowerCase(String str) {
        char[] schar=str.toCharArray();
        for(int i=0;i<schar.length;i++){
            if(schar[i]>=65 && schar[i]<=90) schar[i]+=32;
        }
        return new String(schar);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
