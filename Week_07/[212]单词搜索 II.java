//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
// 
//
// 示例: 
//
// 输入: 
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"] 
//
// 说明: 
//你可以假设所有输入都由小写字母 a-z 组成。 
//
// 提示: 
//
// 
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？ 
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。 
// 
// Related Topics 字典树 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//暴力
//803,7.53;  42.3,100
// class Solution {
//     public List<String> findWords(char[][] board, String[] words) {
//         List<String> res=new ArrayList<>();
//         if(board==null || board.length==0 || words==null || words.length==0) return res;
//         int row=board.length;
//         int col=board[0].length;
//         for(String s:words){
//             boolean[][] visited=new boolean[row][col];
//             char[] schar=s.toCharArray();
//             //遍历board,找到第一个字符后dfs
//             for(int i=0;i<row;i++){
//                 for(int j=0;j<col;j++){
//                     if(board[i][j]==schar[0]){
//                         visited[i][j]=true;
//                         if(dfs(board,schar,1,visited,i,j)){
//                             if(!res.contains(s)) res.add(s);
//                         }
//                         visited[i][j]=false;
//                     }
//                 }
//             }
//         }
//         return res;
//     }

//     private boolean dfs(char[][] board,char[] wchar,int level,boolean[][] visited,int x,int y){
//         if(level == wchar.length) return true;

//         int[] dx={-1,1,0,0};
//         int[] dy={0,0,-1,1};
//         for(int i=0;i<4;i++){
//             int newx=x+dx[i];
//             int newy=y+dy[i];
//             if(newx>=0 && newx<board.length && newy>=0 && newy<board[0].length
//                 && board[newx][newy]==wchar[level] && !visited[newx][newy]){
//                 visited[newx][newy]=true;
//                 if(dfs(board,wchar,level+1,visited,newx,newy)) return true;
//                 visited[newx][newy]=false;
//             }
//         }
//         return false;
//     }
// }

//trie，
//13,74.88;   48.3,40
// class Solution {
//     public List<String> findWords(char[][] board, String[] words) {
//         //构建字典树
//         wordTrie myTrie=new wordTrie();
//         trieNode root=myTrie.root;
//         for(String s:words)
//             myTrie.insert(s);

//         //使用set防止重复
//         Set<String> result =new HashSet<>();
//         int m=board.length;
//         int n=board[0].length;
//         boolean [][]visited=new boolean[m][n];
//         //遍历整个二维数组
//         for(int i=0;i<board.length; i++){
//             for (int j = 0; j < board [0].length; j++){
//                 find(board,visited,i,j,m,n,result,root);
//             }
//         }
//         System.out.print(result);
//         return new LinkedList<String>(result);
//     }
//     private void find(char [] [] board, boolean [][]visited,int i,int j,int m,int n,Set<String> result,trieNode cur){
//         //边界以及是否已经访问判断
//         if(i<0||i>=m||j<0||j>=n||visited[i][j])
//             return;
//         cur=cur.child[board[i][j]-'a'];
//         visited[i][j]=true;
//         if(cur==null)
//         {
//             //如果单词不匹配，回退
//             visited[i][j]=false;
//             return;
//         }
//         //找到单词加入
//         if(cur.isLeaf)
//         {
//             result.add(cur.val);
//             //找到单词后不能回退，因为可能是“ad” “addd”这样的单词得继续回溯
// //            visited[i][j]=false;
// //            return;
//         }
//         find(board,visited,i+1,j,m,n,result,cur);
//         find(board,visited,i,j+1,m,n,result,cur);
//         find(board,visited,i,j-1,m,n,result,cur);
//         find(board,visited,i-1,j,m,n,result,cur);
//         //最后要回退，因为下一个起点可能会用到上一个起点的字符
//         visited[i][j]=false;
//     }


// }

// //字典树
// class wordTrie{
//     public trieNode root=new trieNode();
//     public void insert(String s){
//         trieNode cur=root;
//         for(char c:s.toCharArray()){
//             if(cur.child[c-'a']==null){
//                 cur.child [c-'a'] = new trieNode();
//                 cur=cur.child[c-'a'];
//             }else
//                 cur=cur.child [c-'a'];
//         }
//         cur.isLeaf=true;
//         cur.val=s;
//     }
// }
// //字典树结点
// class trieNode{
//     public String val;
//     public trieNode[] child=new trieNode[26];
//     public boolean isLeaf=false;

//     trieNode(){

//     }
// }


//流啤的trie
//9,97.93;  48,40
class Solution{

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }


    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }
}



//自己实现的trie
//遍历主体，是在遍历整个board的过程中，检查dfs的下一个节点是否在trie树中决定是否对下一个节点dfs
//如果下一个节点的字符没有出现在trie中，说明没有必要遍历下一个位置
//中间为了还原dfs前的状态，使用回溯，和改变已访问位置的字符 的方法
//11,86.26;  46.4,80
class Solution{

    class TrieNode {
        public TrieNode[] links;
        public String string;

        public TrieNode(){
            links=new TrieNode[26];
        }
    }
    class Trie{
        public TrieNode root;

        public Trie(){
            root=new TrieNode();
        }

        //因为只需要添加字符串，所以只写insert方法
        public void insert(String s){
            char[] schar=s.toCharArray();
            TrieNode cur=root;
            for(char c:schar){
                if(cur.links[c-'a']==null){
                    cur.links[c-'a']=new TrieNode();
                }
                cur=cur.links[c-'a'];
            }
            cur.string=s;
        }
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res=new ArrayList<>();
        if(board==null || board.length==0 || words==null || words.length==0) return res;
        Trie trie=new Trie();
        for(String s:words) trie.insert(s);

        int row=board.length;
        int col=board[0].length;

        //开始遍历board
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                dfs(board,i,j,res,trie.root);
            }
        }
        return res;
    }

    private void dfs(char[][] board,int x,int y,List<String> res,TrieNode node){
        //检查新的这个节点有没有出现在trie中,
        char c=board[x][y];
        //如果不包含在trie中或者是'#'，直接退出
        if(c=='#' || node.links[c-'a']==null) return;
        board[x][y]='#';
        //新的节点包含了新遍历的字符
        node=node.links[c-'a'];
        //查看是否是新的string，是的话添加到res
        if(node.string!= null){
            res.add(node.string);
            node.string=null;
        }

        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1};
        for(int i=0;i<4;i++){
            int newx=x+dx[i];
            int newy=y+dy[i];
            if(newx>=0 && newx<board.length && newy>=0 && newy<board[0].length){
                dfs(board,newx,newy,res,node);
            }
        }
        board[x][y]=c;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
