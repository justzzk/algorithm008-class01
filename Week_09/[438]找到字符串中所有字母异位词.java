//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。 
//
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。 
//
// 说明： 
//
// 
// 字母异位词指字母相同，但排列不同的字符串。 
// 不考虑答案输出的顺序。 
// 
//
// 示例 1: 
//
// 
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
// 
//
// 示例 2: 
//
// 
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
// 
// Related Topics 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
//直接思路，用int[26]数组记录子串状态，并与p的状态数组比较，相等则添加本次索引
//6.90.4;  41.2,5.88
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //特殊情况处理
        List<Integer> res=new ArrayList<>();
        if(s==null || s.length()<p.length()) return res;

        char[] schar=s.toCharArray();
        int slen=s.length(),plen=p.length();
        //初始化p的统计数组
        int[] pcnt=new int[26];
        for(char c:p.toCharArray()) pcnt[c-'a']++;
        //初始化s的统计数组
        boolean lastIn=false;
        int[] scnt=new int[26];
        for(int i=0;i<plen;i++) scnt[schar[i]-'a']++;
        if(isEqual(pcnt,scnt)){
            res.add(0);
            lastIn=true;
        }

        for(int i=1;i<slen-plen+1;i++){
            char out=schar[i-1];
            char in=schar[i+plen-1];
            if(lastIn){
                if(out==in) res.add(i);
                else {
                    lastIn=false;
                    scnt[out-'a']--;
                    scnt[in-'a']++;
                }
            }else{
                scnt[out-'a']--;
                scnt[in-'a']++;
                if(isEqual(pcnt,scnt)){
                    res.add(i);
                    lastIn=true;
                }
            }
        }
        return res;
    }

    private boolean isEqual(int[] pcnt,int[] scnt){
        for(int i=0;i<26;i++){
            if(pcnt[i]!=scnt[i]) return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
