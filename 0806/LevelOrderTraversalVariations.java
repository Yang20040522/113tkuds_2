import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ✅ 1. 每層存在不同 List 中
    public static List<List<Integer>> levelOrderByLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    // ✅ 2. 之字形層序走訪（奇數層左→右，偶數層右→左）
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    // ✅ 3. 每層最後一個節點（右側視角）
    public static List<Integer> rightmostPerLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode last = null;
            for (int i = 0; i < size; i++) {
                last = q.poll();
                if (last.left != null) q.offer(last.left);
                if (last.right != null) q.offer(last.right);
            }
            result.add(last.val);
        }
        return result;
    }

    // ✅ 4. 垂直層序走訪（Vertical Order）
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, List<Integer>> columnMap = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            columnMap.putIfAbsent(p.col, new ArrayList<>());
            columnMap.get(p.col).add(p.node.val);

            if (p.node.left != null) q.offer(new Pair(p.node.left, p.col - 1));
            if (p.node.right != null) q.offer(new Pair(p.node.right, p.col + 1));
        }

        result.addAll(columnMap.values());
        return result;
    }

    static class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    // 🔎 測試主程式
    public static void main(String[] args) {
        /*
               1
              / \
             2   3
            / \   \
           4   5   6
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("📋 每層一列：");
        System.out.println(levelOrderByLevel(root));

        System.out.println("🌀 Zigzag 層序：");
        System.out.println(zigzagLevelOrder(root));

        System.out.println("🎯 每層最後節點：");
        System.out.println(rightmostPerLevel(root));

        System.out.println("📊 垂直層序：");
        System.out.println(verticalOrder(root));
    }
}
