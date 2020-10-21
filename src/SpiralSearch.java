import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个M*N的二维数组,请你按照顺(逆)时针顺序,返回数组中的所有元素.
 */
public class SpiralSearch {
    static final int M = (int) (Math.random() * 15 + 1);
    static final int N = (int) (Math.random() * 15 + 1);

    public static void main(String[] args) {
        System.out.println("M = " + M + ", N = " + N);
        int[][] twoDimen = Utils.genarateTwoDimenArray(M, N, 20);
        Utils.printTwoDimenArray(twoDimen, 4);

        SpiralSearch instance = new SpiralSearch();
        int[] result = instance.spiralOrder(twoDimen);
        System.out.println("result:");
        Utils.printIntegerArray(result);
    }

    /**
     * <ol>
     * <li>时间复杂度：O(mn)，其中 m 和 n 分别是输入矩阵的行数和列数。矩阵中的每个元素都要被访问一次。</li>
     *
     * <li>空间复杂度：O(1)。除了输出数组以外，空间复杂度是常数。</li>
     * </l>
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[] order = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    /**
     * <ol>
     * <li>时间复杂度：O(mn)，其中 m 和 n 分别是输入矩阵的行数和列数。矩阵中的每个元素都要被访问一次。</li>
     *
     * <li>空间复杂度：O(mn)。需要创建一个大小为 m×n 的矩阵 visited 记录每个位置是否被访问过。</li>
     * </ol>
     */
    public int[] spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int[] order = new int[total];
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order[i] = matrix[row][column];
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    private int getM(int M, int i) {
        return (i % 2 == 0) ? (i / 2) : (M - 1 - i / 2);
    }

    private int getN(int N, int j) {
        return (j % 2 != 0) ? (j / 2) : (N - 1 - j / 2);
    }
}
