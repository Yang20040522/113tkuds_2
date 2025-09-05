// 檔名：LC17_PhoneCombos_CSShift.java
// 題意：輸入只含 '2'~'9' 的數字字串，輸出所有依電話鍵盤映射展開的字母組合（每行一組）。
// 解法：回溯（深度等於 digits 長度；每層依映射字母分支）。
// 複雜度：時間為所有組合數的總量 O(∏branch)，空間 O(深度)。

import java.io.*;

public class LC17_PhoneCombos_CSShift {
    private static final String[] MAP = {
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String digits = br.readLine();
        if (digits == null) digits = "";
        digits = digits.trim();

        // 邊界：空字串 → 無輸出
        if (digits.isEmpty()) return;

        // 可選：若輸入含非 2-9 的字元，直接不輸出（依題目假設不會發生）
        for (int i = 0; i < digits.length(); i++) {
            char c = digits.charAt(i);
            if (c < '2' || c > '9') return;
        }

        StringBuilder out = new StringBuilder();
        char[] path = new char[digits.length()];

        dfs(digits, 0, path, out);

        System.out.print(out.toString());
    }

    private static void dfs(String digits, int idx, char[] path, StringBuilder out) {
        if (idx == digits.length()) {
            out.append(path).append('\n');
            return;
        }
        String letters = MAP[digits.charAt(idx) - '2'];
        for (int i = 0; i < letters.length(); i++) {
            path[idx] = letters.charAt(i);
            dfs(digits, idx + 1, path, out);
        }
    }
}

/*
【說明】
- 回溯樹的每一層代表一個數字；分支為該數字對應的所有字母。
- 使用 char[] path 記錄當前組合，避免頻繁建立字串；到達葉節點就輸出一行。
- 若輸入長度為 0，按照題意可不輸出任何行。

【例】
輸入
23
輸出（任一標準順序）
ad
ae
af
bd
be
bf
cd
ce
cf
*/
