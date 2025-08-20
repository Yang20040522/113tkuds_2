import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class M08_BSTRangedSum {

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
        while (st == null || !st.hasMoreElements()) {
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

        Integer L = nextInt();
        Integer R = nextInt();
        if (L == null || R == null) {
            System.out.println("Sum: 0");
            return;
        }

        Node root = buildTreeFromLevel(a);
        long sum = rangeSumBST(root, L, R);
        System.out.println("Sum: " + sum);
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
            if (li < n) nodes[i].left = nodes[li];
            if (ri < n) nodes[i].right = nodes[ri];
        }
        return nodes[0];
    }

    private static long rangeSumBST(Node root, int L, int R) {
        long ans = 0;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (cur == null) continue;
            if (cur.val < L) {
                if (cur.right != null) stack.push(cur.right);
            } else if (cur.val > R) {
                if (cur.left != null) stack.push(cur.left);
            } else {
                ans += cur.val;
                if (cur.left != null) stack.push(cur.left);
                if (cur.right != null) stack.push(cur.right);
            }
        }
        return ans;
    }
}
