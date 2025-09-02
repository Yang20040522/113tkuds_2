// 題目：Longest Common Prefix
// 給定一組字串，找出它們的最長共同前綴。

public class It_14_LongestCommonPrefix {

    // 核心方法
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 先取第一個字串為基準
        String prefix = strs[0];

        // 逐一比對其他字串
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                // 每次刪掉最後一個字元再比對
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    // 主程式：測試案例
    public static void main(String[] args) {
        It_14_LongestCommonPrefix solver = new It_14_LongestCommonPrefix();

        String[] test1 = {"flower", "flow", "flight"};
        String[] test2 = {"dog", "racecar", "car"};
        String[] test3 = {"interspecies", "interstellar", "interstate"};

        System.out.println("Input: [flower, flow, flight]");
        System.out.println("Output: " + solver.longestCommonPrefix(test1)); // 預期 fl

        System.out.println("Input: [dog, racecar, car]");
        System.out.println("Output: " + solver.longestCommonPrefix(test2)); // 預期 ""

        System.out.println("Input: [interspecies, interstellar, interstate]");
        System.out.println("Output: " + solver.longestCommonPrefix(test3)); // 預期 inters
    }
}

/*
解題思路：
1. 選第一個字串為初始 prefix。
2. 與每個字串比較，若不以 prefix 開頭，縮短 prefix 直到符合。
3. 若縮到空字串，直接回傳 ""。
時間複雜度：O(n * m)，n=字串數量，m=字串最長長度。
空間複雜度：O(1)。
*/
