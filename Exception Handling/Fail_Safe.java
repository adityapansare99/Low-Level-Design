
public class Fail_Safe {

    public static void main(String[] args) {
        try {
            System.out.println("here is the business logic");
        } catch (Exception e) {
            System.out.println("fallback logic");
        }
    }
}
