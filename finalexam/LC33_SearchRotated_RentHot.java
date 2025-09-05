// 檔名：LC33_SearchRotated_RentHot.java
// 題意：在「被旋轉的升序陣列」中尋找 target 的索引，找不到回 -1。
// 解法：改良二分。每次判斷左半或右半是否有序，target 若落在有序半部的範圍內就收縮到該半部，否則到另一半。
// 複雜度：時間 O(log n)，空間 O(1)。

import java.io.*;

public class LC33_SearchRotated_RentHot {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int target = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        System.out.println(search(a, target));
    }

    private static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (nums[mid] == target) return mid;

            // 判斷哪一半有序
            if (nums[l] <= nums[mid]) {
                // 左半有序：檢查 target 是否在 [l, mid) 之間
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 右半有序：檢查 target 是否在 (mid, r] 之間
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    // 簡易高速輸入
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char)c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}

/*
【邊界案例】
- 未旋轉（純升序）：退化為一般二分。
- 單一元素：直接與 mid 比較後返回。
- target 在分界附近：藉由「有序半部判斷」仍能正確縮小範圍。
- target 不存在：最終 l > r，回 -1。

【核心判斷】
- 若 nums[l] <= nums[mid]，則左半有序；
  - 若 nums[l] <= target < nums[mid]，搜左半，否則搜右半。
- 否則右半有序；
  - 若 nums[mid] < target <= nums[r]，搜右半，否則搜左半。
*/
