//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //暴力递归,超时
    // public int climbStairs(int n) {
    //     if(n==0 || n==1){
    //         return 1;
    //     }
    //     return climbStairs(n-1)+climbStairs(n-2);
    // }

    // public int climbStairs(int n){
    //     return climb(0,n);
    // }
    // //i表示已经爬了台阶数，n表示目标台阶数
    // private int climb(int i,int n){
    //     if(i>n){
    //         return 0;
    //     }
    //     if(i==n){
    //         return 1;
    //     }
    //     return climb(i+1,n)+climb(i+2,n);
    // }


    //使用数组保存中间结果
    //0，100； 36.3，5.66
    // public int climbStairs(int n){
    //     if(n<0) return 0;
    //     if(n==0 || n==1) return 1;
    //     int[] res=new int[n+1];
    //     res[0]=1;
    //     res[1]=1;
    //     return climb(n,res);
    // }

    // private int climb(int n,int[] res){
    //     if(res[n]!=0){
    //         return res[n];
    //     }
    //     return res[n]=climb(n-1,res)+climb(n-2,res);
    // }


    //dp or fi
    //0，100； 36.3，5.66
    // public int climbStairs(int n){
    //     int[] dp=new int[n+1];
    //     dp[0]=1;
    //     dp[1]=1;
    //     for(int i=2;i<=n;i++){
    //         dp[i]=dp[i-1]+dp[i-2];
    //     }
    //     return dp[n];
    // }

    //优化的dp
    // public int climbStairs(int n){
    //     if(n==0 || n==1){
    //         return 1;
    //     }
    //     int add1=1;
    //     int add2=1;
    //     int res=0;
    //     for(int i=2;i<=n;i++){
    //         res=add1+add2;
    //         add1=add2;
    //         add2=res;
    //     }
    //     return res;
    // }
    //fibonaic解法
    // public int climbStairs(int n){
    //     if(n<=1){
    //         return 1;
    //     }
    //     int add1=1;
    //     int add2=1;
    //     int sum=0;
    //     for(int i=2;i<=n;i++){
    //         sum=add1+add2;
    //         add1=add2;
    //         add2=sum;
    //     }
    //     return sum;
    // }

    //dp
    public int climbStairs(int n){
        if(n<0) return 0;
        if(n==0 || n==1) return 1;
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
