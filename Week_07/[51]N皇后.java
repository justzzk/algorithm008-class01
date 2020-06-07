//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 示例: 
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法





//leetcode submit region begin(Prohibit modification and deletion)
//使用hash保存状态
//8，31.27； 41，5.72
//public class Solution {
//
//    public List<List<String>> solveNQueens(int n) {
//        List<List<String>> res = new ArrayList<>();
//        if (n == 0) {
//            return res;
//        }
//
//        int[] nums = new int[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = i;
//        }
//
//        Set<Integer> col = new HashSet<>();
//        Set<Integer> master = new HashSet<>();
//        Set<Integer> slave = new HashSet<>();
//        Stack<Integer> stack = new Stack<>();
//
//        backtrack(nums, 0, n, col, master, slave, stack, res);
//        return res;
//    }
//
//    private void backtrack(int[] nums, int row, int n,
//                           Set<Integer> col,
//                           Set<Integer> master,
//                           Set<Integer> slave,
//                           Stack<Integer> stack,
//                           List<List<String>> res) {
//
//        if (row == n) {
//            List<String> board = convert2board(stack, n);
//            res.add(board);
//            return;
//        }
//
//        // 针对每一列，尝试是否可以放置
//        for (int i = 0; i < n; i++) {
//            if (!col.contains(i) && !master.contains(row + i) && !slave.contains(row - i)) {
//                stack.add(nums[i]);
//                col.add(i);
//                master.add(row + i);
//                slave.add(row - i);
//
//                backtrack(nums, row + 1, n, col, master, slave, stack, res);
//
//                slave.remove(row - i);
//                master.remove(row + i);
//                col.remove(i);
//                stack.pop();
//            }
//        }
//    }
//
//    private List<String> convert2board(Stack<Integer> stack, int n) {
//        List<String> board = new ArrayList<>();
//        for (Integer num : stack) {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (int i = 0; i < n; i++) {
//                stringBuilder.append(".");
//            }
//            stringBuilder.replace(num, num + 1, "Q");
//            board.add(stringBuilder.toString());
//        }
//        return board;
//    }
//
//}


//使用数组保存状态
//4，71.75；  40.1，8.57
class Solution{
    public List<List<String>> solveNQueens(int n){
        List<List<String>> res=new ArrayList<>();
        if(n==0){
            return res;
        }
        //列、撇、捺访问数组
        boolean[] col=new boolean[n];
        //比如n==4，撇的范围0～6，捺的范围-3～3
        boolean[] pie=new boolean[2*n+1];
        boolean[] na=new boolean[2*n-1];
        //位置保存数组
        int[] pos=new int[n];

        backTrace(res,col,pie,na,pos,0);
        return res;
    }

    private void backTrace(List<List<String>> res,
                           boolean[] col,
                           boolean[] pie,
                           boolean[] na,
                           int[] pos,
                           int row){
        if(row==pos.length){
            res.add(convert2board(pos));
            return;
        }
        //每一次循环都是进入下一列尝试
        for(int i=0;i<pos.length;i++){
            if(!col[i] && !pie[row+i] && !na[row-i+pos.length-1]){
                pos[row]=i;
                col[i]=true;
                pie[row+i]=true;
                na[row-i+pos.length-1]=true;
                backTrace(res,col,pie,na,pos,row+1);
                col[i]=false;
                pie[row+i]=false;
                na[row-i+pos.length-1]=false;
                //pos不需要做特殊处理
            }
        }

    }

    private List<String> convert2board(int[] pos){
        List<String> res=new ArrayList<>();
        int length=pos.length;
        for(int i=0;i<length;i++){
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<length;j++){
                sb.append('.');
            }
            sb.replace(pos[i],pos[i]+1,"Q");
            res.add(sb.toString());
        }
        return res;
    }
}


//使用位图保存状态
//6，44.41；  39.6，14.29
// public class Solution {

//     public List<List<String>> solveNQueens(int n) {
//         List<List<String>> res = new ArrayList<>();
//         if (n == 0) {
//             return res;
//         }

//         int[] nums = new int[n];
//         for (int i = 0; i < n; i++) {
//             nums[i] = i;
//         }

//         int col = 0;
//         int master = 0;
//         int slave = 0;
//         Stack<Integer> stack = new Stack<>();

//         backtrack(nums, 0, n, col, master, slave, stack, res);
//         return res;
//     }

//     private void backtrack(int[] nums, int row, int n,
//                            int col,
//                            int master,
//                            int slave,
//                            Stack<Integer> stack,
//                            List<List<String>> res) {

//         if (row == n) {
//             List<String> board = convert2board(stack, n);
//             res.add(board);
//             return;
//         }

//         // 针对每一列，尝试是否可以放置
//         for (int i = 0; i < n; i++) {
//             if (((col >> i) & 1) == 0
//                     && ((master >> (row + i)) & 1) == 0
//                     && ((slave >> (row - i + n - 1)) & 1) == 0) {
//                 stack.add(nums[i]);
//                 col ^= (1 << i);
//                 master ^= (1 << (row + i));
//                 slave ^= (1 << (row - i + n - 1));

//                 backtrack(nums, row + 1, n, col, master, slave, stack, res);

//                 slave ^= (1 << (row - i + n - 1));
//                 master ^= (1 << (row + i));
//                 col ^= (1 << i);
//                 stack.pop();
//             }
//         }
//     }

//     private List<String> convert2board(Stack<Integer> stack, int n) {
//         List<String> board = new ArrayList<>();
//         for (Integer num : stack) {
//             StringBuilder stringBuilder = new StringBuilder();
//             for (int i = 0; i < n; i++) {
//                 stringBuilder.append(".");
//             }
//             stringBuilder.replace(num, num + 1, "Q");
//             board.add(stringBuilder.toString());
//         }
//         return board;
//     }
// }

//leetcode submit region end(Prohibit modification and deletion)
