package util;

import java.util.Scanner;

public class ScannerUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid integer. Please try again.");
            }
        }
    }

    public static String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("⚠️ Input cannot be empty.");
        }
    }

    public static int readPositiveInt(String message) {
        while (true) {
            int number = ScannerUtil.readInt(message);

            if (number > 0) {
                return number;
            }

            System.out.println(
                    "Staff ID or Store ID must be greater than 0."
            );
        }
    }

    public static String readStringWithLimit(String prompt, int maxLength) {
        while (true) {
            String input = ScannerUtil.readNonEmptyString(prompt);
            if (input.length() <= maxLength) {
                return input;
            }

            System.out.println("Input must not exceed " + maxLength + " characters.");
        }
    }

    public static String readValidPhone(String prompt) {
        // Regex kiểm tra số điện thoại: cho phép dấu + ở đầu, theo sau là 9-15 chữ số
        String phoneRegex = "^\\+?[0-9]{9,15}$";
        while (true) {
            String input = ScannerUtil.readStringWithLimit(prompt, 20);
            if (input.matches(phoneRegex)) {
                return input;
            }
            System.out.println("Phone must contain from 9 to 15 digits.");
        }
    }

    public static String readValidEmail(String prompt) {
        // Regex đơn giản kiểm tra cấu trúc email: có chữ/số, có @, có dấu chấm
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$";
        while (true) {
            String input = ScannerUtil.readStringWithLimit(prompt, 100);
            if (input.matches(emailRegex)) {
                return input;
            }
            System.out.println("Invalid email.");
        }
    }
}