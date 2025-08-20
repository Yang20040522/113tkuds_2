// 檔名：M03_TopKConvenience.java

/*
 * Time Complexity: O(n log K)
 * 說明：逐筆以大小為 K 的最小堆維護 Top‑K。每筆插入/可能彈出成本 O(log K)，共 n 筆為 O(n log K)，
 *      最後將堆中 K 筆做一次排序輸出 O(K log K)，整體為 O(n log K)（K ≪ n 時更有效率）。
 *
 *  tie-break 說明：
 *   1) 輸出排序：qty 由大到小；qty 相同時，依品名字典序（升冪）決定先後。
 *   2) 內部最小堆：以 (qty 升冪, name 降冪) 當作「最小」判定，確保需要淘汰時優先淘汰
 *      較小的 qty；同 qty 時淘汰字典序較大的 name，保留將來輸出會更靠前的項目。
 */

import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        Item(String n, int q) { name = n; qty = q; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = readNonEmpty(br).split("\\s+");
        int n = Integer.parseInt(first[0]);
        int K = Integer.parseInt(first[1]);

        // 最小堆：比較 (qty 升冪, name 降冪) —— 堆頂是目前 TopK 中「最差」的
        PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return Integer.compare(a.qty, b.qty);
                return b.name.compareTo(a.name); // 同量時較大的 name 視為「更小」以便先淘汰
            }
        });

        for (int i = 0; i < n; i++) {
            String line = readNonEmpty(br);
            String[] p = line.split("\\s+");
            String name = p[0];               // 題目保證品名不含空白
            int qty = Integer.parseInt(p[1]);

            if (pq.size() < K) {
                pq.offer(new Item(name, qty));
            } else {
                Item worst = pq.peek();
                // 若新來的「更好」(qty 大；或同 qty 但 name 字典序更小) 就取代
                if (qty > worst.qty || (qty == worst.qty && name.compareTo(worst.name) < 0)) {
                    pq.poll();
                    pq.offer(new Item(name, qty));
                }
            }
        }

        // 取出並依輸出規則排序：qty 降冪；同 qty 時 name 升冪
        List<Item> ans = new ArrayList<>(pq);
        ans.sort(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return Integer.compare(b.qty, a.qty);
                return a.name.compareTo(b.name);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Item it : ans) sb.append(it.name).append(' ').append(it.qty).append('\n');
        System.out.print(sb.toString());
    }

    private static String readNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
        return s == null ? "" : s.trim();
    }
}
