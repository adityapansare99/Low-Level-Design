import java.util.*;

public class QueueRequest {
    public static void main(String[] args) {
        Queue<String> requestQueue = new LinkedList<>();
        
        try {
            System.out.println("Processing request in the main service");
        } catch (Exception e) {
            System.out.println("Main service failed, queuing request for later processing");
            requestQueue.add("Request data");
        }
    }    
}
