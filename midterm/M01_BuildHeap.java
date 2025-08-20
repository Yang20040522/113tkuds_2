
/*
 * Time Complexity: O(n)
 * 說明：採用 bottom-up 建堆；從最後一個非葉節點往上做 heapifyDown。
 *      深度愈深節點數愈多但每個節點下沉高度愈小，
 *      總成本 Σ (n/2^{h+1}) * O(h) = O(n)。
 *
 * Space Complexity: O(1)
 * 說明：使用原地建堆，僅需常數額外變數。
 */

import java.io.*;

public class M01_BuildHeap {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        String type = fs.next();          // "max" 或 "min"
        int n = Integer.parseInt(fs.next());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        boolean isMax = "max".equalsIgnoreCase(type);

        buildHeap(a, isMax); // 自底向上

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    // 自底向上建堆：從最後一個非葉節點開始做 heapifyDown
    static void buildHeap(int[] a, boolean isMax) {
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            heapifyDown(a, i, a.length, isMax);
        }
    }

    // 將索引 i 的元素往下調整到正確位置
    static void heapifyDown(int[] a, int i, int n, boolean isMax) {
        while (true) {
            int left = i * 2 + 1;
            if (left >= n) break; // 沒有子節點
            int right = left + 1;

            int best = left;
            if (right < n && better(a[right], a[left], isMax)) best = right;

            if (better(a[best], a[i], isMax)) {
                swap(a, i, best);
                i = best;
            } else {
                break;
            }
        }
    }

    // 比較器：回傳 x 是否比 y 更符合堆頂
    static boolean better(int x, int y, boolean isMax) {
        return isMax ? (x > y) : (x < y);
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    // 簡易高速輸入
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }

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

        int nextInt() throws IOException {
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            int sgn = 1;
            if (c == '-') { sgn = -1; c = read(); }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sgn;
        }
    }
}
