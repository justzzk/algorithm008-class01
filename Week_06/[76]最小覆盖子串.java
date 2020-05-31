//给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。 
//
// 示例： 
//
// 输入: S = "ADOBECODEBANC", T = "ABC"
//输出: "BANC" 
//
// 说明： 
//
// 
// 如果 S 中不存这样的子串，则返回空字符串 ""。 
// 如果 S 中存在这样的子串，我们保证它是唯一的答案。 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window


//leetcode submit region begin(Prohibit modification and deletion)
//没考虑到t中有相同字符
// class Solution {
//     public String minWindow(String s, String t) {
//         int slen=s.length();
//         int tlen=t.length();
//         if(s==null || t==null || slen < tlen) return "";
//         int maxpos=-1;
//         int minpos=slen;
//         char[] schar=s.toCharArray();
//         HashMap<Character,Integer> map=new HashMap<>();
//         HashSet<Character> set=new HashSet<>();
//         for(char c:t.toCharArray()) set.add(c);
//         for(int i=0;i<slen;i++){
//             if(set.contains(schar[i])){
//                 map.put(schar[i],i);
//                 maxpos=i;
//                 minpos=Collections.min(map.values());
//             }
//         }
//         return map.size()==tlen?s.substring(minpos,maxpos+1):"";
//     }
// }


//13ms,73.83;  40.9,6.67
// class Solution {
//     public String minWindow(String s, String t) {
//         Map<Character, Integer> lookup = new HashMap<>();
//         char[] schar=s.toCharArray();
//         char[] tchar=t.toCharArray();
//         for (char c : schar) lookup.put(c, 0);
//         for (char c : tchar) {
//             if (lookup.containsKey(c)) lookup.put(c, lookup.get(c) + 1);
//             else return "";
//         }
//         int start = 0;
//         int end = 0;
//         int min_len = Integer.MAX_VALUE;
//         int counter = t.length();
//         String res = "";
//         while (end < s.length()) {
//             char c1 = schar[end];
//             if (lookup.get(c1) > 0) counter--;
//             lookup.put(c1, lookup.get(c1) - 1);
//             end++;
//             while (counter == 0) {
//                 if (min_len > end - start) {
//                     min_len = end - start;
//                     res = s.substring(start, end);
//                 }
//                 char c2 = schar[start];
//                 if (lookup.get(c2) == 0) counter++;
//                 lookup.put(c2, lookup.get(c2) + 1);
//                 start++;
//             }
//         }
//         return res;
//     }
// }


//双指针/滑动窗口
//3ms,99.34;  39.7,33.33
class Solution{
    public String minWindow(String s, String t) {
        //记录t中的字符是否已被全部包含，主要t中还可能包含重复字符
        //用2个数组，一个记录t中出现的字符和次数，另一个记录已包含的字符和次数
        char[] schar=s.toCharArray();
        char[] tchar=t.toCharArray();
        int slen=schar.length;
        int tlen=tchar.length;
        int[] tcount=new int[128];
        for(char tc:tchar) tcount[tc]++;
        int[] havecount=new int[128];

        //左右指针和记录最终位置的变量
        int left=0;
        int right=0;
        int minpos=-1;
        int maxpos=slen+1;
        //记录已经得到几个t中字符
        int count=0;

        //开始遍历字符串s
        while(right<slen){
            //把即使t中没有出现的也记录在havecount中，因为无法判断当前字符是否出现在t中
            char rightchar=schar[right];
            if(tcount[rightchar] > 0 && tcount[rightchar] > havecount[rightchar]){
                count++;
            }
            havecount[rightchar]++;

            //当已经凑齐了t
            while( count == tlen ){
                //更新索引
                char leftchar=schar[left];
                if( right - left < maxpos - minpos ){
                    minpos=left;
                    maxpos=right;
                }

                //如果当前这个是凑齐t的字符之一，跳过之后应该count--
                if(tcount[leftchar] == havecount[leftchar]) count--;
                havecount[leftchar]--;
                left++;
            }
            right++;
        }
        return minpos==-1? "":s.substring(minpos,maxpos+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
