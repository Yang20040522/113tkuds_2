import java.util.*;

public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 8, 5, 3};
        System.out.println("🔢 原始陣列：" + Arrays.toString(arr));

        // 快速排序
        quickSort(arr, 0, arr.length - 1);
        System.out.println("✅ 快速排序後：" + Arrays.toString(arr));

        // 合併兩個已排序陣列
        int[] sorted1 = {1, 3, 5};
        int[] sorted2 = {2, 4, 6, 8};
        int[] merged = mergeSortedArraysRecursive(sorted1, sorted2, 0, 0, new ArrayList<>());
        System.out.println("✅ 合併後：" + Arrays.toString(merged));

        // 第 k 小元素
        int[] sample = {9, 1, 5, 3, 7, 4};
        int k = 3;
        int kth = quickSelect(sample, 0, sample.length - 1, k);
        System.out.println("🎯 第 " + k + " 小元素為：" + kth);

        // 子序列總和是否等於目標值
        int[] subsetArray = {3, 34, 4, 12, 5, 2};
        int target = 9;
        boolean hasSubset = subsetSum(subsetArray, target, 0);
        System.out.println("🔍 是否存在總和為 " + target + " 的子序列？" + hasSubset);
    }

    // ✅ 快速排序（QuickSort）
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high); // pivot index
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // 助手：快速排序的分割
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // 最右邊為 pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // 工具：交換元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    // ✅ 遞迴合併已排序陣列
    public static int[] mergeSortedArraysRecursive(int[] a, int[] b, int i, int j, List<Integer> merged) {
        if (i == a.length) {
            while (j < b.length) merged.add(b[j++]);
        } else if (j == b.length) {
            while (i < a.length) merged.add(a[i++]);
        } else if (a[i] < b[j]) {
            merged.add(a[i]);
            mergeSortedArraysRecursive(a, b, i + 1, j, merged);
        } else {
            merged.add(b[j]);
            mergeSortedArraysRecursive(a, b, i, j + 1, merged);
        }
        return merged.stream().mapToInt(Integer::intValue).toArray();
    }

    // ✅ 快速選擇：找第 k 小元素（類似 quick sort）
    public static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivotIndex = partition(arr, left, right);
        int rank = pivotIndex - left + 1;

        if (k == rank) return arr[pivotIndex];
        else if (k < rank) return quickSelect(arr, left, pivotIndex - 1, k);
        else return quickSelect(arr, pivotIndex + 1, right, k - rank);
    }

    // ✅ 遞迴檢查是否存在子序列總和 = target
    public static boolean subsetSum(int[] arr, int target, int index) {
        if (target == 0) return true;
        if (index >= arr.length || target < 0) return false;

        // 選取 或 不選取當前元素
        return subsetSum(arr, target - arr[index], index + 1) ||
               subsetSum(arr, target, index + 1);
    }
}
