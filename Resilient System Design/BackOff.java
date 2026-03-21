
public class BackOff {

    public String getETAWithBackoff() throws InterruptedException {
        int retries = 3;
        int delay = 1000;
        while (retries-- > 0) {
            try {
                System.out.println("Trying to fetch ETA...");
            } catch (Exception e) {
                Thread.sleep(delay);
                delay *= 2;
            }
        }
        return "ETA unavailable";
    }
}
