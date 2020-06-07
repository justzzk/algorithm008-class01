//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
//执行用时 :1 ms, 在所有 Java 提交中击败了98.35%的用户
//内存消耗 :39.8 MB, 在所有 Java 提交中击败了5.01%的用户
class Solution {
    private List<String> res=new LinkedList<>();
    public List<String> generateParenthesis(int n) {
        generate(n,n,"");
        return res;
    }

    private void generate(int left,int right,String ans){
        if(left==0 && right==0){
            res.add(ans);
            return;
        }

        if(left>0){
            generate(left-1,right,ans+"(");
        }
        if(right>left){
            generate(left,right-1,ans+")");
        }
    }
}

//回溯
//1,97.59;   39.5,5.26
// class Solution{
//     private List<String> res=new ArrayList<>();
//     public List<String> generateParenthesis(int n){
//         if(n==0) return res;
//         generate(n,0,0,new StringBuilder());
//         return res;
//     }

//     private void generate(int n,int left,int right,StringBuilder sb){
//         if(sb.length()==2*n){
//             res.add(sb.toString());
//             return;
//         }

//         //如果使用数组，就不用sb.delete,因为有了left和right，就可以在数组的相应索引直接写
//         if(left < n){
//             sb.append("(");
//             generate(n,left+1,right,sb);
//             sb.deleteCharAt(sb.length()-1);
//         }

//         if(right < left){
//             sb.append(")");
//             generate(n,left,right+1,sb);
//             sb.deleteCharAt(sb.length()-1);
//         }
//     }
// }


//dp
//10,14.37;   39.8,5.26
class Solution{
    public List<String> generateParenthesis(int n){
        List<String>[] dp=new ArrayList[n+1];
        dp[0]=new ArrayList<>();
        dp[0].add("");
        for(int i=1;i<n+1;i++){
            dp[i]=new ArrayList<>();
            for(int j=0;j<i;j++){
                for(String s1:dp[j]){
                    for(String s2:dp[i-1-j]){
                        dp[i].add( "("+s1+")"+s2 );
                    }
                }
            }
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
