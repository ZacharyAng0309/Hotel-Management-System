package room.booking.system;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ZacharyAZY
 */
public class StaffAccount {

    public String staffId;
    public String staffName;
    public String password;

    public StaffAccount(String id, String n, String p) {
        this.staffId = id;
        this.staffName = n;
        this.password = p;
    }
}
