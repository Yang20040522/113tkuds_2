public class lt_09_PalindromeNumber {

    // 判斷是否為迴文數字（數學法，不轉字串）
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        return x == reversedHalf || x == reversedHalf / 10;
    }

    // 測試 main 方法
    public static void main(String[] args) {
        int[] testCases = {121, -121, 10, 12321, 0};

        for (int num : testCases) {
            System.out.println(num + " -> " + isPalindrome(num));
        }
    }
}
