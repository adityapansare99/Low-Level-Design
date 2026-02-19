
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class TryLock_with_Timeout {

    static class Resource {
        final int id;
        final ReentrantLock lock = new ReentrantLock();

        public Resource(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {

        Resource r1 = new Resource(1);
        Resource r2 = new Resource(2);

        Runnable task1 = () -> tryTransfer(r1, r2);

        Runnable task2 = () -> tryTransfer(r2, r1);

        new Thread(task1, "T1").start();
        new Thread(task2, "T2").start();
    }

    public static void tryTransfer(Resource a, Resource b) {
        boolean acquiredA = false;
        boolean acquiredB = false;

        try {
            acquiredA = a.lock.tryLock(100, TimeUnit.MILLISECONDS);
            if (acquiredA) {
                System.out.println(Thread.currentThread().getName() + " locked Resource " + a.id);

                Thread.sleep(50);

                acquiredB = b.lock.tryLock(100, TimeUnit.MILLISECONDS);
                if (acquiredB) {
                    System.out.println(Thread.currentThread().getName() + " locked Resource " + b.id);
                    System.out.println("Transfer successful between " + a.id + " and " + b.id);
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not lock Resource " + b.id + " - backing off");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not lock Resource " + a.id + " - backing off");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            if (acquiredB) b.lock.unlock();
            if (acquiredA) a.lock.unlock();
        }
    }
}
