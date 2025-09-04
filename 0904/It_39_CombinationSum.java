import java.util.*;

public class It_39_CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            if (val > remain) break;         // 剪枝
            path.add(val);
            backtrack(nums, i, remain - val, path, res); // 可重複使用 i
            path.remove(path.size() - 1);
        }
    }

    // 簡單測試
    public static void main(String[] args) {
        It_39_CombinationSum s = new It_39_CombinationSum();

        System.out.println(s.combinationSum(new int[]{2,3,6,7}, 7));   // [[2,2,3],[7]]
        System.out.println(s.combinationSum(new int[]{2,3,5}, 8));     // [[2,2,2,2],[2,3,3],[3,5]]
        System.out.println(s.combinationSum(new int[]{2}, 1));         // []
        System.out.println(s.combinationSum(new int[]{8,7,4,3}, 11));  // [[3,4,4],[3,8],[4,7]]
    }
}

/*
解題思路（VSCode 版簡述）：
- 排序 + 回溯：固定從索引 start 向右取，避免重複排列。
- 可重複選取某數 → 遞迴呼叫仍從 i 開始。
- 超過 remain 即剪枝。
*/
