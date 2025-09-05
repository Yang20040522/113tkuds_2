// 檔名：LC24_SwapPairs_Shifts.java
// 題意：單向鏈結串列，相鄰兩節點成對交換；若長度為奇數，最後一個節點保持不變。
// 解法：Dummy + 指標操作。每次取出一對 (a,b)，重接：prev.next=b, a.next=b.next, b.next=a。
// 複雜度：時間 O(n)，空間 O(1)。

import java.io.*;

public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            // 空串列
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

        ListNode head = swapPairs(dummy.next);

        // 輸出
        StringBuilder out = new StringBuilder();
        for (ListNode p = head; p != null; p = p.next) {
            if (out.length() > 0) out.append(' ');
            out.append(p.val);
        }
        System.out.println(out.toString());
    }

    private static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            // 交換
            a.next = b.next;
            b.next = a;
            prev.next = b;

            // 前進到下一組
            prev = a;
        }
        return dummy.next;
    }
}

/*
【思路總結】
- 使用 dummy 簡化頭節點交換。
- while 條件：至少還有兩個節點可交換。
- 交換後，prev 移動到剛剛的 a（交換後在 b 後面）。

【邊界案例】
- 空串列或單一節點：直接回傳，不變。
- 偶數長度：全部交換。
- 奇數長度：最後一個不動。

【範例】
輸入: 1 2 3 4
輸出: 2 1 4 3
*/
