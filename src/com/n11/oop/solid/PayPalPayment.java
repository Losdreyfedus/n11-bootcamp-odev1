package com.n11.oop.solid;

public class PayPalPayment implements IPaymentMethod {
    @Override
        public boolean pay(double amount) {
        System.out.println(amount + "TL PayPal yoluyla başarıyla çekildi.");
        return true;
    }
}
