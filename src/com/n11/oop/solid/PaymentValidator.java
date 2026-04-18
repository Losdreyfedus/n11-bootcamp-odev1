package com.n11.oop.solid;

import java.util.Scanner;

public class PaymentValidator {
    
    private static final double MINIMUM_AMOUNT = 0.01;
    private static final String AMOUNT_ERROR_MESSAGE = "❌ Tutar %.2f TL'den büyük olmalıdır!";
    
    public static double getValidAmount(Scanner scanner) {
        System.out.print("\nÖdenecek tutar (TL): ");
        double amount = scanner.nextDouble();
        
        validateAmount(amount);
        return amount;
    }
    
    public static void validateAmount(double amount) {
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                String.format(AMOUNT_ERROR_MESSAGE, MINIMUM_AMOUNT)
            );
        }
    }
}
