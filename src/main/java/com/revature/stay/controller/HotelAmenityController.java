package com.revature.stay.controller;

import com.revature.stay.exceptions.ResourceNotFoundException;
import com.revature.stay.models.HotelAmenity;
import com.revature.stay.services.HotelAmenityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("hotels/{hotelId}/amenities")
public class HotelAmenityController {

    private final HotelAmenityService hotelAmenityService;

    @Autowired
    public HotelAmenityController(HotelAmenityService hotelAmenityService) {
        this.hotelAmenityService = hotelAmenityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelAmenity createAmenityHandler(@PathVariable int hotelId, @RequestBody HotelAmenity amenity, HttpSession session) {

//        if (session.getAttribute("userId") == null){
//            throw new UnauthenticatedException("User is not authenticated");
//        }
//___
//        // I need to make sure the role of the user is a teacher
//        if (session.getAttribute("role") != Role.OWNER){
//            throw new ForbiddenActionException("You must be a teacher to access this");
//        }
        return hotelAmenityService.createHotelAmenity(hotelId, amenity);
    }


    @GetMapping
    public List<HotelAmenity> getAmenityHandler(@PathVariable int hotelId) {

        if(hotelAmenityService.checkHotelExisting(hotelId)){
            throw new ResourceNotFoundException("No hotel with id: " + hotelId);
        }

        return hotelAmenityService.getHotelAmenitiesByHotel(hotelId);
    }

    @GetMapping("{hotelAmenityId}")
    public HotelAmenity getHotelHandler(@PathVariable int hotelId, @PathVariable int hotelAmenityId){
        if(hotelAmenityService.checkHotelExisting(hotelId)){
            throw new ResourceNotFoundException("No hotel with id: " + hotelId);
        }

        return hotelAmenityService.getHotelAmenityById(hotelId, hotelAmenityId).orElseThrow(
                ()-> new ResourceNotFoundException("No hotel amenity with id: " + hotelAmenityId));
    }

    @PutMapping("{hotelAmenityId}")
    public Optional<HotelAmenity> updateHotelHandler(@PathVariable int hotelId, @PathVariable int hotelAmenityId, @RequestBody HotelAmenity updatedHotel, HttpSession session) {
//        if (session.getAttribute("userId") == null) {
//            throw new UnauthenticatedException("User is not authenticated");
//        }
//
//        if (session.getAttribute("role") != Role.OWNER) {
//            throw new ForbiddenActionException("You must be an owner to modify this hotel");
//        }

        if(hotelAmenityService.checkHotelExisting(hotelId)){
            throw new ResourceNotFoundException("No hotel with id: " + hotelId);
        }

        if(hotelAmenityService.checkHotelAmenityExisting(hotelAmenityId)){
            throw new ResourceNotFoundException("No hotel amenity with id: " + hotelAmenityId);
        }

        // there are related?
        if(hotelAmenityService.checkHotelRelationWithAmenity(hotelId,hotelAmenityId)){
            throw new ResourceNotFoundException("No hotel amenity with id: " + hotelAmenityId + " is related to hotel with id: "+hotelId);
        }

        return hotelAmenityService.updateHotelAmenity(hotelId,hotelAmenityId, updatedHotel);
    }

    @DeleteMapping("{hotelAmenityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHotelHandler(@PathVariable int hotelId, @PathVariable int hotelAmenityId, HttpSession session) {
//        if (session.getAttribute("userId") == null) {
//            throw new UnauthenticatedException("User is not authenticated");
//        }
//
//        if (session.getAttribute("role") != Role.OWNER) {
//            throw new ForbiddenActionException("You must be an owner to delete this hotel");
//        }

        if(hotelAmenityService.checkHotelExisting(hotelId)){
            throw new ResourceNotFoundException("No hotel with id: " + hotelId);
        }

        if(hotelAmenityService.checkHotelAmenityExisting(hotelAmenityId)){
            throw new ResourceNotFoundException("No hotel amenity with id: " + hotelAmenityId);
        }

        // there are related?
        if(hotelAmenityService.checkHotelRelationWithAmenity(hotelId,hotelAmenityId)){
            throw new ResourceNotFoundException("No hotel amenity with id: " + hotelAmenityId + " is related to hotel with id: "+hotelId);
        }

        hotelAmenityService.deleteHotelAmenity(hotelAmenityId);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> resourceNotFoundHandler(ResourceNotFoundException e){
        return Map.of(
                "error", e.getMessage()
        );
    }
}