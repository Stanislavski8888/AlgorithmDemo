import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static final AtomicInteger ID = new AtomicInteger(0);


    public static void main(String[] args) {
//        System.out.println(ID.get());
//        System.out.println(ID.getAndIncrement());
//        System.out.println(ID.getAndAdd(10));
//        System.out.println(ID.incrementAndGet());
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Increment());
            thread.start();
        }
    }

    static class Increment implements Runnable {
        ThreadLocal<AtomicInteger> local = new ThreadLocal<>();

        @Override
        public void run() {
            int i = 0;
            while (i <= 100) {
                System.out.println(Thread.currentThread().getId() + " : " + ID.incrementAndGet());
                i++;
                /*try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
}