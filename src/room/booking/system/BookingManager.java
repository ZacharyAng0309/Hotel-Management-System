package room.booking.system;

import java.time.LocalDate;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ZacharyAZY
 */
public class BookingManager extends FileOperator {

    private ArrayList<Booking> bookingList;
    private int bookingAmount;

    public BookingManager() throws Exception {
        super("Booking");
        this.bookingList = new ArrayList<>();
        readOperation();
        this.bookingAmount = getDataLength();
        getData();
    }

    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }

    public int getBookingAmount() {
        return bookingAmount;
    }

    public String getNewBookingID() {
        if (bookingAmount != 0) {
            Booking lastBooking = bookingList.get(bookingAmount - 1);
            int rawIdNum = Integer.parseInt(lastBooking.bookingId.substring(1)) + 1;
            String newBookingID;
            if (rawIdNum < 100) {
                String IdNumber = String.format("%03d", rawIdNum);
                newBookingID = "B" + IdNumber;
            } else {
                newBookingID = "B" + String.valueOf(rawIdNum);
            }
            return newBookingID;
        }
        return "B001";
    }

    public void addBooking(String bookingId, String roomId, String name,
            String IC,
            String telephone,
            String email,
            LocalDate startDate,
            LocalDate endDate,
            double price,
            boolean isCheckedOut) throws Exception {
        Booking booking = new Booking(bookingId, roomId, name, IC, telephone, email, startDate, endDate, price, isCheckedOut);
        bookingList.add(booking);
        modifyData();
    }

    public Booking searchBooking(String target) {
        for (Booking b : bookingList) {
            if (b.bookingId.equals(target)) {
                return b;
            }
        }
        return null;
    }

    public void updateBooking(String bookingId, String name,
            String IC,
            String telephone,
            String email,
            double price,
            boolean isCheckedOut) throws Exception {
        for (Booking b : bookingList) {
            if (b.bookingId.equals(bookingId)) {
                b.customerName = name;
                b.ICNumber = IC;
                b.telephoneNumber = telephone;
                b.email = email;
                b.price = price;
                b.isCheckedOut = isCheckedOut;
            }
        }
        modifyData();
    }

    public boolean deleteBooking(String bookingId) throws Exception {
        for (int i = 0; i < bookingAmount; i++) {
            Booking b = bookingList.get(i);
            if (b.bookingId.equals(bookingId)) {
                bookingList.remove(i);
                modifyData();
                return true;
            }
        }
        return false;
    }

    @Override
    public void getData() throws Exception {
        for (int i = 0; i < bookingAmount; i++) {
            ArrayList<String> data = readSingleData(i);
            String bookingId = data.get(0);
            String roomId = data.get(1);
            String customerName = data.get(2);
            String ICNumber = data.get(3);
            String telephoneNumber = data.get(4);
            String email = data.get(5);
            String rawStartDate = data.get(6);
            LocalDate startDate = LocalDate.parse(rawStartDate);
            String rawEndDate = data.get(7);
            LocalDate endDate = LocalDate.parse(rawEndDate);
            double price = Double.parseDouble(data.get(8));
            boolean isCheckedout = Boolean.parseBoolean(data.get(9));
            Booking booking = new Booking(bookingId, roomId, customerName, ICNumber, telephoneNumber, email, startDate, endDate, price, isCheckedout);
            bookingList.add(booking);
        }
    }

    @Override
    public void modifyData() throws Exception {
        String writeContent = "";
        for (Booking b : bookingList) {
            String bookingId = b.bookingId;
            String roomId = b.roomId;
            String customerName = b.customerName;
            String ICNumber = b.ICNumber;
            String telephoneNumber = b.telephoneNumber;
            String email = b.email;
            String arrivalDate = b.startDate.toString();
            String endDate = b.endDate.toString();
            String price = String.valueOf(b.price);
            String isCheckedOut = Boolean.toString(b.isCheckedOut);
            String StringifiedContent = bookingId + "~" + roomId + "~" + customerName + "~" + ICNumber + "~" + telephoneNumber
                    + "~" + email + "~" + arrivalDate + "~" + endDate + "~" + price + "~" + isCheckedOut + "\n";
            writeContent += StringifiedContent;
        }
        writeOperation(writeContent);
    }

}
