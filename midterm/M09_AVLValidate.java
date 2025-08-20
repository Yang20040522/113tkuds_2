// 檔名：M09_AVLValidate.java

/*
 * Time Complexity: O(n)
 * 說明：一次建樹 O(n)，再做一次 DFS 同時檢查 BST 與計算高度/平衡 O(n)，總計 O(n)。
 *
 * Space Complexity: O(h) ≤ O(n)
 * 說明：遞迴呼叫堆疊深度為樹高 h；最壞情況退化為 O(n)。
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class M09_AVLValidate {

    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    // 讀取工具（UTF-8），支援跨多行 token
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

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = nextInt();

        Node root = buildTreeFromLevel(a);

        // 空樹視為有效 BST/AVL
        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        if (!isAVL(root).ok) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    // 由層序（-1 為 null）建樹；索引 i 的左右子為 2i+1、2i+2
    private static Node buildTreeFromLevel(int[] a) {
        int n = a.length;
        if (n == 0 || a[0] == -1) return null;

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            if (a[i] != -1) nodes[i] = new Node(a[i]);
        }
        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) continue;
            int li = 2 * i + 1, ri = 2 * i + 2;
            if (li < n) nodes[i].left  = nodes[li];
            if (ri < n) nodes[i].right = nodes[ri];
        }
        return nodes[0];
    }

    // 檢查 BST：min < val < max
    private static boolean isBST(Node root, long min, long max) {
        if (root == null) return true;
        if (!(min < root.val && root.val < max)) return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    // 檢查 AVL：後序回傳 (ok, height)
    static class AVLInfo {
        boolean ok;
        int height; // 空樹高度定義為 0
        AVLInfo(boolean ok, int h) { this.ok = ok; this.height = h; }
    }

    private static AVLInfo isAVL(Node root) {
        if (root == null) return new AVLInfo(true, 0);
        AVLInfo L = isAVL(root.left);
        if (!L.ok) return new AVLInfo(false, 0);
        AVLInfo R = isAVL(root.right);
        if (!R.ok) return new AVLInfo(false, 0);

        int bf = Math.abs(L.height - R.height);
        boolean ok = (bf <= 1);
        int h = Math.max(L.height, R.height) + 1;
        return new AVLInfo(ok, h);
    }
}
