import java.util.*;

public class It_40_CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int start, int remain,
                           List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            int val = nums[i];
            if (val > remain) break;                    // 剪枝
            if (i > start && nums[i] == nums[i - 1])    // 同層去重
                continue;

            path.add(val);
            backtrack(nums, i + 1, remain - val, path, res); // 每個元素最多一次
            path.remove(path.size() - 1);
        }
    }

    // 簡單測試
    public static void main(String[] args) {
        It_40_CombinationSumII s = new It_40_CombinationSumII();

        System.out.println(s.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        // [[1,1,6],[1,2,5],[1,7],[2,6]]

        System.out.println(s.combinationSum2(new int[]{2,5,2,1,2}, 5));
        // [[1,2,2],[5]]

        System.out.println(s.combinationSum2(new int[]{1,1,1,2}, 3));
        // [[1,2]]
    }
}

/*
解題思路（VSCode 版摘要）：
- 排序 → val > remain 就 break；也能使用「同層去重」：
  在 for 迴圈內，若 i>start 且 nums[i]==nums[i-1]，略過當前 i。
- 與第 39 題不同之處：只能用一次 → 遞迴呼叫用 i+1。
*/
