// 題目：Divide Two Integers
// 限制：不可用 *, /, %，僅能用加減與位運算。

public class It_29_DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean negative = (dividend < 0) ^ (divisor < 0);

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;

        while (a >= b) {
            long temp = b, multiple = 1;
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }

    // 測試
    public static void main(String[] args) {
        It_29_DivideTwoIntegers solver = new It_29_DivideTwoIntegers();

        System.out.println(solver.divide(10, 3));   // 3
        System.out.println(solver.divide(7, -3));   // -2
        System.out.println(solver.divide(-2147483648, -1)); // 2147483647 (MAX_VALUE)
        System.out.println(solver.divide(-2147483648, 2));  // -1073741824
    }
}

/*
解題思路：
1. 特殊情況：(-2^31)/(-1) → 溢位，回傳 Integer.MAX_VALUE。
2. 判斷符號：若 dividend 和 divisor 符號不同，結果為負。
3. 轉 long 取絕對值，避免溢位。
4. 用位移法模擬除法：
   - 找到最大 2^n * divisor ≤ dividend。
   - 減去該值並累加到商。
5. 持續迴圈直到 dividend < divisor。
時間 O(log n)，空間 O(1)。
*/
