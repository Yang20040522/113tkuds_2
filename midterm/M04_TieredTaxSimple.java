// 檔名：M04_TieredTaxSimple.java

/*
 * Time Complexity: O(n)
 * 說明：一次走訪 n 筆，每筆以常數時間計稅（速算式或逐段累加），並累加總稅額，整體 O(n)。
 */

import java.io.*;
import java.nio.charset.StandardCharsets;

public class M04_TieredTaxSimple {
    // 切換用：評測依範例 → 用速算式
    static final boolean USE_QUICK = true;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

        int n = Integer.parseInt(readNonEmpty(br));
        long sumTax = 0;

        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(readNonEmpty(br));
            long tax = USE_QUICK ? computeTaxQuick(x) : computeTaxProgressive(x);
            out.println("Tax: " + tax);
            sumTax += tax;
        }

        long avg = Math.round(sumTax / (double) n); // 平均取整數
        out.println("Average: " + avg);
    }

    // ✅ 速算式（與範例一致）
    private static long computeTaxQuick(long x) {
        if (x <= 120_000L) {
            return Math.round(x * 0.05);
        } else if (x <= 500_000L) {
            return Math.round(x * 0.12 - 9_000.0);
        } else if (x <= 1_000_000L) {
            return Math.round(x * 0.20 - 49_000.0);
        } else {
            return Math.round(x * 0.30 - 120_000.0);
        }
    }

    // 📌 逐段累加（若照文字敘述要求，應用這個；但與範例不符）
    private static long computeTaxProgressive(long x) {
        long tax = 0;

        long take = Math.min(x, 120_000L);
        tax += Math.round(take * 0.05);
        if (x <= 120_000L) return tax;

        take = Math.min(x, 500_000L) - 120_000L;
        tax += Math.round(take * 0.12);
        if (x <= 500_000L) return tax;

        take = Math.min(x, 1_000_000L) - 500_000L;
        tax += Math.round(take * 0.20);
        if (x <= 1_000_000L) return tax;

        take = x - 1_000_000L;
        tax += Math.round(take * 0.30);
        return tax;
    }

    private static String readNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
        return s == null ? "" : s.trim();
    }
}
