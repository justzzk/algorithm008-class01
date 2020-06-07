//给定一个二维网格和一个单词，找出该单词是否存在于网格中。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//这个不用trie也可以,dfs+回溯
//7，81.88；  40.8，14.28
class Solution {
    public boolean exist(char[][] board, String word) {
        if(board==null || board.length==0 || word==null || word.length()==0) return false;
        int row=board.length;
        int col=board[0].length;
        boolean[][] visited=new boolean[row][col];
        char[] wchar=word.toCharArray();
        //遍历board,找到第一个字符后dfs
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]==wchar[0]){
                    visited[i][j]=true;
                    if(dfs(board,wchar,1,visited,i,j)){
                        return true;
                    }
                    visited[i][j]=false;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board,char[] wchar,int level,boolean[][] visited,int x,int y){
        if(level == wchar.length) return true;

        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1};
        for(int i=0;i<4;i++){
            int newx=x+dx[i];
            int newy=y+dy[i];
            if(newx>=0 && newx<board.length && newy>=0 && newy<board[0].length
                    && board[newx][newy]==wchar[level] && !visited[newx][newy]){
                visited[newx][newy]=true;
                if(dfs(board,wchar,level+1,visited,newx,newy)) return true;
                visited[newx][newy]=false;
            }
        }
        return false;
    }
}


//可以把访问过的设置为另外的字符，时间差不多
class Solution {
    public boolean exist(char[][] board, String word) {
        if(board==null || board.length==0 || word==null || word.length()==0) return false;
        int row=board.length;
        int col=board[0].length;
        char[] wchar=word.toCharArray();
        //遍历board,找到第一个字符后dfs
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]==wchar[0]){
                    char c=board[i][j];
                    board[i][j]='#';
                    if(dfs(board,wchar,1,i,j)){
                        return true;
                    }
                    board[i][j]=c;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board,char[] wchar,int level,int x,int y){
        if(level == wchar.length) return true;

        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1};
        for(int i=0;i<4;i++){
            int newx=x+dx[i];
            int newy=y+dy[i];
            if(newx>=0 && newx<board.length && newy>=0 && newy<board[0].length
                    && board[newx][newy]==wchar[level] ){
                char c=board[newx][newy];
                board[newx][newy]='#';
                if(dfs(board,wchar,level+1,newx,newy)) return true;
                board[newx][newy]=c;
            }
        }
        return false;
    }
}


//7,81.88;  40,19.5
class Solution {
    public boolean exist(char[][] board, String word) {
        if(board==null || board.length==0 || word==null || word.length()==0) return false;
        int row=board.length;
        int col=board[0].length;
        char[] wchar=word.toCharArray();
        //遍历board,找到第一个字符后dfs
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(dfs(board,wchar,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board,char[] wchar,int level,int x,int y){
        if(board[x][y]==wchar[level]) {
            if(level == wchar.length-1) return true;
            else{
                char c=board[x][y];
                board[x][y]='#';
                int[] dx={-1,1,0,0};
                int[] dy={0,0,-1,1};
                for(int i=0;i<4;i++){
                    int newx=x+dx[i];
                    int newy=y+dy[i];
                    if(newx>=0 && newx<board.length && newy>=0 && newy<board[0].length ){
                        if(dfs(board,wchar,level+1,newx,newy)) return true;
                    }
                }
                board[x][y]=c;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
