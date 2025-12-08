package org.example.newRideShare.controller;

import jakarta.validation.Valid;
import org.example.newRideShare.dto.CreateRideRequest;
import org.example.newRideShare.model.Ride;
import org.example.newRideShare.service.RideService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RideController {
    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/rides")
    public ResponseEntity<Ride> createRide(@Valid @RequestBody CreateRideRequest request,
                                           Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(rideService.createRide(request, username));
    }

    @GetMapping("/user/rides")
    public ResponseEntity<List<Ride>> getUserRides(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(rideService.getUserRides(username));
    }

    @GetMapping("/driver/rides/requests")
    public ResponseEntity<List<Ride>> getPendingRides() {
        return ResponseEntity.ok(rideService.getPendingRides());
    }

    @PostMapping("/driver/rides/{rideId}/accept")
    public ResponseEntity<Ride> acceptRide(@PathVariable String rideId,
                                           Authentication authentication) {
        String driverUsername = authentication.getName();
        return ResponseEntity.ok(rideService.acceptRide(rideId, driverUsername));
    }

    @PostMapping("/rides/{rideId}/complete")
    public ResponseEntity<Ride> completeRide(@PathVariable String rideId) {
        return ResponseEntity.ok(rideService.completeRide(rideId));
    }
}
