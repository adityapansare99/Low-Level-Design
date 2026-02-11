
class Counter {
    private int count = 0;

    public void increseCounter() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Race_condition {

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
