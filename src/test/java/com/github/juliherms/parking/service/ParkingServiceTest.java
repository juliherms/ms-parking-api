package com.github.juliherms.parking.service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.juliherms.parking.model.Parking;

@SpringBootTest
public class ParkingServiceTest {
	
	@Autowired
	private ParkingService service;
	
	
	@Test
	@DisplayName("exit_Success")
	public void exit_Success() {
		
		Double INITIAL_TAX = 5D;
		
		Parking p = new Parking();
		p.setColor("White");
		p.setEntryDate(LocalDateTime.now());
		p.setLicense("AAA-1234");
		p.setModel("Celta");
		p.setState("PE");
		
		service.create(p);
		Parking parkingReturn = service.exit(p.getId());
		
		Assert.assertEquals(parkingReturn.getBill(), INITIAL_TAX);
	}

}
