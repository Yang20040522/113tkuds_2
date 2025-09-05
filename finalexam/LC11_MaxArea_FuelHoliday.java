// 檔名：LC11_MaxArea_FuelHoliday.java
// 題意：給高度陣列 heights，選兩指標 i<j 最大化 (j-i) * min(heights[i], heights[j])（最大輸出帶寬）。
// 解法：雙指針夾逼。從最左 L 與最右 R 開始，每步以較短邊為瓶頸，只有移動較短邊才可能讓 min 提升、面積變大。
// 複雜度：時間 O(n)，空間 O(1)。注意用 long 以避免乘積溢位。

import java.io.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) h[i] = fs.nextInt();

        long ans = maxArea(h);
        System.out.println(ans);
    }

    private static long maxArea(int[] h) {
        int L = 0, R = h.length - 1;
        long best = 0;

        while (L < R) {
            int left = h[L], right = h[R];
            int minH = Math.min(left, right);
            long width = (long)(R - L);
            best = Math.max(best, width * (long)minH);

            // 只移動較短邊；若相等，移動任一邊皆可
            if (left < right) {
                L++;
            } else {
                R--;
            }
        }
        return best;
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
                sb.append((char)c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}

/*
【正確性直覺】
- 面積 = 寬 * 短邊。固定兩端時，要想變大只能提升短邊高度；
  因此每一步將較短那邊往內移，尋找更高的桿，寬度雖變小但可能用更高的短邊補回。
- 若移動較長邊，短邊不變，寬度縮小，面積不可能變更好。

【邊界案例】
- n=2 → 回傳那一對的面積。
- 全等高度 → 兩端距離最大者最佳。
- 單調遞增/遞減 → 仍由夾逼法線性求得。
- 高度含 0 → min 邊正確為 0；面積更新不受影響。
*/
