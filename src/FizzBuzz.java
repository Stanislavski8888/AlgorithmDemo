import java.util.function.IntConsumer;

/**
 * <a href="https://leetcode-cn.com/problems/fizz-buzz-multithreaded/">1195. 交替打印字符串</a>
 * <p>
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * <ul>
 *     <li>如果这个数字可以被 3 整除，输出 "fizz"。</li>
 *     <li>如果这个数字可以被 5 整除，输出 "buzz"。</li>
 *     <li>如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。</li>
 * </ul>
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 * <p>
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 * <ol>
 *     <li>线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。</li>
 *     <li>线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。</li>
 *     <li>线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。</li>
 *     <li>线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。</li>
 * </ol>
 *
 */
public class FizzBuzz {
    public static final int N = 15;
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    static class FizzBuzzRunnable implements Runnable {
        String name;
        FizzBuzzRunnable(String n) {
            name = n;
        }

        @Override
        public void run() {
            System.out.print(name + ", ");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(N);
        for (int i = 1; i <= fizzBuzz.n; i++) {
            if (fizzBuzz.n % 15 == 0) {
                fizzBuzz.fizzbuzz(new FizzBuzzRunnable("fizzbuzz"));
            } else if (fizzBuzz.n % 5 == 0) {
                fizzBuzz.buzz(new FizzBuzzRunnable("buzz"));
            } else if (fizzBuzz.n % 3 == 0) {
                fizzBuzz.fizz(new FizzBuzzRunnable("fizz"));
            } else {

            }
        }
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        new Thread(printFizz).start();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        new Thread(printBuzz).start();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        new Thread(printFizzBuzz).start();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        printNumber.accept(n);
        System.out.print(n + ", ");
    }
}
