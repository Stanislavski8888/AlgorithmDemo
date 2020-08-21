import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintInOrder {
    ReentrantLock mLock = new ReentrantLock();
    Condition condition1 = mLock.newCondition();
    Condition condition2 = mLock.newCondition();
    Condition condition3 = mLock.newCondition();

    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);


    public static void main(String[] args) {
        PrintInOrder instance = new PrintInOrder();
        Runnable runnable1 = new PrintRunnable("first");
        Runnable runnable2 = new PrintRunnable("second");
        Runnable runnable3 = new PrintRunnable("third");
        new Thread(() -> {
            try {
//                instance.first(runnable1);
//                instance.second(runnable2);
                instance.third(runnable3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                instance.first(runnable1);
//                instance.second(runnable2);
//                instance.third(runnable3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
//                instance.first(runnable1);
//                instance.third(runnable3);
                instance.second(runnable2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class PrintRunnable implements Runnable {
        private String threadName;

        PrintRunnable(String name) {
            threadName = name;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": " + threadName);
//            notifyAll();
        }
    }

    PrintInOrder() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJobDone.get() != 1) {
//            wait();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJobDone.get() != 1) {
//            wait();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}