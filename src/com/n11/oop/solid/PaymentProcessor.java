package com.n11.oop.solid;

public class PaymentProcessor{
    private IPaymentMethod paymentMethod;

    public PaymentProcessor(IPaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }
    public void processPayment(double amount)
    {
        this.paymentMethod.pay(amount);
    }

}
