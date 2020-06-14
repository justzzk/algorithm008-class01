import java.util.Random;

//三路快排,有大量重复元素

/**
 * |left|...<...less|..=..|index.....|more....|
 */
public class threeways{

    public void quicksort(int[] nums){
        __quicksort(nums,0,nums.length-1);
    }

    private void __quicksort(int[] nums, int left, int right) {
        if(left >= right){
            return;
        }

        //因为需要返回less和more两个值所以就不写partition
        int less=left;
        int more=right+1;
        int index=left+1;
        while(index<more){
            //=,index++
            //<,swap(++less,index),index++
            //>,swap(--more,index)
            if(nums[index]>nums[left]){
                swap(nums,--more,index);
            }else if(nums[index] < nums[left]){
                swap(nums,++less,index++);
            }else{
                index++;
            }
        }
        swap(nums,left,less);

        __quicksort(nums,left,less-1);
        __quicksort(nums,more,right);
    }


    private void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }

    private static boolean isSorted(int[] nums){
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] > nums[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        int[] nums={9,8,7,6,5,4,3,2,1,0};
        int[] nums=new int[10000];
        for (int i = 0; i < nums.length; i++) {
            nums[i]=new Random().nextInt(100);
        }
        new threeways().quicksort(nums);
        System.out.println(isSorted(nums));
    }
}