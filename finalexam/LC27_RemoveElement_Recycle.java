// 檔名：LC27_RemoveElement_Recycle.java
// 題意：就地移除陣列中所有等於 val 的元素，保持其餘元素原順序，回傳新長度與前段內容。
// 解法：單指針覆寫。遍歷陣列，若 nums[i] != val 就寫到 nums[write]，最後 write 即新長度。
// 複雜度：時間 O(n)，空間 O(1)。

import java.io.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int val = fs.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = fs.nextInt();

        int len = removeElement(nums, n, val);

        // 輸出新長度
        System.out.println(len);

        // 輸出前段結果
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i > 0) out.append(' ');
            out.append(nums[i]);
        }
        System.out.println(out.toString());
    }

    private static int removeElement(int[] nums, int n, int val) {
        int write = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                nums[write++] = nums[i];
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
【邏輯】
- 用 write 作為新陣列尾。
- 每當 nums[i] != val，覆寫 nums[write] = nums[i]，並遞增 write。
- 完成後 write 即為新長度。

【邊界案例】
- n=0 → 輸出長度 0，第二行空。
- 全部元素等於 val → 長度 0。
- 沒有任何元素等於 val → 長度不變，序列不變。
- val 在開頭、結尾或連續段 → 皆正確處理。

【範例】
輸入:
4 2
3 2 2 3
輸出:
2
3 3
*/
