// 檔名：LC26_RemoveDuplicates_Scores.java
// 題意：給一個已排序整數陣列，就地去除重複，使每個元素只保留一次，回傳新長度與壓縮後前段。
// 解法：雙指針（讀/寫分離）。write 指向下一個可寫位置，遇到新值才寫入。
// 複雜度：時間 O(n)，空間 O(1)。

import java.io.*;

public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        int len = removeDuplicates(a, n);

        // 輸出新長度
        System.out.println(len);

        // 輸出壓縮後前段內容
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i > 0) out.append(' ');
            out.append(a[i]);
        }
        System.out.println(out.toString());
    }

    private static int removeDuplicates(int[] a, int n) {
        if (n == 0) return 0;

        int write = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] != a[write - 1]) {
                a[write] = a[i];
                write++;
            }
        }
        return write;
    }

    // 高速輸入
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
【正確性】
- 陣列已排序，重複值必相鄰。
- write 指標初始 1，i 從 1 開始。若 a[i] != a[write-1]，代表遇到新值，寫到 a[write]，並推進 write。

【邊界案例】
- n=0 → 輸出長度 0，不輸出內容。
- 全部唯一 → 保持原樣。
- 全部相同 → 長度 1。
- 交替重複（1,1,2,2,3,3）→ 最終 [1,2,3]，長度 3。

【範例】
輸入:
7
0 0 1 1 1 2 2
輸出:
3
0 1 2
*/
