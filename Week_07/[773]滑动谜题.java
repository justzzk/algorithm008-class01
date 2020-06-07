//在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示. 
//
// 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换. 
//
// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。 
//
// 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。 
//
// 示例： 
//
// 
//输入：board = [[1,2,3],[4,0,5]]
//输出：1
//解释：交换 0 和 5 ，1 步完成
// 
//
// 
//输入：board = [[1,2,3],[5,4,0]]
//输出：-1
//解释：没有办法完成谜板
// 
//
// 
//输入：board = [[4,1,2],[5,0,3]]
//输出：5
//解释：
//最少完成谜板的最少移动次数是 5 ，
//一种移动路径:
//尚未移动: [[4,1,2],[5,0,3]]
//移动 1 次: [[4,1,2],[0,5,3]]
//移动 2 次: [[0,1,2],[4,5,3]]
//移动 3 次: [[1,0,2],[4,5,3]]
//移动 4 次: [[1,2,0],[4,5,3]]
//移动 5 次: [[1,2,3],[4,5,0]]
// 
//
// 
//输入：board = [[3,2,4],[1,5,0]]
//输出：14
// 
//
// 提示： 
//
// 
// board 是一个如上所述的 2 x 3 的数组. 
// board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列. 
// 
// Related Topics 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)
//直接BFS，定义了一个状态类
//27,30.69;  39.6,100
// class Solution {
//     public int slidingPuzzle(int[][] board) {
//         int R = board.length, C = board[0].length;
//         int sr = 0, sc = 0;
//         search:
//             for (sr = 0; sr < R; sr++)
//                 for (sc = 0; sc < C; sc++)
//                     if (board[sr][sc] == 0)
//                         break search;

//         // for(sr=0; sr<R; sr++){
//         //     for(sc=0; sc<C; sc++){
//         //         if(board[sr][sc]==0) break;
//         //     }
//         //     if(sc<C && board[sr][sc]==0) break;
//         // }

//         int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//         Queue<Node> queue = new ArrayDeque();
//         Node start = new Node(board, sr, sc, 0);
//         queue.add(start);

//         Set<String> seen = new HashSet();
//         seen.add(start.boardstring);

//         String target = Arrays.deepToString(new int[][]{{1,2,3}, {4,5,0}});

//         while (!queue.isEmpty()) {
//             Node node = queue.remove();
//             if (node.boardstring.equals(target)) return node.depth;

//             for (int[] di: directions) {
//                 int nei_r = di[0] + node.zero_r;
//                 int nei_c = di[1] + node.zero_c;

//                 if (nei_r >= 0 && nei_r < R && nei_c >= 0 && nei_c < C){
//                     int[][] newboard = new int[R][C];
//                     int t = 0;
//                     for (int[] row: node.board) newboard[t++] = row.clone();
//                     newboard[node.zero_r][node.zero_c] = newboard[nei_r][nei_c];
//                     newboard[nei_r][nei_c] = 0;

//                     //这里因为将到达该状态的步数封装到node中所以不需要使用queue.size
//                     Node nei = new Node(newboard, nei_r, nei_c, node.depth+1);
//                     if (!seen.contains(nei.boardstring)){
//                         queue.add(nei);
//                         seen.add(nei.boardstring);
//                     }
//                 }
//             }
//         }
//         return -1;
//     }
// }

// //一个node就是一个状态
// class Node {
//     int[][] board;
//     String boardstring;
//     int zero_r;
//     int zero_c;
//     int depth;
//     Node(int[][] B, int r, int c, int d) {
//         board = B;
//         boardstring = Arrays.deepToString(board);
//         zero_r = r;
//         zero_c = c;
//         depth = d;
//     }
// }

//直接BFS
//11ms,75.32;  39.1,100
// class Solution{
//     //定义状态类，其实可以直接将每个状态表示为字符串
//     public int slidingPuzzle(int[][] board){
//         if(board==null || board.length==0) return -1;
//         int[][] offset={{-1,0},{1,0},{0,-1},{0,1}};
//         //初始状态字符串
//         String startstr=boardToString(board);
//         String endstr="123450";
//         if(endstr.equals(startstr)) return 0;
//         //BFS队列
//         Queue<String> queue=new LinkedList<>();
//         queue.add(startstr);

//         //已访问集合
//         HashSet<String> set=new HashSet<>();
//         set.add(startstr);

//         int step=0;
//         while(!queue.isEmpty()){
//             int size=queue.size();
//             for(int i=0;i<size;i++){
//                 String thisStr=queue.poll();
//                 int pos=thisStr.indexOf("0");
//                 int zr=pos/3, zc=pos%3;
//                 int[][] thisboard=stringToBoard(thisStr);

//                 for(int j=0;j<4;j++){
//                     int nzr=zr+offset[j][0];
//                     int nzc=zc+offset[j][1];
//                     if(nzr>=0 && nzr<2 && nzc>=0 && nzc<3){
//                         int tmp=thisboard[nzr][nzc];
//                         thisboard[nzr][nzc]=0;
//                         thisboard[zr][zc]=tmp;
//                         String newstat=boardToString(thisboard);
//                         if(endstr.equals(newstat)) return step+1;
//                         if(!set.contains(newstat)){
//                             set.add(newstat);
//                             queue.add(newstat);
//                         }
//                         thisboard[nzr][nzc]=tmp;
//                         thisboard[zr][zc]=0;
//                     }
//                 }
//             }
//             step++;
//         }

//         return -1;
//     }

//     private int[][] stringToBoard(String string){
//         //因为题目限制了棋盘大小，所以直接for循环就可以了
//         int[][] res=new int[2][3];
//         char[] schar=string.toCharArray();
//         for(int i=0;i<2;i++)
//             for(int j=0;j<3;j++)
//                 res[i][j]=schar[i*3+j]-'0';
//         return res;
//     }

//     private String boardToString(int[][] board){
//         StringBuilder sb=new StringBuilder();
//         for(int i=0;i<board.length;i++)
//             for(int j=0;j<board[0].length;j++)
//                 sb.append(board[i][j]);
//         return sb.toString();
//     }
// }


//对上面的一点优化
//直接操作string，不在string和board之间左右摇摆
//8,91.35;  39.4,100
// class Solution{
//     //定义状态类，其实可以直接将每个状态表示为字符串
//     public int slidingPuzzle(int[][] board){
//         if(board==null || board.length==0) return -1;
//         //direc表示在第i个位置时可以移动到的位置，如{1，3}表示0在0号位时，可以移动到1和3号位
//         int[][] direc={{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{4,2}};
//         //初始状态字符串
//         StringBuilder sb=new StringBuilder();
//         for(int i=0;i<2;i++)
//             for(int j=0;j<3;j++)
//                 sb.append(board[i][j]);
//         String startstr=sb.toString();
//         String endstr="123450";
//         if(endstr.equals(startstr)) return 0;
//         //BFS队列
//         Queue<String> queue=new LinkedList<>();
//         queue.add(startstr);

//         //已访问集合
//         HashSet<String> set=new HashSet<>();
//         set.add(startstr);

//         int step=0;
//         while(!queue.isEmpty()){
//             int size=queue.size();
//             for(int i=0;i<size;i++){
//                 String thisStr=queue.poll();
//                 int pos=thisStr.indexOf("0");
//                 for(int newpos:direc[pos]){
//                     String newstat=stringSwap(thisStr,pos,newpos);
//                     if(endstr.equals(newstat)) return step+1;
//                     if(!set.contains(newstat)){
//                         set.add(newstat);
//                         queue.add(newstat);
//                     }
//                 }
//             }
//             step++;
//         }
//         return -1;
//     }

//     private String stringSwap(String s,int i,int j){
//         StringBuilder sb=new StringBuilder(s);
//         sb.setCharAt(i,s.charAt(j));
//         sb.setCharAt(j,s.charAt(i));
//         return sb.toString();
//     }
// }

//这个A*直接使用曼哈顿距离为估值函数
//如果使用A*的话，因为每次取出的点不是按层次顺序取出的，
//19,46.57;  39.6,100
// class Solution{
//     public int slidingPuzzle(int[][] board) {
//         //find index of zero in board
//         int[] index = findIndexOfZero(board);
//         if (index == null || index.length == 0) return -1;
//         //create start State
//         State start = new State(board, 0, index[0], index[1]);
//         //PQ
//         PriorityQueue<State> pq = new PriorityQueue<>();
//         pq.add(start);
//         //Set
//         Set<State> visited = new HashSet<>();
//         visited.add(start);
//         //moves
//         int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

//         while (!pq.isEmpty()) {
//             State currState = pq.poll();//poll State by priority
//             if (currState.isGoal()) return currState.taken;
//             for (int[] move : moves) {
//                 int x = currState.zeroSetX + move[0];
//                 int y = currState.zeroSetY + move[1];
//                 if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) continue;
//                 State newState = currState.swap(x, y);
//                 if (newState != null && visited.add(newState)) pq.offer(newState);
//             }
//         }
//         return -1;
//     }

//     class State implements Comparable<State>{
//         int[][] stateBoard;
//         int taken;
//         int zeroSetX;
//         int zeroSetY;

//         public State(int[][] stateBoard, int taken, int zeroSetX, int zeroSetY) {
//             //这里新建数组的原因是：直接赋地址的话, 若通过当前State的stateBoard创建了一个NewState,
//             //修改NewState.stateBoard的同时也会修改this.stateBoard
//             this.stateBoard = new int[2][3];
//             for (int i = 0; i < 2; i++) {
//                 for (int j = 0; j < 3; j++) {
//                     this.stateBoard[i][j] = stateBoard[i][j];
//                 }
//             }
//             this.taken = taken;
//             this.zeroSetX = zeroSetX;
//             this.zeroSetY = zeroSetY;
//         }

//         /**
//          * swap board where x = zeroX, y = zeroY
//          *
//          * @param   x
//          * @param   y
//          * @return  State
//          */
//         public State swap(int x, int y) {
//             State res = new State(stateBoard, taken + 1, x, y);
//             int temp = res.stateBoard[x][y];
//             res.stateBoard[x][y] = res.stateBoard[zeroSetX][zeroSetY];
//             res.stateBoard[zeroSetX][zeroSetY] = temp;
//             return res;
//         }

//         /**
//          * priority distance
//          * @return
//          */
//         public int distance() {
//             int result = 0;
//             for (int i = 0; i < 2; i++) {
//                 for (int j = 0; j < 3; j++) {
//                     if (stateBoard[i][j] == 0) continue;
//                     //二维坐标曼哈顿距离计算：res = |x - i| + |y - j|
//                     int val = stateBoard[i][j] - 1;
//                     int x = val/3;
//                     int y = val%3;
//                     result += Math.abs(x - i) + Math.abs(y - j);
//                 }
//             }
//             return result;
//         }

//         public boolean isGoal() {
//             return distance() == 0;
//         }

//         /**
//          * compare all states in PQ while poll or remove
//          * @param that
//          * @return
//          */
//         @Override
//         public int compareTo(State that) {
//             return this.distance() + this.taken - that.distance() - that.taken;
//         }

//         @Override
//         public boolean equals(Object obj) {
//             return Arrays.deepEquals(((State) obj).stateBoard, this.stateBoard);
//         }

//         @Override
//         public int hashCode() {
//             return Arrays.deepHashCode(stateBoard);
//         }
//     }

//     private int[] findIndexOfZero(int[][] board) {
//         for (int i = 0; i < board.length; i++) {
//             for (int j = 0; j < board[0].length; j++) {
//                 if (board[i][j] == 0) return new int[]{i, j};
//             }
//         }
//         return null;
//     }

// }


//A*，这个使用step+曼哈顿距离
//21,40.2;  39.8,100
// class Solution {
//     public int slidingPuzzle(int[][] board) {
//         int R = board.length, C = board[0].length;
//         int sr = 0, sc = 0;

//         //Find sr, sc
//         search:
//             for (sr = 0; sr < R; sr++)
//                 for (sc = 0; sc < C; sc++)
//                     if (board[sr][sc] == 0)
//                         break search;

//         int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//         PriorityQueue<Node> heap = new PriorityQueue<Node>((a, b) ->
//             (a.heuristic + a.depth) - (b.heuristic + b.depth));
//         Node start = new Node(board, sr, sc, 0);
//         heap.add(start);

//         Map<String, Integer> cost = new HashMap();
//         cost.put(start.boardstring, 9999999);

//         String target = Arrays.deepToString(new int[][]{{1,2,3}, {4,5,0}});
//         String targetWrong = Arrays.deepToString(new int[][]{{1,2,3}, {5,4,0}});

//         while (!heap.isEmpty()) {
//             Node node = heap.poll();
//             if (node.boardstring.equals(target)) return node.depth;
//             if (node.boardstring.equals(targetWrong)) return -1;
//             //这里cost的作用和1091的grid记录step相似，到达了重复状态并且大于已知，则不需处理
//             if (node.depth + node.heuristic > cost.get(node.boardstring)) continue;

//             for (int[] di: directions) {
//                 int nei_r = di[0] + node.zero_r;
//                 int nei_c = di[1] + node.zero_c;

//                 // If the neighbor is not on the board or wraps incorrectly around rows/cols
//                 if ((Math.abs(nei_r - node.zero_r) + Math.abs(nei_c - node.zero_c) != 1) ||
//                         nei_r < 0 || nei_r >= R || nei_c < 0 || nei_c >= C)
//                     continue;

//                 int[][] newboard = new int[R][C];
//                 int t = 0;
//                 for (int[] row: node.board)
//                     newboard[t++] = row.clone();

//                 // Swap the elements on the new board
//                 newboard[node.zero_r][node.zero_c] = newboard[nei_r][nei_c];
//                 newboard[nei_r][nei_c] = 0;

//                 Node nei = new Node(newboard, nei_r, nei_c, node.depth+1);
//                 //得到的新状态也可能是已有的状态，这个处理我觉得和if(>)那里重复了，
//                 //这里只要判断是开销更大的旧状态就不需要再添加到队列
//                 if (nei.depth + nei.heuristic >= cost.getOrDefault(nei.boardstring, 9999999))
//                     continue;
//                 heap.add(nei);
//                 cost.put(nei.boardstring, nei.depth + nei.heuristic);
//             }
//         }

//         return -1;
//     }
// }

// class Node {
//     int[][] board;
//     //这里定义boardstring只是作为map的key，不会用来计算估值函数，所以直接调用deepToString就好了
//     String boardstring;
//     int heuristic;
//     int zero_r;
//     int zero_c;
//     int depth;
//     Node(int[][] B, int zr, int zc, int d) {
//         board = B;
//         boardstring = Arrays.deepToString(board);

//         //Calculate heuristic
//         heuristic = 0;
//         int R = B.length, C = B[0].length;
//         for (int r = 0; r < R; ++r)
//             for (int c = 0; c < C; ++c) {
//                 if (board[r][c] == 0) continue;
//                 int v = (board[r][c] + R*C - 1) % (R*C);
//                 // v/C, v%C: where board[r][c] should go in a solved puzzle
//                 heuristic += Math.abs(r - v/C) + Math.abs(c - v%C);
//             }
//         heuristic /= 2;
//         zero_r = zr;
//         zero_c = zc;
//         depth = d;
//     }
// }


//使用字符串作为状态判断 & step+曼哈顿距离 为估值函数
//如果使用A*的话，因为每次取出的点不是按层次顺序取出的，
//所以每个点的step要这个点自己保存，所以需要定义一个新的类，成员变量有：String,step,f(估值函数)
//还没写完
class Solution{
    class State{
        String statString;
        int step;
        int f;

        public State(String statString,int step){
            this.statString=statString;
            this.step=step;
            int heuristic=
                    this.f=this.step+heuristic;
        }

    }

    public int slidingPuzzle(int[][] board){

    }
}
//leetcode submit region end(Prohibit modification and deletion)
