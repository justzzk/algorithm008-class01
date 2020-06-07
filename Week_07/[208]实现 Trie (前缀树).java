//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


//48，51.43；  50.7，83.33
// class Trie {
//     private boolean isEnd;
//     private Trie[] next;
//     /** Initialize your data structure here. */
//     public Trie() {
//         isEnd = false;
//         next = new Trie[26];
//     }

//     /** Inserts a word into the trie. */
//     public void insert(String word) {
//         if (word == null || word.length() == 0) return;
//         Trie curr = this;
//         char[] words = word.toCharArray();
//         for (int i = 0;i < words.length;i++) {
//             int n = words[i] - 'a';
//             if (curr.next[n] == null) curr.next[n] = new Trie();
//             curr = curr.next[n];
//         }
//         curr.isEnd = true;
//     }

//     /** Returns if the word is in the trie. */
//     public boolean search(String word) {
//         Trie node = searchPrefix(word);
//         return node != null && node.isEnd;
//     }

//     /** Returns if there is any word in the trie that starts with the given prefix. */
//     public boolean startsWith(String prefix) {
//         Trie node = searchPrefix(prefix);
//         return node != null;
//     }

//     private Trie searchPrefix(String word) {
//         Trie node = this;
//         char[] words = word.toCharArray();
//         for (int i = 0;i < words.length;i++) {
//             node = node.next[words[i] - 'a'];
//             if (node == null) return null;
//         }
//         return node;
//     }
// }


//39,99.33;   50.4,100
class Trie{

    class TrieNode {
        public boolean isEnd;
        public TrieNode[] links;

        public TrieNode(){links=new TrieNode[26];}
    }

    private TrieNode root;

    public Trie(){ root=new TrieNode();}

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        char[] wchar=word.toCharArray();
        TrieNode cur=root;
        for(char c:wchar){
            if(cur.links[c-'a']==null){
                cur.links[c-'a']=new TrieNode();
            }
            cur=cur.links[c-'a'];
        }
        cur.isEnd=true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node=searchPrefix(word);
        return node!=null && node.isEnd;
    }

    /** Returns if there is any word in  trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node=searchPrefix(prefix);
        return node!=null ;
    }

    public TrieNode searchPrefix(String word){
        char[] wchar=word.toCharArray();
        TrieNode cur=root;
        for(char c:wchar){
            cur=cur.links[c-'a'];
            if(cur==null) return null;
        }
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
