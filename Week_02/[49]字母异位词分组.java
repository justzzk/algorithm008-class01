//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //第一种方法，排序分组
    //8,98.91;  42.6,32.35
    // public List<List<String>> groupAnagrams(String[] strs) {
    //     ArrayList<List<String>> res=new ArrayList<>();
    //     if(strs.length==0){
    //         return res;
    //     }
    //     HashMap<String,List<String>> map=new HashMap<>();
    //     for(int i=0; i<strs.length; i++){
    //         char[] cstr=strs[i].toCharArray();
    //         Arrays.sort(cstr);
    //         String orderstr=String.valueOf(cstr);
    //         if(!map.containsKey(orderstr)){
    //             map.put(orderstr,new ArrayList<>());
    //         }
    //         map.get(orderstr).add(strs[i]);
    //     }
    //     res.addAll(map.values());
    //     return res;
    // }

    //21,28.35;  42.4,35.29
    public List<List<String>> groupAnagrams(String[] strs){
        ArrayList<List<String>> res=new ArrayList<>();
        if(strs.length==0){
            return res;
        }
        HashMap<String,List<String>> map=new HashMap<>();
        int[] count=new int[26];
        for(int i=0; i<strs.length; i++){
            Arrays.fill(count,0);
            char[] cstr=strs[i].toCharArray();
            for(char c:cstr){
                count[c-'a']++;
            }
            StringBuilder sb=new StringBuilder();
            for(int cnt:count){
                sb.append('#');
                sb.append(cnt);
            }
            String countstr=sb.toString();
            //使用Arrays.toString的时间开销比sb大，有27ms
            // String countstr= Arrays.toString(count);
            // if(!map.containsKey(countstr)){
            //     map.put(countstr,new ArrayList<>());
            // }
            map.putIfAbsent(countstr,new ArrayList<>());
            map.get(countstr).add(strs[i]);
        }
        res.addAll(map.values());
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
