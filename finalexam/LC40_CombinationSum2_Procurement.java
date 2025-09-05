// 檔名：LC40_CombinationSum2_Procurement.java
// 題意（Combination Sum II）：每個價格最多使用一次，列出所有和為 target 的升序組合；需去除重複組合。
// 解法：排序後回溯；在同一層（同一 start）對相同值只取一次（i>start 且 a[i]==a[i-1] 跳過）。
// 複雜度：回溯本質指數；排序 O(n log n)。

import java.io.*;
import java.util.*;

public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int target = fs.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        Arrays.sort(a);

        StringBuilder out = new StringBuilder();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(a, 0, target, path, out);

        System.out.print(out.toString());
    }

    private static void dfs(int[] a, int start, int remain, Deque<Integer> path, StringBuilder out) {
        if (remain == 0) {
            int i = 0;
            for (int v : path) {
                if (i++ > 0) out.append(' ');
                out.append(v);
            }
            out.append('\n');
            return;
        }
        for (int i = start; i < a.length; i++) {
            // 同層去重：避免相同數值導致的重複組合
            if (i > start && a[i] == a[i - 1]) continue;
            int v = a[i];
            if (v > remain) break; // 剪枝
            path.addLast(v);
            // II 版每個數只能用一次 → 下一層從 i+1
            dfs(a, i + 1, remain - v, path, out);
            path.removeLast();
        }
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
