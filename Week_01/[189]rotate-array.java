//三次翻转
//0,100;40.4,7.14
// class Solution {
//     public void rotate(int[] nums, int k) {
//         int length=nums.length;
//         k%=length;
//         // if(k==0) return;
//         reverse(nums,0,length-1);
//         reverse(nums,0,k-1);
//         reverse(nums,k,length-1);
//     }

//     private void reverse(int[] nums,int i,int j){
//         int count=(j-i+1)/2;
//         for (int k = 0; k < count; k++) {
//             swap(nums,i+k,j-k);
//         }
//     }

//     private void swap(int[] nums,int i,int j){
//         int tmp=nums[i];
//         nums[i]=nums[j];
//         nums[j]=tmp;
//     }
// }

//三次翻转
//0,100;40.2,7.14
// public class Solution {
//     public void rotate(int[] nums, int k) {
//         k %= nums.length;
//         reverse(nums, 0, nums.length - 1);
//         reverse(nums, 0, k - 1);
//         reverse(nums, k, nums.length - 1);
//     }
//     public void reverse(int[] nums, int start, int end) {
//         while (start < end) {
//             int temp = nums[start];
//             nums[start] = nums[end];
//             nums[end] = temp;
//             start++;
//             end--;
//         }
//     }
// }

//循环k次，每次移一位
//282,8.13;40.5,7.14
// class Solution{
//     public void rotate(int[] nums, int k) {
//         for (int i = 0; i < k; i++) {
//             int last=nums[nums.length-1];
//             for (int j = nums.length-1; j > 0 ; j--) {
//                 nums[j]=nums[j-1];
//             }
//             nums[0]=last;
//         }
//     }
// }

//额外数组
//0,100;40.3,7.14
// class Solution{
//     public void rotate(int[] nums, int k) {
//         int[] res=new int[nums.length];
//         for (int i = 0; i < nums.length; i++) {
//             res[(i+k)%nums.length]=nums[i];
//         }
//         System.arraycopy(res, 0, nums, 0, nums.length);
//     }
// }

//环形替换
//1,67.53;40.2,7.14
// class Solution {
//     public void rotate(int[] nums, int k) {
//         k%=nums.length;
//         if(k==0) return;
//         int count=0;
//         int flag=gcd(nums.length,k);
//         for (int i = 0; count < flag; i++) {
//             count++;
//             int curindex=i;
//             int nextindex=(curindex+k)%nums.length;
//             while(nextindex!=i){
//                 swap(nums,i,nextindex);
//                 curindex=nextindex;
//                 nextindex=(curindex+k)%nums.length;
//             }
//         }
//     }

//     private void swap(int[] nums,int i,int j){
//         int tmp=nums[i];
//         nums[i]=nums[j];
//         nums[j]=tmp;
//     }

//     private int gcd(int x,int y){
//         return y==0?x:gcd(y,x%y);
//     }

// }

//环形替代2
//0,100;40.2,7.14
class Solution{
    public void rotate(int[] nums, int k) {
        k%=nums.length;
        int count=0;
        for (int i = 0; count < nums.length; i++) {
            int curindex=i;
            int pre=nums[i];
            do{
                count++;
                int nextindex=(curindex+k)%nums.length;
                int tmp=nums[nextindex];
                nums[nextindex]=pre;
                pre=tmp;
                curindex=nextindex;
            }while (curindex!=i);
        }
    }

}