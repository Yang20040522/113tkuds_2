// 題目：Remove Duplicates from Sorted Array
// 使用雙指針原地移除重複，返回唯一元素數量。

public class It_26_RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    // 測試
    public static void main(String[] args) {
        It_26_RemoveDuplicates solver = new It_26_RemoveDuplicates();

        int[] nums1 = {1, 1, 2};
        int k1 = solver.removeDuplicates(nums1);
        System.out.print("Output1: k=" + k1 + ", nums=[");
        for (int i = 0; i < k1; i++) System.out.print(nums1[i] + (i < k1-1 ? "," : ""));
        System.out.println("]"); // 預期 k=2, nums=[1,2]

        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        int k2 = solver.removeDuplicates(nums2);
        System.out.print("Output2: k=" + k2 + ", nums=[");
        for (int i = 0; i < k2; i++) System.out.print(nums2[i] + (i < k2-1 ? "," : ""));
        System.out.println("]"); // 預期 k=5, nums=[0,1,2,3,4]
    }
}

/*
解題思路：
- 陣列有序 → 重複元素相鄰。
- 使用快慢指針：
  fast：逐個掃描元素。
  slow：維護已確認的唯一元素尾端。
- 當 fast 發現新元素時，將其覆蓋到 slow+1 位置。
- slow+1 為唯一元素數量。
時間複雜度 O(n)，空間 O(1)。
*/
