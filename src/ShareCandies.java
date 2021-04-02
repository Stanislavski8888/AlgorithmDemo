import utils.Utils;

/**
 * 初始有100(m)个糖果,有10(n)个小朋友围坐成一圈。老师给每个小朋友随机发偶数个糖果，然后进行下面的游戏：<br>
 * <pre>
 * 每个小朋友都把自己的糖果分一半给左手边的孩子。<br>
 * 一轮分糖后，拥有奇数颗糖的孩子由老师补给1个糖果，从而变成偶数。<br>
 * 反复进行这个游戏，直到所有小朋友的糖果数都相同为止。<br>
 * </pre>
 */
public class ShareCandies {
    static final int N = 10;

    public static void main(String[] args) {
        int origin = 100;
        int[] children = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == N - 1) {
                children[i] = origin;
            } else {
                children[i] = (int) (Math.random() * origin);
                origin -= children[i];
            }
        }
        children = new int[]{90, 10, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println("Original: ");
        Utils.printIntegerArray(children);

        ShareCandies instance = new ShareCandies();

        System.out.println("Begin: ");
        int[] result = instance.share(children);
        System.out.println("Final: ");
        Utils.printIntegerArray(result);
    }

    private int[] share(int[] children) {
        int length = children.length;
        int[] temp = new int[length];
        while (!isEqual(children)) {
            for (int i = 0; i < length; i++) {
                if (children[i % length] % 2 != 0) {
                    ++children[i];
                }
            }
            for (int i = 0; i < length; i++) {
                temp[i] = children[i] / 2;
            }
            for (int i = 0; i < length; i++) {
                children[i] = temp[i] + temp[(i - 1 + length) % length];
            }
            Utils.printIntegerArray(children);
        }
        return children;
    }

    private boolean isEqual(int[] children) {
        boolean equal = true;
        for (int i = 0; i < children.length - 1; i++) {
            equal &= (children[i] == children[i + 1]);
            if (!equal) {
                break;
            }
        }
        return equal;
    }

}