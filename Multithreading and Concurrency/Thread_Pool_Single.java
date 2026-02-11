
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Thread_Pool_Single {
    public static void main(String[] args) {
        ScheduledExecutorService executor=Executors.newScheduledThreadPool(1);

        Runnable task=()->{
            System.out.println("Task executed on " + Thread.currentThread().getName());
        };

        executor.scheduleAtFixedRate(task,0,5,java.util.concurrent.TimeUnit.SECONDS);

    }
}
