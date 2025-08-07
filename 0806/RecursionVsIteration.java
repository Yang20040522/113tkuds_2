
public class RecursionVsIteration {
    public static void main(String[] args) {
        // --- 1. 二項式係數 ---
        int n = 10, k = 3;
        System.out.println("🎯 C(" + n + "," + k + ") =");
        System.out.println("遞迴解法：" + binomialRecursive(n, k));
        System.out.println("迭代解法：" + binomialIterative(n, k));

        // --- 2. 陣列乘積 ---
        int[] arr = {2, 3, 4, 5};
        System.out.println("\n✖ 陣列乘積 =");
        System.out.println("遞迴：" + productRecursive(arr, 0));
        System.out.println("迭代：" + productIterative(arr));

        // --- 3. 字串元音數量 ---
        String text = "RecursionAndIterationAreFun";
        System.out.println("\n🗣 字串元音數量 =");
        System.out.println("遞迴：" + countVowelsRecursive(text.toLowerCase(), 0));
        System.out.println("迭代：" + countVowelsIterative(text.toLowerCase()));

        // --- 4. 括號配對 ---
        String brackets1 = "((())())";
        String brackets2 = "(()(()";
        System.out.println("\n() 括號是否配對：");
        System.out.println(brackets1 + " → 遞迴：" + isBalancedRecursive(brackets1, 0, 0) + "，迭代：" + isBalancedIterative(brackets1));
        System.out.println(brackets2 + " → 遞迴：" + isBalancedRecursive(brackets2, 0, 0) + "，迭代：" + isBalancedIterative(brackets2));
    }

    // ===========================
    // ✅ 1. 二項式係數 C(n, k)
    // ===========================

    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static int binomialIterative(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            int maxJ = Math.min(i, k);
            for (int j = 0; j <= maxJ; j++) {
                if (j == 0 || j == i)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        return dp[n][k];
    }

    // ===========================
    // ✅ 2. 陣列乘積
    // ===========================

    public static int productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static int productIterative(int[] arr) {
        int result = 1;
        for (int val : arr) result *= val;
        return result;
    }

    // ===========================
    // ✅ 3. 字串元音數量
    // ===========================

    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) return 0;
        char c = s.charAt(index);
        int count = isVowel(c) ? 1 : 0;
        return count + countVowelsRecursive(s, index + 1);
    }

    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (isVowel(c)) count++;
        }
        return count;
    }

    public static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    // ===========================
    // ✅ 4. 括號配對正確檢查
    // ===========================

    public static boolean isBalancedRecursive(String s, int index, int count) {
        if (count < 0) return false;
        if (index == s.length()) return count == 0;

        char c = s.charAt(index);
        if (c == '(') return isBalancedRecursive(s, index + 1, count + 1);
        if (c == ')') return isBalancedRecursive(s, index + 1, count - 1);
        return isBalancedRecursive(s, index + 1, count); // 非括號字元可略過
    }

    public static boolean isBalancedIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
}
