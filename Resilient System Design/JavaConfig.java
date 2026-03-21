// @Bean
// public Customizer<CircuitBreakerConfigCustomizer> paymentCircuitBreakerConfig() {
//     return CircuitBreakerConfigCustomizer.of("paymentService", builder -> builder
//         .slidingWindowSize(10)
//         .failureRateThreshold(50)
//         .waitDurationInOpenState(Duration.ofSeconds(10))
//         .permittedNumberOfCallsInHalfOpenState(2)
//         .automaticTransitionFromOpenToHalfOpenEnabled(true));
// }

public class JavaConfig {
    
}
