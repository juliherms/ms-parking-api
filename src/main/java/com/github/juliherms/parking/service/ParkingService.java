package com.github.juliherms.parking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.juliherms.parking.exception.ParkingNotFoundException;
import com.github.juliherms.parking.model.Parking;
import com.github.juliherms.parking.repository.ParkingRepository;

@Service
public class ParkingService {

	@Autowired
	private ParkingRepository repo;

	public List<Parking> findAll() {
		
		return repo.findAll();
		
	}

	public Parking findById(String id) {

		Parking p = repo.findById(id).orElseThrow( () ->
				new ParkingNotFoundException(id)
				);
		
		return p;
	}

	public void delete(String id) {

		findById(id);
		repo.deleteById(id);
	}

	public Parking create(Parking parking) {

		String uuid = getUUID();
		parking.setId(uuid);
		parking.setEntryDate(LocalDateTime.now());

		repo.save(parking);
		
		return parking;
	}

	public Parking exit(String id) {

		// recuperar o parking
		// atualizar data de saida
		// calcular o valor

		return null;
	}

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
