import java.util.*;

public class BSTRangeQuerySystem {

    // === 節點定義 ===
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    // === 插入節點（建樹） ===
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        return root;
    }

    // ✅ 1. 範圍查詢：列出所有在 [min, max] 範圍內的節點值（遞增順序）
    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        inOrderRange(root, min, max, result);
        return result;
    }

    private static void inOrderRange(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (node.val > min) inOrderRange(node.left, min, max, result);
        if (node.val >= min && node.val <= max) result.add(node.val);
        if (node.val < max) inOrderRange(node.right, min, max, result);
    }

    // ✅ 2. 範圍計數：統計在 [min, max] 範圍內的節點數
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeCount(root.right, min, max);
        if (root.val > max) return rangeCount(root.left, min, max);
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // ✅ 3. 範圍總和：計算 [min, max] 範圍內節點總和
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeSum(root.right, min, max);
        if (root.val > max) return rangeSum(root.left, min, max);
        return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // ✅ 4. 最接近查詢：找出與目標值最接近的節點
    public static int findClosest(TreeNode root, int target) {
        return findClosestHelper(root, target, root.val);
    }

    private static int findClosestHelper(TreeNode node, int target, int closest) {
        if (node == null) return closest;
        if (Math.abs(node.val - target) < Math.abs(closest - target)) {
            closest = node.val;
        }
        if (target < node.val) return findClosestHelper(node.left, target, closest);
        else if (target > node.val) return findClosestHelper(node.right, target, closest);
        else return node.val; // exact match
    }

    // === 測試主程式 ===
    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        int min = 10, max = 28;
        System.out.println("🌲 範圍查詢 [" + min + ", " + max + "]：");
        System.out.println("結果：" + rangeQuery(root, min, max));

        System.out.println("🧮 範圍計數：" + rangeCount(root, min, max));
        System.out.println("➕ 範圍總和：" + rangeSum(root, min, max));

        int target = 27;
        System.out.println("🎯 最接近 " + target + " 的節點值為：" + findClosest(root, target));
    }
}
