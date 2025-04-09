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

    private final Map<Long, HotelOwner> ownerDetailsMap = new HashMap<>();

    public HotelOwnerRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find a hotel owner by ID
     */
    public Optional<HotelOwner> findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent() && "OWNER".equals(userOptional.get().getRole())) {
            User user = userOptional.get();

            HotelOwner hotelOwner = ownerDetailsMap.getOrDefault(id, new HotelOwner(user));
            return Optional.of(hotelOwner);
        }

        return Optional.empty();
    }

    /**
     * Find a hotel owner by email
     */
    public Optional<HotelOwner> findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmailAndRole(email, "OWNER");

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            HotelOwner hotelOwner = ownerDetailsMap.getOrDefault(user.getId(), new HotelOwner(user));
            return Optional.of(hotelOwner);
        }

        return Optional.empty();
    }

    /**
     * Get all hotel owners
     */
    public List<HotelOwner> findAll() {
        List<User> owners = userRepository.findAll().stream()
                .filter(user -> "OWNER".equals(user.getRole()))
                .collect(Collectors.toList());

        return owners.stream()
                .map(user -> {
                    return ownerDetailsMap.getOrDefault(user.getId(), new HotelOwner(user));
                })
                .collect(Collectors.toList());
    }

    /**
     * Save hotel owner details
     */
    public HotelOwner save(HotelOwner hotelOwner) {
        User user = hotelOwner.toUser();
        user.setRole("OWNER");
        User savedUser = userRepository.save(user);

        hotelOwner.setId(savedUser.getId());
        hotelOwner.setFirstName(savedUser.getFirstName());
        hotelOwner.setLastName(savedUser.getLastName());
        hotelOwner.setEmail(savedUser.getEmail());

        ownerDetailsMap.put(savedUser.getId(), hotelOwner);

        return hotelOwner;
    }

    /**
     * Delete a hotel owner
     */
    public void delete(HotelOwner hotelOwner) {
        ownerDetailsMap.remove(hotelOwner.getId());

        Optional<User> userOptional = userRepository.findById(hotelOwner.getId());

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
        Optional<HotelOwner> ownerOptional = findById(id);

        if (ownerOptional.isPresent()) {
            HotelOwner existingOwner = ownerOptional.get();

            existingOwner.setBusinessName(updatedDetails.getBusinessName());
            existingOwner.setBusinessAddress(updatedDetails.getBusinessAddress());
            existingOwner.setPhoneNumber(updatedDetails.getPhoneNumber());

            ownerDetailsMap.put(id, existingOwner);

            return existingOwner;
        }

        throw new RuntimeException("Hotel owner not found with id: " + id);
    }
}