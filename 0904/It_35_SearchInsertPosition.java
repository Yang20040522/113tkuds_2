// 題目：Search Insert Position
// 功能：若 target 存在於陣列，回傳索引；否則回傳應插入位置。
// 條件：陣列已升序排序，元素互不重複。

import java.util.Arrays;

public class It_35_SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l; // l 會停在應插入的位置
    }

    // 測試
    public static void main(String[] args) {
        It_35_SearchInsertPosition solver = new It_35_SearchInsertPosition();

        int[] a1 = {1,3,5,6};
        System.out.println(Arrays.toString(a1) + ", target=5 -> " + solver.searchInsert(a1, 5)); // 2

        int[] a2 = {1,3,5,6};
        System.out.println(Arrays.toString(a2) + ", target=2 -> " + solver.searchInsert(a2, 2)); // 1

        int[] a3 = {1,3,5,6};
        System.out.println(Arrays.toString(a3) + ", target=7 -> " + solver.searchInsert(a3, 7)); // 4

        int[] a4 = {1,3,5,6};
        System.out.println(Arrays.toString(a4) + ", target=0 -> " + solver.searchInsert(a4, 0)); // 0
    }
}

/*
解題思路：
1. 二分搜尋找 target。
2. 若找到，直接回傳索引。
3. 若沒找到，最終的 l 指標會停在「第一個大於等於 target」的位置，就是插入點。
4. 時間複雜度 O(log n)，空間 O(1)。
*/
