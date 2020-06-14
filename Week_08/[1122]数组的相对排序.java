//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组


//leetcode submit region begin(Prohibit modification and deletion)
// laji
// 本来是想用map保存arr2中出现的元素和次数，将未出现的直接搬移到arr1的末尾，
// 遍历完arr1后将arr2中出现的从头开始安排到arr1中，
// 但是在第一遍就进行搬移势必会覆盖后面还没有统计的元素，所以可以额外使用数组保存未出现的数字，最后排序后复制到arr1中
// 总的来说，如果不使用桶排序的话，还是挺麻烦的
// class Solution {
//     public int[] relativeSortArray(int[] arr1, int[] arr2) {
//         HashMap<Integer,Integer> map=new HashMap<>();
//         for(int num:arr2) map.put(num,0);
//         int[] others=new int[arr1.length-arr2.length];
//         int index=0;
//         int count=0;
//         int[] others=new int[]
//         for(int num:arr1) {
//             if(map.containsKey(num)) {
//                 map.put(num,map.get(num)+1);
//                 count++;
//             }
//             else arr1[index++]=num;
//         }
//         for(int i=0;i<arr1.length-count;i++) arr1[arr1.length-1-i]=arr1[i];
//         Arrays.sort(arr1,count,arr1.length);
//         int nindex=0;
//         for(int i=0;i<arr2.length;i++){
//             int times=0;
//             while(times < map.get(arr2[i])){
//                 arr1[nindex++]=arr2[i];
//                 times++;
//             }
//         }
//         return arr1;
//     }
// }


//桶排序
//可以使用这种方法的原因是arr[i]的取值范围不大
//0,100;  39.8,10
class Solution{
    public int[] relativeSortArray(int[] arr1,int[] arr2){
        int[] arr=new int[1001];
        for(int num:arr1) arr[num]++;
        int[] res=new int[arr1.length];
        int index=0;
        for(int num:arr2) while(arr[num]-->0) res[index++]=num;
        for(int i=0;i<1001;i++) while(arr[i]-->0) res[index++]=i;
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
