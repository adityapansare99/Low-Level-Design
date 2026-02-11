import java.util.concurrent.*;

public class Thread_Pool_submit {
    public static void main(String[] args) {
        ExecutorService executor=Executors.newFixedThreadPool(6);

        Future<Integer> func=executor.submit(()->{
            Thread.sleep(1000);
            return 26;
        });

        Integer result=null;
        try {
            result = func.get();
        } catch (InterruptedException | ExecutionException ex) {
            System.getLogger(Thread_Pool_submit.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        System.out.println("Result: "+result);

        executor.shutdown();
    }
}
