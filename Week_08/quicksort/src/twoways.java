import java.util.Random;

//二路快排
//仅修改partition

/**
 * |start|...<....less|index.......|more...>......|
 */
public class twoways {
    public void quicksort(int[] nums){
        __quicksort(nums,0,nums.length-1);
    }

    private void __quicksort(int[] nums, int left, int right) {
        if(left >= right){
            return;
        }

        int p=__partition(nums,left,right);
        __quicksort(nums,left,p-1);
        __quicksort(nums,p+1,right);
    }

    private int __partition(int[] nums, int left, int right) {
        //以nums[left]为比较标准
        int less=left+1;
        int more=right;
        while(true){
            while(less<= more && nums[less]<nums[left]) {
                less++;
            }
            while(less<=more && nums[more]>nums[left]){
                more--;
            }
            if(less>more){
                break;
            }
            swap(nums,less++,more--);
        }
        swap(nums,left,more);
        return more;
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
            nums[i]=new Random().nextInt(10000);
        }
        new twoways().quicksort(nums);
        System.out.println(isSorted(nums));
    }
}
