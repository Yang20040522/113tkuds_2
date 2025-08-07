import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // ✅ 1. 驗證是否為有效 BST（中序遞增）
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max))
            return false;
        return isValidBSTHelper(node.left, min, node.val)
            && isValidBSTHelper(node.right, node.val, max);
    }

    // ✅ 2. 找出 BST 中不符規則的節點（非遞增）
    public static List<Integer> findInvalidNodes(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        findInvalidHelper(root, null, null, result);
        return result;
    }

    private static void findInvalidHelper(TreeNode node, Integer min, Integer max, List<Integer> list) {
        if (node == null) return;
        if ((min != null && node.val <= min) || (max != null && node.val >= max))
            list.add(node.val);
        findInvalidHelper(node.left, min, node.val, list);
        findInvalidHelper(node.right, node.val, max, list);
    }

    // ✅ 3. 修復 BST 中兩個交換位置的節點
    static TreeNode first = null, second = null, prev = null;

    public static void recoverBST(TreeNode root) {
        first = second = prev = null;
        inOrderRecover(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    private static void inOrderRecover(TreeNode node) {
        if (node == null) return;
        inOrderRecover(node.left);
        if (prev != null && node.val < prev.val) {
            if (first == null) first = prev;
            second = node;
        }
        prev = node;
        inOrderRecover(node.right);
    }

    // ✅ 4. 計算最少需移除幾個節點才能成為 BST（Longest Increasing Subsequence）
    public static int minRemovalToBST(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inOrderCollect(root, values);
        return values.size() - lengthOfLIS(values);
    }

    private static void inOrderCollect(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inOrderCollect(node.left, values);
        values.add(node.val);
        inOrderCollect(node.right, values);
    }

    // Longest Increasing Subsequence（LIS）
    private static int lengthOfLIS(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int i = Collections.binarySearch(dp, num);
            if (i < 0) i = -(i + 1);
            if (i == dp.size()) dp.add(num);
            else dp.set(i, num);
        }
        return dp.size();
    }

    // 🧪 印中序
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // 🧪 測試主程式
    public static void main(String[] args) {
        /*
             原始錯誤 BST：
                   10
                  /  \
                20    5
               /
              3
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);

        System.out.println("✅ 是否為 BST？" + isValidBST(root)); // false
        System.out.println("🔍 錯誤節點：" + findInvalidNodes(root)); // 20, 5

        System.out.print("🔁 修復前中序：");
        printInOrder(root);
        System.out.println();

        recoverBST(root);

        System.out.print("✅ 修復後中序：");
        printInOrder(root);
        System.out.println("\n✅ 是否為 BST？" + isValidBST(root)); // true

        // 測試需移除幾個節點
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(8);
        root2.right = new TreeNode(12);
        root2.left.left = new TreeNode(11); // 錯誤節點
        root2.right.right = new TreeNode(5); // 錯誤節點

        System.out.print("🌲 中序值：");
        printInOrder(root2);
        System.out.println("\n🔧 需移除節點數 = " + minRemovalToBST(root2)); // e.g. 2
    }
}
