//在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。 
//
// 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成： 
//
// 
// 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角） 
// C_1 位于 (0, 0)（即，值为 grid[0][0]） 
// C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]） 
// 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0） 
// 
//
// 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 输入：[[0,1],[1,0]]
//
//输出：2
//
// 
//
// 示例 2： 
//
// 输入：[[0,0,0],[1,1,0],[1,1,0]]
//
//输出：4
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length == grid[0].length <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)
//DFS回溯，超时，毕竟除了那3个方向，其他方向也检测就太离谱了
//我觉得，可选方向是右、右下、和下，所以只要遍历这3个方向就好了
// class Solution {
//     private int min=Integer.MAX_VALUE;
//     private int row;
//     private int col;
//     private int[][] grid;
//     public int shortestPathBinaryMatrix(int[][] grid) {
//         this.grid=grid;
//         if(grid==null || grid.length==0 || grid[0][0]==1) return -1;
//         this.row=grid.length;
//         this.col=grid[0].length;
//         boolean[][] visited=new boolean[row][col];
//         dfs(visited,0,0,1);
//         return min==Integer.MAX_VALUE?-1:min;
//     }

//     private void dfs(boolean[][] visited,int x,int y,int steps){
//         int row=grid.length;
//         int col=grid[0].length;
//         if(x==row-1 && y==col-1){
//             min=Math.min(min,steps);
//             return;
//         }
//         //右下、右、下、右上、上、左上、左、左下、
//         int[] dx={1,0,1,-1,-1,-1,0,1};
//         int[] dy={1,1,0,1,0,-1,-1,-1};
//         for(int i=0;i<8;i++){
//             int nx=x+dx[i];
//             int ny=y+dy[i];
//             if(nx>=0 && ny>=0 && nx<row && ny<col && grid[nx][ny]!=1 && !visited[nx][ny]){
//                 visited[nx][ny]=true;
//                 dfs(visited,nx,ny,steps+1);
//                 visited[nx][ny]=false;
//             }
//         }
//     }
// }


//基本的BFS
//15,93.19;  41.7,100
// class Solution{
//     public int shortestPathBinaryMatrix(int[][] grid) {
//         int row=grid.length;
//         if(grid==null || row==0 || grid[0][0]==1) return -1;
//         int col=grid[0].length;
//         if(grid[row-1][col-1]==1) return -1;
//         if(row==1 && col==1) return 1;
//         Queue<int[]> queue=new LinkedList<>();
//         queue.add(new int[]{0,0});
//         //偏移，右、右下、下、左下、左、左上、上、右上
//         int[][] offset=new int[][]{{0,1},{1,1},{1,0},{1,-1},
//                                     {0,-1},{-1,-1},{-1,0},{-1,1}};
//         int step=1;
//         //每个点对应的step是到达其需要的最少step，因为BFS就是这样按层次扩散，从源点开始
//         //每层是它能到达的所有点，所以每个点第一次被访问时，也是到达的最快路径
//         while(!queue.isEmpty()){
//             int size=queue.size();
//             for(int i=0;i<size;i++){
//                 int[] pos=queue.poll();
//                 int x=pos[0];
//                 int y=pos[1];
//                 for(int j=0;j<8;j++){
//                     int nx=x+offset[j][0];
//                     int ny=y+offset[j][1];
//                     if(nx==row-1 && ny==col-1) return step+1;
//                     if(nx>=0 && nx<row && ny>=0 && ny<col && grid[nx][ny]==0){
//                         queue.add(new int[]{nx,ny});
//                         grid[nx][ny]=1;
//                     }
//                 }
//             }
//             step++;
//         }
//         return -1;
//     }
// }

//A*
//76,5.28;  41,100
// class Solution{
//     private static class Node implements Comparable<Node> {
//         // coordinate
//         private int x;
//         private int y;

//         // g(node) is the cost of the path from the start node to node
//         private int g;
//         // f(node) = g(node) + h(node)
//         private int f;

//         private Node(int x, int y, int g, int h) {
//             this.x = x;
//             this.y = y;
//             this.g = g;
//             this.f = g + h;
//         }

//         @Override
//         public int compareTo(Node node) {
//             return this.f - node.f;
//         }
//     }

//     // eight directions sorted in clockwise order
//     private static int[][] DIRECTIONS = new int[][]{{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};

//     public int shortestPathBinaryMatrix(int[][] grid) {
//         int m = grid.length, n = grid[0].length;
//         int endX = m - 1, endY = n - 1;

//         if (grid[endX][endY] == 1) return -1; // early exit

//         // Initialize the open list -> which node we expand next?
//         PriorityQueue<Node> pq = new PriorityQueue<Node>();

//         // Initialize the closed list -> which nodes we've already visited? What is the minimum g from start node to this?
//         int[] closed = new int[m * n];
//         Arrays.fill(closed, Integer.MAX_VALUE);

//         // put the starting node on the open list
//         pq.add(new Node(0, 0, 1, Math.max(m, n)));

//         // while the open list is not empty
//         while (!pq.isEmpty()) {

//             // retrive the node with the least f on the open list, call it "node"
//             Node node = pq.remove();

//             int x = node.x;
//             int y = node.y;

//             // skip disallowed area
//             if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 1) continue;

//             // if node is the goal, stop search
//             if (x == endX && y == endY) return node.g;

//             // if a node with the same position is in the closed list
//             // which has a lower or equals g than this, skip this expansion
//             if (closed[x * m + y] <= node.g) continue;

//             // push node on the closed list
//             closed[x * m + y] = node.g;

//             // generate 8 successors to node
//             for (int[] dir : DIRECTIONS) {
//                 // for each successor
//                 // successor.g = node.g + distance between successor and node (equals to 1)
//                 // successor.h = estimate distance from successor to goal
//                 int g = node.g + 1;

//                 // h(node) is a heuristic function that
//                 // estimates the cost of the cheapest path from node to the goal

//                 // Here we use **Diagonal Distance** as heuristic function,
//                 // because we can and only can move in eight directions
//                 int h = Math.max(Math.abs(endX - x), Math.abs(endY - y));

//                 // push the successor on the open list
//                 pq.add(new Node(x + dir[0], y + dir[1], g, h));
//             }
//         }

//         return -1;
//     }
// }

//另一个A*，原版
//15，93.21；  41.4，100
// class Solution {
//     int n;
//     public int shortestPathBinaryMatrix(int[][] grid) {
//         n = grid.length;
//         if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
//         if (n == 1) return 1;
//         int[][] dir = {
//             {-1, 0}, {1, 0}, {0, -1}, {0, 1},
//             {-1, 1}, {-1, -1}, {1, -1}, {1, 1}
//         };
//         Node start = new Node(0, 0, grid[0][0] = 1);
//         //在存入priorityqueue时会将小的排序到前面，取出时会先取出
//         Queue<Node> queue = new PriorityQueue<>();
//         queue.offer(start);
//         while (!queue.isEmpty()) {
//             Node node = queue.poll();
//             int step = grid[node.x][node.y];
//             for (int[] d : dir) {
//                 int x = node.x + d[0];
//                 int y = node.y + d[1];
//                 if (x == n - 1 && y == n - 1) return step + 1;
//                 if (x < 0 || x >= n || y < 0 || y >= n) continue;
//                 if (grid[x][y] != 0 && grid[x][y] <= step + 1) continue;
//                 Node next = new Node(x, y, grid[x][y] = step + 1);
//                 queue.offer(next);
//             }
//         }
//         return -1;
//     }

//     class Node implements Comparable<Node> {
//         int x;
//         int y;
//         int f;

//         public Node(int x, int y, int step) {
//             this.x = x;
//             this.y = y;
//             int distance = Math.max(n - 1 - x, n - 1 - y);
//             this.f = distance + step;
//         }

//         @Override
//         public int compareTo(Node o) {
//             return this.f - o.f;
//         }

//         @Override
//         public boolean equals(Object o) {
//             if (this == o) return true;
//             if (!(o instanceof Node)) return false;
//             Node node = (Node) o;
//             return x == node.x && y == node.y;
//         }

//         @Override
//         public int hashCode() {
//             return Integer.hashCode(x * n + y);
//         }
//     }
// }



//A*，参考如上，做了部分修改，如不需要equals和hashCode
//有问题的做法，原因是代价函数定义的有问题，定义的是到终点的曼哈顿距离
//换成Math.max(n-1-x,n-1-y)也不可以
//换成Math.max(n-1-x,n-1-y)+step也不可以
//将grid修改为到这里的步数，这样可以在重复到达相同的位置时，将到达该位置的step修改为最小需要step
//15ms,93.19;  41.5,100
// class Solution{
//     //题目中的地图n==m，所以用一个变量表示大小即可
//     private int n;

//     //这里比较位置的好坏通过到达这个位置时已经走过的步数和这个位置到终点的曼哈顿距离
//     class Node implements Comparable<Node>{
//         int x;
//         int y;
//         int step;
//         int distance;

//         public Node(int x,int y,int step){
//             this.x=x;
//             this.y=y;
//             this.step=step;
//             this.distance=step+Math.max(n-1-x,n-1-y);
//         }

//         @Override
//         public int compareTo(Node node){
//             //int不会调用Integer.compareTo,如果使用
//             return this.distance-node.distance;
//         }
//         //这里在遍历过一个点之后，会置为1，所以添加的点不会相同
//     }

//     public int shortestPathBinaryMatrix(int[][] grid) {
//         if(grid==null || grid.length==0 || grid[0][0]==1
//             || grid[grid.length-1][grid.length-1]==1) return -1;
//         this.n=grid.length;
//         if( n==1 ) return 1;

//         Queue<Node> queue=new PriorityQueue<>();
//         queue.add(new Node(0,0,grid[0][0]=1));
//         grid[0][0]=1;

//         //定义偏移,
//         int[][] offset={
//             {-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}
//         };
//         while(!queue.isEmpty()){
//             Node node=queue.poll();
//             int x=node.x;
//             int y=node.y;
//             int step=node.step;
//             //这里虽然也是BFS，但是不需要每次迭代遍历一层，因为每个点有记录其到达终点的step
//             for(int i=0;i<8;i++){
//                 int nx=x+offset[i][0];
//                 int ny=y+offset[i][1];
//                 if(nx==n-1 && ny==n-1) return step+1;
//                 if(nx>=0 && nx<n && ny>=0 && ny<n && (grid[nx][ny]==0 || grid[nx][ny]>step+1)){
//                     //这里还有一个操作，是如果发现了已经遍历过的点，其step可以比当前step更小，就更新
//                     //如果当前队列中已经有这个位置了，那么如果这个位置在最短路径上，
//                     //也会优先使用step小的这个位置的node，因为二者distance，step小靠前
//                     queue.add(new Node(nx,ny,grid[nx][ny]=step+1));
//                 }
//             }
//         }
//         return -1;
//     }
// }



//双向BFS，beginSet endSet tmp
//28ms,36.72;  41.7,100
//时间长了的原因 可能是频繁的操作set，毕竟要维护3个set，比1个queue时间长
// class Solution{
//     public int shortestPathBinaryMatrix(int[][] grid){
//         if(grid==null || grid.length==0 || grid[0][0]==1
//             || grid[grid.length-1][grid.length-1]==1) return -1;
//         int n=grid.length;
//         if( n==1 ) return 1;

//         //准备两个状态空间
//         HashSet<Integer> beginSet=new HashSet<>();
//         HashSet<Integer> endSet=new HashSet<>();
//         beginSet.add(0);
//         endSet.add(n*n-1);
//         //修改已访问位置的值为1
//         grid[0][0]=1;
//         grid[n-1][n-1]=1;

//         int[][] offset={
//             {-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}
//         };

//         int step=1;
//         while(!beginSet.isEmpty() && !endSet.isEmpty()){
//             if(beginSet.size() > endSet.size()){
//                 HashSet<Integer> tmp=beginSet;
//                 beginSet=endSet;
//                 endSet=tmp;
//             }
//             HashSet<Integer> tmp=new HashSet<>();
//             for(int pos:beginSet){
//                 int x=pos/n;
//                 int y=pos%n;
//                 for(int i=0;i<8;i++){
//                     int nx=x+offset[i][0];
//                     int ny=y+offset[i][1];
//                     //放在这里的话，ny可以不在0～n-1中，如果为负，nx*n+ny就可能计算出endSet中的值
//                     // if(endSet.contains(nx*n+ny)) return step+1;
//                     if(nx>=0 && nx<n && ny>=0 && ny<n){
//                         if(endSet.contains(nx*n+ny)) return step+1;
//                         if(grid[nx][ny]==0){
//                             tmp.add(nx*n+ny);
//                             grid[nx][ny]=1;
//                         }
//                     }
//                 }
//             }
//             beginSet=tmp;
//             step++;
//         }
//         return -1;
//     }
// }



//使用list和boolean数组的BFS
//17，73.09；  41.9，100
//这个题可能是因为测试集，只用单向bfs还是挺快的
// class Solution {
//     private class State {
//         int i;
//         int j;

//         public State(int i, int j) {
//             this.i = i;
//             this.j = j;
//         }
//     }

//     static final int[][] direc = new int[][]{{1,0},{-1,0},{1,1},{-1,1}, {0,1},{1,-1},{-1,-1},{0,-1}};

//     public int shortestPathBinaryMatrix(int[][] grid) {
//         if (grid.length == 0) {
//             return -1;
//         }

//         if (grid.length == 1) {
//             return grid[0][0] == 0 ? 1 : -1;
//         }

//         final int N = grid.length;

//         if ((grid[0][0] == 1) || (grid[N-1][N-1] == 1)) {
//             return -1;
//         }

//         List<State> startSta = new ArrayList<>(N*N);
//         List<State> endSta = new ArrayList<>(N*N);
//         startSta.add(new State(0, 0));
//         endSta.add(new State(N-1, N-1));

//         boolean[][] visited = new boolean[N][N];
//         visited[0][0] = true;
//         visited[N-1][N-1] = true;

//         boolean[][] startVisited = new boolean[N][N];
//         boolean[][] endVisited = new boolean[N][N];
//         startVisited[0][0] = true;
//         endVisited[N-1][N-1] = true;
//         int len = 1;
//         while (!startSta.isEmpty() && !endSta.isEmpty()) {
//             if (startSta.size() > endSta.size()) {
//                 List<State> tmp = startSta; startSta = endSta; endSta = tmp;
//                 boolean[][] tmpA = startVisited; startVisited = endVisited; endVisited = tmpA;
//             }
//             List<State> newStaSet = new ArrayList<>();
//             for (State curSta : startSta) {
//                 int i = curSta.i, j = curSta.j;
//                 for (int[] pos : direc) {
//                     int new_i = i + pos[0], new_j = j + pos[1];
//                     if (new_i >= 0 && new_i < N && new_j >= 0
//                         && new_j < N && grid[new_i][new_j] == 0) {
//                         State newSta = new State(new_i, new_j);
//                         if (endVisited[newSta.i][newSta.j]) {
//                             return len + 1;
//                         }
//                         if (visited[newSta.i][newSta.j]) {
//                             continue;
//                         }
//                         newStaSet.add(newSta);
//                         visited[newSta.i][newSta.j] = true;
//                         startVisited[newSta.i][newSta.j] = true;
//                     }
//                 }
//             }
//             startSta = newStaSet;
//             len++;
//         }
//         return -1;
//     }
// }



//另一个双向BFS
//25,42.3;  40.7,100
class Solution{
    private int directions[][] = {{0,1},{0,-1},{1,0},{-1,0},{1,-1},{-1,1},{-1,-1},{1,1}};

    public int shortestPathBinaryMatrix(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return -1;

        Set<Integer> memo1 = new HashSet<>();
        Set<Integer> memo2 = new HashSet<>();

        memo1.add(0);
        memo2.add((m - 1) * n + n - 1);

        Queue<int[]> start = new LinkedList<>();
        start.offer(new int[] {0, 0});

        Queue<int[]> end = new LinkedList<>();
        end.offer(new int[] {m - 1, n - 1});

        int steps1 = 0;
        int steps2 = 0;

        while (true) {
            int count = memo1.size();

            boolean isFound = bfs(grid, start, memo1, memo2);
            if (isFound) return steps1 + steps2 + 1;

            if (memo1.size() == count) return -1;

            steps1++;

            count = memo2.size();
            isFound = bfs(grid, end, memo2, memo1);

            if (isFound) return steps1 + steps2 + 1;
            if (memo2.size() == count) return -1;

            steps2++;
        }
    }

    private boolean bfs(int[][] grid, Queue<int[]> queue,
                        Set<Integer> memo1, Set<Integer> memo2) {
        for (int count = queue.size(); count > 0; count--) {
            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];

            if (memo2.contains(x * grid[0].length + y)) return true;

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                int next = nx * grid[0].length + ny;

                if (isValid(grid, nx, ny) && !memo1.contains(next)) {
                    memo1.add(next);
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
        return false;
    }

    private boolean isValid(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 0;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
