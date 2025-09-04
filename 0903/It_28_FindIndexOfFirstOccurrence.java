// 題目：Find the Index of the First Occurrence in a String
// 使用暴力法搜尋 needle 在 haystack 的第一次出現位置。

public class It_28_FindIndexOfFirstOccurrence {

    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    // 測試
    public static void main(String[] args) {
        It_28_FindIndexOfFirstOccurrence solver = new It_28_FindIndexOfFirstOccurrence();

        System.out.println("Case1: " + solver.strStr("sadbutsad", "sad"));  // 0
        System.out.println("Case2: " + solver.strStr("leetcode", "leeto")); // -1
        System.out.println("Case3: " + solver.strStr("mississippi", "issi")); // 1
    }
}

/*
解題思路：
1. 用雙迴圈暴力檢查 needle 是否出現在 haystack。
2. 對於每個起點 i，取 haystack[i..i+m-1] 與 needle 比較。
3. 若完全相等 → 返回 i。
4. 若遍歷完都沒找到 → 返回 -1。
時間 O(n*m)，空間 O(1)。
*/
