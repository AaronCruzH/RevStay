package com.revature.stay.services;

import com.revature.stay.dto.request.HotelFilterDTO;
import com.revature.stay.dto.request.RoomFilterDTO;
import com.revature.stay.dto.response.RoomWithDetailsDTO;
import com.revature.stay.exceptions.ResourceNotFoundException;
import com.revature.stay.models.Hotel;
import com.revature.stay.models.Room;
import com.revature.stay.models.RoomStatus;
import com.revature.stay.models.RoomType;
import com.revature.stay.repos.RoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomDAO roomDAO;


    private static final ZoneId MEXICO_CITY_ZONE = ZoneId.of("America/Mexico_City");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public Room createRoom(Room roomCreationRequest){
        return roomDAO.save(roomCreationRequest);
    }

    // filters
    public List<RoomWithDetailsDTO> filterRoom(RoomFilterDTO roomFilterDTO) {
        Integer hotelId = roomFilterDTO.getHotelId() == 0 ? null : roomFilterDTO.getHotelId();
        String name = roomFilterDTO.getName() == null || roomFilterDTO.getName().isEmpty() ? null : roomFilterDTO.getName();
        String country = roomFilterDTO.getCountry() == null || roomFilterDTO.getCountry().isEmpty() ? null : roomFilterDTO.getCountry();
        String state = roomFilterDTO.getState() == null || roomFilterDTO.getState().isEmpty() ? null : roomFilterDTO.getState();
        String city = roomFilterDTO.getCity() == null || roomFilterDTO.getCity().isEmpty() ? null : roomFilterDTO.getCity();
        Integer roomId = roomFilterDTO.getRoomId() == 0 ? null : roomFilterDTO.getRoomId();
        String roomType = roomFilterDTO.getRoomType() == null || roomFilterDTO.getRoomType().toString().isEmpty() ? null : roomFilterDTO.getState();
        Integer capacity = roomFilterDTO.getCapacity() == 0 ? null : roomFilterDTO.getCapacity();
        Float priceMin = roomFilterDTO.getPriceMin() == 0.0 ? null : roomFilterDTO.getPriceMin();
        Float priceMax = roomFilterDTO.getPriceMax() == 0.0 ? null : roomFilterDTO.getPriceMax();

        // Initialize with current date and default times
        LocalDate today = LocalDate.now();

        // Parse check-in date or use today with 9:00 AM
        LocalDate checkInDate;
        if (roomFilterDTO.getCheckIn() == null || roomFilterDTO.getCheckIn().isEmpty()) {
            checkInDate = today;
        } else {
            checkInDate = LocalDate.parse(roomFilterDTO.getCheckIn(), DATE_FORMATTER);
        }
        ZonedDateTime checkInDateTime = checkInDate.atTime(9, 0).atZone(MEXICO_CITY_ZONE);

        // Parse check-out date or use tomorrow with 3:00 PM
        LocalDate checkOutDate;
        if (roomFilterDTO.getCheckOut() == null || roomFilterDTO.getCheckOut().isEmpty()) {
            checkOutDate = today.plusDays(1);
        } else {
            checkOutDate = LocalDate.parse(roomFilterDTO.getCheckOut(), DATE_FORMATTER);
        }
        ZonedDateTime checkOutDateTime = checkOutDate.atTime(15, 0).atZone(MEXICO_CITY_ZONE);

        // Convert to java.util.Date for the DAO method
        Date checkIn = Date.from(checkInDateTime.toInstant());
        Date checkOut = Date.from(checkOutDateTime.toInstant());

        List<Room> rooms = roomDAO.findAvailableRooms(
                hotelId, name, country, state, city, roomId, roomType,capacity,
                priceMin, priceMax, checkIn, checkOut);


        return rooms.stream()
                .map(RoomWithDetailsDTO::new)
                .collect(Collectors.toList());
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
