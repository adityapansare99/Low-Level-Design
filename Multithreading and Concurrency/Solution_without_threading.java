class SendSms {
    public static void sendSMS() {
        try {
            Thread.sleep(2000);
            System.out.println("SMS sent successfully");
        } catch (InterruptedException err) {
            System.out.println(err);
        }

    }
}

class SendEmail {
    public static void sendEmail() {
        try {
            Thread.sleep(1000);
            System.out.println("Email sent successfully");
        } catch (InterruptedException err) {
            System.out.println(err);
        }

    }
}

class CalculateETA{
    public static String calculateETA() {
        try {
            Thread.sleep(1000);
            System.out.println("ETA calculated successfully");
        } catch (InterruptedException err) {
            System.out.println(err);
        }

        return "ETA";

    }
}

@SuppressWarnings("unused")
class Solution_without_threading {
    public static void main(String[] args) {
        System.out.println("Starting main thread");
        SendSms.sendSMS();
        SendEmail.sendEmail();
        String ETA = CalculateETA.calculateETA();
        System.out.println("Calculated " + ETA);
        System.out.println("All tasks completed");
    }
}
