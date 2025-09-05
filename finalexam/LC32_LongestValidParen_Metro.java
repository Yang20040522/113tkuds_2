// 檔名：LC32_LongestValidParen_Metro.java
// 題意：給一行只含 '('、')' 的字串，求最長「括號合法」子字串長度（進出站完全配對）。
// 解法：索引棧法（棧底放 -1 作為基準）。掃描：
//   - 遇 '(' → push 其索引
//   - 遇 ')' → pop 一個；若棧空則推入當前索引作為新基準；否則用 i - 棧頂索引更新最長長度
// 複雜度：O(n) 時間、O(n) 空間（最壞情形全是 '(' ）。

import java.io.*;
import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";

        int ans = longestValidParentheses(s);
        System.out.println(ans);
    }

    private static int longestValidParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);           // 基準：上一個不可能匹配的位置
        int best = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);    // 記錄 '(' 的索引
            } else { // c == ')'
                if (!st.isEmpty()) st.pop();  // 嘗試匹配一個 '('
                if (st.isEmpty()) {
                    // 沒東西可配，這個 ')' 成為新的基準
                    st.push(i);
                } else {
                    // 目前有效段長度 = i - 棧頂（上一個未匹配索引）
                    best = Math.max(best, i - st.peek());
                }
            }
        }
        return best;
    }
}

/*
【為何有效】
- 棧中存放的是「尚未匹配的索引」；棧頂永遠是最近一個未匹配位置。
- 以 -1 為初始基準可正確處理前綴即合法的情況（如 "()"、"()()"）。
- 當遇到無法匹配的 ')'，將其索引作為新的基準，後續合法段長度都相對於此計算。

【邊界案例】
- "" → 0（無字元）
- "((((" 或 "))))" → 0（無合法片段）
- "()()" → 4
- ")()"：當 i=0 的 ')' 使棧空，基準重置為 0；之後 "()" 可得到長度 2。
- "())(())"：答案為 4（最後段 "(())"）。

【複雜度】
- 單次線性掃描 O(n)；棧最壞存放 O(n) 個索引。
*/
