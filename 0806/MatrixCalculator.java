public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("🔢 矩陣 A：");
        printMatrix(matrixA);

        System.out.println("🔢 矩陣 B：");
        printMatrix(matrixB);

        System.out.println("🔢 矩陣 C：");
        printMatrix(matrixC);

        System.out.println("📌 A + B：");
        printMatrix(addMatrices(matrixA, matrixB));

        System.out.println("📌 A x C：");
        printMatrix(multiplyMatrices(matrixA, matrixC));

        System.out.println("📌 A 的轉置：");
        printMatrix(transposeMatrix(matrixA));

        int[] maxMin = findMaxMin(matrixA);
        System.out.println("📈 A 的最大值：" + maxMin[0]);
        System.out.println("📉 A 的最小值：" + maxMin[1]);
    }

    // ✅ 矩陣加法（相同維度）
    public static int[][] addMatrices(int[][] m1, int[][] m2) {
        int rows = m1.length;
        int cols = m1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }

        return result;
    }

    // ✅ 矩陣乘法（m1 的列數與 m2 的欄數可乘）
    public static int[][] multiplyMatrices(int[][] m1, int[][] m2) {
        int rows1 = m1.length;
        int cols1 = m1[0].length;
        int cols2 = m2[0].length;

        int[][] result = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return result;
    }

    // ✅ 矩陣轉置（行列互換）
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                transposed[i][j] = matrix[j][i];
            }
        }

        return transposed;
    }

    // ✅ 找出最大值與最小值，回傳 [最大值, 最小值]
    public static int[] findMaxMin(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];

        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        }

        return new int[]{max, min};
    }

    // ✅ 印出矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
        System.out.println();
    }
}
