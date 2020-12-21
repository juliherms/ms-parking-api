package com.github.juliherms.parking.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.juliherms.parking.dto.ParkingCreateDTO;
import com.github.juliherms.parking.dto.ParkingDTO;
import com.github.juliherms.parking.mapper.ParkingMapper;
import com.github.juliherms.parking.model.Parking;
import com.github.juliherms.parking.service.ParkingService;
import com.github.juliherms.parking.util.DateUtil;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

/**
 * Class responsible to provide Parking End Point
 * @author jlv
 *
 */
@RestController
@RequestMapping("/parkings")
@Api(tags = "Parking")
@Log4j2
public class ParkingController {

	@Autowired
	private ParkingService service;
	
	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private ParkingMapper parkingMapper;

	@GetMapping
	@Timed("get.parkings")
	@ApiOperation("Find all parkings")
	public ResponseEntity<Page<ParkingDTO>> list(Pageable pageable) {
		
		//TODO log
		log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));

		Page<Parking> parkingList = service.findAll(pageable);
		Page<ParkingDTO> result = parkingMapper.converterPageEntityToDto(parkingList);

		return ResponseEntity.ok(result);
	}
	
	@ApiOperation("Find all parkings/car by model")
	@GetMapping(path="/car")
	public ResponseEntity<List<ParkingDTO>> findByModel(@RequestParam(name = "model") String model) {
		
		List<Parking> parkingList = service.findByModel(model);
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);

		return ResponseEntity.ok(result);
	}


	@ApiOperation("Find Parking by Id")
	@GetMapping("{id}")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {

		Parking parking = service.findById(id);
		ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);

		return ResponseEntity.ok(parkingDTO);
	}
	
	@ApiOperation("Find Parking by License")
	@GetMapping(path="/find")
	public ResponseEntity<ParkingDTO> findByLicense(@RequestParam(required = true, name = "license") String license) {

		Parking parking = service.findByLicense(license);
		ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);

		return ResponseEntity.ok(parkingDTO);
	}


	@PostMapping
	@Timed("create.parking")
	public ResponseEntity<ParkingDTO> create(@RequestBody @Valid ParkingCreateDTO dto) {

		Parking p = parkingMapper.toParkingCreate(dto);
		Parking parkingCreated = service.create(p);
		ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parkingCreated);

		return ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody @Valid ParkingCreateDTO dto) {

		Parking p = parkingMapper.toParkingCreate(dto);
		Parking parkingCreated = service.update(id,p);
		ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parkingCreated);

		return ResponseEntity.status(HttpStatus.OK).body(parkingDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<ParkingDTO> exit(@PathVariable String id){
		
		Parking parking = service.exit(id);
		return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
	}
}
