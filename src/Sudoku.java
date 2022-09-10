import java.util.Random;

public class Sudoku {

    private static int[] num = new int[81];
    private static Random random = new Random();

    /**
     * 随机产生一个数独
     * @return 返回一个随机产生的数独
     */
    public static int[] generate() {
        for (int i = 0; i < 81; i++) {
            num[i] = 0;
        }
        solve(0);
        return num;
    }

    /**
     * 递归产生数独位置i的值
     * @param i 数独位置i
     * @return 位置i是否可以填入值
     */
    private static boolean solve(int i) {
        /* 如果已经填满81个格子则返回true */
        if (i == 81) {
            return true;
        }
        /* 如果位置i已经填入了合适的值则递归产生下一个位置的值 */
        else if (num[i] != 0) {
            return solve(i + 1);
        }
        /* 如果恰好需要填入位置i的值 */
        else {
            /* 用数组randOrder存储每个位置可能产生的值，即为1~9 */
            int[] randOrder = new int[10];
            for (int val = 1; val < 10; val++) {
                randOrder[val] = val;
            }

            /* 将数组randOrder变为一个随机存储1~9的数组 */
            for (int val = 1; val < 10; val++) {
                int rand = random.nextInt(10);
                int tmp = randOrder[rand];
                randOrder[rand] = randOrder[val];
                randOrder[val] = tmp;
            }

            /* 在位置i随机填入一个值，并且判断是否有效 */
            for (int val = 1; val < 10; val++) {
                /* 如果在位置i填入的1~9中的某个随机数有效 */
                if (isLegal(i, randOrder[val])) {
                    /* 则将此随机值放入位置i */
                    num[i] = randOrder[val];
                    /* 探索i的下一个位置是否能正确填入，如果可以则返回true */
                    if (solve(i + 1)) {
                        return true;
                    }
                }
            }
        }

        /* 如果在位置i不能填入1~9中的任何值，则需要回溯 */
        num[i] = 0;
        return false;
    }

    /**
     * 在位置i填入value数字是否有效，通过按行列和小矩阵判断
     * @param i 填入的位置i
     * @param value 填入位置i的数字value
     * @return 在位置i填入数字value是否有效
     */
    private static boolean isLegal(int i, int value) {
        /* 判断行是否有效 */
        if (!isRowLegal(i, value)) {
            return false;
        }
        /* 判断列是否有效 */
        if (!isColLegal(i, value)) {
            return false;
        }
        /* 判断小矩阵是否有效 */
        if (!isSubLegal(i, value)) {
            return false;
        }

        return true;
    }

    /**
     * 判断在位置i填入value行规则是否满足
     * @param i 填入的位置i
     * @param value 填入位置i的数字value
     * @return 在位置i填入数字value行规则是否有效
     */
    private static boolean isRowLegal(int i, int value) {
        int row = i / 9;
        for (int val = 0; val < 9; val++) {
            if (value == num[row * 9 + val]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断在位置i填入value列规则是否满足
     * @param i 填入的位置i
     * @param value 填入位置i的数字value
     * @return 在位置i填入数字value列规则是否有效
     */
    private static boolean isColLegal(int i, int value) {
        int col = i % 9;
        for (int val = 0; val < 9; val++) {
            if (value == num[val * 9 + col]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断在位置i填入value小矩阵规则是否满足
     * @param i 填入的位置i
     * @param value 填入位置i的数字value
     * @return 在位置i填入数字value小矩阵规则是否有效
     */
    private static boolean isSubLegal(int i, int value) {
        int row = i / 9;
        int col = i % 9;
        int xOff = row / 3 * 3;
        int yOff = col / 3 * 3;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (value == num[(xOff + x) * 9 + yOff + y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid = new int[9][9];
        int[] sudoku = new int[81];

        for (int i = 0; i < 81; i++) {
            sudoku[i] = 0;
        }
        sudoku = generate();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = sudoku[i * 9 + j];
            }
        }

        System.out.println("-----------------------");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
                if (j % 3 == 2) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i % 3 == 2) {
                System.out.println("-----------------------");
            }
        }
    }
}