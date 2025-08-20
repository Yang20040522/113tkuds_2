// 檔名：M02_YouBikeNextArrival.java
import java.io.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(readNonEmpty(br));

        int[] t = new int[n];
        for (int i = 0; i < n; i++) t[i] = toMin(readNonEmpty(br));

        int q = toMin(readNonEmpty(br));

        // upper_bound：找出第一個 > q 的位置
        int lo = 0, hi = n; // [lo, hi)
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (t[mid] > q) hi = mid;
            else lo = mid + 1;
        }

        if (lo == n) System.out.println("No bike");
        else System.out.println(toHHMM(t[lo]));
    }

    private static String readNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
        return s == null ? "" : s.trim();
    }

    private static int toMin(String hhmm) {
        String[] p = hhmm.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    private static String toHHMM(int m) {
        int h = m / 60, mm = m % 60;
        return String.format("%02d:%02d", h, mm);
    }
}
