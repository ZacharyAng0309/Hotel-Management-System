package room.booking.system;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ZacharyAZY
 */
public class StaffAccountManager extends FileOperator {

    private ArrayList<StaffAccount> staffList;
    private int staffAmount;

    public StaffAccountManager() throws Exception {
        super("Staff");
        this.staffList = new ArrayList<>();
        readOperation();
        this.staffAmount = getDataLength();
        getData();
    }

    public ArrayList<StaffAccount> getStaffList() {
        return staffList;
    }

    public int getStaffAmount() {
        return staffAmount;
    }

    public String getNewStaffID() {
        if (staffAmount != 0) {
            StaffAccount lastStaffId = staffList.get(staffAmount - 1);
            int rawIdNum = Integer.parseInt(lastStaffId.staffId.substring(2)) + 1;
            String newStaffId;
            if (rawIdNum < 100) {
                String IdNumber = String.format("%03d", rawIdNum);
                newStaffId = "ST" + IdNumber;
            } else {
                newStaffId = "ST" + String.valueOf(rawIdNum);
            }
            return newStaffId;
        }
        return "ST001";
    }

    public void addStaff(String id, String n, String p) throws Exception {
        StaffAccount sa = new StaffAccount(id, n, p);
        staffList.add(sa);
        modifyData();
    }

    public StaffAccount searchStaff(String target) {
        for (StaffAccount sa : staffList) {
            if (sa.staffId.equals(target)) {
                return sa;
            }
        }
        return null;
    }

    public boolean updateStaff(String id, String n, String p)throws Exception {
        boolean staffFound = false;
        for (StaffAccount sa : staffList) {
            String staffId = sa.staffId;
            if (staffId.equals(id)) {
                sa.staffName = n;
                sa.password = p;
                staffFound = true;
            }
        }
        if (staffFound) {
            modifyData();
            return staffFound;
        }
        return staffFound;
    }

    public boolean deleteStaff(String id) throws Exception {
        for (int i = 0; i < staffAmount; i++) {
            StaffAccount sa = staffList.get(i);
            if (sa.staffId.equals(id)) {
                staffList.remove(i);
                modifyData();
                return true;
            }
        }
        return false;
    }

    @Override
    public void getData() throws Exception {
        for (int i = 0; i < staffAmount; i++) {
            ArrayList<String> data = readSingleData(i);
            String id = data.get(0);
            String name = data.get(1);
            String password = data.get(2);
            StaffAccount sa = new StaffAccount(id, name, password);
            staffList.add(sa);
        }
    }

    @Override
    public void modifyData() throws Exception {
        String writeContent = "";
        for (StaffAccount sa : staffList) {
            String id = sa.staffId;
            String name = sa.staffName;
            String password = sa.password;
            String StringifiedContent = id + "~" + name + "~" + password + "\n";
            writeContent += StringifiedContent;
        }
        writeOperation(writeContent);
    }

}
