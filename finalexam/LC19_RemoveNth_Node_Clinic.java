// 檔名：LC19_RemoveNth_Node_Clinic.java
// 題意：單向鏈結串列，刪除「倒數第 k 個」節點，僅一趟遍歷，輸出刪除後序列。
// 解法：雙指標 + 假頭(dummy)。fast 先走 k 步，之後 fast/slow 同步走到尾；此時 slow 停在待刪節點之前，調整指標即可。
// 複雜度：時間 O(n)，空間 O(1)

import java.io.*;

public class LC19_RemoveNth_Node_Clinic {
    // 基本節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();              // 節點數
        ListNode dummy = new ListNode(0);  // 假頭
        ListNode tail = dummy;

        for (int i = 0; i < n; i++) {
            tail.next = new ListNode(fs.nextInt());
            tail = tail.next;
        }
        int k = fs.nextInt();              // 要刪除的倒數第 k 個

        ListNode head = removeNthFromEnd(dummy.next, k);

        // 輸出：刪除後序列（以空白分隔）；若空串列則輸出空行
        StringBuilder out = new StringBuilder();
        for (ListNode p = head; p != null; p = p.next) {
            if (out.length() > 0) out.append(' ');
            out.append(p.val);
        }
        System.out.println(out.toString());
    }

    // 一趟遍歷刪除倒數第 k 個
    private static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy, slow = dummy;

        // fast 先走 k+1 步，使 slow 恰停在「待刪前一個」的位置
        for (int i = 0; i < k + 1; i++) {
            fast = fast.next;
        }

        // 同步前進直到 fast 抵達尾端之後(null)
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除 slow 後面那個
        slow.next = slow.next.next;

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
【邊界】
- n=1, k=1：刪除唯一節點 → 空串列（輸出空行）。
- k=n：刪除頭節點（dummy 幫忙處理）。
- k=1：刪除尾節點。
- 值可重複不影響邏輯。

【正確性】
- fast 先走 k+1 步，保持 fast 與 slow 間距 k+1。
- 當 fast 為 null 時，slow 位於待刪節點之前一格，直接改指標即可。
*/
