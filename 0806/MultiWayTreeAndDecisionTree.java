import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // ✅ 定義多路樹節點
    static class Node {
        String value;
        List<Node> children;

        Node(String val) {
            value = val;
            children = new ArrayList<>();
        }

        void addChild(Node child) {
            children.add(child);
        }
    }

    // ✅ 深度優先走訪（前序）
    public static void dfs(Node root) {
        if (root == null) return;
        System.out.print(root.value + " ");
        for (Node child : root.children) {
            dfs(child);
        }
    }

    // ✅ 廣度優先走訪
    public static void bfs(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.value + " ");
            for (Node child : current.children) {
                queue.offer(child);
            }
        }
    }

    // ✅ 計算多路樹的高度（根節點深度為 1）
    public static int getHeight(Node root) {
        if (root == null) return 0;
        int maxChildHeight = 0;
        for (Node child : root.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        return 1 + maxChildHeight;
    }

    // ✅ 列出每個節點的度數
    public static void printNodeDegrees(Node root) {
        if (root == null) return;
        System.out.println("節點 " + root.value + " 的度數：" + root.children.size());
        for (Node child : root.children) {
            printNodeDegrees(child);
        }
    }

    // ✅ 模擬決策樹（猜數字）例子
    public static void runDecisionTree() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎯 猜數字遊戲（1~10）：");

        Node root = new Node("你猜的是偶數嗎？");
        Node yes = new Node("你猜的是 > 5 嗎？");
        Node no = new Node("你猜的是 < 5 嗎？");

        root.addChild(yes);
        root.addChild(no);

        yes.addChild(new Node("答案可能是 6, 8, 或 10"));
        yes.addChild(new Node("答案可能是 2 或 4"));

        no.addChild(new Node("答案可能是 1 或 3"));
        no.addChild(new Node("答案可能是 5, 7, 或 9"));

        // 模擬互動
        System.out.print(root.value + " (yes/no)：");
        String ans1 = scanner.nextLine();

        if (ans1.equalsIgnoreCase("yes")) {
            System.out.print(yes.value + " (yes/no)：");
            String ans2 = scanner.nextLine();
            if (ans2.equalsIgnoreCase("yes")) {
                System.out.println("💡 " + yes.children.get(0).value);
            } else {
                System.out.println("💡 " + yes.children.get(1).value);
            }
        } else {
            System.out.print(no.value + " (yes/no)：");
            String ans2 = scanner.nextLine();
            if (ans2.equalsIgnoreCase("yes")) {
                System.out.println("💡 " + no.children.get(0).value);
            } else {
                System.out.println("💡 " + no.children.get(1).value);
            }
        }
    }

    // ✅ 主程式
    public static void main(String[] args) {
        // 建立範例多路樹
        Node root = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");

        root.addChild(b);
        root.addChild(c);
        b.addChild(d);
        b.addChild(e);
        c.addChild(f);

        System.out.println("🔎 深度優先走訪：");
        dfs(root);  // A B D E C F
        System.out.println();

        System.out.println("🔎 廣度優先走訪：");
        bfs(root);  // A B C D E F
        System.out.println();

        System.out.println("📏 樹高：" + getHeight(root));  // 應為 3
        System.out.println("📌 各節點度數：");
        printNodeDegrees(root);

        System.out.println("\n🤖 開始決策樹互動：");
        runDecisionTree();
    }
}
