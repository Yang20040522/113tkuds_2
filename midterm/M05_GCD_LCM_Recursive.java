// 檔名：M05_GCD_LCM_Recursive.java

/*
 * Time Complexity: O(log(min(a,b)))
 * 說明：使用遞迴歐幾里得算法計算 GCD，遞迴深度與 log(min(a,b)) 成正比；
 *      LCM 透過 a / gcd * b 計算，為 O(1)。整體 O(log(min(a,b)))。
 *
 * Space Complexity: O(log(min(a,b)))
 * 說明：遞迴呼叫堆疊最多 log(min(a,b)) 層。
 */

import java.io.*;
import java.nio.charset.StandardCharsets;

public class M05_GCD_LCM_Recursive {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

        String[] parts = readNonEmpty(br).split("\\s+");
        long a = Long.parseLong(parts[0]);
        long b = Long.parseLong(parts[1]);

        long g = gcd(a, b);
        long l = (a / g) * b; // 先除後乘，避免溢位

        out.println("GCD: " + g);
        out.println("LCM: " + l);
        out.flush();
    }

    // 遞迴歐幾里得
    private static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }

    private static String readNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
        return s == null ? "" : s.trim();
    }
}
