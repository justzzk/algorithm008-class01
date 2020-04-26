//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1:
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
//
//
// 示例 2:
//
// 输入: s = "rat", t = "car"
//输出: false
//
// 说明:
//你可以假设字符串只包含小写字母。
//
// 进阶:
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
// Related Topics 排序 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
//2，99.9； 40.2，5.66
// class Solution {
//     public boolean isAnagram(String s, String t) {
//         int[] fre = new int[26];
//         char[] schar=s.toCharArray();
//         char[] tchar=t.toCharArray();
//         for (char c:schar) {
//             fre[c-'a']+=1;
//         }

//         for(char c: tchar){
//             fre[c-'a']-=1;
//             if(fre[c-'a'] < 0){
//                 return false;
//             }
//         }

//         for(int i:fre){
//             if(i!=0){
//                 return false;
//             }
//         }
//         return true;
//     }
// }

//2，99.9； 40.2，5.66
// class Solution {
//     public boolean isAnagram(String s, String t) {
//直接判断是否相等
// if(s.length()!=t.length()){
//             return false;
//         }
//         int[] fre = new int[26];
//         char[] schar=s.toCharArray();
//         char[] tchar=t.toCharArray();
//         for(int i=0; i<schar.length; i++){
//             fre[schar[i]-'a']++;
//             fre[tchar[i]-'a']--;
//         }
//         for(int i:fre){
//             if(i<0) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }

class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int[] fre = new int[26];
        char[] schar=s.toCharArray();
        char[] tchar=t.toCharArray();
        for (char c:schar) {
            fre[c-'a']++;
        }

        for(char c: tchar){
            fre[c-'a']--;
            if(fre[c-'a'] < 0){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
