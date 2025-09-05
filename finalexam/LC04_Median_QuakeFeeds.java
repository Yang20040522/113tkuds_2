// 檔名：LC04_Median_QuakeFeeds.java
// 題意：給兩個已排序數列（可長度不同、可含重複、可有空列），在「不真正合併」前提下求聯合集的中位數。
// 解法：在較短的陣列上做二分切割，使左半元素數量 = (n+m+1)/2，並滿足 Aleft <= Bright 且 Bleft <= Aright。
// 複雜度：時間 O(log(min(n,m)))，空間 O(1)

import java.io.*;
import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int m = fs.nextInt();

        double[] A = new double[n];
        for (int i = 0; i < n; i++) A[i] = fs.nextDouble();

        double[] B = new double[m];
        for (int i = 0; i < m; i++) B[i] = fs.nextDouble();

        // 確保 A 是較短的陣列，降低二分範圍
        if (A.length > B.length) {
            double[] t = A; A = B; B = t;
            int tmp = n; n = m; m = tmp;
        }

        // 特判：若短陣列為空，直接從長陣列取中位數
        if (n == 0) {
            double median = medianSingleSorted(B);
            System.out.printf(Locale.US, "%.1f%n", median);
            return;
        }

        int total = n + m;
        int half = (total + 1) / 2; // 左半元素的總數（偏向左）

        int lo = 0, hi = n; // 在 A 上切的位置 i：左邊取 A[0..i-1]，右邊取 A[i..n-1]
        final double INF = 1e18;

        while (lo <= hi) {
            int i = (lo + hi) >>> 1;   // A 切 i
            int j = half - i;          // B 切 j，使左半總數為 half

            double Aleft  = (i == 0) ? -INF : A[i - 1];
            double Aright = (i == n) ?  INF : A[i];

            double Bleft  = (j == 0) ? -INF : B[j - 1];
            double Bright = (j == m) ?  INF : B[j];

            // 目標條件：Aleft <= Bright 且 Bleft <= Aright
            if (Aleft <= Bright && Bleft <= Aright) {
                double median;
                if ((total & 1) == 1) {
                    // 總數為奇數，取左半最大
                    median = Math.max(Aleft, Bleft);
                } else {
                    // 總數為偶數，取左半最大與右半最小的平均
                    double leftMax  = Math.max(Aleft, Bleft);
                    double rightMin = Math.min(Aright, Bright);
                    median = (leftMax + rightMin) / 2.0;
                }
                System.out.printf(Locale.US, "%.1f%n", median);
                return;
            } else if (Aleft > Bright) {
                // A 的左邊太大 → i 往左縮
                hi = i - 1;
            } else {
                // Bleft > Aright → i 往右擴
                lo = i + 1;
            }
        }

        // 理論上不會到這裡（因為一定能找到切割）
        System.out.println("0.0");
    }

    // 單一已排序數列的中位數（支援長度 >= 1）
    private static double medianSingleSorted(double[] X) {
        int n = X.length;
        if ((n & 1) == 1) return X[n / 2];
        return (X[n / 2 - 1] + X[n / 2]) / 2.0;
    }

    // 高效輸入（支援大量資料）
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
            // skip spaces
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char)c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
        double nextDouble() throws IOException { return Double.parseDouble(next()); }
    }
}

/*
【解題說明】
- 僅在較短陣列 A 上做二分，決定切點 i；另一陣列 B 的切點 j = half - i。
- 驗證條件 Aleft <= Bright 且 Bleft <= Aright：
  成立 → 依奇偶數量計算中位數；
  不成立 → 若 Aleft > Bright，i 左移；若 Bleft > Aright，i 右移。
- 以 ±INF 處理 i=0、i=n、j=0、j=m 的邊界。

【邊界案例】
- 其中一列為空：直接對非空列取中位數。
- 兩列各 1 個元素：依奇偶與大小計算。
- 全部相同、長度極端不等：皆可處理。

【輸出格式】
- 使用 Locale.US 並保留 1 位小數（題目示例為 1 位）。

【複雜度】
- 時間 O(log(min(n,m)))，空間 O(1)。
*/
