import java.util.*;

public class RecursiveTreePreview {

    // === 模擬檔案系統 ===
    static class FileNode {
        String name;
        boolean isFile;
        List<FileNode> children;

        FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            this.children = new ArrayList<>();
        }

        void add(FileNode child) {
            if (!this.isFile) children.add(child);
        }
    }

    // ✅ 1. 遞迴計算資料夾中總檔案數
    public static int countFiles(FileNode node) {
        if (node.isFile) return 1;

        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    // ✅ 2. 遞迴列印多層選單結構
    public static void printMenu(MenuItem item, int depth) {
        System.out.println("  ".repeat(depth) + "- " + item.title);
        for (MenuItem child : item.subItems) {
            printMenu(child, depth + 1);
        }
    }

    static class MenuItem {
        String title;
        List<MenuItem> subItems = new ArrayList<>();

        MenuItem(String title) {
            this.title = title;
        }

        void add(MenuItem item) {
            subItems.add(item);
        }
    }

    // ✅ 3. 遞迴展平巢狀陣列
    public static List<Object> flatten(Object[] arr) {
        List<Object> result = new ArrayList<>();
        for (Object item : arr) {
            if (item instanceof Object[]) {
                result.addAll(flatten((Object[]) item));
            } else {
                result.add(item);
            }
        }
        return result;
    }

    // ✅ 4. 遞迴計算巢狀清單的最大深度
    public static int maxDepth(Object[] arr) {
        int depth = 1;
        for (Object item : arr) {
            if (item instanceof Object[]) {
                depth = Math.max(depth, 1 + maxDepth((Object[]) item));
            }
        }
        return depth;
    }

    // === 測試主程式 ===
    public static void main(String[] args) {
        // 1️⃣ 檔案系統模擬
        FileNode root = new FileNode("root", false);
        root.add(new FileNode("file1.txt", true));
        FileNode folder = new FileNode("folder", false);
        folder.add(new FileNode("file2.txt", true));
        folder.add(new FileNode("file3.txt", true));
        root.add(folder);
        System.out.println("📁 總檔案數：" + countFiles(root)); // 應為 3

        // 2️⃣ 多層選單列印
        MenuItem mainMenu = new MenuItem("主選單");
        MenuItem sub1 = new MenuItem("子選單1");
        MenuItem sub2 = new MenuItem("子選單2");
        sub1.add(new MenuItem("子選單1-1"));
        sub1.add(new MenuItem("子選單1-2"));
        sub2.add(new MenuItem("子選單2-1"));
        mainMenu.add(sub1);
        mainMenu.add(sub2);
        System.out.println("\n📋 多層選單結構：");
        printMenu(mainMenu, 0);

        // 3️⃣ 巢狀陣列展平
        Object[] nestedArray = {1, new Object[]{2, 3}, new Object[]{new Object[]{4}}, 5};
        List<Object> flattened = flatten(nestedArray);
        System.out.println("\n🪜 展平後：" + flattened);  // [1, 2, 3, 4, 5]

        // 4️⃣ 巢狀清單最大深度
        Object[] deepArray = {1, new Object[]{2, new Object[]{3, new Object[]{4}}}};
        System.out.println("🧭 最大深度：" + maxDepth(deepArray));  // 應為 4
    }
}
