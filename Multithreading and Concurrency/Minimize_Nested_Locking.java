class Minimize_Nested_Locking {

    static class SharedResource {
        private final Object lock = new Object();
        private int data;

        public void update(int value) {
            synchronized (lock) {
                data = value;
                System.out.println(Thread.currentThread().getName() + " updated data to " + value);
            }
        }

        public int read() {
            synchronized (lock) {
                return data;
            }
        }
    }

    public static void main(String[] args) {
        SharedResource resource1 = new SharedResource();
        SharedResource resource2 = new SharedResource();

        Runnable task1 = () -> {
            resource1.update(100);
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            resource2.update(200);  
        };

        Runnable task2 = () -> {
            resource2.update(300);
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            resource1.update(400);
        };

        new Thread(task1, "T1").start();
        new Thread(task2, "T2").start();
    }
}
