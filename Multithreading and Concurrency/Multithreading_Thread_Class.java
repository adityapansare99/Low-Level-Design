class SendSMS extends Thread{
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

class SendEmail extends Thread{
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

public class Multithreading_Thread_Class{
    public static void main(String[] args){
        SendSMS sms=new SendSMS();
        SendEmail email=new SendEmail();
        System.out.println("Starting main thread");
        sms.start();
        email.start();

        try {
            sms.join();
            email.join();
            System.out.println("All tasks completed");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}