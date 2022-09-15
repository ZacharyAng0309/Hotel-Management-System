package room.booking.system;

import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ZacharyAZY
 */
public class Booking {

    public String bookingId;
    public String roomId;
    public String customerName;
    public String ICNumber;
    public String telephoneNumber;
    public String email;
    public LocalDate startDate;
    public LocalDate endDate;
    public double price;
    public boolean isCheckedOut;

    public Booking(String bookingId, String roomId, String name,
            String IC,
            String telephone,
            String email,
            LocalDate startDate,
            LocalDate endDate,
            double price,
            boolean isCheckedOut) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.customerName = name;
        this.ICNumber = IC;
        this.telephoneNumber = telephone;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.isCheckedOut = isCheckedOut;
    }

    public boolean checkAvailability(LocalDate startDate, LocalDate endDate) {
        boolean isAvailable = true;
        if ( 
                //Check if selected Start Date is BEFORE this booking's start date
                !startDate.isBefore(this.startDate)&& 
                //Check if selected Start Date is AFTER this booking's end date
                !startDate.isAfter(this.endDate)) {
            isAvailable = false;
        }
        if ( 
                //Check if selected End Date is BEFORE this booking's start date
                !endDate.isBefore(this.startDate)&& 
                   //Check if selected Start Date is AFTER this booking's end date
                !endDate.isAfter(this.endDate)) {
            isAvailable = false;
        }
        //Check if the selected StartDate is before this booking's start date and selected enddate 
        if(startDate.isBefore(this.startDate) && endDate.isAfter(this.endDate)){
            isAvailable = false;
        }
        return isAvailable;
    }

}
