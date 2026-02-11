class SendSms implements Runnable{
    @Override
    public void run(){
        try {
            Thread.sleep(2000);
            System.out.println("SMS sent to user");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class SendEmail implements Runnable{
    @Override
    public void run(){
        try {
            Thread.sleep(1000);
            System.out.println("Email sent to user");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

public class Multithreading_Runnable_Interface {
    public static void main(String[] args) {
        SendSms smsTask = new SendSms();
        SendEmail emailTask = new SendEmail();

        Thread smsThread = new Thread(smsTask);
        Thread emailThread = new Thread(emailTask);

        System.out.println("Starting main thread");
        smsThread.start();
        emailThread.start();

        try {
            smsThread.join();
            emailThread.join();
            System.out.println("All tasks completed");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
