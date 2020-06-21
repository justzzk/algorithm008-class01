//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
//1,78.63;  38.2,14.29
class Solution {
    public String reverseOnlyLetters(String S) {
        if(S==null || S.length()==0) return "";
        char[] schar=S.toCharArray();
        int length=schar.length;
        for(int left=0,right=length-1;left<right;left++,right--){
            while(left<right && !Character.isLetter(schar[left])) left++;
            while(left<right && !Character.isLetter(schar[right])) right--;
            char tmp=schar[left];
            schar[left]=schar[right];
            schar[right]=tmp;
        }
        return String.valueOf(schar);
    }
}

//1,83.63;  37.8,14.29
class Solution {
    public String reverseOnlyLetters(String S) {
        char[] chars=S.toCharArray();
        int left=0;
        int right=chars.length-1;
        while(left < right){
            // while(left < right && !isChar(chars[left])) left++;
            // while(left < right && !isChar(chars[right])) right--;
            while(left < right && !Character.isLetter(chars[left])) left++;
            while(left < right && !Character.isLetter(chars[right])) right--;
            if(left<right){
                // swap(chars,left++,right--);
                chars[left] ^= chars[right];
                chars[right] ^= chars[left];
                chars[left++] ^= chars[right--];
            }
        }
        // return new String(chars);
        return String.valueOf(chars);
    }

    // private boolean isChar(char c){
    //     return ((c-'a'>=0 && c-'a'<26) || (c-'A'>=0 && c-'A'<26));
    // }

    // private void swap(char[] chars,int left,int right){
    //     char tmp=chars[left];
    //     chars[left]=chars[right];
    //     chars[right]=tmp;
    // }
}


//stack,两次循环
//2,31.97;  38.1,14.29
// class Solution{
//     public String reverseOnlyLetters(String S){
//         Stack<Character> stack=new Stack<>();
//         for(char c:S.toCharArray()){
//             if(Character.isLetter(c)){
//                 stack.push(c);
//             }
//         }

//         StringBuilder ans=new StringBuilder();
//         for(char c:S.toCharArray()){
//             if(Character.isLetter(c)){
//                 ans.append(stack.pop());
//             }else{
//                 ans.append(c);
//             }
//         }

//         return ans.toString();
//     }
// }

//翻转指针
//1.83.63;  37.5,14.29
// class Solution{
//     public String reverseOnlyLetters(String S){
//         int index=S.length()-1;
//         StringBuilder ans=new StringBuilder();
//         for(char c:S.toCharArray()){
//             if(Character.isLetter(c)){
//                 while(index>=0 && !Character.isLetter(S.charAt(index))) index--;
//                 ans.append(S.charAt(index--));
//             }else{
//                 ans.append(c);
//             }
//         }
//         return ans.toString();
//     }
// }


//leetcode submit region end(Prohibit modification and deletion)
