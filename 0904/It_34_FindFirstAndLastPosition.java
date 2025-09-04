// 題目：Find First and Last Position of Element in Sorted Array
// 用二分搜尋找 target 的最左與最右邊界

import java.util.Arrays;

public class It_34_FindFirstAndLastPosition {

    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int l = 0, r = nums.length - 1, res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                res = mid;
                if (isFirst) {
                    r = mid - 1;   // 繼續往左
                } else {
                    l = mid + 1;   // 繼續往右
                }
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    // 測試
    public static void main(String[] args) {
        It_34_FindFirstAndLastPosition solver = new It_34_FindFirstAndLastPosition();

        int[] a1 = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(a1) + " target=8 -> " + Arrays.toString(solver.searchRange(a1, 8))); // [3,4]

        int[] a2 = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(a2) + " target=6 -> " + Arrays.toString(solver.searchRange(a2, 6))); // [-1,-1]

        int[] a3 = {};
        System.out.println(Arrays.toString(a3) + " target=0 -> " + Arrays.toString(solver.searchRange(a3, 0))); // [-1,-1]

        int[] a4 = {1};
        System.out.println(Arrays.toString(a4) + " target=1 -> " + Arrays.toString(solver.searchRange(a4, 1))); // [0,0]
    }
}

/*
解題思路：
1. 使用二分搜尋兩次：
   - 一次找最左邊界（遇到 target 還要往左）。
   - 一次找最右邊界（遇到 target 還要往右）。
2. 若沒找到，返回 -1。
3. 時間 O(log n)，空間 O(1)。
*/
