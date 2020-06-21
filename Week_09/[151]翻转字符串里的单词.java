//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 
//
// 示例 1： 
//
// 输入: "the sky is blue"
//输出: "blue is sky the"
// 
//
// 示例 2： 
//
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 
//
// 说明： 
//
// 
// 无空格字符构成一个单词。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 
//
// 进阶： 
//
// 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。 
// Related Topics 字符串

//leetcode submit region begin(Prohibit modification and deletion)


//遍历一遍原字符串，分割保存其中单词，然后使用栈逆序输出
//6,59.95;  40.3,5.26
// class Solution {
//     public String reverseWords(String s) {
//         if(s==null || s.length()==0) return "";
//         char[] schar=s.trim().toCharArray();
//         int length=schar.length;
//         Deque<String> stack=new ArrayDeque<>();
//         int index=0;
//         while(index<length){
//             StringBuilder sb=new StringBuilder();
//             while(index<length && schar[index]!=' ') sb.append(schar[index++]);
//             stack.addLast(sb.toString());
//             while(index<length && schar[index]==' ') index++;
//         }
//         StringBuilder res=new StringBuilder();
//         while(!stack.isEmpty()){
//             if(stack.size()==1) res.append(stack.pollLast());
//             else res.append(stack.pollLast()).append(' ');
//         }
//         return res.toString();
//     }
// }


//简化上面的写法
//2,90.81;  40,5.26
//trim就是首尾遍历，所以复杂度很低
// class Solution{
//     public String reverseWords(String s){
//         if(s==null || s.length()==0) return "";
//         char[] schar=s.toCharArray();
//         int length=schar.length;

//         StringBuilder res=new StringBuilder();
//         int index=length-1;
//         while(index >= 0){
//             while(index >= 0 && schar[index]==' ') index--;

//             int end=index;
//             while(index >= 0 && schar[index]!=' ') index--;
//             //因为这里是取s的子串，如果之前s又trim了，就会出错
//             res.append(s.substring(index+1,end+1)).append(" ");
//         }
//         return res.toString().trim();
//     }
// }


//完全使用API
//7,51.68;  40,5.26
// class Solution{
//     public String reverseWords(String s){
//         // List<String> list=Arrays.asList(s.trim().split(" +"));
//         // Collections.reverse(list);
//         // return String.join(" ",list);

//         String[] words = s.trim().split(" +");
//         Collections.reverse(Arrays.asList(words));
//         System.out.println(words instanceof String[]);
//         return String.join(" ", words);
//     }
// }



//trim split后，将数组中单词逆序添加到sb
// 2,90.81;  39.8,5.26
// class Solution {
//     public String reverseWords(String s) {
//         String[] words = s.trim().split(" ");
//         int len = words.length;
//         StringBuilder ans = new StringBuilder();
//         ans.append(words[len - 1]);
//         for (int i = len - 2; i >= 0; i --) {
//             if (!words[i].equals("")) ans.append(" " + words[i]);
//         }
//         return ans.toString();
//     }
// }

//对上面的split使用正则表达式，看一下性能会不会又很大差距
//7,51.68;  40,5.26
//可以看出使用正则确实开销大很多
// class Solution {
//     public String reverseWords(String s) {
//         String[] words = s.trim().split(" +");
//         int len = words.length;
//         StringBuilder ans = new StringBuilder();
//         ans.append(words[len - 1]);
//         for (int i = len - 2; i >= 0; i --) ans.append(" " + words[i]);
//         return ans.toString();
//     }
// }

//递归
//1,99.99;  40,5.26
class Solution{
    public String reverseWords(String s){
        if(s==null || s.length()==0) return "";
        char[] schar=s.toCharArray();
        return reverseHelper(schar,0,new StringBuilder()).toString().trim();
    }

    private StringBuilder reverseHelper(char[] schar,int start,StringBuilder sb){
        //跳过空格
        while(start<schar.length && schar[start]==' ') start++;
        //递归中止
        if(start==schar.length) return sb;
        //start为单词起点，接下来找单词终点
        int end=start;
        while(end<schar.length && schar[end]!=' ') end++;

        //递归
        reverseHelper(schar,end,sb);

        //递归后本层单词之后的都已经添加到sb中了，这里要添加本轮的单词
        sb.append(new String(schar,start,end-start)).append(" ");
        return sb;
    }
}


//			执行耗时:1 ms,击败了100.00% 的Java用户
//			内存消耗:39.1 MB,击败了5.41% 的Java用户
class Solution {
    public String reverseWords(String s) {
        //删除前后空格
        s = s.trim();
        //如果为空直接返回
        if (s.equals("")) return s;
        //按照空格划分
        String[] str = s.split(" ");
        //使用正则6ms
//        String[] str = s.split("\\s+");
        int n = str.length;
        StringBuilder result = new StringBuilder();
        //从后往前遍历数组，
        for (int i = n - 1;i >= 0;i--){
            //防止出现多个空格
            //多个空格在split的时候会分割为"",长度为0的空串
            //使用正则可以不用这一步判断
            if (str[i].length() != 0) {
                result.append(str[i]).append(" ");
            }

        }
        //删除结尾多余空格
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

}


//leetcode submit region end(Prohibit modification and deletion)
