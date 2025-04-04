package com.revature.stay.repos;

import com.revature.stay.models.HotelOwner;
import com.revature.stay.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This repository handles hotel owner specific data.
 * It's not a true JPA repository but works alongside the UserRepository to manage hotel owner details.
 */
@Repository
public class HotelOwnerRepository {

    private final UserRepository userRepository;

    // This map stores additional hotel owner details
    // In a real application, this would be a database table with a foreign key to users
    private final Map<Long, HotelOwner> ownerDetailsMap = new HashMap<>();

    public HotelOwnerRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find a hotel owner by ID
     */
    public Optional<HotelOwner> findById(Long id) {
        // First, check if user exists and is an owner
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent() && "OWNER".equals(userOptional.get().getRole())) {
            User user = userOptional.get();

            // Get owner details or create a new one if not exists
            HotelOwner hotelOwner = ownerDetailsMap.getOrDefault(id, new HotelOwner(user));
            return Optional.of(hotelOwner);
        }

        return Optional.empty();
    }

    /**
     * Find a hotel owner by email
     */
    public Optional<HotelOwner> findByEmail(String email) {
        // First, check if user exists and is an owner
        Optional<User> userOptional = userRepository.findByEmailAndRole(email, "OWNER");

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Get owner details or create a new one if not exists
            HotelOwner hotelOwner = ownerDetailsMap.getOrDefault(user.getId(), new HotelOwner(user));
            return Optional.of(hotelOwner);
        }

        return Optional.empty();
    }

    /**
     * Get all hotel owners
     */
    public List<HotelOwner> findAll() {
        // Get all users with OWNER role
        List<User> owners = userRepository.findAll().stream()
                .filter(user -> "OWNER".equals(user.getRole()))
                .collect(Collectors.toList());

        // Convert to HotelOwner objects
        return owners.stream()
                .map(user -> {
                    // Get owner details or create a new one if not exists
                    return ownerDetailsMap.getOrDefault(user.getId(), new HotelOwner(user));
                })
                .collect(Collectors.toList());
    }

    /**
     * Save hotel owner details
     */
    public HotelOwner save(HotelOwner hotelOwner) {
        // First save/update the user
        User user = hotelOwner.toUser();
        user.setRole("OWNER");
        User savedUser = userRepository.save(user);

        // Update the hotel owner with the latest user data
        hotelOwner.setId(savedUser.getId());
        hotelOwner.setFirstName(savedUser.getFirstName());
        hotelOwner.setLastName(savedUser.getLastName());
        hotelOwner.setEmail(savedUser.getEmail());

        // Save hotel owner specific details
        ownerDetailsMap.put(savedUser.getId(), hotelOwner);

        return hotelOwner;
    }

    /**
     * Delete a hotel owner
     */
    public void delete(HotelOwner hotelOwner) {
        // Remove from owner details map
        ownerDetailsMap.remove(hotelOwner.getId());

        // Get the user
        Optional<User> userOptional = userRepository.findById(hotelOwner.getId());

        // Delete the user if it exists
        userOptional.ifPresent(userRepository::delete);
    }

    /**
     * Check if a business name already exists
     */
    public boolean existsByBusinessName(String businessName) {
        return ownerDetailsMap.values().stream()
                .anyMatch(owner -> businessName.equals(owner.getBusinessName()));
    }

    /**
     * Update hotel owner details
     */
    public HotelOwner updateDetails(Long id, HotelOwner updatedDetails) {
        // Check if owner exists
        Optional<HotelOwner> ownerOptional = findById(id);

        if (ownerOptional.isPresent()) {
            HotelOwner existingOwner = ownerOptional.get();

            // Update business details
            existingOwner.setBusinessName(updatedDetails.getBusinessName());
            existingOwner.setBusinessAddress(updatedDetails.getBusinessAddress());
            existingOwner.setPhoneNumber(updatedDetails.getPhoneNumber());

            // Save updated details
            ownerDetailsMap.put(id, existingOwner);

            return existingOwner;
        }

        throw new RuntimeException("Hotel owner not found with id: " + id);
    }
}