
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {
        boolean isNull; // vi == -1
        boolean isBlack; // null 視為黑
        Node(boolean isNull, boolean isBlack) { this.isNull = isNull; this.isBlack = isBlack; }
    }

    // --------- 讀取工具（UTF‑8、跨行 token） ---------
    static BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in, StandardCharsets.UTF_8));
    static StringTokenizer st;
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) return null;
            line = line.trim();
            if (line.isEmpty()) continue;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }
    static Integer nextInt() throws IOException {
        String s = next();
        return s == null ? null : Integer.parseInt(s);
    }

    public static void main(String[] args) throws Exception {
        Integer nObj = nextInt();
        int n = (nObj == null ? 0 : nObj);

        Node[] a = new Node[n];
        for (int i = 0; i < n; i++) {
            Integer v = nextInt();   // 可能跨行
            String c = next();       // B 或 R
            boolean isNull = (v == null || v == -1);
            boolean isBlack = true;  // 預設黑
            if (!isNull) {
                isBlack = !"R".equalsIgnoreCase(c); // 'R'→紅；其他→黑
            }
            a[i] = new Node(isNull, isBlack);
        }

        // 1) 根節點為黑（null 視為黑）
        if (n > 0) {
            Node root = a[0];
            if (!root.isNull && !root.isBlack) {
                System.out.println("RootNotBlack");
                return;
            }
        }

        // 2) 不得有紅紅相鄰：掃描層序索引，回報第一個違規的父節點索引
        for (int i = 0; i < n; i++) {
            Node cur = a[i];
            if (cur == null || cur.isNull) continue;
            if (!cur.isBlack) { // 當前為紅
                int li = 2 * i + 1, ri = 2 * i + 2;
                if (li < n) {
                    Node L = a[li];
                    if (L != null && !L.isNull && !L.isBlack) {
                        System.out.println("RedRedViolation at index " + i);
                        return;
                    }
                }
                if (ri < n) {
                    Node R = a[ri];
                    if (R != null && !R.isNull && !R.isBlack) {
                        System.out.println("RedRedViolation at index " + i);
                        return;
                    }
                }
            }
        }

        // 3) 黑高度一致：對每個節點到所有 NIL 的路徑黑節點數相同
        //    定義：null/NIL 的黑高度為 1（NIL 視為黑）
        int bh = blackHeight(a, 0, n);
        if (bh == MISMATCH) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }
    }

    static final int MISMATCH = -1;

    // 回傳黑高度；若左右子樹黑高度不一致，回傳 MISMATCH
    // 索引越界或節點為 null（-1）時，視為 NIL，黑高度 = 1
    static int blackHeight(Node[] a, int i, int n) {
        if (i >= n || a[i] == null || a[i].isNull) return 1; // NIL（黑）

        int li = 2 * i + 1, ri = 2 * i + 2;
        int left = blackHeight(a, li, n);
        if (left == MISMATCH) return MISMATCH;
        int right = blackHeight(a, ri, n);
        if (right == MISMATCH) return MISMATCH;

        if (left != right) return MISMATCH;

        // 若當前為黑，黑高度 +1；紅則不加
        return left + (a[i].isBlack ? 1 : 0);
    }
}
