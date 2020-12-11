package com.github.juliherms.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.juliherms.parking.dto.ParkingDTO;
import com.github.juliherms.parking.mapper.ParkingMapper;
import com.github.juliherms.parking.model.Parking;
import com.github.juliherms.parking.service.ParkingService;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

	@Autowired
	private ParkingService service;

	@Autowired
	private ParkingMapper parkingMapper;

	@GetMapping
	public ResponseEntity<List<ParkingDTO>> findAll() {
		List<Parking> parkingList = service.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}

	@GetMapping("{id}")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
		Parking parking = service.findById(id);
		ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok(parkingDTO);
	}
}
