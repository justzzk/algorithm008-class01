//我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 数学


//leetcode submit region begin(Prohibit modification and deletion)
//3,86.88;  38,100
class Solution {
    public int nthUglyNumber(int n) {
        int[] res=new int[n];
        res[0]=1;
        int ptr2=0;
        int ptr3=0;
        int ptr5=0;
        for(int i=1;i<n;i++){
            res[i]=Math.min(res[ptr2]*2,Math.min(res[ptr3]*3,res[ptr5]*5));
            if(res[i]==res[ptr2]*2){
                ptr2++;
            }
            if(res[i]==res[ptr3]*3){
                ptr3++;
            }
            if(res[i]==res[ptr5]*5){
                ptr5++;
            }
        }
        return res[n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
