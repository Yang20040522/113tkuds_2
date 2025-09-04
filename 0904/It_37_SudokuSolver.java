// 題目：Sudoku Solver
// 版本：VSCode 可直接編譯執行。回溯 + 位元遮罩 + MRV。

public class It_37_SudokuSolver {

    private int[] rows = new int[9], cols = new int[9], boxes = new int[9];
    // 修正這裡 → 二維陣列，每個空格存 row 與 col
    private int[][] blanks = new int[81][2];
    private int blankCnt = 0;

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) rows[i] = cols[i] = boxes[i] = 0;
        blankCnt = 0;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') {
                    blanks[blankCnt][0] = r;
                    blanks[blankCnt][1] = c;
                    blankCnt++;
                } else {
                    int d = ch - '0';
                    int bit = 1 << d;
                    int b = (r / 3) * 3 + (c / 3);
                    rows[r] |= bit;
                    cols[c] |= bit;
                    boxes[b] |= bit;
                }
            }
        }
        dfs(board);
    }

    private boolean dfs(char[][] board) {
        if (blankCnt == 0) return true;

        int pick = -1, bestMask = 0, bestCount = 10;
        for (int i = 0; i < blankCnt; i++) {
            int r = blanks[i][0], c = blanks[i][1];
            int b = (r / 3) * 3 + (c / 3);
            int used = rows[r] | cols[c] | boxes[b];
            int cand = (~used) & 0x3FE;  // 可用數字
            int cnt = Integer.bitCount(cand);
            if (cnt == 0) return false;
            if (cnt < bestCount) {
                bestCount = cnt;
                bestMask = cand;
                pick = i;
                if (cnt == 1) break;
            }
        }

        int r = blanks[pick][0], c = blanks[pick][1];
        blanks[pick][0] = blanks[blankCnt - 1][0];
        blanks[pick][1] = blanks[blankCnt - 1][1];
        blankCnt--;

        int b = (r / 3) * 3 + (c / 3);
        for (int cand = bestMask; cand != 0; cand &= (cand - 1)) {
            int bit = cand & -cand;
            int d = Integer.numberOfTrailingZeros(bit);

            rows[r] |= bit; cols[c] |= bit; boxes[b] |= bit;
            board[r][c] = (char)('0' + d);

            if (dfs(board)) return true;

            rows[r] ^= bit; cols[c] ^= bit; boxes[b] ^= bit;
            board[r][c] = '.';
        }

        blanks[blankCnt][0] = r;
        blanks[blankCnt][1] = c;
        blankCnt++;
        return false;
    }

    private static void print(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("+-------+-------+-------+");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+");
    }

    public static void main(String[] args) {
        It_37_SudokuSolver solver = new It_37_SudokuSolver();
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println("Before:");
        print(board);

        solver.solveSudoku(board);

        System.out.println("After:");
        print(board);
    }
}
