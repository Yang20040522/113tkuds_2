// 題目：Next Permutation
// 將陣列原地調整為字典序下一個排列；若無則轉為最小（升序）。

import java.util.Arrays;

public class It_31_NextPermutation {

    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // 1) 找到第一個上升轉折 i：nums[i] < nums[i+1]
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            // 2) 從右往左找第一個 > nums[i] 的 j，交換
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }

        // 3) 反轉尾巴，使其為最小升序
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] a, int x, int y) {
        int t = a[x]; a[x] = a[y]; a[y] = t;
    }

    private void reverse(int[] a, int l, int r) {
        while (l < r) swap(a, l++, r--);
    }

    // 簡單測試
    public static void main(String[] args) {
        It_31_NextPermutation s = new It_31_NextPermutation();

        int[] a1 = {1,2,3};
        s.nextPermutation(a1);
        System.out.println(Arrays.toString(a1)); // [1,3,2]

        int[] a2 = {3,2,1};
        s.nextPermutation(a2);
        System.out.println(Arrays.toString(a2)); // [1,2,3]

        int[] a3 = {1,1,5};
        s.nextPermutation(a3);
        System.out.println(Arrays.toString(a3)); // [1,5,1]

        int[] a4 = {1,3,2};
        s.nextPermutation(a4);
        System.out.println(Arrays.toString(a4)); // [2,1,3]
    }
}

/*
解題思路：
1. 從右往左找第一個 index i 滿足 nums[i] < nums[i+1]；若找不到，代表當前為最大排列，
   直接把整個陣列反轉成最小排列即可返回。
2. 若找到 i，從右往左找第一個 > nums[i] 的 j，交換 nums[i] 與 nums[j]，使高位「最小幅度」變大。
3. 把區間 [i+1..n-1] 反轉，由於這段原本是降序，反轉後成為升序，取得最小尾巴，整體即為下一個排列。
複雜度：單趟掃描與一次反轉，時間 O(n)，空間 O(1)。
*/
