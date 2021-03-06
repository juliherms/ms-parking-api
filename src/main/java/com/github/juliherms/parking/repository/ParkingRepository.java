package com.github.juliherms.parking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.juliherms.parking.model.Parking;

/**
 * Class responsible to provide table parking
 * 
 * @author jlv
 *
 */
@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {

	Optional<Parking> findByLicense(String license);

	List<Parking> findByModel(String model);

}
