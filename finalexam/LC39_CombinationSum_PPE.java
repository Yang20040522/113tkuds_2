// 檔名：LC39_CombinationSum_PPE.java
// 題意（Combination Sum I）：在可重複使用每個價格的情況下，找出所有數值和為 target 的升序組合（組內遞增）。
// 重點：輸入可能含重複價格；為避免重複解，先排序並做唯一化處理再回溯。
// 複雜度：回溯本質指數；排序 O(n log n)。

import java.io.*;
import java.util.*;

public class LC39_CombinationSum_PPE {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int target = fs.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        Arrays.sort(a);
        // 唯一化，因為 I 版允許重複使用數字；輸入若有重複價格，會導致重複組合，先壓成唯一集合
        int[] candidates = unique(a);

        StringBuilder out = new StringBuilder();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, target, path, out);

        System.out.print(out.toString());
    }

    private static void dfs(int[] c, int start, int remain, Deque<Integer> path, StringBuilder out) {
        if (remain == 0) {
            // 輸出一行
            int i = 0;
            for (int v : path) {
                if (i++ > 0) out.append(' ');
                out.append(v);
            }
            out.append('\n');
            return;
        }
        for (int i = start; i < c.length; i++) {
            int v = c[i];
            if (v > remain) break; // 剪枝（已排序）
            path.addLast(v);
            // I 版可重複使用同一數字 → 下一層仍從 i 開始
            dfs(c, i, remain - v, path, out);
            path.removeLast();
        }
    }

    private static int[] unique(int[] a) {
        if (a.length == 0) return new int[0];
        int w = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[w - 1]) a[w++] = a[i];
        }
        return Arrays.copyOf(a, w);
    }

    // 簡易高速輸入
    private static class FastScanner {
        private final InputStream in; private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0; FastScanner(InputStream is){in=is;}
        private int read() throws IOException {
            if (ptr >= len) { len = in.read(buffer); ptr = 0; if (len <= 0) return -1; }
            return buffer[ptr++];
        }
        String next() throws IOException {
            StringBuilder sb = new StringBuilder(); int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do { sb.append((char)c); c = read(); } while (c != -1 && c > ' ');
            return sb.toString();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}
