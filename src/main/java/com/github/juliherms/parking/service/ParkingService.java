package com.github.juliherms.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.juliherms.parking.exception.ParkingNotFoundException;
import com.github.juliherms.parking.model.Parking;

@Service
public class ParkingService {

	private static Map<String, Parking> parkingMap = new HashMap<>();

	public List<Parking> findAll() {
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	public Parking findById(String id) {

		Parking p = parkingMap.get(id);

		if (p == null) {
			throw new ParkingNotFoundException(id);
		}

		return p;
	}

	public void delete(String id) {

		Parking p = findById(id);
		parkingMap.remove(p.getId());
	}

	public Parking create(Parking parking) {

		String uuid = getUUID();
		parking.setId(uuid);
		parking.setEntryDate(LocalDateTime.now());

		parkingMap.put(uuid, parking);

		return parking;
	}
	
	public Parking exit(String id) {
		
		//recuperar o parking
		//atualizar data de saida
		//calcular o valor
		
		return null;
	}

	public Parking update(String id,Parking p) {

		Parking actualParking = findById(id);
		actualParking.setColor(p.getColor());
		parkingMap.replace(id,actualParking);
		return actualParking;
	}

	static {
		String id = getUUID();
		String id1 = getUUID();
		Parking parking = new Parking(id, "PGP-1111", "PE", "CELTA", "PRETO");
		Parking parking1 = new Parking(id1, "PGP-1620", "PE", "CRONOS", "BRANCO");

		parkingMap.put(id, parking);
		parkingMap.put(id1, parking1);
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
