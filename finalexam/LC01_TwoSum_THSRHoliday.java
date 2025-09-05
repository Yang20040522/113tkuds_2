// 檔名：LC01_TwoSum_THSRHoliday.java
// 題意：給定 n 個班次可釋出座位數與 target，找兩個不同索引 i, j 使 seats[i] + seats[j] == target；否則輸出 -1 -1。
// 解法：一次遍歷 + HashMap（key=等待被配對的值、value=其索引）。
// 複雜度：時間 O(n)，空間 O(n)

import java.io.*;
import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        long target = fs.nextLong();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        // map: 需要的另一半數值 -> 目前索引
        // 當前看到 x，如果 map 內存在 x，表示先前有人「需要 x」，配對成功
        Map<Long, Integer> need = new HashMap<>(Math.max(16, n * 2));

        for (int i = 0; i < n; i++) {
            long x = a[i];

            // 檢查是否有人在等 x
            Integer j = need.get(x);
            if (j != null) {
                // 找到一組解，輸出 j i（或 i j 皆可，題目不要求順序）
                System.out.println(j + " " + i);
                return;
            }

            // 否則登記「之後需要 target - x」來配對當前索引 i
            long want = target - x;
            // 若同一數值多次出現，保留最早或最晚皆可；題目允許任一解
            need.put(want, i);
        }

        // 走完仍未找到
        System.out.println("-1 -1");
    }

    // 讀取器：支援大量輸入
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
            // token
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
【解題說明】
- 走訪陣列每個 x：
  - 若 map 內已存在 key=x，表示先前某索引 j 需要 x 才能湊 target，立即輸出 j 與 i。
  - 否則把 (target - x) 登記為「待配對」並記住當前索引 i。
- 允許多組解，任一組先被掃到就輸出即可。
- 支援相同數值的情境（例如 target 偶數且陣列中恰有兩個 target/2）。

【複雜度】
- 時間 O(n)：每個元素被查/寫 HashMap 一次。
- 空間 O(n)：最壞在未配對前，map 儲存至多 n-1 個需求。
*/
