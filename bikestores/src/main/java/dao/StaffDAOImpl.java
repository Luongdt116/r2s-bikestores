package dao;

import entity.Staff;
import exception.DAOException;

import java.sql.*;
import java.util.ArrayList;

public class StaffDAOImpl implements StaffDAO {
    private final Connection conn;

    public StaffDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<Staff> DisplayALl() throws DAOException {
        if(conn == null){
            return null;
        }

        String select = "select * from staffs";
        ArrayList<Staff> staffs = new ArrayList<>();

        try(Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(select)){

            while (rs.next()){
                Staff staff = new Staff();

                staff.setStaff_id(rs.getInt("staff_id"));
                staff.setName(rs.getString("name"));
                staff.setRole(rs.getString("role"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setStore_id(rs.getInt("store_id"));

                staffs.add(staff);
            }
        }catch (SQLException e){
            throw new DAOException("Failed to display staff list.", e);
        }
        return staffs;
    }

    @Override
    public boolean insert(Staff staff) throws DAOException {
        if(conn == null){
            return false;
        }

        if (!checkStoreExists(staff.getStore_id())) {
            System.out.println("Store ID " + staff.getStore_id() + " does not exist.");

            return false;
        }

        String sql = "insert into staffs (name, role, email, phone, store_id) values (?,?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            int index = 1;

            ps.setString(index++, staff.getName());
            ps.setString(index++, staff.getRole());
            ps.setString(index++, staff.getEmail());
            ps.setString(index++, staff.getPhone());
            ps.setInt(index, staff.getStore_id());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DAOException("Failed to insert staff ", e);
        }
    }

    @Override
    public boolean update(int id, Staff staff) throws DAOException {
        if(conn == null){
            return false;
        }

        if (!checkStaffExists(id)) {
            System.out.println("Staff ID " + id + " does not exist.");

            return false;
        }

        if (!checkStoreExists(staff.getStore_id())) {
            System.out.println("Store ID " + staff.getStore_id() + " does not exist.");

            return false;
        }

        String sql = "update staffs set name =?, role = ?, email = ?, phone = ?, store_id = ? where staff_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            int index = 1;

            ps.setString(index++, staff.getName());
            ps.setString(index++, staff.getRole());
            ps.setString(index++, staff.getEmail());
            ps.setString(index++, staff.getPhone());
            ps.setInt(index++, staff.getStore_id());
            ps.setInt(index, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        }catch (SQLException e){
            throw new DAOException("Failed to update staff ", e);
        }
    }

    @Override
    public boolean delete(Staff staff) throws DAOException {
        if(conn == null){
            return false;
        }

        int staffId = staff.getStaff_id();

        if (!checkStaffExists(staffId)) {
            System.out.println("Staff ID " + staffId + " does not exist.");

            return false;
        }

        String sql = "delete from staffs where staff_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, staffId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        }catch (SQLException e){
            throw new DAOException("Failed to delete staff ", e);
        }
    }

    @Override
    public Staff findById(int id) throws DAOException {
        String sql = "select * from staffs where staff_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Staff staff = new Staff();
                staff.setStaff_id(rs.getInt("staff_id"));
                staff.setName(rs.getString("name"));
                staff.setRole(rs.getString("role"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setStore_id(rs.getInt("store_id"));

                return staff;
            }

        }catch (SQLException e){
            throw new DAOException("Failed to find Staff by ID.", e);
        }
        return null;
    }

    @Override
    public ArrayList<Staff> findByName(String name) throws DAOException {
        String sql = "SELECT * FROM staffs WHERE name LIKE ?";
        ArrayList<Staff> staffs = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaff_id(rs.getInt("staff_id"));
                staff.setName(rs.getString("name"));
                staff.setRole(rs.getString("role"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setStore_id(rs.getInt("store_id"));
                staffs.add(staff);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find Staff by name.", e);
        }

        return staffs;
    }

    private boolean checkStaffExists(int staffId) throws DAOException{
        String sql = "select count(*) from staffs where staff_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, staffId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DAOException("Failed to check Staff ID.", e);
        }

        return false;
    }

    private boolean checkStoreExists(int storeId) throws DAOException{
        String sql = "select count(*) from stores where store_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, storeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DAOException("Failed to check Store ID.", e);
        }

        return false;
    }
}












