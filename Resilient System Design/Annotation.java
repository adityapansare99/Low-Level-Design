
// @Service
class PaymentService {

    // @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public String charge(String userId, double amount) {
        return "externalPaymentApi.charge(userId, amount)";
    }

    public String paymentFallback(String userId, double amount, Throwable t) {
        System.out.println("Payment Service Down. Fallback triggered.");
        return "PAYMENT_FAILED";
    }
}

public class Annotation {

}
