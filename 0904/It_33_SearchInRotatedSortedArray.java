// 題目：Search in Rotated Sorted Array
// 說明：在可能被左旋的升序陣列中，用 O(log n) 找 target 的索引。

import java.util.Arrays;

public class It_33_SearchInRotatedSortedArray {

    // 核心：改良二分搜尋（每回合判斷哪半段有序）
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) return mid;

            if (nums[l] <= nums[mid]) {
                // 左半段有序
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;                // 往左半找
                } else {
                    l = mid + 1;                // 往右半找
                }
            } else {
                // 右半段有序
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;                // 往右半找
                } else {
                    r = mid - 1;                // 往左半找
                }
            }
        }
        return -1;
    }

    // 簡單測試
    public static void main(String[] args) {
        It_33_SearchInRotatedSortedArray s = new It_33_SearchInRotatedSortedArray();

        int[] a1 = {4,5,6,7,0,1,2};
        System.out.println(Arrays.toString(a1) + ", target=0 -> " + s.search(a1, 0)); // 4

        int[] a2 = {4,5,6,7,0,1,2};
        System.out.println(Arrays.toString(a2) + ", target=3 -> " + s.search(a2, 3)); // -1

        int[] a3 = {1};
        System.out.println(Arrays.toString(a3) + ", target=0 -> " + s.search(a3, 0)); // -1

        int[] a4 = {6,7,8,1,2,3,4,5};
        System.out.println(Arrays.toString(a4) + ", target=3 -> " + s.search(a4, 3)); // 5
    }
}

/*
解題思路：
1. 旋轉後的陣列依然保證：對任意區間 [l..r]，至少有一半是單調有序。
2. 在二分迴圈中：
   - 若 nums[l] <= nums[mid]，左半有序；檢查 target 是否在 [nums[l], nums[mid])。
   - 否則右半有序；檢查 target 是否在 (nums[mid], nums[r]]。
   - 依結果縮小搜尋區間，維持 O(log n)。
3. 整程式不需要找 pivot（最小值位置），直接在同一個迴圈判斷即可。
時間複雜度：O(log n)，空間複雜度：O(1)。
*/
