import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * <p>
 * A Demo for CyclicBarrier
 * <p>
 * 试图让每匹马都打印自己，但是之后的显示顺序取决于任务管理器。CyclicBarrier使得每匹马都要执行为了向前移动所必需执行的所有工作，然后必须在栅栏处等待其他所有的马都准备完毕。
 * 当所有的马都向前移动时，CyclicBarrier将自动调用Runnable栅栏动作任务，按顺序显示马和终点线的位置。
 * 一旦所有的任务都越过了栅栏，它就会自动地为下一回合比赛做好准备。
 * @author HuangHao
 *
 */
public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    
    public HorseRace(int nHorses, final int pause) {
        //向CyclicBarrier提供一个“栅栏动作”，它是一个Runnable，当计数值到达0时自动执行——这是CyclicBarrier和CountDownLatch之间的另一个区别。
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            
            @Override
            public void run() {
                StringBuilder s = new StringBuilder();
                for(int i = 0; i < FINISH_LINE; i++) {
                    s.append("=");
                }
                System.out.println(s);
                
                for(Horse horse : horses) {
                    System.out.println(horse.tracks());
                }
                
                for(Horse horse : horses) {
                    if(horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + "won!");
                        exec.shutdownNow();
                        return;
                    }
                }
                
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        for(int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;
        if(args.length > 0) {
            int n = new Integer(args[0]);
            nHorses = n > 0 ? n : nHorses;
        }
        
        if(args.length > 1) {
            int p = new Integer(args[1]);
            pause = p > -1 ? p : pause;
        }
        new HorseRace(nHorses, pause);
    }

}

class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;
    
    public Horse(CyclicBarrier b) {
        barrier = b;
    }
    
    public synchronized int getStrides() { return strides; }
    
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    strides += rand.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String toString() { return "Horse "+id+" "; }
    
    public String tracks() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < getStrides(); i++) {
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }
}