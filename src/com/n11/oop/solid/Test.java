package com.n11.oop.solid;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Test {
    
    public static void main(String[] args)
    {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<Integer, IPaymentMethod> paymentMethods = new HashMap<>();
            paymentMethods.put(1, new CreditCardPayment());
            paymentMethods.put(2, new PayPalPayment());
            
            System.out.println("=== Ödeme Sistemi ===");
            System.out.println("\nLütfen ödeme yöntemi seçiniz:");
            System.out.println("1. Kredi Kartı");
            System.out.println("2. PayPal");
            System.out.print("\nSeçiminiz (1 veya 2): ");
            
            IPaymentMethod selectedPayment = paymentMethods.getOrDefault(
                scanner.nextInt(), new CreditCardPayment()
            );
            
            double amount = PaymentValidator.getValidAmount(scanner);
            
            System.out.println("\n--- İşlem Gerçekleştiriliyor ---");
            new PaymentProcessor(selectedPayment).processPayment(amount);
            System.out.println("--- İşlem Tamamlandı ---\n");
        }
    }

}
