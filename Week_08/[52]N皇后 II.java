//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回 n 皇后不同的解决方案的数量。 
//
// 示例: 
//
// 输入: 4
//输出: 2
//解释: 4 皇后问题存在如下两个不同的解法。
//[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
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

//0，100； 36.2，16.67
// class Solution {
//     public int totalNQueens(int n) {
//         int[] rs = new int[]{0,1,0,0,2,10,4,40,92,352,724,2680};
//         return rs[n];
//     }
// }


//回溯
class Solution{

    private int count=0;
    public int totalNQueens(int n){
        if(n==0) return 0;
        //已访问数组
        boolean[] col=new boolean[n];
        boolean[] pie=new boolean[2*n-1];
        boolean[] na=new boolean[2*n-1];

        NQueen(0,col,pie,na);
        return count;
    }

    private void NQueen(int row,boolean[] col,boolean[] pie,boolean[] na){
        if(row==col.length){
            count++;
            return;
        }

        for(int i=0;i<col.length;i++){
            if(!col[i] && !pie[row+i] && !na[row-i+col.length-1]){
                col[i]=true;
                pie[row+i]=true;
                na[row-i+col.length-1]=true;
                NQueen(row+1,col,pie,na);
                col[i]=false;
                pie[row+i]=false;
                na[row-i+col.length-1]=false;
            }
        }
    }
}

//位运算练习
//0，100；  36.5，16.67
class Solution{
    private int ans=0;
    private int size;
    public int totalNQueens(int n){
        size=(1<<n)-1;
        solve(0,0,0);
        return ans;
    }

    private void solve(int col,int pie,int na){
        if(col==size) {
            ans++;
            return;
        }

        int pos= size & (~(col | pie | na));
        while(pos != 0){
            int p = pos & -pos;
            solve(col | p, (pie | p)<<1, (na | p)>>1);
            pos  ^= p;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
