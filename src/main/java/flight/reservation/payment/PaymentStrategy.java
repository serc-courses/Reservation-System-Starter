package flight.reservation.payment;

/**
 * Interface for payment strategy
 */
public interface PaymentStrategy {

    boolean pay(double amount);
    
} 