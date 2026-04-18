package com.n11.oop.solid;

public class Test {
    public static void main(String[] args)
    {
      PayPalPayment payPalPayment = new PayPalPayment();
      CreditCardPayment creditCardPayment = new CreditCardPayment();
      PaymentProcessor paymentProcessor = new PaymentProcessor(creditCardPayment);
      paymentProcessor.processPayment(650);
    }

}
