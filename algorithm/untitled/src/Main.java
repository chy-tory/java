import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntPredicate;






//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
class Soluton_maximumSum{
    public int maxSubArray(int[] nums){
        int pre = 0,maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre+x,x);
            maxAns = Math.max(maxAns,pre);
        }
        return maxAns;
    }
}

// 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
//
//你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//
//你可以按任意顺序返回答案。
class Solution_one {
    public int[] twoSum(int[] nums,int target){
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}

//给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
//
//请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
class Solution_two{
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}
  

//给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
class Solution_three {
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1; //getOrDefault() 方法获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值。
            map.put(num, count);  //put() 方法将指定的键/值对插入到 HashMap 中。
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        int[] num1 = {1,2,3,4,1,1,1};
        int[] num2 = {2,3,4,5,6,1,1,1};
        int[] intersect = Solution_three.intersect(num1,num2);
        for (int num : intersect){
            System.out.println(num);
        }
    }
}