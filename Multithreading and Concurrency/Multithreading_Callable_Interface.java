
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class SendSms implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("SMS sent to user");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class SendEmail implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Email sent to user");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class SendETA implements Callable<String> {

    @Override
    public String call() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("ETA is calculated successfully");

        return "25 mins";
    }
}

public class Multithreading_Callable_Interface {

    public static void main(String[] args) {
        FutureTask<String> etaTask = new FutureTask<>(new SendETA());
        Thread etaThread = new Thread(etaTask);
        SendSms smsTask = new SendSms();
        SendEmail emailTask = new SendEmail();
        Thread smsThread = new Thread(smsTask);
        Thread emailThread = new Thread(emailTask);

        System.out.println("Starting main thread");
        smsThread.start();
        emailThread.start();
        etaThread.start();

        try {
            smsThread.join();
            emailThread.join();
            String eta = (String) etaTask.get();
            System.out.println("Calculated ETA: " + eta);
            System.out.println("All tasks completed");
        } catch (InterruptedException | java.util.concurrent.ExecutionException e) {
            System.out.println(e);
        }
    }
}
