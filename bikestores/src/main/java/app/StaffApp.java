package app;

import dao.StaffDAO;
import dao.StaffDAOImpl;
import entity.Staff;
import exception.DAOException;
import exception.GlobalExceptionHandler;
import form.StaffForm;
import util.JDBCUtil;
import util.ScannerUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffApp {
    private static final int GET_ALL_STAFF = 1;
    private static final int ADD_NEW_STAFF = 2;
    private static final int UPDATE_STAFF = 3;
    private static final int REMOVE_STAFF = 4;
    private static final int FIND_STAFF_BY_ID = 5;
    private static final int FIND_STAFF_BY_NAME = 6;
    private static final int BACK = 0;

    public static void run(){
        try(Connection conn = JDBCUtil.getConnection()){
            StaffDAO staffDAO = new StaffDAOImpl(conn);

            int choice;

            do{
                showMenu();
                choice = ScannerUtil.readInt("Enter your choice: ");

                try{
                    switch (choice){
                        case GET_ALL_STAFF:
                            displayAllStaff(staffDAO);
                            break;
                        case ADD_NEW_STAFF:
                            addStaff(staffDAO);
                            break;
                        case UPDATE_STAFF:
                            updateStaff(staffDAO);
                            break;
                        case REMOVE_STAFF:
                            deleteStaff(staffDAO);
                            break;
                        case FIND_STAFF_BY_ID:
                            findStaffById(staffDAO);
                            break;
                        case FIND_STAFF_BY_NAME:
                            findStaffByName(staffDAO);
                            break;
                        case BACK:
                            System.out.println("Back to menu.");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (DAOException e){
                    GlobalExceptionHandler.handle(e);
                }
            }while (choice != BACK);
        }catch (SQLException e){
            System.out.println("Cannot connect to the database.");
            System.out.println("Cause: " + e.getMessage());
        }
    }

    private static void showMenu(){
        System.out.println("\n====== STAFF MANAGEMENT =======");
        System.out.println("1. Display all staff");
        System.out.println("2. Add new staff");
        System.out.println("3. Update staff");
        System.out.println("4. Delete staff");
        System.out.println("5 Find staff by ID");
        System.out.println("6 Find staff by name");
        System.out.println("0. Back to main menu");
    }

    private static void displayAllStaff(StaffDAO staffDAO) throws DAOException {
        ArrayList<Staff> staffList = staffDAO.DisplayALl();

        if(staffList == null || staffList.isEmpty()){
            System.out.println("No staff found");
            return;
        }
        for (Staff staff : staffList) {
            System.out.println(staff);
        }
    }

    private static void addStaff(StaffDAO staffDAO) throws DAOException{
        Staff newStaff = StaffForm.getStaff();

        if (staffDAO.insert(newStaff)) {
            System.out.println("Staff added successfully.");
        } else {
            System.out.println("Failed to add staff.");
        }

    }

    private static void updateStaff(StaffDAO staffDAO) throws DAOException{
        int id = StaffForm.getId();
        Staff staff = StaffForm.getStaff();

        if (staffDAO.update(id, staff)) {
            System.out.println("Staff updated successfully.");
        } else {
            System.out.println("Staff was not found or update failed.");
        }
    }

    private static void deleteStaff(StaffDAO staffDAO) throws DAOException{
        int id = StaffForm.getId();
        Staff staff = new Staff();
        staff.setStaff_id(id);

        if (staffDAO.delete(staff)) {
            System.out.println("Staff deleted successfully.");
        } else {
            System.out.println("Staff was not found or delete failed.");
        }
    }

    private static void findStaffById(StaffDAO staffDAO) throws DAOException {
        int id = StaffForm.getId();
        Staff staff = staffDAO.findById(id);

        if (staff != null) {
            System.out.println(staff);
        } else {
            System.out.println("Staff not found.");
        }
    }

    private static void findStaffByName(StaffDAO staffDAO) throws DAOException {
        String name = StaffForm.getName();
        ArrayList<Staff> staffs = staffDAO.findByName(name);

        if (staffs.isEmpty()) {
            System.out.println("Staff not found.");
            return;
        }

        for (Staff staff : staffs) {
            System.out.println(staff);
        }
    }
}
