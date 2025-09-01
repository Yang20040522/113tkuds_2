// 檔名: lt_10_RegularExpressionMatching.java
public class lt_10_RegularExpressionMatching {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 範例測試
        String s1 = "aa", p1 = "a";
        System.out.println("s = \"" + s1 + "\", p = \"" + p1 + "\" → " + solution.isMatch(s1, p1));

        String s2 = "aa", p2 = "a*";
        System.out.println("s = \"" + s2 + "\", p = \"" + p2 + "\" → " + solution.isMatch(s2, p2));

        String s3 = "ab", p3 = ".*";
        System.out.println("s = \"" + s3 + "\", p = \"" + p3 + "\" → " + solution.isMatch(s3, p3));
    }

    static class Solution {
        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();

            // dp[i][j] = true if s[0..i-1] matches p[0..j-1]
            boolean[][] dp = new boolean[m + 1][n + 1];
            dp[0][0] = true; // empty string matches empty pattern

            // 初始化 dp，處理空字串與像 a*, a*b* 的 pattern
            for (int j = 2; j <= n; j += 2) {
                if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                    dp[0][j] = true;
                }
            }

            // 填充 dp 表
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (p.charAt(j - 1) == '*') {
                        // Case 1: '*' 當作零次前一個字符
                        dp[i][j] = dp[i][j - 2];

                        // Case 2: '*' 當作一個或多個前一個字符
                        if (matches(s, p, i, j - 1)) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    } else {
                        if (matches(s, p, i, j)) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }

            return dp[m][n];
        }

        // 檢查 s[i-1] 是否匹配 p[j-1]
        private boolean matches(String s, String p, int i, int j) {
            if (i == 0) return false;
            if (p.charAt(j - 1) == '.') return true;
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }
}
