package room.booking.system;

import java.util.ArrayList;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ZacharyAZY
 */
public class RoomManager extends FileOperator {
    private ArrayList<Room> roomList;
    private int roomAmount;

    public RoomManager() throws Exception {
        super("Room");
        this.roomList = new ArrayList<>();
        readOperation();
        this.roomAmount = getDataLength();
        getData();
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    @Override
    public void getData() throws Exception {
        for (int i = 0; i < roomAmount; i++) {
            ArrayList<String> data = readSingleData(i);
            String roomId = data.get(0);
            String roomView = data.get(1);
            int roomSize = Integer.parseInt(data.get(2));
            Room room = new Room(roomId, roomView, roomSize);
            roomList.add(room);
        }
    }

    @Override
    public void modifyData() throws Exception {
        return;
    }
}
