// 檔名：LC20_ValidParentheses_AlertFormat.java
// 題意：輸入只含 ()[]{} 的字串，判斷括號是否成對、正確巢狀且無錯配。
// 解法：使用 Stack 模擬，遇到開括號 push，遇到閉括號檢查棧頂是否匹配後 pop。
// 複雜度：時間 O(n)，空間 O(n)

import java.io.*;
import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";

        boolean valid = isValid(s);
        System.out.println(valid ? "true" : "false");
    }

    private static boolean isValid(String s) {
        Map<Character, Character> match = new HashMap<>();
        match.put(')', '(');
        match.put(']', '[');
        match.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else { // c is a closing bracket
                if (stack.isEmpty()) return false;
                if (stack.peek() != match.get(c)) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}

/*
【解題說明】
- 掃描字串：
  - 若是開括號 ( [ { → push 到 stack。
  - 若是閉括號 ) ] } → 檢查 stack 是否空、以及頂端是否與之匹配。
    - 不匹配立即回傳 false。
- 掃描結束後 stack 必須為空才合法。

【邊界案例】
- 空字串 → true（沒有不合法結構）。
- 單一字元 → false（無法成對）。
- 以閉括號開頭 → false。
- 巢狀與並列結構 → 正確即 true。

【複雜度】
- 時間 O(n)：每個字元處理一次。
- 空間 O(n)：最壞情況全部為開括號。
*/
