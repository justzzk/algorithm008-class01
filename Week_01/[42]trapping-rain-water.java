//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针


import org.omg.CORBA.INTERNAL;

import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //直接遍历
    //			执行耗时:150 ms,击败了5.06% 的Java用户
    //			内存消耗:39.2 MB,击败了11.78% 的Java用户
//    public int trap(int[] height) {
//        int ans=0;
//        for (int i = 1; i < height.length; i++) {
//            int leftmax=0;
//            int rightmax=0;
//            for (int j = i; j < height.length; j++) {
//                rightmax=Math.max(rightmax,height[j]);
//            }
//            for (int j = i; j >= 0; j--) {
//                leftmax=Math.max(leftmax,height[j]);
//            }
//            ans+=(Math.min(leftmax,rightmax)-height[i]);
//        }
//        return ans;
//    }

    //记录最值
    //			执行耗时:1 ms,击败了99.98% 的Java用户
    //			内存消耗:39 MB,击败了11.91% 的Java用户
//    public int trap(int[] height) {
//        int len=height.length;
//        if(height==null || len==0) return 0;
//        int[] leftmax=new int[len];
//        leftmax[0]=height[0];
//        int[] rightmax=new int[len];
//        rightmax[len-1]=height[len-1];
//        for (int i = 1; i < len; i++) {
//            leftmax[i]=Math.max(leftmax[i-1],height[i]);
//        }
//
//        for (int i = len-2; i >=0 ; i--) {
//            rightmax[i]=Math.max(rightmax[i+1],height[i]);
//        }
//
//        int ans=0;
//        for (int i = 0; i < len; i++) {
//            ans+=(Math.min(leftmax[i],rightmax[i])-height[i]);
//        }
//        return ans;
//    }
//
    //栈
    //			执行耗时:3 ms,击败了32.65% 的Java用户
    //			内存消耗:39.1 MB,击败了11.78% 的Java用户
//    public int trap(int[] height) {
//        int len=height.length;
//        if(height==null || len==0) return 0;
//        Deque<Integer> stack=new ArrayDeque<>();
//        int ans=0;
//        int current=0;
//        while (current < len){
//            while (!stack.isEmpty() && height[current] > height[stack.peek()]){
//                int tophgt=height[stack.pop()];
//                if(stack.isEmpty()) break;
//                int distance=current-stack.peek()-1;
//                ans+=(Math.min(height[current],height[stack.peek()])-tophgt)*distance;
//            }
//            stack.push(current++);
//        }
//        return ans;
//    }
//
    //双指针
    //			执行耗时:1 ms,击败了99.98% 的Java用户
    //			内存消耗:39.7 MB,击败了11.78% 的Java用户
    public int trap(int[] height) {
        int len=height.length;
        if(height==null || len==0) {
            return 0;
        }
        int leftindex=0;
        int rightindex=len-1;
        int leftmax=height[0];
        int rightmax=height[len-1];
        int ans=0;
        while(leftindex < rightindex){
            if ( leftmax < rightmax ){
                leftindex++;
                leftmax=Math.max(leftmax,height[leftindex]);
                ans+=leftmax-height[leftindex];
            }else{
                rightindex--;
                rightmax=Math.max(rightmax,height[rightindex]);
                ans+=rightmax-height[rightindex];
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
