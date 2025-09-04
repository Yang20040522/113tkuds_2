// 題目：Generate Parentheses
// 產生 n 對括號的所有合法組合（回溯 + 剪枝）。

import java.util.*;

public class It_22_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    // 回溯：cur 為當前字串；open/close 分別是已加入的 '(' / ')' 數量
    private void backtrack(List<String> ans, StringBuilder cur, int open, int close, int n) {
        if (cur.length() == 2 * n) {        // 放滿 2n 個字元即為一個解
            ans.add(cur.toString());
            return;
        }
        if (open < n) {                      // 還有左括號可放
            cur.append('(');
            backtrack(ans, cur, open + 1, close, n);
            cur.deleteCharAt(cur.length() - 1); // 回溯
        }
        if (close < open) {                  // 右括號不可超過左括號
            cur.append(')');
            backtrack(ans, cur, open, close + 1, n);
            cur.deleteCharAt(cur.length() - 1); // 回溯
        }
    }

    // 簡單測試
    public static void main(String[] args) {
        It_22_GenerateParentheses solver = new It_22_GenerateParentheses();

        System.out.println("n=3 → " + solver.generateParenthesis(3));
        System.out.println("n=1 → " + solver.generateParenthesis(1));
        System.out.println("n=4 → " + solver.generateParenthesis(4));
    }
}

/*
解題思路：
1. 問題可視為在長度為 2n 的字串中擺放 '(' 與 ')'。為了保持合法：
   - 左括號數 open 最多 n 個；
   - 右括號數 close 含量不得超過 open。
2. 回溯時，只在滿足條件時擴展分支：
   - open < n 時可加入 '('；
   - close < open 時可加入 ')'
3. 當字串長度達 2n，代表形成一個合法組合，加入答案。
複雜度：輸出的組合數為卡塔蘭數 C_n，整體時間為 O(C_n)，遞迴深度 O(n)。
*/
