import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1️⃣ 計算總和與平均值
    static class SumResult {
        int sum = 0;
        int count = 0;
    }

    public static SumResult calculateSum(TreeNode root) {
        SumResult result = new SumResult();
        sumHelper(root, result);
        return result;
    }

    private static void sumHelper(TreeNode node, SumResult res) {
        if (node == null) return;
        res.sum += node.val;
        res.count++;
        sumHelper(node.left, res);
        sumHelper(node.right, res);
    }

    // 2️⃣ 找出最大與最小節點值
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 3️⃣ 計算樹的寬度（每層最大節點數）
    public static int getMaxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    // 4️⃣ 判斷是否為完全二元樹（使用層序遍歷）
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean seenNull = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                seenNull = true;
            } else {
                if (seenNull) return false; // 出現過 null 又出現節點 → 非完全
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }

    // 🔎 測試主程式
    public static void main(String[] args) {
        /*
            測試樹：
                  10
                 /  \
                5    20
               / \   /
              3   7 15
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(15);

        // 1️⃣ 總和與平均
        SumResult res = calculateSum(root);
        System.out.println("📊 總和 = " + res.sum + "，平均值 = " + ((double) res.sum / res.count));

        // 2️⃣ 最大與最小值
        System.out.println("🔺 最大值 = " + findMax(root));
        System.out.println("🔻 最小值 = " + findMin(root));

        // 3️⃣ 樹的寬度
        System.out.println("📏 最大寬度 = " + getMaxWidth(root));

        // 4️⃣ 判斷是否為完全二元樹
        System.out.println("🌳 是完全二元樹嗎？" + isCompleteTree(root));
    }
}
