// 檔名：LC34_SearchRange_DelaySpan.java
// 題意：在已排序（非遞減）的整數陣列中，找出 target 的首末索引；若不存在輸出 -1 -1。
// 解法：兩次二分（lower_bound）。左界 = lower_bound(target)；右界 = lower_bound(target+1) - 1。
// 複雜度：O(log n) 時間，O(1) 空間。

import java.io.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        Integer nObj = fs.nextIntOrNull();
        if (nObj == null) { // 無輸入
            System.out.println("-1 -1");
            return;
        }
        int n = nObj;
        int target = fs.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        int left = lowerBound(a, target);
        if (left == n || a[left] != target) {
            System.out.println("-1 -1");
            return;
        }
        int right = lowerBound(a, target + 1) - 1;
        System.out.println(left + " " + right);
    }

    // 回傳最小索引 idx 使得 a[idx] >= x；若皆小於 x，回傳 n
    private static int lowerBound(int[] a, int x) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = l + ((r - l) >>> 1);
            if (a[m] < x) l = m + 1;
            else r = m;
        }
        return l;
    }

    // 高速輸入（以 token 為單位）
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
            while ((c = read()) != -1 && c <= ' ') {} // skip spaces/newlines
            if (c == -1) return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        Integer nextIntOrNull() throws IOException {
            String t = next();
            return (t == null) ? null : Integer.parseInt(t);
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}

/*
【說明】
- 以 lower_bound 兩次即可鎖定 [first, last]：
  - first = lower_bound(target)
  - last  = lower_bound(target+1) - 1
- 若 first 超出或 a[first] != target，表示不存在。

【邊界】
- n=0 → 輸出 -1 -1
- target 僅出現一次或全為 target → 正確回傳
- target 不存在但位於值域間 → 仍輸出 -1 -1
*/
