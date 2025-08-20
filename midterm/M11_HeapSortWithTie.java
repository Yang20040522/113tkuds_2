// 檔名：M11_HeapSortWithTie.java

/*
 * Time Complexity: O(n log n)
 * 說明：先以自底向上建 Max-Heap O(n)，之後進行 n-1 次「取頂/下沉」，
 *      每次下沉 O(log n)，整體為 O(n log n)。
 *
 * Space Complexity: O(1)
 * 說明：就地（in-place）堆排序，僅使用常數額外空間（不計輸入儲存）。
 *
 * 排序規則（遞增輸出）：
 *  1) 先比 score（小的在前）
 *  2) score 相同時，比 index（小的在前；即先輸入者優先）
 *
 * 作法：用「Max-Heap」配合比較子：
 *   a 比 b 大 ⇔ (a.score > b.score) 或 (score 相等且 a.index > b.index)
 *   這樣每次把「最大」丟到尾端，最後整體即為 (score 升冪, index 升冪)。
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class M11_HeapSortWithTie {

    static class Pair {
        int score;
        int idx;     // 輸入順序（0-based）
        Pair(int s, int i) { score = s; idx = i; }
    }

    // -------- I/O --------
    static BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in, StandardCharsets.UTF_8));
    static StringTokenizer st;
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) return null;
            line = line.trim();
            if (line.isEmpty()) continue;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }
    static Integer nextInt() throws IOException {
        String s = next();
        return s == null ? null : Integer.parseInt(s);
    }

    public static void main(String[] args) throws Exception {
        Integer nObj = nextInt();
        int n = (nObj == null ? 0 : nObj);

        Pair[] a = new Pair[n];
        for (int i = 0; i < n; i++) {
            int s = nextInt();
            a[i] = new Pair(s, i);
        }

        heapSort(a); // 依規則排序（結果為遞增）

        // 輸出：僅分數，遞增
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i].score);
        }
        System.out.println(sb.toString());
    }

    // -------- 堆排序（Max-Heap） --------
    static void heapSort(Pair[] a) {
        int n = a.length;
        // 建堆：自底向上 O(n)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(a, i, n);
        }
        // 取出最大放到尾端，再縮小堆
        for (int end = n - 1; end > 0; end--) {
            swap(a, 0, end);
            heapifyDown(a, 0, end);
        }
        // 完成後為遞增 (score, idx)
    }

    // 將 i 往下調整至正確位置（範圍 [0, n)）
    static void heapifyDown(Pair[] a, int i, int n) {
        while (true) {
            int left = i * 2 + 1;
            if (left >= n) break;
            int right = left + 1;
            int best = left;
            if (right < n && greater(a[right], a[left])) best = right;
            if (greater(a[best], a[i])) {
                swap(a, i, best);
                i = best;
            } else break;
        }
    }

    // Max-Heap 的「更大」判定
    // a 比 b 大 ⇔ (score 大) 或 (score 相等且 index 大)
    static boolean greater(Pair a, Pair b) {
        if (a.score != b.score) return a.score > b.score;
        return a.idx > b.idx;
    }

    static void swap(Pair[] a, int i, int j) {
        Pair t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
