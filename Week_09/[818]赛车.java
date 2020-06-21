//你的赛车起始停留在位置 0，速度为 +1，正行驶在一个无限长的数轴上。（车也可以向负数方向行驶。） 
//
// 你的车会根据一系列由 A（加速）和 R（倒车）组成的指令进行自动驾驶 。 
//
// 当车得到指令 "A" 时, 将会做出以下操作： position += speed, speed *= 2。 
//
// 当车得到指令 "R" 时, 将会做出以下操作：如果当前速度是正数，则将车速调整为 speed = -1 ；否则将车速调整为 speed = 1。 (当前所
//处位置不变。) 
//
// 例如，当得到一系列指令 "AAR" 后, 你的车将会走过位置 0->1->3->3，并且速度变化为 1->2->4->-1。 
//
// 现在给定一个目标位置，请给出能够到达目标位置的最短指令列表的长度。 
//
// 示例 1:
//输入: 
//target = 3
//输出: 2
//解释: 
//最短指令列表为 "AA"
//位置变化为 0->1->3
// 
//
// 示例 2:
//输入: 
//target = 6
//输出: 5
//解释: 
//最短指令列表为 "AAARA"
//位置变化为 0->1->3->7->7->6
// 
//
// 说明: 
//
// 
// 1 <= target（目标位置） <= 10000。 
// 
// Related Topics 堆 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
//bottom-up  dp
//9，56.06；  36.4，100
 class Solution {
     public int racecar(int target) {
         int[] dp = new int[target + 1];

         for (int i = 1; i <= target; i++) {
             dp[i] = Integer.MAX_VALUE;

             int m = 1, j = 1;

             for (; j < i; j = (1 << ++m) - 1) {
                 for (int q = 0, p = 0; p < j; p = (1 << ++q) - 1) {
                     dp[i] = Math.min(dp[i], m + 1 + q + 1 + dp[i - (j - p)]);
                 }
             }

             dp[i] = Math.min(dp[i], m + (i == j ? 0 : 1 + dp[j - i]));
         }

         return dp[target];
     }
 }



//top-down  dp
//16,26.26;   37.9,100
// class Solution{
//     public int racecar(int target) {
//         int[] dp = new int[target + 1];
//         Arrays.fill(dp, 1, dp.length, -1);
//         return racecar(target, dp);
//     }

//     private int racecar(int i, int[] dp) {
//         if (dp[i] >= 0) {
//             return dp[i];
//         }

//         dp[i] = Integer.MAX_VALUE;

//         int m = 1, j = 1;

//         for (; j < i; j = (1 << ++m) - 1) {
//             for (int q = 0, p = 0; p < j; p = (1 << ++q) - 1) {
//                 dp[i] = Math.min(dp[i],  m + 1 + q + 1 + racecar(i - (j - p), dp));
//             }
//         }

//         dp[i] = Math.min(dp[i], m + (i == j ? 0 : 1 + racecar(j - i, dp)));

//         return dp[i];
//     }
// }


//dp by lee
//备忘录的方式
//Let's say n is the length of target in binary and we have 2 ^ (n - 1) <= target < 2 ^ n
// We have 2 strategies here:
// 1. Go pass our target , stop and turn back
// We take n instructions of A.
// 1 + 2 + 4 + ... + 2 ^ (n-1) = 2 ^ n - 1
// Then we turn back by one R instruction.
// In the end, we get closer by n + 1 instructions.
// 2. Go as far as possible before pass target, stop and turn back
// We take n - 1 instruction of A and one R.
// Then we take m instructions of A, where m < n
//2,91.92;   38.7,100
// class Solution{
//     int[] dp = new int[10001];
//     public int racecar(int t) {
//         if (dp[t] > 0) return dp[t];
//         int n = (int)(Math.log(t) / Math.log(2)) + 1;
//         if (1 << n == t + 1) {
//             dp[t] = n;
//         } else {
//             dp[t] = racecar((1 << n) - 1 - t) + n + 1;
//             for (int m = 0; m < n - 1; ++m) {
//                 dp[t] = Math.min(dp[t], racecar(t - (1 << (n - 1)) + (1 << m)) + n + m + 1);
//             }
//         }
//         return dp[t];
//     }
// }

//dfs
//253,14.14;   84.7,100
// class Solution{
//     public int racecar(int target) {
//         Deque<int[]> queue = new LinkedList<>();
//         queue.offerLast(new int[] {0, 1}); // starts from position 0 with speed 1

//         Set<String> visited = new HashSet<>();
//         visited.add(0 + " " + 1);

//         for (int level = 0; !queue.isEmpty(); level++) {
//             for(int k = queue.size(); k > 0; k--) {
//                 int[] cur = queue.pollFirst();  // cur[0] is position; cur[1] is speed

//                 if (cur[0] == target) {
//                     return level;
//                 }

//                 int[] nxt = new int[] {cur[0] + cur[1], cur[1] << 1};  // accelerate instruction
//                 String key = (nxt[0] + " " + nxt[1]);

//                 if (!visited.contains(key) && 0 < nxt[0] && nxt[0] < (target << 1)) {
//                     queue.offerLast(nxt);
//                     visited.add(key);
//                 }

//                 nxt = new int[] {cur[0], cur[1] > 0 ? -1 : 1};  // reverse instruction
//                 key = (nxt[0] + " " + nxt[1]);

//                 if (!visited.contains(key) && 0 < nxt[0] && nxt[0] < (target << 1)) {
//                     queue.offerLast(nxt);
//                     visited.add(key);
//                 }
//             }
//         }

//         return -1;
//     }
// }



//使用一个一维数组dp，dp[i]代表走到位置i处所需要的最小步数。因为先向前走forword步再向后走back步与先向后走back步再向前走forword步最后到达的位置相同，所以可以假设永远保持第一步是向前走的。第一步有三种情况：

//第一种是刚好走forword步后到达了目标位置i，则dp[i] = forword。
// 第二种情况是向前走forword步后到达了位置i后面，这时需要再往回走，再加上回头的那一步，此时dp[i] = Math.min(dp[i], forword + 1 + dp[j - i]);（注意这里的上限是走到2 * i处）
// 第三种情况是向前走forword步后未到达位置i处就需要返回，此时在保证返回的步数back < forword的条件下遍历back，此时dp[i] = Math.min(dp[i], forword + 1 + back + 1 + dp[i - j + k])。
// 得到转移方程后递归i，最后dp[target]即为所求值。
class Solution {
    public int racecar(int target) {
        int[] dp = new int[target + 1];
        for (int i = 1;i <= target; i++){
            dp[i] = Integer.MAX_VALUE;
            for (int forword = 1;(1 << forword) - 1 < 2 * i; forword++) {
                int j = (1 << forword) - 1;
                if(j == i)
                    dp[i] = forword;
                else if (j > i)
                    dp[i] = Math.min(dp[i], forword + 1 + dp[j - i]);
                else
                    for(int back = 0; back < forword; back++) {
                        int k = (1 << back) - 1;
                        dp[i] =Math.min(dp[i], forword + 1 + back + 1 + dp[i - j + k]);
                    }
            }
        }
        return dp[target];
    }
}

//leetcode submit region end(Prohibit modification and deletion)
