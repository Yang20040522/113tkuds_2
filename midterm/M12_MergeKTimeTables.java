import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {

    static class Node implements Comparable<Node> {
        int time;      // 轉為分鐘
        int listIdx;   // 來源哪個清單
        int elemIdx;   // 清單內的索引
        Node(int t, int li, int ei) { time = t; listIdx = li; elemIdx = ei; }
        @Override public int compareTo(Node o) { return Integer.compare(this.time, o.time); }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FastScanner fs = new FastScanner(br);

        Integer kVal = fs.nextIntOrNull();
        if (kVal == null) return;
        int K = kVal;

        List<int[]> lists = new ArrayList<>(K);
        boolean sawClock = false; // 是否看過 HH:mm 格式

        for (int i = 0; i < K; i++) {
            int len = fs.nextInt(); // 每條列表長度
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                String tok = fs.next();
                if (tok.indexOf(':') >= 0) { // HH:mm
                    sawClock = true;
                    arr[j] = hhmmToMinutes(tok);
                } else {
                    arr[j] = Integer.parseInt(tok);
                }
            }
            lists.add(arr);
        }

        // 最小堆合併
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            int[] arr = lists.get(i);
            if (arr.length > 0) pq.offer(new Node(arr[0], i, 0));
        }

        List<Integer> merged = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            merged.add(cur.time);
            int[] src = lists.get(cur.listIdx);
            int nextIdx = cur.elemIdx + 1;
            if (nextIdx < src.length) {
                pq.offer(new Node(src[nextIdx], cur.listIdx, nextIdx));
            }
        }

        // 輸出（若有任一輸入為 HH:mm，則輸出 HH:mm；否則輸出分鐘整數）
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < merged.size(); i++) {
            if (i > 0) out.append(' ');
            if (sawClock) out.append(minutesToHHmm(merged.get(i)));
            else out.append(merged.get(i));
        }
        System.out.println(out.toString());
    }

    // 解析 HH:mm -> 分鐘
    private static int hhmmToMinutes(String s) {
        String[] p = s.split(":");
        int h = Integer.parseInt(p[0]);
        int m = Integer.parseInt(p[1]);
        return h * 60 + m;
    }

    // 分鐘 -> HH:mm（零補）
    private static String minutesToHHmm(int mins) {
        int h = Math.floorDiv(mins, 60);
        int m = Math.floorMod(mins, 60);
        return String.format("%02d:%02d", h, m);
    }

    // 簡易掃描器：支援跨行讀取 token 與 int/字串混合
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        FastScanner(BufferedReader br) { this.br = br; }
        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        Integer nextIntOrNull() throws IOException {
            String t = next();
            return (t == null) ? null : Integer.parseInt(t);
        }
    }
}
