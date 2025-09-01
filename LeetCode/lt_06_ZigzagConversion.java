public class lt_06_ZigzagConversion {

    static class Solution {
        public String convert(String s, int numRows) {
            if (numRows == 1 || s.length() <= numRows) return s;

            StringBuilder[] rows = new StringBuilder[numRows];
            for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

            int r = 0;
            int dir = 1; // 1: 往下, -1: 往上
            for (char c : s.toCharArray()) {
                rows[r].append(c);
                if (r == 0) dir = 1;
                else if (r == numRows - 1) dir = -1;
                r += dir;
            }

            StringBuilder ans = new StringBuilder();
            for (StringBuilder sb : rows) ans.append(sb);
            return ans.toString();
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        Solution sol = new Solution();

        String s1 = "PAYPALISHIRING";
        int numRows1 = 3;
        System.out.println("Input: " + s1 + ", numRows=" + numRows1);
        System.out.println("Output: " + sol.convert(s1, numRows1)); // 預期: "PAHNAPLSIIGYIR"

        int numRows2 = 4;
        System.out.println("Input: " + s1 + ", numRows=" + numRows2);
        System.out.println("Output: " + sol.convert(s1, numRows2)); // 預期: "PINALSIGYAHRPI"

        String s2 = "A";
        int numRows3 = 1;
        System.out.println("Input: " + s2 + ", numRows=" + numRows3);
        System.out.println("Output: " + sol.convert(s2, numRows3)); // 預期: "A"
    }
}
