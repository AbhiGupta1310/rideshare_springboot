package org.example.newRideShare.service;

import org.example.newRideShare.dto.CreateRideRequest;
import org.example.newRideShare.exception.BadRequestException;
import org.example.newRideShare.exception.NotFoundException;
import org.example.newRideShare.model.Ride;
import org.example.newRideShare.model.User;
import org.example.newRideShare.repository.RideRepository;
import org.example.newRideShare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {
    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    public RideService(RideRepository rideRepository, UserRepository userRepository) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
    }

    public Ride createRide(CreateRideRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Ride ride = new Ride();
        ride.setUserId(user.getId());
        ride.setPickupLocation(request.getPickupLocation());
        ride.setDropLocation(request.getDropLocation());

        return rideRepository.save(ride);
    }

    public List<Ride> getPendingRides() {
        return rideRepository.findByStatus("REQUESTED");
    }

    public Ride acceptRide(String rideId, String driverUsername) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new NotFoundException("Ride not found"));

        if (!"REQUESTED".equals(ride.getStatus())) {
            throw new BadRequestException("Ride is not available for acceptance");
        }

        User driver = userRepository.findByUsername(driverUsername)
                .orElseThrow(() -> new NotFoundException("Driver not found"));

        ride.setDriverId(driver.getId());
        ride.setStatus("ACCEPTED");

        return rideRepository.save(ride);
    }

    public Ride completeRide(String rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new NotFoundException("Ride not found"));

        if (!"ACCEPTED".equals(ride.getStatus())) {
            throw new BadRequestException("Ride must be accepted before completion");
        }

        ride.setStatus("COMPLETED");
        return rideRepository.save(ride);
    }

    public List<Ride> getUserRides(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return rideRepository.findByUserId(user.getId());
    }
}
