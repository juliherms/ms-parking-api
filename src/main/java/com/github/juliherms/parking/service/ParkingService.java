package com.github.juliherms.parking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.juliherms.parking.exception.CarNotFoundException;
import com.github.juliherms.parking.exception.ParkingNotFoundException;
import com.github.juliherms.parking.model.Parking;
import com.github.juliherms.parking.repository.ParkingRepository;

@Service
public class ParkingService {

	@Autowired
	private ParkingRepository repo;
	
	@Autowired
	private ParkingCheckOutService parkingCheckOutService;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Parking> findAll() {
		
		return repo.findAll();
		
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Parking> findByModel(String model){
		
		return repo.findByModel(model);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Parking findByLicense(String license) {
	
		Parking p = repo.findByLicense(license).orElseThrow( () ->
				new CarNotFoundException(license)
				);
		
		return p;
	}
	

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Parking findById(String id) {

		Parking p = repo.findById(id).orElseThrow( () ->
				new ParkingNotFoundException(id)
				);
		
		return p;
	}

	@Transactional
	public void delete(String id) {

		findById(id);
		repo.deleteById(id);
	}

	@Transactional
	public Parking create(Parking parking) {

		String uuid = getUUID();
		parking.setId(uuid);
		parking.setEntryDate(LocalDateTime.now());

		repo.save(parking);
		
		return parking;
	}

	@Transactional
	public Parking exit(String id) {

		Parking p = findById(id);
		p.setExitDate(LocalDateTime.now());
		p.setBill(parkingCheckOutService.getBill(p));
		
		repo.save(p);
		
		return p;
	}

	@Transactional
	public Parking update(String id, Parking p) {

		Parking actualParking = findById(id);
		
		//update fields
		actualParking.setColor(p.getColor());
		actualParking.setState(p.getState());
		actualParking.setModel(p.getModel());
		actualParking.setLicense(p.getLicense());
		
		repo.save(actualParking);
		
		return actualParking;
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
