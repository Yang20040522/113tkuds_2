// 檔名：LC28_StrStr_NoticeSearch.java
// 題意：回傳子字串 needle 在主字串 haystack 中首次出現的起始索引；若不存在回 -1；若 needle 為空回 0。
// 解法：KMP（建失敗函數 lps，主掃描時若不匹配依 lps 回退，總複雜度 O(n+m)）。
// 輸入：兩行字串（第一行 haystack，第二行 needle）。

import java.io.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String haystack = br.readLine();
        String needle   = br.readLine();

        if (haystack == null) haystack = "";
        if (needle == null) needle = "";

        System.out.println(strStr(haystack, needle));
    }

    // KMP 主流程
    private static int strStr(String s, String p) {
        int n = s.length(), m = p.length();
        if (m == 0) return 0;
        if (m > n) return -1;

        int[] lps = buildLPS(p);
        int i = 0, j = 0; // i 指向 s，j 指向 p
        while (i < n) {
            if (s.charAt(i) == p.charAt(j)) {
                i++; j++;
                if (j == m) return i - m; // 命中：回起始索引
            } else {
                if (j > 0) j = lps[j - 1]; // 失配，依 lps 回退
                else i++;                  // j==0 無法回退，前進 i
            }
        }
        return -1;
    }

    // 構建部分匹配表（Longest Prefix which is also Suffix）
    private static int[] buildLPS(String p) {
        int m = p.length();
        int[] lps = new int[m];
        int len = 0; // 當前最長相等前後綴長度
        for (int i = 1; i < m; ) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else {
                if (len > 0) len = lps[len - 1];
                else lps[i++] = 0;
            }
        }
        return lps;
    }
}

/*
【邊界處理】
- needle 空 → 0
- needle 長於 haystack → -1
- 大量重複前綴（如 "aaaaab" 找 "aaab"）→ KMP 能避免重複比較。

【複雜度】
- 建 lps：O(m)
- 匹配：O(n)
- 總計：O(n+m)
*/
