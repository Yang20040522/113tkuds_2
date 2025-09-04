// 題目：Reverse Nodes in k-Group
// 每 k 個節點一組反轉，不足 k 個保持原樣。

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int v) { val = v; }
    ListNode(int v, ListNode n) { val = v; next = n; }
}

public class It_25_ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kth = getKth(prevGroupEnd, k);
            if (kth == null) break;

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            reverse(groupStart, kth);

            prevGroupEnd.next = kth;
            groupStart.next = nextGroupStart;

            prevGroupEnd = groupStart;
        }
        return dummy.next;
    }

    private ListNode getKth(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }

    private void reverse(ListNode start, ListNode end) {
        ListNode prev = end.next;
        ListNode cur = start;
        while (prev != end) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
    }

    // ===== 測試工具 =====
    private static ListNode build(int[] arr) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int x : arr) { cur.next = new ListNode(x); cur = cur.next; }
        return dummy.next;
    }
    private static void print(ListNode h) {
        if (h == null) { System.out.println("[]"); return; }
        StringBuilder sb = new StringBuilder();
        while (h != null) {
            sb.append(h.val);
            if (h.next != null) sb.append("->");
            h = h.next;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        It_25_ReverseKGroup solver = new It_25_ReverseKGroup();

        ListNode head1 = build(new int[]{1,2,3,4,5});
        print(solver.reverseKGroup(head1, 2)); // 2->1->4->3->5

        ListNode head2 = build(new int[]{1,2,3,4,5});
        print(solver.reverseKGroup(head2, 3)); // 3->2->1->4->5

        ListNode head3 = build(new int[]{1,2});
        print(solver.reverseKGroup(head3, 2)); // 2->1

        ListNode head4 = build(new int[]{1});
        print(solver.reverseKGroup(head4, 2)); // 1
    }
}

/*
解題思路：
1. 用 dummy 節點簡化操作，dummy.next = head。
2. 每次從 prevGroupEnd 開始，檢查是否還有 k 個節點：
   - 若不足 k 個，直接停止。
   - 若足夠，標記 groupStart、kth 與 nextGroupStart。
3. 呼叫 reverse(start, end)，將這一段反轉。
4. 接回原鏈表：prevGroupEnd.next = kth，groupStart.next = nextGroupStart。
5. 移動 prevGroupEnd 到 groupStart，準備處理下一組。
時間複雜度 O(n)，空間 O(1)。
*/
