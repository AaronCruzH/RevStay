package com.revature.stay.services;

import com.revature.stay.exceptions.ResourceNotFoundException;
import com.revature.stay.models.Room;
import com.revature.stay.models.RoomStatus;
import com.revature.stay.models.RoomType;
import com.revature.stay.repos.RoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomDAO roomDAO;

    @Autowired
    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public Room createRoom(Room roomCreationRequest){
        return roomDAO.save(roomCreationRequest);
    }

    public Room uptadeRoom(int roomId, Room updatedRoom){
        System.out.println(roomId);
        Optional<Room> existingRoom = roomDAO.findById(roomId);

        if(existingRoom.isPresent()){
            Room newRoom = existingRoom.get();
            newRoom.setRoomNumber(updatedRoom.getRoomNumber());
            newRoom.setRoomType(updatedRoom.getRoomType());
            newRoom.setCapacity(updatedRoom.getCapacity());
            //newRoom.setStatus(updatedRoom.getStatus());
            newRoom.setPrice(updatedRoom.getPrice());
            return roomDAO.save(newRoom);
        } else {
            throw new ResourceNotFoundException("No room found with ID: "+roomId);
        }
    }

    /*
    public Room updateRoomStatus(int roomId, String updatedStatus){

        Optional<Room> roomToUpdate = roomDAO.findById(roomId);

        if(roomToUpdate.isEmpty())        {
            throw new ResourceNotFoundException("No room found with ID: "+roomId);
        }

        if(!RoomStatus.isContained(updatedStatus)){
            throw new ResourceNotFoundException("Valid status " +updatedStatus+" not found");
        }

        Room updatedRoom = roomToUpdate.get();
        updatedRoom.setStatus(RoomStatus.valueOf(updatedStatus));

        return roomDAO.save(updatedRoom);
    }*/

    public List<Room> getAllRooms(){
        return roomDAO.findAll();
    }

    public Room getRoomById(int roomId){
        Optional<Room> roomFound =  roomDAO.findById(roomId);
        if(roomFound.isEmpty())
        {
            return null;
        }
        else {
            return roomFound.get();
        }
    }

    public void deleteRoom(int roomId){
        if(roomDAO.existsById(roomId))
        {
            roomDAO.deleteById(roomId);
        }else {
            throw new ResourceNotFoundException("No room found with ID: "+roomId);
        }
    }

    public List<Room> getRoomsByHotelId(int hotelId){
        return roomDAO.getRoomsByHotelId(hotelId);
    }
}
