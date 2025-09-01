import java.util.HashMap;
import java.util.Map;

public class lt_01_twosum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // 用來記錄數字與索引
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 補數
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i}; // 找到答案，回傳索引
            }
            map.put(nums[i], i); // 把當前數字和索引存進map
        }
        throw new IllegalArgumentException("No two sum solution"); // 找不到答案時丟錯誤
    }

    // 測試用 main
    public static void main(String[] args) {
        lt_01_twosum solution = new lt_01_twosum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println("Output: [" + result[0] + ", " + result[1] + "]");
    }
}
