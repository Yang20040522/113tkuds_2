// 檔名：LC18_4Sum_Procurement.java
// 題意：給 n 個整數與 target，輸出所有不重複且遞增的四元組，使其總和為 target（每行一組）。
// 解法：排序後雙層枚舉 i、j，內層以雙指針 L、R 搜索；i、j、L、R 處處去重。
// 複雜度：時間 O(n^3)，空間 O(1)（不計輸出）。用 long 避免加總溢位。

import java.io.*;
import java.util.*;

public class LC18_4Sum_Procurement {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        long target = fs.nextLong();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        Arrays.sort(a);

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            // i 去重
            if (i > 0 && a[i] == a[i - 1]) continue;

            for (int j = i + 1; j < n; j++) {
                // j 去重（相對於同一個 i）
                if (j > i + 1 && a[j] == a[j - 1]) continue;

                int L = j + 1, R = n - 1;

                while (L < R) {
                    long sum = (long)a[i] + a[j] + a[L] + a[R];

                    if (sum == target) {
                        out.append(a[i]).append(' ')
                           .append(a[j]).append(' ')
                           .append(a[L]).append(' ')
                           .append(a[R]).append('\n');

                        int vL = a[L], vR = a[R];
                        // 內層去重：跳過與當前值相同的 L、R
                        while (L < R && a[L] == vL) L++;
                        while (L < R && a[R] == vR) R--;
                    } else if (sum < target) {
                        L++;
                    } else { // sum > target
                        R--;
                    }
                }
            }
        }

        // 無解時不輸出任何行（符合題意）
        System.out.print(out.toString());
    }

     //簡易高速輸入
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
            // 跳過空白
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            // 讀取 token
            do {
                sb.append((char)c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }
}

/*
【正確性與去重】
- 排序後固定 i、j；以 L、R 雙指針尋找兩數和 = target - a[i] - a[j]。
- 去重策略：
  1) i > 0 且 a[i]==a[i-1] → continue；
  2) j > i+1 且 a[j]==a[j-1] → continue；
  3) 命中一組後，L、R 各自跳過連續重複值。
- 以上確保每個數值集合僅輸出一次，且天生遞增。

【邊界案例】
- n=4：只會檢查那一組；若和等於 target 就輸出一行。
- 全為 0 且 target=0：只輸出「0 0 0 0」一次。
- 無解：輸出空。
- 數值範圍至 1e9：加總用 long 防溢位。
*/
