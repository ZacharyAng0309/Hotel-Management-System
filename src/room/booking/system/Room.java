package room.booking.system;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ZacharyAZY
 */
public class Room {
    public String roomId;
    public String roomView;
    public int roomSize;
    public final double pricePerNight = 350.00;

    public Room(String id, String rt, int size) {
        this.roomId = id;
        this.roomView = rt;
        this.roomSize = size;
    }
}