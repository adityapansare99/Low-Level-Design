public class Multithreading_Other_methods {
    public static void main(String[] args) {
        @SuppressWarnings("Convert2Lambda")
        Runnable task1=new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000); 
                    System.out.println("Task 1 completed");
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        };

        Thread thread1=new Thread(task1);
        thread1.start();

        Runnable task2=()->{
            try {
                Thread.sleep(1000); 
                System.out.println("Task 2 completed");
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        };

        Thread thread2=new Thread(task2);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.out.println("All tasks completed");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
