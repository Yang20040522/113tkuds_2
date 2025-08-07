import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] data1 = {29, 10, 14, 37, 13};
        int[] data2 = data1.clone(); // 複製一份給 Bubble Sort 使用

        System.out.println("📌 原始資料：" + Arrays.toString(data1));

        System.out.println("\n🔽 選擇排序過程：");
        selectionSort(data1);

        System.out.println("\n🔁 氣泡排序過程：");
        bubbleSort(data2);
    }

    // ✅ 選擇排序實作
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int compareCount = 0;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                compareCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(arr, i, minIndex);
                swapCount++;
            }

            System.out.println("第 " + (i + 1) + " 輪：" + Arrays.toString(arr));
        }

        System.out.println("✅ 比較次數：" + compareCount);
        System.out.println("✅ 交換次數：" + swapCount);
    }

    // ✅ 氣泡排序實作
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int compareCount = 0;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                compareCount++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapCount++;
                    swapped = true;
                }
            }

            System.out.println("第 " + (i + 1) + " 輪：" + Arrays.toString(arr));
            if (!swapped) break; // 提早結束
        }

        System.out.println("✅ 比較次數：" + compareCount);
        System.out.println("✅ 交換次數：" + swapCount);
    }

    // ✅ 交換兩元素
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
