import java.io.*;
import java.nio.charset.StandardCharsets;

public class M06_PalindromeClean {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String line = br.readLine();
        if (line == null) line = "";

        // 清洗：僅保留英文字母，統一轉小寫
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        String cleaned = sb.toString();

        // 雙指標檢查回文
        int l = 0, r = cleaned.length() - 1;
        boolean ok = true;
        while (l < r) {
            if (cleaned.charAt(l) != cleaned.charAt(r)) {
                ok = false;
                break;
            }
            l++;
            r--;
        }

        System.out.println(ok ? "Yes" : "No");
    }
}