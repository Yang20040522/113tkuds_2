// 檔名：LC15_3Sum_THSRStops.java
// 題意：給定整數陣列，找出所有「和為 0」且不重複的三元組（遞增排列後輸出，每行一組）。
// 解法：排序 + 固定 i，於區間 [i+1, n-1] 用雙指針尋找 target = -nums[i]；找到解後同步略過重複元素。
// 複雜度：時間 O(n^2)，空間 O(1)（不計輸出）。

import java.io.*;
import java.util.*;

public class LC15_3Sum_THSRStops {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        Arrays.sort(a);

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            // 若 a[i] > 0，後面皆 >= a[i]，不可能再湊到 0
            if (a[i] > 0) break;

            // 跳過相同的起點值，避免重複三元組
            if (i > 0 && a[i] == a[i - 1]) continue;

            int L = i + 1, R = n - 1;
            int target = -a[i];

            while (L < R) {
                int sum = a[L] + a[R];
                if (sum == target) {
                    // a[i] <= a[L] <= a[R]（因已排序），天然遞增
                    out.append(a[i]).append(' ')
                       .append(a[L]).append(' ')
                       .append(a[R]).append('\n');

                    // 略過重複的 L、R
                    int vL = a[L], vR = a[R];
                    while (L < R && a[L] == vL) L++;
                    while (L < R && a[R] == vR) R--;
                } else if (sum < target) {
                    L++;
                } else {
                    R--;
                }
            }
        }

        // 無解時不輸出任何行（題目規格）
        System.out.print(out.toString());
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
                sb.append((char) c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}

/*
【正確性重點】
- 排序後固定 a[i]，剩餘用雙指針找兩數和為 -a[i]。
- 為避免重複：
  1) i 位置若與前一個相同則 continue。
  2) 取得一組解後，L 與 R 都要跳過各自的連續重複值。
- 若 a[i] > 0 可提早結束，因為後續皆非負，無法再湊 0。

【邊界案例】
- 全 0：只會輸出「0 0 0」一次。
- 無解：輸出空（無任何行）。
- 多重重複（如 [-2,-2,0,0,2,2]）：去重策略可保證唯一解集。
*/
