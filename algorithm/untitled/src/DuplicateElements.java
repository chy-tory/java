import java.lang.reflect.Array;
import java.util.Arrays;

/* 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
*/
public class DuplicateElements {
    static final int[] sort = null;
    static int[] nums = new int[]{1,2,4,2,1};

    public static boolean main(String[] args) {
        Arrays.sort(nums);
//        sort(nums);
        for (int i=0;i<nums.length-1; i++) {
            if (nums[i] == nums [i+1]) {
                return false;
            }
        }
        return false;
    }

//    public static int[] sort(int[] nums) {
//
//
//        return sort;
//    }
}
