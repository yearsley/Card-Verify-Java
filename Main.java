/** * * * * * * * * * * * *
 *  CreditCardChecker.java
 *  @author Joey Yearsley
 *  @version 1.0
 * * * * * * * * * * * **/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // Setup
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("Enter input filename: ");
        String fileName = consoleScanner.next();

        Scanner fileScanner = new Scanner(new File(fileName));

        while (fileScanner.hasNext()) {
            String cardType = fileScanner.next();
            String cardNumber = fileScanner.next();

            String resultText;

            // Length check
            boolean lengthIsValid = false;
            int numberLength = cardNumber.length();

            if (cardType.equalsIgnoreCase("AmericanExpress") || cardType.equalsIgnoreCase("amex")) {
                if (numberLength == 15) lengthIsValid = true;
            } else if (cardType.equalsIgnoreCase("MasterCard") || cardType.equalsIgnoreCase("mastercard")) {
                if (numberLength == 16) lengthIsValid = true;
            } else if (cardType.equalsIgnoreCase("Visa")) {
                if (numberLength == 13 || numberLength == 16) lengthIsValid = true;
            }

            //Make sure length is valid
            boolean proceed = true;
            if (!lengthIsValid) {
                resultText = "Invalid length";
                System.out.format("%-15s %-19s %s%n", cardType, cardNumber, resultText);
                proceed = false;
            }

            // Prefix check
            boolean prefixIsValid = false;

            if (proceed) {
                if (cardType.equalsIgnoreCase("AmericanExpress") || cardType.equalsIgnoreCase("amex")) {
                    // Amex: 34 or 37
                    char c0 = cardNumber.charAt(0);
                    char c1 = cardNumber.charAt(1);
                    if (c0 == '3' && (c1 == '4' || c1 == '7')) prefixIsValid = true;
                } else if (cardType.equalsIgnoreCase("MasterCard") || cardType.equalsIgnoreCase("mastercard")) {
                    // MasterCard: 51-55
                    char c0 = cardNumber.charAt(0);
                    char c1 = cardNumber.charAt(1);
                    int firstTwo = (c0 - '0') * 10 + (c1 - '0');
                    if (firstTwo >= 51 && firstTwo <= 55) prefixIsValid = true;
                } else if (cardType.equalsIgnoreCase("Visa")) {
                    // Visa: starts with 4
                    if (cardNumber.charAt(0) == '4') prefixIsValid = true;
                }

                if (!prefixIsValid) {
                    resultText = "Invalid prefix";
                    System.out.format("%-15s %-19s %s%n", cardType, cardNumber, resultText);
                    proceed = false;
                }
            }

            // Luhn check
            if (proceed) {
                int sum = 0;
                boolean doubleDigit = false;

                for (int i = cardNumber.length() - 1; i >= 0; i--) {
                    int digit = cardNumber.charAt(i) - '0';
                    if (doubleDigit) {
                        digit = digit * 2;
                        if (digit > 9) digit = digit - 9;
                    }
                    sum += digit;
                    doubleDigit = !doubleDigit;
                }

                if (sum % 10 == 0) {
                    resultText = "valid";
                } else {
                    resultText = "invalid checksum";
                }

                System.out.format("%-15s %-19s %s%n", cardType, cardNumber, resultText);
            }
        }

        fileScanner.close();
        consoleScanner.close();
    }
}