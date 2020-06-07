//判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 
//
// 上图是一个部分填充的有效的数独。 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 示例 1: 
//
// 输入:
//[
//  ["5","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//[
//  ["8","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//输出: false
//解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
//     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。 
//
// 说明: 
//
// 
// 一个有效的数独（部分已被填充）不一定是可解的。 
// 只需要根据以上规则，验证已经填入的数字是否有效即可。 
// 给定数独序列只包含数字 1-9 和字符 '.' 。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
// 暴力遍历，取得所在的九宫格的范围比较麻烦，不想写了
// class Solution {
//     public boolean isValidSudoku(char[][] board) {
//         for(int i=0;i<board.length;i++){
//             for(int j=0;j<board[0].length;j++){
//                 if(inRow(board[i],board[i][j] && inCol(board,j,board[i][j])
//                             && inRoom(board,i,j))){
//                     return false;
//                 }
//             }
//         }
//         return true;
//     }

//     private boolean inRow(char[] row,char c){
//         for(char crow:row){
//             if(crow==c) return false;
//         }
//         return true;
//     }

//     private boolean inCol(char[][] board,int col,char c){
//         for(int i=0;i<board.length;i++){
//             if(board[i][col]==c) return false;
//         }
//         return true;
//     }

//     private boolean inRoom(char[][] board,int i,int j){

//     }
// }

//2,95.72;  39.2,100
class Solution{
    public boolean isValidSudoku(char[][] board){
        boolean[][] row=new boolean[9][9];
        boolean[][] col=new boolean[9][9];  //第i 行表示九宫格第i 列
        boolean[][] room=new boolean[9][9]; //一行代表一个宫格

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char c=board[i][j];
                if(c!='.'){
                    int num=c-'1';
                    int roomnum=i/3*3 + j/3;
                    if(row[i][num] || col[j][num] || room[roomnum][num]){
                        return false;
                    }
                    row[i][num]=true;
                    col[j][num]=true;
                    room[roomnum][num]=true;
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
