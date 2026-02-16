
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Write_Read_Lock {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private int price = 10;

    public void UpdatePrice(int newPrice) {
        lock.writeLock().lock();

        try {
            System.out.println("Updating price from " + price + " to " + newPrice + "by " + Thread.currentThread().getName());
            price = newPrice;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void ReadPrice() {
        lock.readLock().lock();

        try {
            System.out.println("Reading price " + price + "by " + Thread.currentThread().getName());
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {

        Write_Read_Lock stock = new Write_Read_Lock();

        // Reader Threads
        Runnable readerTask = () -> {
            for (int i = 0; i < 3; i++) {
                stock.ReadPrice();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Writer Thread
        Runnable writerTask = () -> {
            int[] prices = {20, 30, 40};
            for (int price : prices) {
                stock.UpdatePrice(price);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Creating Threads
        Thread reader1 = new Thread(readerTask, "Reader-1");
        Thread reader2 = new Thread(readerTask, "Reader-2");
        Thread reader3 = new Thread(readerTask, "Reader-3");

        Thread writer = new Thread(writerTask, "Writer");

        // Start threads
        reader1.start();
        reader2.start();
        reader3.start();
        writer.start();
    }
}
