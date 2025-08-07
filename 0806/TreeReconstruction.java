import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // ✅ 1. 根據前序 + 中序重建
    public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inIndex.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1, 0, inIndex);
    }

    private static TreeNode buildPreIn(int[] pre, int preStart, int preEnd, int inStart, Map<Integer, Integer> inIndex) {
        if (preStart > preEnd) return null;
        int rootVal = pre[preStart];
        TreeNode root = new TreeNode(rootVal);
        int index = inIndex.get(rootVal);
        int leftSize = index - inStart;

        root.left = buildPreIn(pre, preStart + 1, preStart + leftSize, inStart, inIndex);
        root.right = buildPreIn(pre, preStart + leftSize + 1, preEnd, index + 1, inIndex);
        return root;
    }

    // ✅ 2. 根據後序 + 中序重建
    public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inIndex.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1, 0, inIndex);
    }

    private static TreeNode buildPostIn(int[] post, int postStart, int postEnd, int inStart, Map<Integer, Integer> inIndex) {
        if (postStart > postEnd) return null;
        int rootVal = post[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int index = inIndex.get(rootVal);
        int leftSize = index - inStart;

        root.left = buildPostIn(post, postStart, postStart + leftSize - 1, inStart, inIndex);
        root.right = buildPostIn(post, postStart + leftSize, postEnd - 1, index + 1, inIndex);
        return root;
    }

    // ✅ 3. 根據層序重建完全二元樹
    public static TreeNode buildCompleteTreeFromLevelOrder(Integer[] levelOrder) {
        if (levelOrder.length == 0 || levelOrder[0] == null) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < levelOrder.length) {
            TreeNode current = queue.poll();
            if (i < levelOrder.length && levelOrder[i] != null) {
                current.left = new TreeNode(levelOrder[i]);
                queue.offer(current.left);
            }
            i++;
            if (i < levelOrder.length && levelOrder[i] != null) {
                current.right = new TreeNode(levelOrder[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // ✅ 4. 驗證（中序 + 前序）
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void printPreOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    public static void printLevelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            System.out.print((node != null ? node.val : "null") + " ");
            if (node != null) {
                q.offer(node.left);
                q.offer(node.right);
            }
        }
    }

    // === 測試主程式 ===
    public static void main(String[] args) {
        // 測試 1：前序 + 中序
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root1 = buildFromPreIn(preorder, inorder);
        System.out.print("✅ 驗證前序建樹 → 中序輸出：");
        printInOrder(root1);
        System.out.println();

        // 測試 2：後序 + 中序
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root2 = buildFromPostIn(postorder, inorder);
        System.out.print("✅ 驗證後序建樹 → 前序輸出：");
        printPreOrder(root2);
        System.out.println();

        // 測試 3：層序建完全二元樹
        Integer[] levelOrder = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root3 = buildCompleteTreeFromLevelOrder(levelOrder);
        System.out.print("✅ 層序建樹 → 層序輸出：");
        printLevelOrder(root3);
        System.out.println();
    }
}
