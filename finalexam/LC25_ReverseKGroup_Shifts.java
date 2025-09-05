// 檔名：LC25_ReverseKGroup_Shifts.java
// 題意：單向鏈結串列，將節點以 k 個為一組做區段反轉；不足 k 的尾端保持原樣。
// 解法：迭代檢查每組長度是否足夠，若足夠則反轉該組；否則保留。
// 複雜度：時間 O(n)，空間 O(1)。

import java.io.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String kLine = br.readLine();
        if (kLine == null || kLine.trim().isEmpty()) {
            System.out.println();
            return;
        }
        int k = Integer.parseInt(kLine.trim());

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println();
            return;
        }

        String[] parts = line.trim().split("\\s+");
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (String s : parts) {
            tail.next = new ListNode(Integer.parseInt(s));
            tail = tail.next;
        }

        ListNode head = reverseKGroup(dummy.next, k);

        // 輸出
        StringBuilder out = new StringBuilder();
        for (ListNode p = head; p != null; p = p.next) {
            if (out.length() > 0) out.append(' ');
            out.append(p.val);
        }
        System.out.println(out.toString());
    }

    private static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            // 找到這一組的最後一個節點
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) break; // 不足 k，結束

            ListNode groupNext = kth.next;

            // 反轉這一組
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 調整 groupPrev 與下一組連接
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;
        }
        return dummy.next;
    }

    // 從 start 開始，往後走 k 步，返回第 k 個節點；若不足 k 步回傳 null
    private static ListNode getKthNode(ListNode start, int k) {
        ListNode curr = start;
        for (int i = 0; i < k; i++) {
            if (curr.next == null) return null;
            curr = curr.next;
        }
        return curr;
    }
}

/*
【說明】
- getKthNode：檢查是否有足夠 k 個節點，若不足則結束。
- 若足夠，反轉該區段，並用 groupPrev 和 groupNext 重新接好。
- 迭代直到串列尾端。

【邊界案例】
- k=1 → 不反轉。
- 長度 < k → 保留原樣。
- 長度 = k → 整串反轉。
- 長度 = m*k + r（r<k）→ 只有前 m*k 反轉，尾巴 r 保留。

【範例】
輸入:
2
1 2 3 4 5
輸出:
2 1 4 3 5
*/
