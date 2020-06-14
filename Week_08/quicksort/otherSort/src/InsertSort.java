/**
 * @author justzzk
 * 一般插入排序
 * 从头开始构建有序序列，每增加一个新的元素，插入到前面有序序列的合适位置
 */
public class InsertSort {
    public void insertSort(int[] nums){
        int length=nums.length;
        for(int i=1;i<length;i++){
            int nowIndex=i;
            for(int j=i-1;j>=0;j--){
                if(nums[j]>nums[nowIndex]){
                    SortUtils.swap(nums,j,nowIndex);
                    nowIndex=j;
                }else {
                    break;
                }
            }
        }
    }

    /**
     * 一点优化，找到合适的位置才交换
     * @param nums
     */
    public void insertSortOp(int[] nums){
        int length=nums.length;
        for(int i=1;i<length;i++){
            int value=nums[i];
            for(int j=i-1;j>=0;j--){
                if(nums[j]>value){
                    nums[j+1]=nums[j];
                }else {
                    nums[j+1]=value;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = SortUtils.geneNums(10000,10000);
        System.out.println(SortUtils.isSorted(nums));
//        new InsertSort().insertSort(nums);
        new InsertSort().insertSortOp(nums);
        System.out.println(SortUtils.isSorted(nums));
    }
}
