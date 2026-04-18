package com.n11.oop.solid;

public class CreditCardPayment implements IPaymentMethod{
    @Override
    public boolean pay(double amount) {
        System.out.println(amount + "TL Kredi kartı yoluyla başarıyla çekildi.");
        return true;
    }
}
