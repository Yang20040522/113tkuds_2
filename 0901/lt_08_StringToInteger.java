public class lt_08_StringToInteger {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();
        // 1. 忽略前導空白
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 如果全是空白
        if (i == n) return 0;

        // 2. 處理正負號
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. 讀取數字
        long num = 0; // 暫用 long 避免溢出
        while (i < n && Character.isDigit(s.charAt(i))) {
            num = num * 10 + (s.charAt(i) - '0');

            // 4. 溢出檢查
            if (sign == 1 && num > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -num < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }

        return (int)(sign * num);
    }

    // 測試用 main
    public static void main(String[] args) {
        lt_08_StringToInteger solution = new lt_08_StringToInteger();

        System.out.println(solution.myAtoi("42"));           // 42
        System.out.println(solution.myAtoi("   -042"));      // -42
        System.out.println(solution.myAtoi("1337c0d3"));     // 1337
        System.out.println(solution.myAtoi("0-1"));          // 0
        System.out.println(solution.myAtoi("words and 987"));// 0
        System.out.println(solution.myAtoi("-91283472332")); // -2147483648 (溢出)
        System.out.println(solution.myAtoi("91283472332"));  // 2147483647 (溢出)
    }
}
