// 檔名：LC23_MergeKLists_Hospitals.java
// 題意：合併 k 條已排序的單向鏈結串列為一條升序串列，效率需達到 O(N log k)。
// 解法：最小堆（PriorityQueue）。先把每條串列的頭節點丟進堆中；每次彈出最小者接到結果尾巴，
//      並把該節點的下一個節點再放回堆。直到堆空即可完成合併。
// 複雜度：時間 O(N log k)，空間 O(k)，其中 N 為總節點數。

import java.io.*;
import java.util.*;

public class LC23_MergeKLists_Hospitals {
    // 基本單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        Integer Kobj = fs.nextIntOrNull();
        if (Kobj == null) { // 沒有輸入
            System.out.println();
            return;
        }
        int k = Kobj;

        // 讀取 k 行序列，每行以 -1 結束；可能出現空串列（僅 -1）
        ListNode[] lists = new ListNode[Math.max(k, 0)];
        for (int i = 0; i < k; i++) {
            lists[i] = readOneListUntilMinus1(fs);
        }

        ListNode merged = mergeKLists(lists);

        // 輸出：合併後序列（空白分隔）；若空則輸出空行
        StringBuilder out = new StringBuilder();
        for (ListNode p = merged; p != null; p = p.next) {
            if (out.length() > 0) out.append(' ');
            out.append(p.val);
        }
        System.out.println(out.toString());
    }

    // 使用最小堆合併 k 條升序鏈結串列
    private static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(n -> n.val));

        // 初始化：把每條非空串列的頭放入堆
        for (ListNode head : lists) {
            if (head != null) pq.offer(head);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();  // 目前最小
            tail.next = node;           // 接到結果尾端
            tail = tail.next;

            if (node.next != null) pq.offer(node.next);  // 推進原串列
        }
        return dummy.next;
    }

    // 讀一行（實際以 token 為單位）直到遇到 -1，組成一條鏈結串列
    private static ListNode readOneListUntilMinus1(FastScanner fs) throws IOException {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (true) {
            int x = fs.nextInt();       // 題目保證每行會有 -1 作為終止
            if (x == -1) break;
            tail.next = new ListNode(x);
            tail = tail.next;
        }
        return dummy.next;
    }

    // 高速輸入（基於位元組緩衝的 token parser）
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        private String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            // skip spaces/newlines
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char)c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }

        Integer nextIntOrNull() throws IOException {
            String t = next();
            return (t == null) ? null : Integer.parseInt(t);
        }
    }
}

/*
【要點回顧】
- 初始化把每條串列頭節點放入最小堆；每次彈出值最小者接到答案，並將其 next 放回堆。
- 如此每個節點僅入堆/出堆一次，總成本 O(N log k)。
- 輸入以 -1 結尾的設計可簡單解析每行；若整行是 -1 即代表空串列。

【邊界案例】
- k=0 或所有串列皆空 → 輸出空行。
- 僅單一串列 → 直接輸出原序列。
- 值高度交錯 → 由堆維持整體排序，不會失序。
*/
