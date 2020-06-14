/**
 * @author justzzk
 * 一般冒泡排序
 * 每次迭代将最大值放到最后
 */
public class BubbleSort {
    public void bubbleSort(int[] nums){
        int length=nums.length;
        for(int i=length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(nums[j]>nums[j+1]){
                    SortUtils.swap(nums,j,j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = SortUtils.geneNums(10000,10000);
        System.out.println(SortUtils.isSorted(nums));
        new BubbleSort().bubbleSort(nums);
        System.out.println(SortUtils.isSorted(nums));
    }
}
