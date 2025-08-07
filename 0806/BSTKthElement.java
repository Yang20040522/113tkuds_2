import java.util.*;

public class BSTKthElement {

    // ========== 標準 BST 節點 ==========
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // ✅ 插入節點
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        return root;
    }

    // ✅ 1. 找出第 k 小元素（中序走訪）
    public static int findKthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (--k == 0) return curr.val;
            curr = curr.right;
        }
        return -1; // not found
    }

    // ✅ 2. 找出第 k 大元素（反中序走訪）
    public static int findKthLargest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.right;
            }
            curr = stack.pop();
            if (--k == 0) return curr.val;
            curr = curr.left;
        }
        return -1;
    }

    // ✅ 3. 找出第 k 小 ~ 第 j 小所有元素（中序）
    public static List<Integer> rangeKthToJth(TreeNode root, int k, int j) {
        List<Integer> result = new ArrayList<>();
        inOrderKthRange(root, result, new int[]{0}, k, j);
        return result;
    }

    private static void inOrderKthRange(TreeNode node, List<Integer> list, int[] count, int k, int j) {
        if (node == null) return;
        inOrderKthRange(node.left, list, count, k, j);
        count[0]++;
        if (count[0] >= k && count[0] <= j) list.add(node.val);
        inOrderKthRange(node.right, list, count, k, j);
    }

    // ✅ 4. 支援動態插入刪除與第 k 小查詢的 BST（帶節點數量）
    static class KthOrderStatisticBST {
        static class Node {
            int val, size = 1;
            Node left, right;
            Node(int val) { this.val = val; }
        }

        Node root;

        public void insert(int val) {
            root = insert(root, val);
        }

        private Node insert(Node node, int val) {
            if (node == null) return new Node(val);
            if (val < node.val) node.left = insert(node.left, val);
            else if (val > node.val) node.right = insert(node.right, val);
            node.size = 1 + getSize(node.left) + getSize(node.right);
            return node;
        }

        public void delete(int val) {
            root = delete(root, val);
        }

        private Node delete(Node node, int val) {
            if (node == null) return null;
            if (val < node.val) node.left = delete(node.left, val);
            else if (val > node.val) node.right = delete(node.right, val);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node minLarger = getMin(node.right);
                node.val = minLarger.val;
                node.right = delete(node.right, minLarger.val);
            }
            node.size = 1 + getSize(node.left) + getSize(node.right);
            return node;
        }

        private Node getMin(Node node) {
            while (node.left != null) node = node.left;
            return node;
        }

        public int findKth(int k) {
            return findKth(root, k);
        }

        private int findKth(Node node, int k) {
            if (node == null) throw new IllegalArgumentException("k is out of range");
            int leftSize = getSize(node.left);
            if (k <= leftSize) return findKth(node.left, k);
            else if (k == leftSize + 1) return node.val;
            else return findKth(node.right, k - leftSize - 1);
        }

        private int getSize(Node node) {
            return node == null ? 0 : node.size;
        }
    }

    // === 主程式測試 ===
    public static void main(String[] args) {
        int[] data = {20, 10, 30, 5, 15, 25, 35};
        TreeNode root = null;
        for (int val : data) {
            root = insert(root, val);
        }

        System.out.println("🔎 第 3 小元素：" + findKthSmallest(root, 3)); // 15
        System.out.println("🔎 第 2 大元素：" + findKthLargest(root, 2)); // 30

        System.out.println("📋 第 2 ~ 5 小元素：" + rangeKthToJth(root, 2, 5)); // [10, 15, 20, 25]

        // 動態查詢版本
        KthOrderStatisticBST bst = new KthOrderStatisticBST();
        for (int val : data) bst.insert(val);

        System.out.println("🧠 動態查詢第 4 小：" + bst.findKth(4)); // 20
        bst.delete(10);
        System.out.println("❌ 刪除 10 後第 4 小：" + bst.findKth(4)); // 25
    }
}
