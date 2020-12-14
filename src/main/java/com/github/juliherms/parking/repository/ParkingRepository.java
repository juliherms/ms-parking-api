package com.github.juliherms.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.juliherms.parking.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

}
