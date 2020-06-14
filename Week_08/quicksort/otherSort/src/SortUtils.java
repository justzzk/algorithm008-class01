import java.util.Random;

/**
 * @author justzzk
 * 一些通用函数，如swap，生成nums，isSorted
 */
public class SortUtils {
    public static boolean isSorted(int[] nums){
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] > nums[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }

    public static int[] geneNums(int size,int bound){
        int[] nums=new int[size];
        for (int i = 0; i < nums.length; i++) {
            nums[i]=new Random().nextInt(bound);
        }
        return nums;
    }

}
