// 題目：3Sum
// 找出所有不重複的三元組，使得三數和為 0。

import java.util.*;

public class It_15_ThreeSum {

    // 核心：排序 + 雙指針 + 去重
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重起點

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 跳過重複的左右值
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++; right--;
                } else if (sum < 0) {
                    left++;    // 需要更大 → 左指針右移
                } else {
                    right--;   // 需要更小 → 右指針左移
                }
            }
        }
        return res;
    }

    // 主程式測試
    public static void main(String[] args) {
        It_15_ThreeSum solver = new It_15_ThreeSum();

        int[] nums1 = {-1,0,1,2,-1,-4};
        int[] nums2 = {0,1,1};
        int[] nums3 = {0,0,0};

        System.out.println("Input: [-1,0,1,2,-1,-4]");
        System.out.println("Output: " + solver.threeSum(nums1)); // [[-1,-1,2], [-1,0,1]]

        System.out.println("Input: [0,1,1]");
        System.out.println("Output: " + solver.threeSum(nums2)); // []

        System.out.println("Input: [0,0,0]");
        System.out.println("Output: " + solver.threeSum(nums3)); // [[0,0,0]]
    }
}

/*
解題思路（同上）：
- 排序後固定 i，左右指針逼近；和=0 收錄並跳重，和<0 左++，和>0 右--。
- 去重關鍵：i 去重、left/right 去重。
時間：O(n^2)；空間：O(1)（不含輸出）。
*/
