import java.util.*;

public class Lock_Ordering{
    static class Resource{
        int id;
        @SuppressWarnings("unused")
        int value;

        public Resource(int id,int value) {
            this.id=id;
            this.value=value;
        }
    }

    public static void main(String[] args) {
        Resource r1=new Resource(1,10);
        Resource r2=new Resource(2,20);

        Runnable task1=()->transfer(r1, r2, 100);
        Runnable task2=()->transfer(r2, r1, 100);

        new Thread(task1).start();
        new Thread(task2).start();
    }

    static void transfer(Resource r1,Resource r2,int amount){
        Resource[] tasks=new Resource[]{r1,r2};
        Arrays.sort(tasks,(x, y) -> Integer.compare(x.id, y.id));

        synchronized (tasks[0]) {
            System.out.println(Thread.currentThread().getName() + " locked " + tasks[0].id);

            try {
                Thread.sleep(50); 
            } catch (InterruptedException ignored) {}

            synchronized (tasks[1]) {
                System.out.println(Thread.currentThread().getName() + " locked " + tasks[1].id);
                System.out.println("Transferred " + amount + " from " + r1.id + " to " + r2.id);
            }
        }
    }
}