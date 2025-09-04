// 題目：Merge Two Sorted Lists
// 合併兩個已排序的鏈表。

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class It_21_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        if (list1 != null) tail.next = list1;
        if (list2 != null) tail.next = list2;

        return dummy.next;
    }

    // 工具方法：建立鏈表
    private static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : arr) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 工具方法：打印鏈表
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? "->" : ""));
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        It_21_MergeTwoSortedLists solver = new It_21_MergeTwoSortedLists();

        ListNode list1 = buildList(new int[]{1, 2, 4});
        ListNode list2 = buildList(new int[]{1, 3, 4});

        System.out.print("list1: ");
        printList(list1);
        System.out.print("list2: ");
        printList(list2);

        ListNode merged = solver.mergeTwoLists(list1, list2);

        System.out.print("Merged: ");
        printList(merged); // 預期 1->1->2->3->4->4
    }
}

/*
解題思路：
1. 使用 dummy 節點作為合併鏈表的起點，避免頭節點特判。
2. 指針 tail 每次選擇 list1 或 list2 的較小節點接上。
3. 其中一個鏈表用完後，把另一個鏈表剩餘部分接上即可。
時間複雜度：O(m+n)，空間複雜度：O(1)。
*/