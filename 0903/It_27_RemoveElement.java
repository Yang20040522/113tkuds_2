// 題目：Remove Element
// 使用雙指針原地移除指定值，返回剩下元素數量。

public class It_27_RemoveElement {

    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    // 測試
    public static void main(String[] args) {
        It_27_RemoveElement solver = new It_27_RemoveElement();

        int[] nums1 = {3,2,2,3};
        int k1 = solver.removeElement(nums1, 3);
        System.out.print("Output1: k=" + k1 + ", nums=[");
        for (int i = 0; i < k1; i++) System.out.print(nums1[i] + (i < k1-1 ? "," : ""));
        System.out.println("]"); // 預期 k=2, nums=[2,2]

        int[] nums2 = {0,1,2,2,3,0,4,2};
        int k2 = solver.removeElement(nums2, 2);
        System.out.print("Output2: k=" + k2 + ", nums=[");
        for (int i = 0; i < k2; i++) System.out.print(nums2[i] + (i < k2-1 ? "," : ""));
        System.out.println("]"); // 預期 k=5, nums=[0,1,3,0,4] 或任意順序
    }
}

/*
解題思路：
1. 使用快慢指針：
   - i 掃描所有元素；
   - k 紀錄下一個有效元素存放位置。
2. 若 nums[i] != val，將 nums[i] 搬到 nums[k]，再 k++。
3. 結束後，k 即為保留元素數量，前 k 個元素是結果。
時間 O(n)，空間 O(1)。
*/
