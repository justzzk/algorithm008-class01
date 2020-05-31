//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。 
//
// 示例: 
//
// 输入: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
//7,46.26;  42.9,50
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix==null || matrix.length==0){
            return 0;
        }
        //使用二维数组保存每个位置作为正方形右下角时正方形的边长
        int[][] res=new int[matrix.length][matrix[0].length];
        int maxlen=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]=='1'){
                    // int leftup=((i-1<0 || j-1<0)?0:res[i-1][j-1]);
                    // int left=((j-1<0)?0:res[i][j-1]);
                    // int up=((i-1<0)?0:res[i-1][j]);
                    // res[i][j]=Math.min(leftup,Math.min(left,up))+1;
                    if (i == 0 || j == 0) {
                        res[i][j] = 1;
                    } else {
                        res[i][j] = Math.min(Math.min(res[i - 1][j], res[i][j - 1]), res[i - 1][j - 1]) + 1;
                    }
                    maxlen=Math.max(maxlen,res[i][j]);
                }
            }
        }
        return maxlen*maxlen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
