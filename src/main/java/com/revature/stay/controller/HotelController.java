package com.revature.stay.controller;


import com.revature.stay.dto.request.HotelFilterDTO;
import com.revature.stay.exceptions.ResourceNotFoundException;
import com.revature.stay.exceptions.UnauthenticatedException;
import com.revature.stay.models.Hotel;
import com.revature.stay.models.Role;
import com.revature.stay.services.HotelService;
import com.revature.stay.services.UserService;
import com.revature.stay.utils.AuthUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("hotels")
public class HotelController {

    private final HotelService hotelService;
    private final UserService userService;

    @Autowired
    public HotelController(HotelService hotelService, UserService userService) {
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel createHotelHandler(@RequestBody Hotel hotel, HttpSession session){
//        if (session.getAttribute("userId") == null){
//            throw new UnauthenticatedException("User is not authenticated");
//        }
//
//        // I need to make sure the role of the user is a teacher
//        if (session.getAttribute("role") != Role.OWNER){
//            throw new ForbiddenActionException("You must be a teacher to access this");
//        }
        hotel.setOwner(userService.getUserByEmail(AuthUtil.getCurrentUserEmail()));

        return hotelService.createHotel(hotel);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public List<Hotel> getAllHotelsHandler(){
        return hotelService.getAllHotels();
    }

    @GetMapping("{hotelId}")
    public Hotel getHotelHandler(@PathVariable int hotelId){
        return hotelService.getHotelById(hotelId).orElseThrow(
        ()-> new ResourceNotFoundException("No hotel with id: " + hotelId));
    }

    @PostMapping("/filter")
    public List<Hotel> filterHotels(@RequestBody HotelFilterDTO filter, HttpSession session) {

        if (session.getAttribute("userId") == null){
            throw new UnauthenticatedException("User is not authenticated");
        }

        // I need to make sure the role of the user is a teacher
        if (session.getAttribute("role") == Role.OWNER){
            filter.setOwner((Integer) session.getAttribute("userId"));
        }else{
            filter.setOwner(null);
        }
        return hotelService.filterHotels(filter);
    }

    @PutMapping("{hotelId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('OWNER') and #id == authentication.principal.id)")
    public Optional<Hotel> updateHotelHandler(@PathVariable int hotelId, @RequestBody Hotel updatedHotel, HttpSession session) {
        if(hotelService.checkHotelExisting(hotelId)){
            throw new ResourceNotFoundException("No hotel with id: " + hotelId);
        }

        return hotelService.updateHotel(hotelId, updatedHotel);
    }

    @DeleteMapping("{hotelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('OWNER') and #id == authentication.principal.id)")
    public void deleteHotelHandler(@PathVariable int hotelId, HttpSession session) {
        if(hotelService.checkHotelExisting(hotelId)){
            throw new ResourceNotFoundException("No hotel with id: " + hotelId);
        }

        hotelService.deleteHotel(hotelId);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> resourceNotFoundHandler(ResourceNotFoundException e){
        return Map.of(
                "error", e.getMessage()
        );
    }
}
