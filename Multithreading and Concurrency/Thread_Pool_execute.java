
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Thread_Pool_execute {

    private static final ExecutorService executor = Executors.newFixedThreadPool(6);

    private static void SendMail(String email) {
        executor.execute(()->{
            System.out.println("Sending email to " + email + " on " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            System.out.println("Email sent to " + email + " on " + Thread.currentThread().getName());
        });
    }

    public static void main(String[] args) {
        for(int i=0;i<25;i++){
            String email = "user" + i + "@example.com";
            SendMail(email);
        }

        executor.shutdown();
    }
}
