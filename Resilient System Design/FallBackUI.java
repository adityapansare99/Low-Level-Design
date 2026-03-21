public class FallBackUI {
    public static void main(String[] args) {
        try {
            System.out.println("here is the main UI");
        } catch (Exception e) {
            System.out.println("main UI failed, showing fallback UI");
        }
    }    
}
