//给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 示例: 
//
// 输入:
//[
//  ["1","0","1","0","0"],
//  ["1","0","1","1","1"],
//  ["1","1","1","1","1"],
//  ["1","0","0","1","0"]
//]
//输出: 6 
// Related Topics 栈 数组 哈希表 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
//想到一种，每个位置使用[x,y]表示，即每个位置记录两个数据，其他都和正方形一样
//不行，这样与正方形没有区别，辣鸡
 class Solution {
     public int maximalRectangle(char[][] matrix) {
         if(matrix==null || matrix.length==0) return 0;
         int[][][] dp=new int[matrix.length][matrix[0].length][2];
         int maxX=0,maxY=0;
         for(int i=0;i<matrix.length;i++){
             for(int j=0;j<matrix[0].length;j++){
                 if(matrix[i][j]=='1'){
                     if(i==0 || j==0) dp[i][j]=new int[]{1,1};
                     else {
                         dp[i][j][0]=Math.min(Math.min(dp[i-1][j][0],dp[i][j-1][0]),dp[i-1][j-1][0])+1;
                         dp[i][j][1]=Math.min(Math.min(dp[i-1][j][1],dp[i][j-1][1]),dp[i-1][j-1][1])+1;
                     }
                     maxX=Math.max(maxX,dp[i][j][0]);
                     maxY=Math.max(maxY,dp[i][j][1]);
                 }
             }
         }
         return maxX*maxY;
     }
 }



//通过位运算求柱子高度
//89，5.05；40.9，93.33
// import java.math.BigInteger;
// class Solution {
//     public int maximalRectangle(char[][] matrix) {
//        int m=matrix.length;
//        if(m==0) return 0;
//        int n=matrix[0].length;
//        int res=0;
//        List<BigInteger> s=new ArrayList<>();
//        for(int i=0;i<m;i++){
//            String str="";
//            BigInteger b=new BigInteger("0");
//            for(int j=0;j<n;j++){
//               BigInteger bit= matrix[i][j]=='0'?BigInteger.ZERO:BigInteger.ONE;
//               b=b.or(bit.shiftLeft(j)) ;
//            }
//            s.add(b);
//        }
//        for(int i=0;i<m;i++){
//             BigInteger b=s.get(i);
//             for(int j=i;j<m;j++){
//               b=b.and(s.get(j));
//               if(b.equals(BigInteger.ZERO))break;
//               BigInteger cur=new BigInteger(b.toString());
//               int width=0;
//               while(!cur.equals(BigInteger.ZERO)){
//                   ++width;
//                   cur=cur.and(cur.shiftRight(1));
//               }
//               res=Math.max(res,(j-i+1)*width);
//             }
//         }
//         return res;
//     }
// }


//O(M*N^2)
//遍历整个数组，每个位置计算从本行到第一行的以本节点为右下点的矩形面积
//20,19.12;  43,26.67
// class Solution{
//     public int maximalRectangle(char[][] matrix) {
//         if (matrix.length == 0) {
//             return 0;
//         }
//         //保存以当前数字结尾的连续 1 的个数
//         int[][] width = new int[matrix.length][matrix[0].length];
//         int maxArea = 0;
//         //遍历每一行
//         for (int row = 0; row < matrix.length; row++) {
//             for (int col = 0; col < matrix[0].length; col++) {
//                 //更新 width
//                 if (matrix[row][col] == '1') {
//                     if (col == 0) {
//                         width[row][col] = 1;
//                     } else {
//                         width[row][col] = width[row][col - 1] + 1;
//                     }
//                 } else {
//                     width[row][col] = 0;
//                 }
//                 //记录所有行中最小的数
//                 int minWidth = width[row][col];
//                 //向上扩展行
//                 for (int up_row = row; up_row >= 0; up_row--) {
//                     int height = row - up_row + 1;
//                     //找最小的数作为矩阵的宽
//                     minWidth = Math.min(minWidth, width[up_row][col]);
//                     //更新面积
//                     maxArea = Math.max(maxArea, height * minWidth);
//                 }
//             }
//         }
//         return maxArea;
//     }
// }


//借助84题的最大矩形面积
//这里是直接调用84题的方法，可以将栈调用直接融合到主函数中，每计算出一个高度就更新栈
//9,70.73;  42.4,73.33
// class Solution{
//     public int maximalRectangle(char[][] matrix) {
//         if (matrix.length == 0) {
//             return 0;
//         }
//         int[] heights = new int[matrix[0].length];
//         int maxArea = 0;
//         for (int row = 0; row < matrix.length; row++) {
//             //遍历每一列，更新高度
//             for (int col = 0; col < matrix[0].length; col++) {
//                 if (matrix[row][col] == '1') {
//                     heights[col] += 1;
//                 } else {
//                     heights[col] = 0;
//                 }
//             }
//             //调用上一题的解法，更新函数
//             maxArea = Math.max(maxArea, largestRectangleArea(heights));
//         }
//         return maxArea;
//     }

//     public int largestRectangleArea(int[] heights) {
//         // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
//         int[] tmp = new int[heights.length + 2];
//         System.arraycopy(heights, 0, tmp, 1, heights.length);

//         Deque<Integer> stack = new ArrayDeque<>();
//         int area = 0;
//         for (int i = 0; i < tmp.length; i++) {
//             // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于自身的柱体」；
//             // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」
//             // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积🌶️ ～
//             while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
//                 int h = tmp[stack.pop()];
//                 area = Math.max(area, (i - stack.peek() - 1) * h);
//             }
//             stack.push(i);
//         }
//         return area;
//     }
// }



//84题的另一种解法，使用两个数组保存每个index的左边第一个和右边第一个小于heigth[index]的索引
//因为使用单调栈无法保存状态，所以使用数组保存每层时的两个数组，
//5,85.77;  42.7,60
// class Solution{
//     public int maximalRectangle(char[][] matrix) {
//         if (matrix.length == 0) {
//             return 0;
//         }
//         int[] heights = new int[matrix[0].length];
//         int maxArea = 0;
//         for (int row = 0; row < matrix.length; row++) {
//             //遍历每一列，更新高度
//             for (int col = 0; col < matrix[0].length; col++) {
//                 if (matrix[row][col] == '1') {
//                     heights[col] += 1;
//                 } else {
//                     heights[col] = 0;
//                 }
//             }
//             //调用上一题的解法，更新函数
//             maxArea = Math.max(maxArea, largestRectangleArea(heights));
//         }
//         return maxArea;
//     }

//     public int largestRectangleArea(int[] heights) {
//         if (heights.length == 0) {
//             return 0;
//         }
//         int[] leftLessMin = new int[heights.length];
//         leftLessMin[0] = -1;
//         for (int i = 1; i < heights.length; i++) {
//             int l = i - 1;
//             while (l >= 0 && heights[l] >= heights[i]) {
//                 l = leftLessMin[l];
//             }
//             leftLessMin[i] = l;
//         }

//         int[] rightLessMin = new int[heights.length];
//         rightLessMin[heights.length - 1] = heights.length;
//         for (int i = heights.length - 2; i >= 0; i--) {
//             int r = i + 1;
//             while (r <= heights.length - 1 && heights[r] >= heights[i]) {
//                 r = rightLessMin[r];
//             }
//             rightLessMin[i] = r;
//         }
//         int maxArea = 0;
//         for (int i = 0; i < heights.length; i++) {
//             int area = (rightLessMin[i] - leftLessMin[i] - 1) * heights[i];
//             maxArea = Math.max(area, maxArea);
//         }
//         return maxArea;
//     }
// }


//dp，根据新加入的值更新状态数组而不是完全重新计算
//4,96.81;  43.5,13.33
class Solution{
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int maxArea = 0;
        int cols = matrix[0].length;
        int[] leftLessMin = new int[cols];
        int[] rightLessMin = new int[cols];
        Arrays.fill(leftLessMin, -1); //初始化为 -1，也就是最左边
        Arrays.fill(rightLessMin, cols); //初始化为 cols，也就是最右边
        int[] heights = new int[cols];
        for (int row = 0; row < matrix.length; row++) {
            //更新所有高度
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == '1') {
                    heights[col] += 1;
                } else {
                    heights[col] = 0;
                }
            }
            //更新所有leftLessMin
            int boundary = -1; //记录上次出现 0 的位置
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == '1') {
                    //和上次出现 0 的位置比较
                    leftLessMin[col] = Math.max(leftLessMin[col], boundary);
                } else {
                    //当前是 0 代表当前高度是 0，所以初始化为 -1，防止对下次循环的影响
                    leftLessMin[col] = -1;
                    //更新 0 的位置
                    boundary = col;
                }
            }
            //右边同理
            boundary = cols;
            for (int col = cols - 1; col >= 0; col--) {
                if (matrix[row][col] == '1') {
                    rightLessMin[col] = Math.min(rightLessMin[col], boundary);
                } else {
                    rightLessMin[col] = cols;
                    boundary = col;
                }
            }

            //更新所有面积
            for (int col = cols - 1; col >= 0; col--) {
                int area = (rightLessMin[col] - leftLessMin[col] - 1) * heights[col];
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
