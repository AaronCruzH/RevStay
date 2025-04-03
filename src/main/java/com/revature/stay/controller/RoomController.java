package com.revature.stay.controller;

import com.revature.stay.exceptions.ResourceNotFoundException;
import com.revature.stay.models.Hotel;
import com.revature.stay.models.Room;
import com.revature.stay.services.HotelService;
import com.revature.stay.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("rooms")
public class RoomController {
    private final RoomService roomService;
    private final HotelService hotelService;


    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @PostMapping("register/{hotelId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoomHandler(@PathVariable int hotelId, @RequestBody Room room) {

        Optional<Hotel> hotel = hotelService.getHotelById(hotelId);

        if (hotel.isEmpty()) {
            throw new ResourceNotFoundException("No Hotel found with id: " + hotelId);
        } else {
            room.setHotel(hotel.get());
        }
        //TODO add session and user role validation
        return roomService.createRoom(room);
    }

    @PutMapping("update/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public Room updateRoom(@PathVariable int roomId, @RequestBody Room updatedRoom) {
        return roomService.uptadeRoom(roomId, updatedRoom);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Room> getRoomsByHotel(@PathVariable int hotelId){
        return roomService.getRoomsByHotelId(hotelId);
    }

    @DeleteMapping("{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreatureHandler(@PathVariable int roomId){
        roomService.deleteRoom(roomId);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> resourceNotFoundHandler(ResourceNotFoundException e) {
        return Map.of(
                "error", e.getMessage()
        );
    }
}
