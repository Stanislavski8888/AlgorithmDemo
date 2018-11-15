import java.util.concurrent.Semaphore;


/***
 * <b>需求</b>：有三个线程轮流执行，第一个线程打印A，第二个线程打印B，第三个线程打印C……循环10次
 * @author HuangHao
 *
 */
public class PrintABC {
    public static final int COUNT = 10;

    public static void main(String[] args) {
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);
        new Thread(new SemaphoreRunnable("A", semaphoreA, semaphoreB)).start();
        new Thread(new SemaphoreRunnable("B", semaphoreB, semaphoreC)).start();
        new Thread(new SemaphoreRunnable("C", semaphoreC, semaphoreA)).start();
    }
}

/***
 * <p>思路：三个线程对应三个Semaphore，三个Semaphore维护一个Permit。当前线程通过对应的Semaphore获取Permit，执行打印，并通过下一个线程对应的Semaphore释放Permit。
 * 类似于Permit在当前的线程对应的Semaphore中，传递到了下一个线程对应的Semaphore中。下一个线程通过对应的Semaphore获取Permit，继续执行……循环10次。
 * <p>
 * 效率：每个线程使用一个Semaphore，一个Permit在不同的Semaphore之间循环传递，当前线程消费完Permit后，无法立即进行下一次打印，而下一个线程使用的Semaphore刚好获取到了
 * Permit，从而使线程可以交替执行。不需要额外的线程轮流状态state字段。代码简洁，效率高。
 *
 * @author HuangHao
 *
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
                acquireSemaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ": " + pString);
                releaseSemaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
}
