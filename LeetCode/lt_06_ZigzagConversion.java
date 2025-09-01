public class lt_06_ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        lt_06_ZigzagConversion converter = new lt_06_ZigzagConversion();

        // Example 1
        String input1 = "PAYPALISHIRING";
        int rows1 = 3;
        System.out.println("Input: \"" + input1 + "\", numRows = " + rows1);
        System.out.println("Output: \"" + converter.convert(input1, rows1) + "\"");

        // Example 2
        String input2 = "PAYPALISHIRING";
        int rows2 = 4;
        System.out.println("Input: \"" + input2 + "\", numRows = " + rows2);
        System.out.println("Output: \"" + converter.convert(input2, rows2) + "\"");

        // Example 3
        String input3 = "A";
        int rows3 = 1;
        System.out.println("Input: \"" + input3 + "\", numRows = " + rows3);
        System.out.println("Output: \"" + converter.convert(input3, rows3) + "\"");
    }
}
