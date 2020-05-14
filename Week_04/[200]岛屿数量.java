//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//11110
//11010
//11000
//00000
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//11000
//11000
//00100
//00011
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集


//leetcode submit region begin(Prohibit modification and deletion)
//2,96.16;  42.2,6.25
class Solution {
    public int numIslands(char[][] grid) {
        int count=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid,int i,int j){
        int[] dx={0,0,-1,1};
        int[] dy={-1,1,0,0};
        grid[i][j]='0';
        for(int k=0;k<4;k++){
            if(i+dx[k]>=0 && i+dx[k]<grid.length && j+dy[k]>=0
                    && j+dy[k]<grid[0].length && grid[i+dx[k]][j+dy[k]]=='1'){
                dfs(grid,i+dx[k],j+dy[k]);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
