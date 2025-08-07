public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // ✅ 1. BST 轉換為排序雙向鏈結串列（中序 + 雙指標）
    static TreeNode prev = null, head = null;

    public static TreeNode bstToDoublyList(TreeNode root) {
        prev = null;
        head = null;
        convertToDoublyList(root);
        return head;
    }

    private static void convertToDoublyList(TreeNode node) {
        if (node == null) return;
        convertToDoublyList(node.left);

        if (prev != null) {
            prev.right = node;
            node.left = prev;
        } else {
            head = node;
        }
        prev = node;

        convertToDoublyList(node.right);
    }

    // ✅ 2. 排序陣列轉換為平衡 BST（中間為根）
    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);
        return root;
    }

    // ✅ 3. 檢查是否平衡並輸出平衡因子
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int left = checkHeight(node.left);
        int right = checkHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }

    public static void printBalanceFactor(TreeNode root) {
        if (root == null) return;
        int left = height(root.left);
        int right = height(root.right);
        int balance = left - right;
        System.out.println("節點 " + root.val + " 的平衡因子：" + balance);
        printBalanceFactor(root.left);
        printBalanceFactor(root.right);
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // ✅ 4. 節點值改為所有 ≥ 該節點的總和（反中序）
    static int runningSum = 0;

    public static void convertToGreaterTree(TreeNode root) {
        runningSum = 0;
        reverseInOrder(root);
    }

    private static void reverseInOrder(TreeNode node) {
        if (node == null) return;
        reverseInOrder(node.right);
        runningSum += node.val;
        node.val = runningSum;
        reverseInOrder(node.left);
    }

    // 🔧 中序列印
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // 🔧 雙向串列輸出
    public static void printDoublyList(TreeNode head) {
        TreeNode node = head;
        while (node != null) {
            System.out.print(node.val + " <-> ");
            if (node.right == null) break;
            node = node.right;
        }
        System.out.println("null");
    }

    // === 測試主程式 ===
    public static void main(String[] args) {
        // 測試 BST 轉雙向鏈結串列
        TreeNode bst = new TreeNode(4);
        bst.left = new TreeNode(2);
        bst.right = new TreeNode(5);
        bst.left.left = new TreeNode(1);
        bst.left.right = new TreeNode(3);
        System.out.println("📎 BST → 雙向串列：");
        TreeNode dllHead = bstToDoublyList(bst);
        printDoublyList(dllHead);

        // 測試排序陣列轉 BST
        int[] sorted = {-10, -3, 0, 5, 9};
        TreeNode balancedBST = sortedArrayToBST(sorted);
        System.out.print("🌳 平衡 BST 中序：");
        printInOrder(balancedBST);
        System.out.println();

        // 檢查是否平衡 + 印出平衡因子
        System.out.println("🧭 是否平衡：" + isBalanced(balancedBST));
        System.out.println("⚖️ 各節點平衡因子：");
        printBalanceFactor(balancedBST);

        // 測試節點值替換為 ≥ 總和
        TreeNode bst2 = new TreeNode(4);
        bst2.left = new TreeNode(1);
        bst2.right = new TreeNode(6);
        bst2.left.left = new TreeNode(0);
        bst2.left.right = new TreeNode(2);
        bst2.right.left = new TreeNode(5);
        bst2.right.right = new TreeNode(7);
        System.out.print("🧮 原始 BST 中序：");
        printInOrder(bst2);
        System.out.println();

        convertToGreaterTree(bst2);
        System.out.print("💰 轉換後中序：");
        printInOrder(bst2);
        System.out.println();
    }
}
