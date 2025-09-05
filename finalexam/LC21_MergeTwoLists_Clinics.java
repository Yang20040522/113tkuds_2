// 檔名：LC21_MergeTwoLists_Clinics.java
// 題意：合併兩條已排序的單向鏈結串列，輸出合併後升序串列。
// 解法：雙指針 + Dummy Head，逐步選取較小節點接到結果串列尾端。
// 複雜度：時間 O(n+m)，空間 O(1)（僅指標操作）。

import java.io.*;

public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int m = fs.nextInt();

        ListNode l1 = buildList(fs, n);
        ListNode l2 = buildList(fs, m);

        ListNode merged = mergeTwoLists(l1, l2);

        // 輸出合併後序列
        StringBuilder out = new StringBuilder();
        for (ListNode p = merged; p != null; p = p.next) {
            if (out.length() > 0) out.append(' ');
            out.append(p.val);
        }
        System.out.println(out.toString());
    }

    // 合併兩條升序鏈結串列
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // 剩餘部分直接掛尾
        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;

        return dummy.next;
    }

    // 建立鏈結串列
    private static ListNode buildList(FastScanner fs, int n) throws IOException {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(fs.nextInt());
            tail = tail.next;
        }
        return dummy.next;
    }

    // 高速輸入
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

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}

/*
【說明】
- Dummy Head：簡化邊界處理。
- 雙指針：逐步比較兩串頭部，選較小者掛上，並前進。
- 最後若某串未走完，直接接上尾端。

【邊界案例】
- 其中一串為空 → 直接回傳另一串。
- 全部元素相等 → 按順序合併，保留重複。
- 長度差極大（如 1 vs 5e4） → 一樣正確，尾段直接掛尾。

【範例】
輸入
3 3
1 2 4
1 3 4
輸出
1 1 2 3 4 4
*/
