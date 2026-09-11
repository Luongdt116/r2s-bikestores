package form;

import entity.Staff;
import static util.ScannerUtil.*;

public class StaffForm {

    public static int getId() {
        return readPositiveInt("Enter staff ID: ");
    }

    public static String getName() {
        return readNonEmptyString("Enter staff name: ");
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

    public static int inputStaffId(String action) {
        return readPositiveInt("Enter staff ID to " + action + ": ");
    }
}
