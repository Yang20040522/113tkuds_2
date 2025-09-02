// 題目：Roman to Integer
// 將羅馬數字字串轉換為整數（範圍 1~3999）。

import java.util.*;

public class It_13_RomanToInteger {

    // 核心方法：將羅馬數字轉為整數
    public int romanToInt(String s) {
        // 建立字典：羅馬字元 → 整數值
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;

        // 從左到右掃描字串
        for (int i = 0; i < s.length(); i++) {
            int val = map.get(s.charAt(i));

            // 如果後一位比當前大，則需減去 val
            if (i + 1 < s.length() && map.get(s.charAt(i + 1)) > val) {
                total -= val;
            } else {
                total += val;
            }
        }

        return total;
    }

    // 主程式：測試案例
    public static void main(String[] args) {
        It_13_RomanToInteger solver = new It_13_RomanToInteger();

        String[] tests = {"III", "LVIII", "MCMXCIV", "IX", "XL", "CD", "CM"};
        for (String roman : tests) {
            System.out.printf("Input: %s -> Output: %d%n", roman, solver.romanToInt(roman));
        }
        // 期望：
        // III -> 3
        // LVIII -> 58
        // MCMXCIV -> 1994
        // IX -> 9
        // XL -> 40
        // CD -> 400
        // CM -> 900
    }
}

/*
解題思路：
1. 建立羅馬符號對應表：I=1, V=5, X=10, L=50, C=100, D=500, M=1000。
2. 從左到右遍歷字串：
   - 若下一個字元代表的數值大於當前值，則需要減去當前值（如 IV=4, IX=9）。
   - 否則直接加上當前值。
3. 最後得到的總和即為整數結果。
時間複雜度：O(n)，其中 n 為字串長度。
空間複雜度：O(1)，字典大小固定。
*/
