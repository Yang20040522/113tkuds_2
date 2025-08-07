public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        double average = calculateAverage(scores);
        int max = findMax(scores);
        int min = findMin(scores);
        int aboveAverageCount = countAboveAverage(scores, average);
        int[] gradeCounts = countGrades(scores);

        System.out.println("📊 成績統計報表");
        System.out.println("-----------------------");
        printScoresWithGrades(scores);
        System.out.println("-----------------------");
        System.out.printf("平均分數：%.2f\n", average);
        System.out.println("最高分： " + max);
        System.out.println("最低分： " + min);
        System.out.println("高於平均分的人數： " + aboveAverageCount);
        System.out.println("等第統計：");
        System.out.println("A (90-100)： " + gradeCounts[0]);
        System.out.println("B (80-89) ： " + gradeCounts[1]);
        System.out.println("C (70-79) ： " + gradeCounts[2]);
        System.out.println("D (60-69) ： " + gradeCounts[3]);
        System.out.println("F (<60)   ： " + gradeCounts[4]);
    }

    // 計算平均
    public static double calculateAverage(int[] scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.length;
    }

    // 找最高分
    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int score : scores) {
            if (score > max) max = score;
        }
        return max;
    }

    // 找最低分
    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int score : scores) {
            if (score < min) min = score;
        }
        return min;
    }

    // 統計各等第人數（A, B, C, D, F）
    public static int[] countGrades(int[] scores) {
        int[] counts = new int[5]; // A, B, C, D, F
        for (int score : scores) {
            if (score >= 90) counts[0]++;
            else if (score >= 80) counts[1]++;
            else if (score >= 70) counts[2]++;
            else if (score >= 60) counts[3]++;
            else counts[4]++;
        }
        return counts;
    }

    // 統計高於平均分人數
    public static int countAboveAverage(int[] scores, double average) {
        int count = 0;
        for (int score : scores) {
            if (score > average) count++;
        }
        return count;
    }

    // 列印每位學生的分數與等第
    public static void printScoresWithGrades(int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            char grade = getGrade(scores[i]);
            System.out.println("學生 " + (i + 1) + "：分數 = " + scores[i] + "，等第 = " + grade);
        }
    }

    // 根據分數回傳等第
    public static char getGrade(int score) {
        if (score >= 90) return 'A';
        else if (score >= 80) return 'B';
        else if (score >= 70) return 'C';
        else if (score >= 60) return 'D';
        else return 'F';
    }
}
