// 題目：Longest Valid Parentheses
// 回傳最長合法括號子字串的長度（僅含 '(' 與 ')'）。
// 這個檔案同時提供三種寫法：Stack、DP、雙向掃描（O(1) 空間）。

import java.util.*;

public class It_32_LongestValidParentheses {

    // 解法一：Stack（索引入棧，放 -1 當哨兵邊界）
    public int longestValidParentheses_stack(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);                       // 哨兵：遇到無法匹配的 ')' 時重置邊界
        int best = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);               // 左括號位置入棧，等待配對
            } else {
                st.pop();                 // 嘗試配對最近的 '('
                if (st.isEmpty()) {
                    st.push(i);           // 無邊界可用，將當前 i 當作新的邊界
                } else {
                    best = Math.max(best, i - st.peek()); // 當前合法長度
                }
            }
        }
        return best;
    }

    // 解法二：DP（dp[i] = 以 i 結尾的最長合法長度）
    public int longestValidParentheses_dp(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] dp = new int[n];
        int best = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // ......()
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else {
                    // ......))
                    int j = i - 1 - dp[i - 1];        // 嘗試找可與 s[i] 配對的 '('
                    if (j >= 0 && s.charAt(j) == '(') {
                        dp[i] = dp[i - 1] + 2 + (j >= 1 ? dp[j - 1] : 0);
                    }
                }
                best = Math.max(best, dp[i]);
            }
        }
        return best;
    }

    // 解法三：雙向線性掃描（常數空間）
    // 左→右：')' 多於 '(' 代表無效，清零；相等時更新長度
    // 右→左再掃一次處理 '(' 偏多的情況
    public int longestValidParentheses_twoScan(String s) {
        int best = 0, left = 0, right = 0;

        // 左到右
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++; else right++;
            if (left == right) best = Math.max(best, 2 * right);
            else if (right > left) left = right = 0;
        }

        // 右到左
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++; else right++;
            if (left == right) best = Math.max(best, 2 * left);
            else if (left > right) left = right = 0;
        }
        return best;
    }

    // ====== 簡單測試 ======
    public static void main(String[] args) {
        It_32_LongestValidParentheses solver = new It_32_LongestValidParentheses();

        String[] tests = {"(()", ")()())", "", "()(())", "())(())", "()()", "(((((", "))))))"};
        for (String s : tests) {
            int a = solver.longestValidParentheses_stack(s);
            int b = solver.longestValidParentheses_dp(s);
            int c = solver.longestValidParentheses_twoScan(s);
            System.out.printf("s=\"%s\"  stack=%d  dp=%d  twoScan=%d%n", s, a, b, c);
        }
        // 重點案例期望：
        // "(())" 或 "()(())" -> 4 / 6
        // ")()())"           -> 4
        // ""                 -> 0
        // "()()"             -> 4
    }
}

/*
解題思路（三種方法）：
A) Stack
- 用棧保存尚未匹配的索引，初始化壓入 -1 當邊界。
- 遇 '(' 入棧；遇 ')' 先彈出一個索引。若棧空，將當前 i 作為新邊界；否則 i - 棧頂即為
  以 i 為右端的合法長度。全程取最大值。
- 時間 O(n)，空間 O(n)。

B) DP
- 定義 dp[i]：以 i 結尾的最長合法長度（只有 s[i]==')' 才可能 >0）。
- 兩種轉移：
  1) s[i-1]=='('：dp[i] = dp[i-2] + 2。
  2) s[i-1]==')'：若 j = i - 1 - dp[i-1] 且 s[j]=='('，則
     dp[i] = dp[i-1] + 2 + dp[j-1]（把前一段合法串與更左側相鄰的合法串連在一起）。
- 時間 O(n)，空間 O(n)。

C) 兩次線性掃描（O(1) 空間）
- 由左到右：計數 '(' 與 ')'，當右括號超過左括號時清零；相等時更新長度。
- 由右到左再掃一遍，對稱處理「左括號偏多」的情形。
- 時間 O(n)，空間 O(1)。

三法都可過；若想拿高分，建議 DP 或同檔展示多解法。
*/
