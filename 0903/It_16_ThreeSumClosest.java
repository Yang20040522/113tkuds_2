// 題目：3Sum Closest
// 找出三個數的總和，使其最接近 target，回傳該總和。

import java.util.*;

public class It_16_ThreeSumClosest {

    // 核心解法：排序 + 雙指針，維護目前最接近的三數和
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        int closest = nums[0] + nums[1] + nums[2]; // 以前三個初始化

        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, r = n - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum; // 更新更接近者
                }

                if (sum == target) {
                    return sum;   // 最理想的情況
                } else if (sum < target) {
                    l++;          // 需要更大 → 左指針右移
                } else {
                    r--;          // 需要更小 → 右指針左移
                }
            }
        }
        return closest;
    }

    // 主程式：本地測試
    public static void main(String[] args) {
        It_16_ThreeSumClosest solver = new It_16_ThreeSumClosest();

        int[] a1 = {-1, 2, 1, -4};
        int t1 = 1;
        System.out.println(Arrays.toString(a1) + ", target=" + t1 +
                " -> " + solver.threeSumClosest(a1, t1)); // 2

        int[] a2 = {0, 0, 0};
        int t2 = 1;
        System.out.println(Arrays.toString(a2) + ", target=" + t2 +
                " -> " + solver.threeSumClosest(a2, t2)); // 0

        int[] a3 = {1, 1, 1, 0};
        int t3 = -100;
        System.out.println(Arrays.toString(a3) + ", target=" + t3 +
                " -> " + solver.threeSumClosest(a3, t3)); // 2 (0+1+1)
    }
}

/*
解題思路：
- 排序後固定 i，利用左右指針計算三數和 sum，持續更新最接近 target 的 closest。
- 指針移動規則：sum < target → l++；sum > target → r--；sum == target → 直接回傳。
正確性關鍵：排序 + 雙指針使得調整方向單調，能在 O(n^2) 內完成搜尋。
時間複雜度：O(n^2)，空間複雜度：O(1)。
*/
