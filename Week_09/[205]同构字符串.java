//给定两个字符串 s 和 t，判断它们是否是同构的。 
//
// 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。 
//
// 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。 
//
// 示例 1: 
//
// 输入: s = "egg", t = "add"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "foo", t = "bar"
//输出: false 
//
// 示例 3: 
//
// 输入: s = "paper", t = "title"
//输出: true 
//
// 说明: 
//你可以假设 s 和 t 具有相同的长度。 
// Related Topics 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
//默认只有26个小写字母，并不
// class Solution {
//     public boolean isIsomorphic(String s, String t) {
//         char[] schar=s.toCharArray();
//         char[] tchar=t.toCharArray();
//         int length=schar.length;
//         //char的默认值是空字符null
//         char[] alert=new char[26];
//         boolean[] visited=new boolean[26];
//         for(int i=0;i<length;i++){
//             if(alert[schar[i]-'a']==0) {
//                 if(visited[tchar[i]-'a']) return false;
//                 else {
//                     alert[schar[i]-'a']=tchar[i];
//                     visited[tchar[i]-'a']=true;
//                 }
//             }
//             else if(alert[schar[i]-'a']!=tchar[i]) return false;
//         }
//         return true;
//     }
// }


//2，99.84； 39.9，5.88
class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] schar=s.toCharArray();
        char[] tchar=t.toCharArray();
        int length=schar.length;
        //char的默认值是空字符null
        char[] alert=new char[128];
        boolean[] visited=new boolean[128];
        for(int i=0;i<length;i++){
            if(alert[schar[i]]==0) {
                if(visited[tchar[i]]) return false;
                else {
                    alert[schar[i]]=tchar[i];
                    visited[tchar[i]]=true;
                }
            }
            else if(alert[schar[i]]!=tchar[i]) return false;
        }
        return true;
    }
}



//一种简化一点的写法
//两个字符串中，相同索引i 对应的map的值是相同的，并且不同i 不同，即可保证替换的两个字母一一对应
class Solution{
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        int[] mapS = new int[128];
        int[] mapT = new int[128];
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            //当前的映射值是否相同
            if (mapS[c1] != mapT[c2]) {
                return false;
            } else {
                //是否已经修改过，修改过就不需要再处理
                //相同可能是还没有修改，也可能是已修改
                if (mapS[c1] == 0) {
                    mapS[c1] = i + 1;
                    mapT[c2] = i + 1;
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
