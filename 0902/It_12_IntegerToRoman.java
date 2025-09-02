// 題目：Integer to Roman
// 將 1..3999 的整數轉換為羅馬數字表示。

public class It_12_IntegerToRoman {

    // 核心解法：貪婪法 + 預先列舉面額（含減法表示）
    public String intToRoman(int num) {
        // 依照由大至小排列，包含 900/400/90/40/9/4 的減法表示
        int[] vals =    {1000, 900, 500, 400, 100,  90,  50,  40,  10,   9,   5,   4,   1};
        String[] syms = {"M",  "CM","D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        // 從最大面額一路往下，能扣就扣，並把符號附上
        for (int i = 0; i < vals.length && num > 0; i++) {
            while (num >= vals[i]) {
                sb.append(syms[i]);
                num -= vals[i];
            }
        }
        return sb.toString();
    }

    // 主程式：本地測試
    public static void main(String[] args) {
        It_12_IntegerToRoman solver = new It_12_IntegerToRoman();

        int[] tests = {3749, 58, 1994, 3, 4, 9, 40, 90, 400, 944, 3999};
        for (int n : tests) {
            System.out.printf("Input: %d -> Output: %s%n", n, solver.intToRoman(n));
        }
        // 期望：
        // 3749 -> MMMDCCXLIX
        // 58   -> LVIII
        // 1994 -> MCMXCIV
        // 3    -> III
        // 4    -> IV
        // 9    -> IX
        // 40   -> XL
        // 90   -> XC
        // 400  -> CD
        // 944  -> CMXLIV
        // 3999 -> MMMCMXCIX
    }
}

/*
解題思路：
1. 預先準備所有必要的羅馬面額（含減法表示）：M, CM, D, CD, C, XC, L, XL, X, IX, V, IV, I，
   並依數值由大到小排列。
2. 以貪婪法處理整數 num：對每個面額 v，能扣幾次就扣幾次，並把對應符號附加到結果。
3. 由於面額集合固定且順序正確，能保證轉換正確且效率高。
時間複雜度：O(1)（常數級），空間複雜度：O(1)。
*/
