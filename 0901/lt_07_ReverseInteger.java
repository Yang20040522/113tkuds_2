public class lt_07_ReverseInteger {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;   // 取最後一位數字
            x /= 10;            // 去掉最後一位

            // 檢查是否溢出 (32-bit signed integer)
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && pop > 7)) {
                return 0; // 正數溢出
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && pop < -8)) {
                return 0; // 負數溢出
            }

            rev = rev * 10 + pop; // 將數字加到反轉後的結果
        }
        return rev;
    }

    public static void main(String[] args) {
        lt_07_ReverseInteger solution = new lt_07_ReverseInteger();

        // 測試案例
        System.out.println(solution.reverse(123));    // 321
        System.out.println(solution.reverse(-123));   // -321
        System.out.println(solution.reverse(120));    // 21
        System.out.println(solution.reverse(0));      // 0
        System.out.println(solution.reverse(1534236469)); // 0 (溢出)
    }
}
