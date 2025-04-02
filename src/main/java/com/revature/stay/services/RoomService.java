package com.revature.stay.services;

import com.revature.stay.models.Room;
import com.revature.stay.repos.RoomDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RoomService {
    private final RoomDAO roomDAO;

    @Autowired
    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public Room createRoom(Room roomCreationRequest){
        return roomDAO.save(roomCreationRequest);
    }

    public List<Room> getAllRooms(){
        return roomDAO.findAll();
    }

    public Optional<Room> getRoomById(int roomId){
        
    }
}
