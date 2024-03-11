package flight.reservation.payment;

import java.util.Date;

public class CreditCardStrategy implements PaymentStrategy{

    CreditCard creditCard; 

    public CreditCardStrategy(String number, Date expirationDate, String cvv) {
        this.creditCard = new CreditCard(number, expirationDate, cvv);
    }

    public CreditCardStrategy(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    private boolean cardIsPresentAndValid() {
        return creditCard != null && this.creditCard.isValid();
    }

    @Override
    public boolean pay(double amount) throws IllegalStateException {

        if (!cardIsPresentAndValid()) {
            throw new IllegalStateException("Payment information is not set or not valid.");
        }

        if (creditCard.isValid()) {
            System.out.println("Paying " + amount + " using Credit Card.");
            double remainingAmount = creditCard.getAmount() - amount;
            if (remainingAmount < 0) {
                System.out.printf("Card limit reached - Balance: %f%n", remainingAmount);
                throw new IllegalStateException("Card limit reached");
            }
            creditCard.setAmount(remainingAmount);
            return true;
        } else {
            throw new IllegalStateException("Credit card is not valid");
        }
    }
    
}
