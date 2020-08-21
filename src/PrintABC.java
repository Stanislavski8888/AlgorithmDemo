import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/***
 * <b>需求</b>：有三个线程轮流执行，第一个线程打印A，第二个线程打印B，第三个线程打印C……循环10次
 * @author HuangHao
 *
 */
public class PrintABC {
    public static final int COUNT = 10;
    public static int state = 0;

    public static void main(String[] args) {
        //用Semaphore方式打印
//        Semaphore semaphoreA = new Semaphore(1);
//        Semaphore semaphoreB = new Semaphore(0);
//        Semaphore semaphoreC = new Semaphore(0);
//        new Thread(new SemaphoreRunnable("A", semaphoreA, semaphoreB)).start();
//        new Thread(new SemaphoreRunnable("B", semaphoreB, semaphoreC)).start();
//        new Thread(new SemaphoreRunnable("C", semaphoreC, semaphoreA)).start();

        //用synchronized方式打印
//        Object a = new Object();
//        Object b = new Object();
//        Object c = new Object();
//        new Thread(new SyncRunnable("A", c, a)).start();    //如果不加Thread.sleep()无法保证初始ABC的启动顺序
//        new Thread(new SyncRunnable("B", a, b)).start();
//        new Thread(new SyncRunnable("C", b, c)).start();

        //用Lock的方式打印，表面看是顺序打印，实际并不是由各线程分别各自打印ABC
//        Lock lock = new ReentrantLock();
//        new Thread(new LockRunnable(lock)).start();
//        new Thread(new LockRunnable(lock)).start();
//        new Thread(new LockRunnable(lock)).start();

        //用Lock & Condition的方式打印
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        new Thread(new LockConditionRunnable("A", 0, lock, conditionA, conditionB)).start();
        new Thread(new LockConditionRunnable("B", 1, lock, conditionB, conditionC)).start();
        new Thread(new LockConditionRunnable("C", 2, lock, conditionC, conditionA)).start();
    }
}

/***
 * <p>思路：三个线程对应三个Semaphore，三个Semaphore维护一个Permit。当前线程通过对应的Semaphore获取Permit，执行打印，并通过下一个线程对应的Semaphore释放Permit。
 * 类似于Permit在当前的线程对应的Semaphore中，传递到了下一个线程对应的Semaphore中。下一个线程通过对应的Semaphore获取Permit，继续执行……循环10次。
 * <p>
 * 效率：每个线程使用一个Semaphore，一个Permit在不同的Semaphore之间循环传递，当前线程消费完Permit后，无法立即进行下一次打印，而下一个线程使用的Semaphore刚好获取到了
 * Permit，从而使线程可以交替执行。不需要额外的线程轮流状态state字段。代码简洁，效率高。
 */
class SemaphoreRunnable implements Runnable {
    private String pString;
    private Semaphore acquireSemaphore;
    private Semaphore releaseSemaphore;

    public SemaphoreRunnable(String str, Semaphore acquire, Semaphore release) {
        pString = str;
        acquireSemaphore = acquire;
        releaseSemaphore = release;
    }

    @Override
    public void run() {
        for(int i = 0; i < PrintABC.COUNT; i++) {
            try {
//                System.out.println(pString+": "+acquireSemaphore + "," + releaseSemaphore);
                acquireSemaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ": " + pString);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                releaseSemaphore.release();
            }
        }
    }
}

/**
 * 定义了a,b,c三个对象锁，分别对应A、B、C三个线程。<br>
 * A线程最先运行，A线程按顺序申请c,a对象锁，打印操作后按顺序释放a,c对象锁，并且通过notify操作唤醒线程B。<br>
 * 线程B首先等待获取A锁，再申请B锁，后打印B，再释放B，A锁，唤醒C。<br>
 * 线程C等待B锁，再申请C锁，后打印C，再释放C,B锁，唤醒A。
 *
 */
class SyncRunnable implements Runnable {
    private String name;
    private Object prev;
    private Object self;

    public SyncRunnable(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = PrintABC.COUNT;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.println(Thread.currentThread().getName() + ": " + name);
                    count--;

                    self.notifyAll();   // 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                }

                // 此时执行完self的同步块，这时self锁才释放。
                try {
                    if (count == 0) {   // 如果count==0,表示这是最后一次打印操作，通过notifyAll操作释放对象锁。
                        prev.notifyAll();
                    } else {
                        prev.wait();    // 立即释放 prev锁，当前线程休眠，等待唤醒
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


/***
 * 这种方法看起来是顺序打印ABC，但并不是分别由各个线程单独打印
 *
 */
class LockRunnable implements Runnable {
    private Lock lock;

    public LockRunnable(Lock lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        for (int i = 0; i < PrintABC.COUNT; i++) {
            try {
                lock.lock();
                switch (PrintABC.state % 3) {
                case 0:
                    System.out.println(Thread.currentThread().getName() + ": A");
                    break;
                case 1:
                    System.out.println(Thread.currentThread().getName() + ": B");
                    break;
                case 2:
                    System.out.println(Thread.currentThread().getName() + ": C");
                    break;
                }
                PrintABC.state++;
            } finally {
                lock.unlock();
            }
        }
    }
}

/***
 * 有问题。。。。
 *
 */
class LockConditionRunnable implements Runnable {
    private Lock lock;
    private String name;
    private final int STATE;
    private Condition awaitCond;
    private Condition signalCond;


    public LockConditionRunnable(String name, int state, Lock lock, Condition await, Condition signal) {
        this.name = name;
        this.lock = lock;
        awaitCond = await;
        signalCond = signal;
        this.STATE = state;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for(int i = 0; i < PrintABC.COUNT; i++) {
                if (PrintABC.state % 3 != STATE) {
                    awaitCond.await();
                }
                System.out.println(Thread.currentThread().getName() + ": "+ name);
                PrintABC.state++;
                signalCond.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
