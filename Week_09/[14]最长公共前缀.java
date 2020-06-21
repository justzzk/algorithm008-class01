//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)

//水平扫描1
//执行用时 :1 ms, 在所有 Java 提交中击败了80.35%的用户
//执行用时 :4 ms, 在所有 Java 提交中击败了14.80%的用户
//内存消耗 :38.2 MB, 在所有 Java 提交中击败了5.05%的用户
//class Solution {
//    public String longestCommonPrefix(String[] strs) {
//        if(strs.length==0 || strs==null) return "";
//        int minindex=strs[0].length();
//        for (int i = 1; i < strs.length; i++) {
//            int j;
//            for (j = 0; j < Math.min(strs[i].length(),minindex); j++) {
//                if(strs[i].charAt(j)!=strs[0].charAt(j)) break;
//            }
//            minindex=Math.min(minindex,j);
//        }
//        return strs[0].substring(0,minindex);
//    }
//
//    public static void main(String[] args) {
////        String s="abc";
////        System.out.println(s.substring(0,0));  //""
//    }
//}

//水平扫描2
//执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
//内存消耗 :38.3 MB, 在所有 Java 提交中击败了5.05%的用户
//class Solution{
//    public String longestCommonPrefix(String[] strs){
//        if(strs.length==0 || strs==null) return "";
//        String ans=strs[0];
//        for (int i = 1; i < strs.length; i++) {
////            while(!strs[i].startsWith(ans)){   //亦可
//            while(strs[i].indexOf(ans)!=0){
//                ans=ans.substring(0,ans.length()-1);
//                if(ans.length()==0) return ans;
//            }
//        }
//        return ans;
//    }
//}



//垂直扫描
//从strs[0].charAt(i)，查看strs剩下的所有串的charAt(i)是否和strs[0].charAt(i)相同
//执行用时 :1 ms, 在所有 Java 提交中击败了80.33%的用户
//内存消耗 :37.8 MB, 在所有 Java 提交中击败了26.07%的用户
class Solution{
    public String longestCommonPrefix(String[] strs){
        if(strs.length==0 || strs==null) return "";
//        int minlen=Integer.MAX_VALUE;
//        for (String word : strs) {
//            minlen=Math.min(minlen,word.length());
//        }
//        for (int i = 0; i < minlen; i++) {
//            char curletter=strs[0].charAt(i);
//            for (int j = 1; j < strs.length; j++) {
//                if (strs[j].charAt(i)!=curletter)
//                    return strs[0].substring(0,i);
//            }
//        }
//        return strs[0].substring(0,minlen);

        //0,100;  37.6,42.50
        for (int i = 0; i < strs[0].length(); i++) {
            char curletter=strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if(i==strs[j].length() || strs[j].charAt(i)!=curletter)
                    return strs[0].substring(0,i);
            }
        }
        return strs[0];
    }
}



//分治
//执行用时 :3 ms, 在所有 Java 提交中击败了21.12%的用户
//内存消耗 :37.9 MB, 在所有 Java 提交中击败了16.04%的用户
//class Solution{
//
//    //求两个字符串的lcp
//    private String prefixForTwo(String str1,String str2){
//        String ans=str1.length()>str2.length()?str2:str1;
//        for (int i = 0; i < ans.length(); i++) {
//            if(str1.charAt(i)!=str2.charAt(i))
//                return ans.substring(0,i);
//        }
//        return ans;
//    }
//
//    private String LCP(String[] strs,int left,int right){
//        if(left==right) return strs[left];
//        int mid=(left+right)/2;
//        String str1=LCP(strs,left,mid);
//        String str2=LCP(strs,mid+1,right);
//        return prefixForTwo(str1,str2);
//    }
//
//    public String longestCommonPrefix(String[] strs){
//        if(strs.length==0 || strs==null) return "";
//        return LCP(strs,0,strs.length-1);
//    }
//}


//二分查找执行用时 :1 ms, 在所有 Java 提交中击败了80.33%的用户
//内存消耗 :38.1 MB, 在所有 Java 提交中击败了5.80%的用户
//class Solution{
//
//    private boolean isCommonPrefix(String[] strs,int len){
//        String prefix=strs[0].substring(0,len);
//        for (int i = 1; i < strs.length; i++) {
//            if(!strs[i].startsWith(prefix)) return false;
//        }
//        return true;
//    }
//
//    public String longestCommonPrefix(String[] strs){
//        if(strs==null || strs.length==0) return "";
//        int minlen=Integer.MAX_VALUE;
//        for(String word:strs){
//            minlen=Math.min(minlen,word.length());
//        }
//        int low=1;
//        int high=minlen;
//        while(low <= high){
//            int mid=(low+high)/2;
//            if(isCommonPrefix(strs,mid)){
//                low=mid+1;
//            }else{
//                high=mid-1;
//            }
//        }
//        return strs[0].substring(0,(low+high)/2);
//    }
//}


//找出最短的str,暴力匹配
//0,100;  37.9,17.5
// class Solution {
//     public String longestCommonPrefix(String[] strs) {
//         String res="";
//         if(strs==null || strs.length==0) return res;
//         int startlen=strs[0].length();
//         for(int i=1;i<strs.length;i++){
//             int length=strs[i].length();
//             if(strs[i].length()<startlen) startlen=length;
//         }
//         for(int i=startlen;i>=0;i--){
//             res=strs[0].substring(0,i);
//             int index=-1;
//             for(int j=0;j<strs.length;j++){
//                 index=strs[j].indexOf(res);
//                 if(index!=0) break;
//             }
//             if(index==0) break;
//         }
//         return res;
//     }
// }


//trie
//1ms,78.66; 39.4,5
class Solution{

    class TrieNode {
        public boolean isEnd;
        public TrieNode[] links;
        public int size;

        public TrieNode(){
            links=new TrieNode[26];
        }

        public void put(char c){
            TrieNode node=links[c-'a'];
            if(node==null){
                links[c-'a']=new TrieNode();
                size++;
            }
        }
    }

    class Trie{

        public TrieNode root;

        public Trie(){ root=new TrieNode();}

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            char[] wchar=word.toCharArray();
            TrieNode cur=root;
            for(char c:wchar){
                if(cur.links[c-'a']==null){
                    cur.links[c-'a']=new TrieNode();
                    cur.size++;
                }
                cur=cur.links[c-'a'];
            }
            cur.isEnd=true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] wchar=word.toCharArray();
            TrieNode cur=root;
            for(char c:wchar){
                cur=cur.links[c-'a'];
                if(cur==null) return false;
            }
            return cur.isEnd;
        }

        /** Returns if there is any word in  trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] prechar=prefix.toCharArray();
            TrieNode cur=root;
            for(char c:prechar){
                cur=cur.links[c-'a'];
                if(cur==null) return false;
            }
            return true;
        }
    }

    public String longestCommonPrefix(String[] strs){
        if(strs==null || strs.length==0) return "";
        //检查生成的trie从root开始的单路线
        //这样有问题，如果有str==""，就应该返回""
        //可以先找出最短的str，走trie
        Trie trie=new Trie();
        for(String s:strs){ trie.insert(s);}

        String shortest=strs[0];
        int length=shortest.length();
        for(int i=1;i<strs.length;i++){
            int len=strs[i].length();
            if(len < length) {
                shortest=strs[i];
                length=len;
            }
            if(length==0) return "";
        }

        StringBuilder sb=new StringBuilder();
        TrieNode cur=trie.root;
        for(int i=0;cur.size==1 && i<length;cur=cur.links[shortest.charAt(i)-'a'],i++){
            sb.append(shortest.charAt(i));
        }
        return sb.toString();
    }
}

//leetcode submit region end(Prohibit modification and deletion)
