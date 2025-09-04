// 題目：Merge k Sorted Lists
// 以最小堆合併多條已排序鏈表。

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class It_23_MergeKSortedLists {

    // 核心：最小堆 O(N log k)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.val, b.val)
        );

        for (ListNode head : lists) if (head != null) pq.offer(head);

        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }

    // ====== 測試工具 ======
    private static ListNode build(int[] a) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : a) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }
    private static void print(ListNode h) {
        while (h != null) { 
            System.out.print(h.val + (h.next != null ? "->" : ""));
            h = h.next;
        }
        System.out.println();
    }

    // 主程式：簡單驗證
    public static void main(String[] args) {
        It_23_MergeKSortedLists solver = new It_23_MergeKSortedLists();

        ListNode[] lists1 = new ListNode[] {
            build(new int[]{1,4,5}),
            build(new int[]{1,3,4}),
            build(new int[]{2,6})
        };
        System.out.print("Case1: ");
        print(solver.mergeKLists(lists1)); // 1->1->2->3->4->4->5->6

        ListNode[] lists2 = new ListNode[] {};
        System.out.print("Case2: ");
        print(solver.mergeKLists(lists2)); // (空輸出)

        ListNode[] lists3 = new ListNode[] { build(new int[]{}) };
        System.out.print("Case3: ");
        print(solver.mergeKLists(lists3)); // (空輸出)
    }
}

/*
解題思路：
1. 問題本質是將 k 條遞增序列合併。直接逐條掃描會退化到 O(kN)；更優做法是用最小堆維護當前最小值。
2. 初始化：把每條鏈表的首節點（若存在）放到最小堆中。
3. 循環：彈出堆頂（全體當前最小），接到結果尾端；若該節點仍有後續，將其 next 放回堆，保持候選集完備。
4. 重複直到堆空。由於每個節點最多入堆/出堆一次，總計 N 次堆操作。
複雜度：時間 O(N log k)；空間 O(k)。
*/
