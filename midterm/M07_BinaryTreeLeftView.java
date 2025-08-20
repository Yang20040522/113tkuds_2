// 檔名：M07_BinaryTreeLeftView.java

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class M07_BinaryTreeLeftView {

    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    // 讀取工具（UTF-8），支援跨多行輸入的 token 讀取
    static BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in, StandardCharsets.UTF_8));
    static StringTokenizer st;

    static String next() throws IOException {
        while (st == null || !st.hasMoreElements()) {
            String line = br.readLine();
            if (line == null) return null;
            if (line.trim().isEmpty()) continue;
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

        // 建樹（以陣列索引建立左右連結；-1 代表 null）
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
        Node root = n > 0 ? nodes[0] : null;

        // BFS 輸出每層第一個（左視圖）
        List<Integer> leftView = new ArrayList<>();
        if (root != null) {
            ArrayDeque<Node> q = new ArrayDeque<>();
            q.add(root);
            while (!q.isEmpty()) {
                int sz = q.size();
                for (int i = 0; i < sz; i++) {
                    Node cur = q.poll();
                    if (i == 0) leftView.add(cur.val); // 每層第一個出佇列者
                    if (cur.left != null) q.add(cur.left);
                    if (cur.right != null) q.add(cur.right);
                }
            }
        }

        // 輸出
        StringBuilder sb = new StringBuilder("LeftView:");
        if (!leftView.isEmpty()) sb.append(' ');
        for (int i = 0; i < leftView.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(leftView.get(i));
        }
        System.out.println(sb.toString());
    }
}
