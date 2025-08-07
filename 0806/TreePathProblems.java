import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // ✅ 1. 找出所有根到葉的路徑
    public static List<List<Integer>> allPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPaths(root, new ArrayList<>(), result);
        return result;
    }

    private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);

        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path)); // 到達葉節點
        } else {
            dfsPaths(node.left, path, result);
            dfsPaths(node.right, path, result);
        }

        path.remove(path.size() - 1); // 回溯
    }

    // ✅ 2. 是否存在根到葉路徑和為 target
    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == target;
        return hasPathSum(root.left, target - root.val) ||
               hasPathSum(root.right, target - root.val);
    }

    // ✅ 3. 找出和最大的根到葉路徑
    static int maxSum = Integer.MIN_VALUE;
    static List<Integer> maxPath = new ArrayList<>();

    public static List<Integer> maxRootToLeafPath(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPath.clear();
        dfsMaxSum(root, 0, new ArrayList<>());
        return maxPath;
    }

    private static void dfsMaxSum(TreeNode node, int currentSum, List<Integer> path) {
        if (node == null) return;

        path.add(node.val);
        currentSum += node.val;

        if (node.left == null && node.right == null) {
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxPath = new ArrayList<>(path);
            }
        } else {
            dfsMaxSum(node.left, currentSum, path);
            dfsMaxSum(node.right, currentSum, path);
        }

        path.remove(path.size() - 1); // 回溯
    }

    // ✅ 4. 任意兩節點最大路徑和（直徑總和）
    static int maxAnyPathSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxAnyPathSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxAnyPathSum;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxGain(node.left));
        int right = Math.max(0, maxGain(node.right));

        int localMax = node.val + left + right;
        maxAnyPathSum = Math.max(maxAnyPathSum, localMax);

        return node.val + Math.max(left, right);
    }

    // === 測試主程式 ===
    public static void main(String[] args) {
        /*
                 10
                /  \
               5    -3
              / \     \
             3   2     11
            / \   \
           3  -2   1
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        // 1️⃣ 所有根到葉路徑
        System.out.println("📋 所有根到葉路徑：");
        List<List<Integer>> paths = allPaths(root);
        for (List<Integer> p : paths) {
            System.out.println(p);
        }

        // 2️⃣ 是否存在和為 18 的路徑
        int target = 18;
        System.out.println("🎯 是否有根到葉路徑和為 " + target + "？" + hasPathSum(root, target));

        // 3️⃣ 最大總和根到葉路徑
        List<Integer> maxLeafPath = maxRootToLeafPath(root);
        System.out.println("💰 最大和的根到葉路徑：" + maxLeafPath + "，總和 = " + maxSum);

        // 4️⃣ 任意兩節點最大路徑和
        System.out.println("🌐 任意兩節點最大路徑和：" + maxPathSum(root));
    }
}
