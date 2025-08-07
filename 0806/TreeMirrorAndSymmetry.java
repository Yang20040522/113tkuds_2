public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1️⃣ 判斷是否為對稱樹（左右對稱）
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return t1.val == t2.val &&
               isMirror(t1.left, t2.right) &&
               isMirror(t1.right, t2.left);
    }

    // 2️⃣ 鏡像化：左右子樹交換
    public static void mirrorTree(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
    }

    // 3️⃣ 判斷兩棵樹是否為鏡像
    public static boolean areMirrors(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val &&
               areMirrors(a.left, b.right) &&
               areMirrors(a.right, b.left);
    }

    // 4️⃣ 判斷 T 是否為 S 的子樹
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isIdentical(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private static boolean isIdentical(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.val != b.val) return false;
        return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    }

    // 工具：中序列印
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // 🔎 測試主程式
    public static void main(String[] args) {
        /*
              1
             / \
            2   2
           /     \
          3       3
        */
        TreeNode symmetricRoot = new TreeNode(1);
        symmetricRoot.left = new TreeNode(2);
        symmetricRoot.right = new TreeNode(2);
        symmetricRoot.left.left = new TreeNode(3);
        symmetricRoot.right.right = new TreeNode(3);

        System.out.println("🪞 isSymmetric？" + isSymmetric(symmetricRoot)); // true

        System.out.print("🔁 中序（原樹）：");
        printInOrder(symmetricRoot);
        System.out.println();

        mirrorTree(symmetricRoot);
        System.out.print("🔁 中序（鏡像後）：");
        printInOrder(symmetricRoot);
        System.out.println();

        // 測試是否為鏡像
        TreeNode mirrorA = new TreeNode(1);
        mirrorA.left = new TreeNode(2);
        mirrorA.right = new TreeNode(3);

        TreeNode mirrorB = new TreeNode(1);
        mirrorB.left = new TreeNode(3);
        mirrorB.right = new TreeNode(2);

        System.out.println("🔍 areMirrors？" + areMirrors(mirrorA, mirrorB)); // true

        // 測試子樹判斷
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);

        TreeNode subtree = new TreeNode(2);
        subtree.left = new TreeNode(4);
        subtree.right = new TreeNode(5);

        System.out.println("🌱 isSubtree？" + isSubtree(tree, subtree)); // true
    }
}
