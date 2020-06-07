//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集


//leetcode submit region begin(Prohibit modification and deletion)
//10,13.41；  41.5，56.25
// class Solution {
//     class UnionFind{
//         private int count;
//         private int[] rank;
//         private int[] parent;
//         private int row;
//         private int col;

//         public UnionFind(char[][] grid){
//             row=grid.length;
//             col=grid[0].length;
//             parent=new int[row*col];
//             rank=new int[row*col];
//             for(int i=0;i<row;i++){
//                 for(int j=0;j<col;j++){
//                     //因为‘0’不会被合并，所以赋值也没问题
//                     parent[i*col+j]=i*col+j;
//                     if(grid[i][j]=='O'){
//                         count++;
//                         rank[i*col+j]=1;
//                     }
//                 }
//             }
//         }

//         public int find(int i){
//             while(parent[i]!=i){
//                 parent[i]=find(parent[i]);
//                 i=parent[i];
//             }
//             return i;
//         }

//         //改造union
//         public void union(int i,int j){
//             int rooti=find(i);
//             int rootj=find(j);
//             if (rooti==rootj) return ;
//             if (OnEdge(rooti,row,col)){
//                 parent[rootj]=rooti;
//                 rank[rooti]+=rank[rootj];
//             }else if(OnEdge(rootj,row,col)){
//                 parent[rooti]=rootj;
//                 rank[rootj]+=rank[rooti];
//             }else if (rank[rooti]>rank[rootj]) {
//                 parent[rootj]=i;
//                 rank[rooti]+=rank[rootj];
//             } else {
//                 parent[rooti]=rootj;
//                 rank[rootj]+=rank[rooti];
//             }
//             count--;
//         }

//         public int getCount(){ return count; }

//         // public boolean OnEdge(int pos,int hei,int len){
//         //     int row=pos/len;
//         //     int col=pos%len;
//         //     if(row==0 || row==hei-1 || col==0 || col==len-1) return true;
//         //     else  return false;
//         // }

//     }

//     public void solve(char[][] board) {
//         if(board==null || board.length==0) return;
//         int row=board.length;
//         int col=board[0].length;
//         UnionFind uf=new UnionFind(board);
//         //第一遍遍历，union
//         for(int i=0;i<row;i++){
//             for(int j=0;j<col;j++){
//                 if(board[i][j]=='O'){
//                     //只检查右和下
//                     if(i+1<row && board[i+1][j]=='O') {
//                         uf.union(i*col+j,(i+1)*col+j);
//                     }
//                     if(j+1<col && board[i][j+1]=='O') {
//                         uf.union(i*col+j,i*col+j+1);
//                     }
//                 }
//             }
//         }

//         for(int i=0;i<row;i++){
//             for(int j=0;j<col;j++){
//                 if(board[i][j]=='O' && !OnEdge(uf.find(i*col+j),board.length,board[0].length)){
//                 // if(board[i][j]=='O' && !uf.OnEdge(uf.find(i*col+j),board.length,board[0].length)){
//                     board[i][j]='X';
//                 }
//             }
//         }
//     }

//     //还是写在外部类比较合理
//     public boolean OnEdge(int pos,int hei,int len){
//         int row=pos/len;
//         int col=pos%len;
//         if(row==0 || row==hei-1 || col==0 || col==len-1) return true;
//         else  return false;
//     }
// }


//另一个UnionFind
//9，14.31；   41.6，56.25
class Solution{
    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    //只判断上下两个方向不行
    // private static int[][] directions = {{0, 1}, {1, 0}};

    public void solve(char[][] board) {

        if (board == null || board.length == 0) {
            return;
        }

        DisjointSets disjointSets = new DisjointSets(board);
        int rows = board.length, cols = board[0].length;
        int dummyBorder = rows * cols;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (board[x][y] == 'O') {
                    int borderO = x * cols + y;
                    //因为边界O时直接与dummy union，没有与周围需要的位置union，所以不能只访问右和下
                    if (x == 0 || x == rows - 1 || y == 0 || y == cols - 1) {
                        //如果是边界，直接与dummyBoardunion
                        // disjointSets.union(dummyBorder, borderO);
                        //换下顺序，让dummy的parent一直是自己
                        //顺序不会产生影响
                        disjointSets.union( borderO,dummyBorder);
                        continue;
                    }
                    for (int[] dir : directions) {
                        int nx = x + dir[0];
                        int ny = y + dir[1];
                        if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && board[nx][ny] == 'O') {
                            int neighbor = nx * cols + ny;
                            //合并顺序无所谓
                            // disjointSets.union(borderO, neighbor);
                            disjointSets.union(neighbor,borderO);
                        }
                    }
                }
            }
        }

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (board[x][y] == 'O' && disjointSets.find(x * cols + y) != disjointSets.find(dummyBorder)) {
                    //直接判断parent是不是dummyboard不行，也就是说disjointSets.find(dummyBorder)！=dummyBorder，因为union时可能改变parent[dummy]
                    // if (board[x][y] == 'O' && disjointSets.find(x * cols + y) != dummyBorder) {
                    //在dummy所在的团体中，root不一定是dummy，但与dummy同根的一定是与边界相连的
                    board[x][y] = 'X';
                }
            }
        }

    }

    class DisjointSets {

        int[] parent;

        public DisjointSets(char[][] board) {

            int rows = board.length, cols = board[0].length;
            parent = new int[rows * cols + 1];

            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    if (board[x][y] == 'O') {
                        int id = x * cols + y;
                        parent[id] = id;
                    }
                }
            }
            //为dummyBoard设置一个位置，因为在遍历board之前不知道哪个位置是边界上的O，
            //所以定义一个dummyBoard，这个是认为定义的边界O，以后所有发现的与边界O相连的，parent都是它
            parent[rows * cols] = rows * cols;
        }

        public int find(int x) {

            if (x == parent[x]) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }
}


//DFS
//使用直接思路解决,没有解决；问题是dfs本身无法区分当前点相对上一层的点是否已经遍历过，
//如果已遍历并且是false，也会被上一层的点影响置换为X
// class Solution{
//         public void solve(char[][] board){
//         if(board==null || board.length==0) return;
//         int row=board.length;
//         int col=board[0].length;
//         //false标识与边界O相连的
//         boolean[][] visited=new boolean[row][col];
//         for(int i=0;i<board.length;i++) Arrays.fill(visited[i],true);
//         for(int i=0;i<row;i++){
//             for(int j=0;j<col;j++){
//                 if(board[i][j]=='O' && visited[i][j]){
//                     dfs(board,i,j,visited);
//                 }
//             }
//         }
//     }

//     private boolean dfs(char[][] board,int i,int j,boolean[][] visited){
//         int row=board.length;
//         int col=board[0].length;

//         if(board[i][j]=='O'){
//             if(OnEdge(row,col,i,j) || !visited[i][j]) {
//                 visited[i][j]=false;
//                 return false;
//             }else {
//                 //不是边界上的O，并且visited为true
//                 board[i][j]='#';
//                 //上、左、下、右
//                 //这里和并查集一样，去掉左和上，只右和下即可，因为即使左上是O也已经检查过了，
//                 //不行，因为上和左可能有与边界相连的，而右下没有
//                 //使用一个boolean[][]记录，标识访问过的并且为true的数组
//                 boolean ans=dfs(board,i-1,j,visited) && dfs(board,i,j-1,visited)
//                         && dfs(board,i+1,j,visited) && dfs(board,i,j+1,visited);
//                 if(ans) {
//                     board[i][j]='X';
//                     return true;
//                 }else {
//                     board[i][j]='O';
//                     visited[i][j]=false;
//                     return false;
//                 }
//             }
//         }else return true;
//     }

//     //递归判断是否与边界O相连
//     private boolean OnEdge(int row,int col,int i,int j){
//         return (i==0 || i==row-1 || j==0 || j==col-1);
//     }
// }


//DFS
//使用直接思路解决,应该可以，但是因为每个点都要完全从自身开始遍历，没有利用之前点遍历得到的信息
//所以时间长，所以超时
// class Solution{
//     public void solve(char[][] board){
//         if(board==null || board.length==0) return;
//         int row=board.length;
//         int col=board[0].length;
//         for(int i=0;i<row;i++){
//             for(int j=0;j<col;j++){
//                 if(board[i][j]=='O' || board[i][j]=='#' ){
//                     if(dfs(board,i,j)){
//                         board[i][j]='X';
//                     }
//                 }
//             }
//         }
//     }

//     private boolean dfs(char[][] board,int i,int j){
//         int row=board.length;
//         int col=board[0].length;

//         if(board[i][j]=='O'){
//             if(OnEdge(row,col,i,j)) return false;
//             else {
//                 //不是边界上的O，并且visited为false
//                 board[i][j]='#';
//                 //上、下、左、右
//                 //这里和并查集一样，去掉左和上，只右和下即可，因为即使左上是O也已经检查过了，
//                 //不行，因为上和左可能有与边界相连的，而右下没有
//                 boolean ans=dfs(board,i-1,j) && dfs(board,i+1,j) && dfs(board,i,j-1) && dfs(board,i,j+1);
//                 board[i][j]='O';
//                 if(ans) return true;
//                 else return false;
//             }
//         }else return true;
//     }

//     //递归判断是否与边界O相连
//     private boolean OnEdge(int row,int col,int i,int j){
//         return (i==0 || i==row-1 || j==0 || j==col-1);
//     }
// }


//从边界的O入手，将与其相连的O特殊标记，最后修改剩余的O和特殊标记
//2,98.33;  41.7,56.25
class Solution{
    public void solve(char[][] board){
        if(board==null || board.length==0) return;
        int row=board.length;
        int col=board[0].length;
        //如何遍历最外层？直接遍历
        //第一行和最后一行
        for(int i=0;i<col;i++){
            if(board[0][i]=='O') dfs(0,i,board,row,col);
            if(board[row-1][i]=='O') dfs(row-1,i,board,row,col);
        }
        //第一列和最后一列还没检查的
        for(int i=1;i<row-1;i++){
            if(board[i][0]=='O') dfs(i,0,board,row,col);
            if(board[i][col-1]=='O') dfs(i,col-1,board,row,col);
        }

        //替换
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]=='O') board[i][j]='X';
                if(board[i][j]=='#') board[i][j]='O';
            }
        }

    }

    private void dfs(int x,int y,char[][] board,int row,int col){
        board[x][y]='#';
        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1};
        for(int i=0;i<4;i++){
            int newx=x+dx[i];
            int newy=y+dy[i];
            if(newx >= 0 && newx < row && newy >= 0
                    && newy < col && board[newx][newy]=='O'){
                dfs(newx,newy,board,row,col);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
