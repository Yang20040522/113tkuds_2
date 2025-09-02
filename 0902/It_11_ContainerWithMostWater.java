// 題目：Container With Most Water
// 給定一個整數陣列 height，代表不同座標位置的垂直線高度。
// 請找出兩條線，與 x 軸構成的容器能裝最多的水，回傳這個最大容量。

public class It_11_ContainerWithMostWater {

    // 解題核心方法
    public int maxArea(int[] height) {
        int left = 0;                   // 左指標，從陣列開頭開始
        int right = height.length - 1;  // 右指標，從陣列尾端開始
        int maxArea = 0;                // 用來記錄最大容量

        // 雙指標向內移動，直到相遇為止
        while (left < right) {
            // 計算當前容器容量
            int h = Math.min(height[left], height[right]); // 高度由較矮的一邊決定
            int width = right - left;                      // 寬度為兩指標距離
            int area = h * width;                          // 容量 = 高度 * 寬度
            maxArea = Math.max(maxArea, area);             // 更新最大容量

            // 移動較矮的指標，因為移動較高邊不可能增加容量
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea; // 回傳最大容量
    }

    // 主程式：提供測試範例
    public static void main(String[] args) {
        It_11_ContainerWithMostWater solution = new It_11_ContainerWithMostWater();

        // 範例 1
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Input: [1,8,6,2,5,4,8,3,7]");
        System.out.println("Output: " + solution.maxArea(height1)); // 預期 49

        // 範例 2
        int[] height2 = {1, 1};
        System.out.println("Input: [1,1]");
        System.out.println("Output: " + solution.maxArea(height2)); // 預期 1

        // 自訂範例
        int[] height3 = {4, 3, 2, 1, 4};
        System.out.println("Input: [4,3,2,1,4]");
        System.out.println("Output: " + solution.maxArea(height3)); // 預期 16
    }
}

/*
解題思路：
1. 容器的容量取決於「寬度」和「高度」，其中高度由兩邊較小的值決定。
2. 使用雙指標法：
   - 初始化 left = 0（最左邊）、right = n-1（最右邊）。
   - 每次計算當前容量，並更新最大值。
   - 移動高度較小的指標，因為只移動短邊才有可能找到更大的容量。
3. 重複以上步驟直到兩指標相遇，答案即為最大容量。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
