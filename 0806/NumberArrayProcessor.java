import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] array1 = {4, 2, 2, 5, 3, 3, 1, 4};
        int[] sorted1 = {1, 3, 5, 7};
        int[] sorted2 = {2, 4, 6, 8};

        System.out.println("🔢 原始陣列：" + Arrays.toString(array1));
        System.out.println("✅ 移除重複後：" + Arrays.toString(removeDuplicates(array1)));

        System.out.println("🔀 合併已排序陣列：");
        System.out.println("陣列1：" + Arrays.toString(sorted1));
        System.out.println("陣列2：" + Arrays.toString(sorted2));
        System.out.println("合併後：" + Arrays.toString(mergeSortedArrays(sorted1, sorted2)));

        System.out.println("🔥 出現頻率最高的元素是：" + findMostFrequentElement(array1));

        System.out.println("🔗 分割成兩個總和最接近的子陣列：");
        splitArrayClosestSum(array1);
    }

    // ✅ 移除陣列中的重複元素（保持原順序）
    public static int[] removeDuplicates(int[] arr) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    // ✅ 合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) merged[k++] = arr1[i++];
        while (j < arr2.length) merged[k++] = arr2[j++];

        return merged;
    }

    // ✅ 找出陣列中出現最多次的元素
    public static int findMostFrequentElement(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxCount = 0;
        int mostFrequent = arr[0];

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequent = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostFrequent;
    }

    // ✅ 將陣列分成兩個子陣列，使總和最接近
    public static void splitArrayClosestSum(int[] arr) {
        Arrays.sort(arr);  // 排序讓分配更平均
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();

        int sum1 = 0, sum2 = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                group1.add(arr[i]);
                sum1 += arr[i];
            } else {
                group2.add(arr[i]);
                sum2 += arr[i];
            }
        }

        System.out.println("子陣列1：" + group1 + " → 總和 = " + sum1);
        System.out.println("子陣列2：" + group2 + " → 總和 = " + sum2);
    }
}
