/**
 * @author justzzk
 * 归并排序简单实现
 */
public class MergeSort {
    public void mergeSort(int[] nums){
        mergeSort(nums,0,nums.length-1);
    }

    private void mergeSort(int[] nums, int left, int right) {
        if(left >= right) {
            return;
        }

        int mid=left+(right-left)/2;

        mergeSort(nums,left,mid);
        mergeSort(nums,mid+1,right);

        if(nums[mid]<=nums[mid+1]){
            return;
        }
        int i=left;
        int j=mid+1;
        int c=0;
        int[] cache=new int[right-left+1];
        while(i<=mid || j<=right){
            if(i>mid){
                cache[c++]=nums[j++];
            }else if(j>right){
                cache[c++]=nums[i++];
            }else{
                if(nums[i]<nums[j]){
                    cache[c++]=nums[i++];
                }else{
                    cache[c++]=nums[j++];
                }
            }
        }
        System.arraycopy(cache,0,nums,left,right-left+1);
    }

    public static void main(String[] args) {
        int[] nums = SortUtils.geneNums(10000,10000);
        System.out.println(SortUtils.isSorted(nums));
        new MergeSort().mergeSort(nums);
        System.out.println(SortUtils.isSorted(nums));
    }
}
