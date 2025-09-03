// 題目：Valid Parentheses
// 使用 Stack 檢查字串是否為有效括號。

import java.util.*;

public class It_20_ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // 測試
    public static void main(String[] args) {
        It_20_ValidParentheses solver = new It_20_ValidParentheses();

        System.out.println("Input: () → " + solver.isValid("()"));          // true
        System.out.println("Input: ()[]{} → " + solver.isValid("()[]{}")); // true
        System.out.println("Input: (] → " + solver.isValid("(]"));         // false
        System.out.println("Input: ([]) → " + solver.isValid("([])"));     // true
        System.out.println("Input: ([)] → " + solver.isValid("([)]"));     // false
    }
}

/*
解題思路：
- 用 Stack 依序檢查括號。
- 左括號 → push 對應的右括號；
- 右括號 → pop 檢查是否相符；
- 不符或提前遇到空 stack → false。
- 最後若 stack 為空 → true。
時間 O(n)，空間 O(n)。
*/
