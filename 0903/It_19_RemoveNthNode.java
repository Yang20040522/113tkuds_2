// 題目：Remove Nth Node From End of List
// 使用快慢指針刪除倒數第 n 個節點。

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class It_19_RemoveNthNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy, slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    // 測試用工具方法
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? "->" : ""));
            head = head.next;
        }
        System.out.println();
    }

    // 主程式
    public static void main(String[] args) {
        // 建立測試鏈表 [1,2,3,4,5]
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        It_19_RemoveNthNode solver = new It_19_RemoveNthNode();

        System.out.print("原始鏈表: ");
        printList(head);

        ListNode result = solver.removeNthFromEnd(head, 2);

        System.out.print("刪除倒數第 2 個後: ");
        printList(result); // 預期 1->2->3->5
    }
}

/*
解題思路（同 LeetCode 版）：
- dummy + 雙指針，避免頭節點特判。
- fast 先走 n+1 步，再與 slow 同時移動。
- fast 到尾時，slow 剛好在要刪除的前一個。
- 修改指向刪除節點。
*/
