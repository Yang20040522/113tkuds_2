public class RecursiveMathCalculator {
    public static void main(String[] args) {
        // 測試資料
        int n = 5, k = 2;

        System.out.println("🔢 組合數 C(" + n + ", " + k + ") = " + combination(n, k));
        System.out.println("🌱 第 " + n + " 個卡塔蘭數 Catalan(" + n + ") = " + catalan(n));
        System.out.println("🗼 漢諾塔移動步數 Hanoi(" + n + ") = " + hanoiMoves(n));

        int pal1 = 12321;
        int pal2 = 12345;
        System.out.println("🔍 " + pal1 + " 是回文數嗎？" + isPalindrome(pal1));
        System.out.println("🔍 " + pal2 + " 是回文數嗎？" + isPalindrome(pal2));
    }

    // ✅ 組合數 C(n, k) = C(n-1, k-1) + C(n-1, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // ✅ 卡塔蘭數 C(n) = Σ(C(i) × C(n-1-i))
    public static int catalan(int n) {
        if (n <= 1) return 1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // ✅ 漢諾塔移動步數：h(n) = 2 * h(n-1) + 1
    public static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    // ✅ 判斷是否為回文數（遞迴）
    public static boolean isPalindrome(int num) {
        return isPalindromeHelper(Integer.toString(num), 0);
    }

    // 遞迴輔助函式
    private static boolean isPalindromeHelper(String s, int index) {
        int j = s.length() - 1 - index;
        if (index >= j) return true;
        if (s.charAt(index) != s.charAt(j)) return false;
        return isPalindromeHelper(s, index + 1);
    }
}
