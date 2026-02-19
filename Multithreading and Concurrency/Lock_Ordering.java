import java.util.*;

class Lock_Ordering {

    static class Resource {
        int id;
        int value;

        public Resource(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        Resource r1 = new Resource(101, 500);
        Resource r2 = new Resource(102, 300);

        Runnable task1 = () -> transfer(r1, r2, 50);

        Runnable task2 = () -> transfer(r2, r1, 30);

        new Thread(task1, "T1").start();
        new Thread(task2, "T2").start();
    }

    public static void transfer(Resource a, Resource b, int amount) {

        Resource[] locks = new Resource[] {a, b};

        Arrays.sort(locks, (x, y) -> Integer.compare(x.id, y.id));

        synchronized (locks[0]) {
            System.out.println(Thread.currentThread().getName() + " locked " + locks[0].id);

            try {
                Thread.sleep(50); 
            } catch (InterruptedException ignored) {}

            synchronized (locks[1]) {
                System.out.println(Thread.currentThread().getName() + " locked " + locks[1].id);
                System.out.println("Transferred " + amount + " from " + a.id + " to " + b.id);
            }
        }
    }
}