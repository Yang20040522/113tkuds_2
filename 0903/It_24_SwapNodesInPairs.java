// 題目：Swap Nodes in Pairs
// 交換相鄰的每兩個節點（只能改指標，不能改值）

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int v) { val = v; }
    ListNode(int v, ListNode n) { val = v; next = n; }
}

public class It_24_SwapNodesInPairs {

    // 迭代法：dummy + 三指針(prev, a, b)
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;
            ListNode b = a.next;

            a.next = b.next; // 斷開 a->b，讓 a 指向後面
            b.next = a;      // b 指向 a，完成局部反轉
            prev.next = b;   // prev 指向新的頭 b

            prev = a;        // 下一回合從 a 之後開始
        }
        return dummy.next;
    }

    // 工具：從陣列建表
    private static ListNode build(int[] arr) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int x : arr) { cur.next = new ListNode(x); cur = cur.next; }
        return dummy.next;
    }
    // 工具：列印
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
        It_24_SwapNodesInPairs s = new It_24_SwapNodesInPairs();

        print(s.swapPairs(build(new int[]{1,2,3,4}))); // 2->1->4->3
        print(s.swapPairs(build(new int[]{})));        // []
        print(s.swapPairs(build(new int[]{1})));       // 1
        print(s.swapPairs(build(new int[]{1,2,3})));   // 2->1->3
    }
}

/*
解題思路（迭代版）：
1. 以 dummy 作為固定起點，避免處理頭部交換的特殊情況。
2. 每回合鎖定一對節點 (a,b) 以及它們前一個節點 prev，將
   prev->a->b->next 重新連成 prev->b->a->next。
3. 交換完成後，prev 移到 a（因為 a 現在在 b 後面），繼續處理下一對。
4. 遍歷一次即可完成所有交換；邊界情況（空表、單節點）自然滿足。
複雜度：時間 O(n)；空間 O(1)。
*/
