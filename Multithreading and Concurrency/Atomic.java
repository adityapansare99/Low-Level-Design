import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increseCounter() {
        int prev;
        int next;

        do { 
            prev=count.get();
            next=prev+1;
        } while (!count.compareAndSet(prev, next));
    }

    public int getCount(){
        return count.get();
    }
}

public class Atomic {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increseCounter();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(counter.getCount());
    }
}
