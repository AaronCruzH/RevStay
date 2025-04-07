package com.revature.stay.services;


import com.revature.stay.dto.request.HotelOwnerRegistrationDto;
import com.revature.stay.models.User;
import com.revature.stay.repos.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelOwnerServiceImpl implements HotelOwnerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // This is a simplified approach. In a real application, you would have:
    // 1. A Hotel entity
    // 2. A HotelRepository
    // 3. A proper relationship between User(OWNER) and Hotel entities
    // This map is just for demonstration purposes
    private final Map<Long, List<Long>> ownerHotelsMap = new HashMap<>();

    public HotelOwnerServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getHotelOwnerById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel owner not found with id: " + id));

        if (!user.getRole().equals("OWNER")) {
            throw new RuntimeException("User with id: " + id + " is not a hotel owner");
        }

        return user;
    }

    @Override
    public User getHotelOwnerByEmail(String email) {
        return userRepository.findByEmailAndRole(email, "OWNER")
                .orElseThrow(() -> new RuntimeException("Hotel owner not found with email: " + email));
    }

    @Override
    public List<User> getAllHotelOwners() {
        return userRepository.findAll().stream()
                .filter(user -> "OWNER".equals(user.getRole()))
                .toList();
    }

    @Override
    public User updateHotelOwner(Long id, HotelOwnerRegistrationDto ownerDetails) {
        User owner = getHotelOwnerById(id);

        // Update basic user fields
        owner.setFirstName(ownerDetails.getFirstName());
        owner.setLastName(ownerDetails.getLastName());
        owner.setEmail(ownerDetails.getEmail());

        // Only update password if it's provided
        if (ownerDetails.getPassword() != null && !ownerDetails.getPassword().isEmpty()) {
            owner.setPassword(passwordEncoder.encode(ownerDetails.getPassword()));
        }

        // Save business details in a separate table (not implemented here)
        // This would involve updating a related entity with business details

        return userRepository.save(owner);
    }

    @Override
    public void deleteHotelOwner(Long id) {
        User owner = getHotelOwnerById(id);
        userRepository.delete(owner);

        // Clean up hotel associations
        ownerHotelsMap.remove(id);
    }

    @Override
    public void addHotel(Long ownerId, Long hotelId) {
        // Verify the owner exists
        getHotelOwnerById(ownerId);

        // Add the hotel to the owner's list
        ownerHotelsMap.computeIfAbsent(ownerId, k -> new ArrayList<>()).add(hotelId);
    }

    @Override
    public void removeHotel(Long ownerId, Long hotelId) {
        // Verify the owner exists
        getHotelOwnerById(ownerId);

        // Remove the hotel from the owner's list
        if (ownerHotelsMap.containsKey(ownerId)) {
            ownerHotelsMap.get(ownerId).remove(hotelId);
        }
    }

    @Override
    public List<Long> getOwnedHotels(Long ownerId) {
        // Verify the owner exists
        getHotelOwnerById(ownerId);

        // Return the list of hotels owned by this owner
        return ownerHotelsMap.getOrDefault(ownerId, new ArrayList<>());
    }
}
