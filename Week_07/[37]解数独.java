//编写一个程序，通过已填充的空格来解决数独问题。 
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// Note: 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
//暴力，主要是回溯，与皇后本质一样，但是复杂度提升，随机填入一个数字然后判断isValid
// 递归中止条件是 pos==81
//722,5.11;   39.7,7.14
// class Solution {
//     public void solveSudoku(char[][] board) {
//         solver(board,0);
//     }

//     //用boolean返回值标记是否结束,如何控制程序退出
//     private boolean solver(char[][] board,int pos){
//         if(pos==81) return true;

//         int thisrow=pos/9;
//         int thiscol=pos%9;
//         if(board[thisrow][thiscol]=='.'){
//             //需要填数字
//             for(char c='1';c<='9';c++){
//                 board[thisrow][thiscol]=c;
//                 if(isValid(board) && solver(board,pos+1)) return true;
//                 board[thisrow][thiscol]='.';
//             }
//         }else{
//             //这个位置是数字，直接进入下一个位置
//             return solver(board,pos+1);
//         }
//         return false;
//     }


//     public boolean isValid(char[][] board){
//         boolean[][] row=new boolean[9][9];
//         boolean[][] col=new boolean[9][9];  //第i 行表示九宫格第i 列
//         boolean[][] room=new boolean[9][9]; //一行代表一个宫格

//         for(int i=0;i<9;i++){
//             for(int j=0;j<9;j++){
//                 char c=board[i][j];
//                 if(c!='.'){
//                     int num=c-'1';
//                     int roomnum=i/3*3 + j/3;
//                     if(row[i][num] || col[j][num] || room[roomnum][num]){
//                         return false;
//                     }
//                     row[i][num]=true;
//                     col[j][num]=true;
//                     room[roomnum][num]=true;
//                 }
//             }
//         }
//         return true;
//     }
// }



//暴力，主要是回溯，与皇后本质一样，但是复杂度提升，随机填入一个数字然后判断isValid
//递归中止条件是i==9
//701,5.11;  39.7,7.14
// class Solution {
//     public void solveSudoku(char[][] board) {
//         solver(board,0,0);
//     }

//     //用boolean返回值标记是否结束,如何控制程序退出
//     private boolean solver(char[][] board,int i,int j){
//         if(i==9) return true;
//         if(j==9) return solver(board,i+1,0);
//         if(board[i][j]=='.'){
//             for(char c='1';c<='9';c++){
//                 board[i][j]=c;
//                 if(isValid(board) && solver(board,i,j+1)) return true;
//                 board[i][j]='.';
//             }
//         }else return solver(board,i,j+1);
//         return false;
//     }


//     public boolean isValid(char[][] board){
//         boolean[][] row=new boolean[9][9];
//         boolean[][] col=new boolean[9][9];  //第i 行表示九宫格第i 列
//         boolean[][] room=new boolean[9][9]; //一行代表一个宫格

//         for(int i=0;i<9;i++){
//             for(int j=0;j<9;j++){
//                 char c=board[i][j];
//                 if(c!='.'){
//                     int num=c-'1';
//                     int roomnum=i/3*3 + j/3;
//                     if(row[i][num] || col[j][num] || room[roomnum][num]){
//                         return false;
//                     }
//                     row[i][num]=true;
//                     col[j][num]=true;
//                     room[roomnum][num]=true;
//                 }
//             }
//         }
//         return true;
//     }
// }


//7，52.99；  37.1，35.71
// class Solution {
//     // box size
//     int n = 3;
//     // row size
//     int N = n * n;

//     int [][] rows = new int[N][N + 1];
//     int [][] columns = new int[N][N + 1];
//     int [][] boxes = new int[N][N + 1];

//     char[][] board;

//     boolean sudokuSolved = false;

//     public boolean couldPlace(int d, int row, int col) {
//     /*
//     Check if one could place a number d in (row, col) cell
//     */
//         int idx = (row / n ) * n + col / n;
//         return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
//     }

//     public void placeNumber(int d, int row, int col) {
//     /*
//     Place a number d in (row, col) cell
//     */
//         int idx = (row / n ) * n + col / n;

//         rows[row][d]++;
//         columns[col][d]++;
//         boxes[idx][d]++;
//         board[row][col] = (char)(d + '0');
//     }

//     public void removeNumber(int d, int row, int col) {
//     /*
//     Remove a number which didn't lead to a solution
//     */
//         int idx = (row / n ) * n + col / n;
//         rows[row][d]--;
//         columns[col][d]--;
//         boxes[idx][d]--;
//         board[row][col] = '.';
//     }

//     public void placeNextNumbers(int row, int col) {
//     /*
//     Call backtrack function in recursion
//     to continue to place numbers
//     till the moment we have a solution
//     */
//         // if we're in the last cell
//         // that means we have the solution
//         if ((col == N - 1) && (row == N - 1)) {
//             sudokuSolved = true;
//         }
//         // if not yet
//         else {
//             // if we're in the end of the row
//             // go to the next row
//             if (col == N - 1) backtrack(row + 1, 0);
//                 // go to the next column
//             else backtrack(row, col + 1);
//         }
//     }

//     public void backtrack(int row, int col) {
//     /*
//     Backtracking
//     */
//         // if the cell is empty
//         if (board[row][col] == '.') {
//             // iterate over all numbers from 1 to 9
//             for (int d = 1; d < 10; d++) {
//                 if (couldPlace(d, row, col)) {
//                     placeNumber(d, row, col);
//                     placeNextNumbers(row, col);
//                     // if sudoku is solved, there is no need to backtrack
//                     // since the single unique solution is promised
//                     if (!sudokuSolved) removeNumber(d, row, col);
//                 }
//             }
//         }
//         else placeNextNumbers(row, col);
//     }

//     public void solveSudoku(char[][] board) {
//         this.board = board;

//         // init rows, columns and boxes
//         for (int i = 0; i < N; i++) {
//             for (int j = 0; j < N; j++) {
//                 char num = board[i][j];
//                 if (num != '.') {
//                     int d = Character.getNumericValue(num);
//                     placeNumber(d, i, j);
//                 }
//             }
//         }
//         backtrack(0, 0);
//     }
// }


//2，98.64；37.1，35.71
class Solution {
    final int N = 9;
    int[] row = new int [N], col = new int [N];
    //ones数组表示0~2^9 - 1的整数中二进制表示中1的个数:如ones[7] = 3 ones[8] = 1
    //map数组表示2的整数次幂中二进制1所在位置（从0开始） 如 map[1] = 0,map[2] = 1, map[4] = 2
    int[] ones = new int[1 << N], map = new int[1 << N];
    int[][] cell = new int[3][3];
    public void solveSudoku(char[][] board) {
        init();
        int cnt = fill_state(board);
        dfs(cnt, board);
    }
    void init(){
        for(int i = 0; i < N; i++) row[i] = col[i] = (1 << N) - 1;
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                cell[i][j] = (1 << N) - 1;
        //以上2个循环把数组的数初始化为二进制表示9个1，即一开始所以格子都可填
        for(int i = 0; i < N; i++) map[1 << i] = i;
        //统计0~2^9 - 1的整数中二进制表示中1的个数
        for(int i = 0; i < 1 << N; i++){
            int n = 0;
            for(int j = i; j != 0; j ^= lowBit(j)) n++;
            ones[i] = n;
        }
    }
    //计算空格数量以及填写row、col和cell
    int fill_state(char[][] board){
        int cnt = 0;    //统计board数组空格('.')的个数
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[i][j] != '.'){
                    int t = board[i][j] - '1';
                    //数独中 i,j位置为数组下标，修改row col cell数组中状态
                    change_state(i, j, t);
                }else cnt++;
            }
        }
        return cnt;
    }
    boolean dfs(int cnt, char[][] board){
        if(cnt == 0) return true;
        int min = 10, x = 0, y = 0;
        //剪枝，即找出当前所以空格可填数字个数最少的位置 记为x y
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[i][j] == '.'){
                    int k = ones[get(i, j)];
                    if(k < min){
                        min = k;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        //遍历当前位置所有可选数字
        for(int i = get(x, y); i != 0; i ^= lowBit(i)){
            int t = map[lowBit(i)];

            change_state(x, y, t);
            board[x][y] = (char)('1' + t);

            if(dfs(cnt - 1, board)) return true;

            //恢复现场
            change_state(x, y, t);
            board[x][y] = '.';
        }
        return false;
    }
    void change_state(int x, int y, int t){
        row[x] ^= 1 << t;
        col[y] ^= 1 << t;
        cell[x / 3][y / 3] ^= 1 << t;
    }
    //对维护数组x行,y列的3个集合(行、列、九宫格)进行&运算
    //就可以获得可选数字的集合(因为我们用1标识可填数字)
    //根据返回的二进制数字中有几个1就有几个可选数字
    int get(int x, int y){
        return row[x] & col[y] & cell[x / 3][y / 3];
    }
    int lowBit(int x){
        return -x & x;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
