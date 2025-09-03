// 題目：4Sum
// 回傳所有不重複的四元組，使其和為 target（可任意順序）。

import java.util.*;

public class It_18_FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        if (n < 4) return ans;

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重 i

            long min1 = (long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min1 > target) break; // 更後面只會更大
            long max1 = (long) nums[i] + nums[n-1] + nums[n-2] + nums[n-3];
            if (max1 < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重 j

                long min2 = (long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (min2 > target) break;
                long max2 = (long) nums[i] + nums[j] + nums[n-1] + nums[n-2];
                if (max2 < target) continue;

                int l = j + 1, r = n - 1;
                while (l < r) {
                    long sum = (long) nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++; // 去重 l
                        while (l < r && nums[r] == nums[r - 1]) r--; // 去重 r
                        l++; r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return ans;
    }

    // 本地測試
    public static void main(String[] args) {
        It_18_FourSum solver = new It_18_FourSum();

        int[] a1 = {1,0,-1,0,-2,2};
        System.out.println("Input: " + Arrays.toString(a1) + ", target=0");
        System.out.println("Output: " + solver.fourSum(a1, 0));
        // 期望：[[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]

        int[] a2 = {2,2,2,2,2};
        System.out.println("Input: " + Arrays.toString(a2) + ", target=8");
        System.out.println("Output: " + solver.fourSum(a2, 8));
        // 期望：[[2,2,2,2]]
    }
}

/*
解題思路（摘要）：
- 排序後，枚舉 i、j，內層以雙指針 l、r 搜索；遇到相等值進行去重。
- 以當前最小/最大可達和與 target 比較作剪枝，提升效率。
- 全程以 long 計算三/四數和，避免溢位。
*/
