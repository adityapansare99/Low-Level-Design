public class NavieRetry {
    public static void main(String[] args) {
        int retries = 4; 
        while (retries-- > 0) {
            try {
                System.out.println("Trying to fetch ETA...");
                throw new RuntimeException("Failed to fetch ETA");
            } catch (RuntimeException e) {
                System.out.println("Retrying ETA, attempts left: " + retries);
            }
        } 
    }
}
