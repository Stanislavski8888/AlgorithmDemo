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
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int M = matrix.length;
        int N = matrix[0].length;
        int min = Math.min(M, N);
        int round = min % 2 == 0 ? min / 2 : (min + 1) / 2;

        for (int i = 0; i != M / 2; i++) {
            int m = getM(M, i);
            int n = getN(N, i);
            for (int j = 0; j <= n; j++) {
                System.out.println(matrix[i][j]);
            }
            ++m;
            for (int k = m; k <= getM(M, m); k++) {
                System.out.println(matrix[k][n]);
            }
        }


        return result;
    }

    private int getM(int M, int i) {
        return (i % 2 == 0) ? (i / 2) : (M - 1 - i / 2);
    }

    private int getN(int N, int j) {
        return (j % 2 != 0) ? (j / 2) : (N - 1 - j / 2);
    }
}
