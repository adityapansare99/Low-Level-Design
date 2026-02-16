
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class ExpiringLock {

    private final ReentrantLock lock = new ReentrantLock();
    private final ScheduledExecutorService schedular = Executors.newSingleThreadScheduledExecutor();

    private volatile boolean isLocked = false;

    public boolean tryLockWithExpiry(long miliseconds) {
        boolean acquire = lock.tryLock();

        if (acquire) {
            schedular.schedule(() -> {
                if (isLocked || lock.isHeldByCurrentThread()) {
                    System.out.println("Lock Expired");
                    unlockSafely();
                }
            }, miliseconds, TimeUnit.MILLISECONDS);
        }

        return acquire;
    }

    public void unlockSafely() {
        if (lock.isHeldByCurrentThread() || isLocked) {
            isLocked = false;
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                System.out.println("Lock released");
            }
        }
    }

    public void shutdown() {
        schedular.shutdownNow();
    }
}

public class ReentrantLockTimeOut {

    public static void main(String[] args) {
        ExpiringLock expLock = new ExpiringLock();

        Thread idleUser = new Thread(() -> {
            if (expLock.tryLockWithExpiry(3000)) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                expLock.unlockSafely();
            }
        }, "IdleUser");

        Thread activeUser = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            while (true) {
                if (expLock.tryLockWithExpiry(3000)) {
                    System.out.println("Active user booked!");
                    expLock.unlockSafely();
                    break;
                } else {
                    System.out.println("Active user still waiting...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }, "ActiveUser");

        idleUser.start();
        activeUser.start();

        try {
            idleUser.join();
            activeUser.join();
        } catch (InterruptedException ignored) {
        }

        expLock.shutdown();
    }
}
