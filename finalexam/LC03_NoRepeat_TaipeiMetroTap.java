// 檔名：LC03_NoRepeat_TaipeiMetroTap.java
// 題意：給定字串 s，求最長「不含重複字元」的連續子字串長度。
// 解法：滑動視窗 + 最近出現位置表（lastIndex）。右指針 r 右移；若 s[r] 曾在目前視窗內出現，將左指針 left 推進到 lastIndex[s[r]]+1。
// 複雜度：時間 O(n)，空間 O(k)（k 為字元集大小，這裡用 char(0..65535)）。

import java.io.*;
import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = ""; // 空輸入視為空字串

        // 使用 char 全域大小 65536 的陣列記錄「最後一次出現的索引」，初始化為 -1
        int[] last = new int[1 << 16];
        Arrays.fill(last, -1);

        int left = 0;  // 視窗左界（含）
        int ans  = 0;  // 目前的最佳解

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);

            // 若字元 c 之前在視窗內出現（last[c] >= left），把 left 推到其後一格
            if (last[c] >= left) {
                left = last[c] + 1;
            }

            // 更新 c 的最後出現位置為 r
            last[c] = r;

            // 視窗長度 = r - left + 1
            ans = Math.max(ans, r - left + 1);
        }

        System.out.println(ans);
    }
}

/*
【解題說明】
- 維護一個 [left..r] 的滑動視窗，保證視窗內無重複字元。
- 當右端 r 讀到字元 c，若 c 在視窗內出現過（last[c] >= left），
  將 left 跳到 last[c] + 1，以排除重複。
- 每步更新目前視窗長度與答案。

【邊界案例】
- 空字串 → 輸出 0。
- 全相同字元（如 "bbbbb"）→ 輸出 1。
- 交錯重複（如 "abba"）→ 輸出 2（"ab" 或 "ba"）。

【複雜度】
- 時間 O(n)：每個字元最多被 left、r 各處理一次。
- 空間 O(k)：k 為字元集大小（此處為 65536）。
*/
