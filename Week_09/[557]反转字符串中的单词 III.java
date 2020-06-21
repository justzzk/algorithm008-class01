//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 示例 1: 
//
// 
//输入: "Let's take LeetCode contest"
//输出: "s'teL ekat edoCteeL tsetnoc" 
// 
//
// 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
//3,100;  40.2,5
class Solution {
    public String reverseWords(String s) {
        if(s==null || s.length()==0) return "";
        char[] schar=s.toCharArray();
        int start=0,end=0;
        while(start<schar.length){
            while(start<schar.length && schar[start]==' ') start++;
            end=start;
            while(end<schar.length && schar[end]!=' ') end++;
            reverse(schar,start,end-1);
            start=end;
        }
        return String.valueOf(schar);
    }

    private void reverse(char[] schar,int start,int end){
        for(int left=start,right=end;left<right;left++,right--){
            char c=schar[left];
            schar[left]=schar[right];
            schar[right]=c;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
