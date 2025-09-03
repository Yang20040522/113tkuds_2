// 題目：Letter Combinations of a Phone Number
// 輸入一個數字字串 (digits)，回傳所有對應電話按鍵的字母組合。

import java.util.*;

public class It_17_LetterCombinations {

    // 數字到字母的映射表
    private static final String[] KEYS = {
        "",    // 0
        "",    // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs",// 7
        "tuv", // 8
        "wxyz" // 9
    };

    // 核心方法
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    // 回溯法
    private void backtrack(String digits, int idx, StringBuilder path, List<String> res) {
        if (idx == digits.length()) {
            res.add(path.toString());
            return;
        }

        String letters = KEYS[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            backtrack(digits, idx + 1, path, res);
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }

    // 主程式測試
    public static void main(String[] args) {
        It_17_LetterCombinations solver = new It_17_LetterCombinations();

        System.out.println("Input: \"23\"");
        System.out.println("Output: " + solver.letterCombinations("23"));

        System.out.println("Input: \"\"");
        System.out.println("Output: " + solver.letterCombinations(""));

        System.out.println("Input: \"2\"");
        System.out.println("Output: " + solver.letterCombinations("2"));
    }
}

/*
解題思路：
- 每位數字對應一組字母，需列舉所有可能組合。
- 使用回溯法，依次選取每位數字的字母，直到組合長度等於 digits 長度。
- 結果放入 res。
時間複雜度：O(3^n ~ 4^n)，其中 n = digits 長度，最大 n=4，所以可接受。
*/
