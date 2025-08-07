import java.util.Scanner;

public class TicTacToeBoard {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initBoard();
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        System.out.println("🎮 井字遊戲開始！");
        printBoard();

        while (!gameEnded) {
            System.out.println("玩家 " + currentPlayer + " 的回合。請輸入列與欄（0~2）：");
            System.out.print("列 (row)：");
            int row = scanner.nextInt();
            System.out.print("欄 (col)：");
            int col = scanner.nextInt();

            if (placeMark(row, col)) {
                printBoard();

                if (checkWin()) {
                    System.out.println("🎉 玩家 " + currentPlayer + " 獲勝！");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    System.out.println("🤝 平手！");
                    gameEnded = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("⚠️ 無效的位置，請重新輸入！");
            }
        }

        scanner.close();
    }

    // ✅ 初始化棋盤為空白
    public static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // ✅ 顯示棋盤
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // ✅ 嘗試放置棋子，成功回傳 true
    public static boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    // ✅ 切換玩家
    public static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // ✅ 檢查是否有玩家獲勝
    public static boolean checkWin() {
        return (checkRows() || checkCols() || checkDiagonals());
    }

    // 檢查三行是否有連線
    public static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    // 檢查三列是否有連線
    public static boolean checkCols() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' &&
                board[0][j] == board[1][j] &&
                board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    // 檢查兩條對角線是否有連線
    public static boolean checkDiagonals() {
        return (board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) ||
               (board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]);
    }

    // ✅ 檢查棋盤是否填滿（平手）
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }
}
