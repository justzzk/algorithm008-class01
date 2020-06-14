
/**
 * @author justzzk
 * 一般选择排序
 * 选择排序；每遍历一遍，找出剩余中的最小值，所以时间复杂度O(n^2)
 */
public class SelectSort {
    public void selectSort(int[] nums){
        int length=nums.length;
        for(int i=0;i<length-1;i++){
            for(int j=i+1;j<length;j++){
                if(nums[j]<nums[i]) {
                    SortUtils.swap(nums,i,j);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = SortUtils.geneNums(10000,10000);
        System.out.println(SortUtils.isSorted(nums));
        new SelectSort().selectSort(nums);
        System.out.println(SortUtils.isSorted(nums));
    }
}
