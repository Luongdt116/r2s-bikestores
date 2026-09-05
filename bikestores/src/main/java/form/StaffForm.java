package form;

import entity.Staff;
import util.ScannerUtil;

public class StaffForm {

    public static int getId() {
        return readPositiveInt("Enter staff ID: ");
    }

    public static Staff getStaff() {
        Staff staff = new Staff();

        staff.setName(readStringWithLimit("Enter staff name: ", 100));
        staff.setEmail(readValidEmail("Enter staff email: "));
        staff.setPhone(readValidPhone("Enter staff phone: "));
        staff.setRole(readStringWithLimit("Enter staff role: ", 50));
        staff.setStore_id(readPositiveInt("Enter store ID: "));

        return staff;
    }

    // --- CÁC HÀM HỖ TRỢ KIỂM TRA DỮ LIỆU ---
    // kiểm tra id khi update, delete, find
    public static int inputStaffId(String action) {
        return readPositiveInt("Enter staff ID to " + action + ": ");
    }

    private static int readPositiveInt(String message) {
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

    private static String readStringWithLimit(String prompt, int maxLength) {
        while (true) {
            String input = ScannerUtil.readNonEmptyString(prompt);
            if (input.length() <= maxLength) {
                return input;
            }

            System.out.println("Input must not exceed " + maxLength + " characters.");
        }
    }

    private static String readValidEmail(String prompt) {
        // Regex đơn giản kiểm tra cấu trúc email: có chữ/số, có @, có dấu chấm
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+$";
        while (true) {
            String input = readStringWithLimit(prompt, 100);
            if (input.matches(emailRegex)) {
                return input;
            }
            System.out.println("Invalid email.");
        }
    }

    private static String readValidPhone(String prompt) {
        // Regex kiểm tra số điện thoại: cho phép dấu + ở đầu, theo sau là 9-15 chữ số
        String phoneRegex = "^\\+?[0-9]{9,15}$";
        while (true) {
            String input = readStringWithLimit(prompt, 20);
            if (input.matches(phoneRegex)) {
                return input;
            }
            System.out.println("Phone must contain from 9 to 15 digits.");
        }
    }
}
