package com.github.juliherms.parking.service;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.juliherms.parking.model.Parking;

@SpringBootTest
public class ParkingCheckOutServiceTest {

	@Autowired
	private ParkingCheckOutService service;

	@Test
	@DisplayName("calculate Tax After One Hour Success")
	void calcTaxAfterOneHourSuccess() {

		Double expectedTax = 9.0;

		LocalDateTime localDateTime = LocalDateTime.now().minusHours(2);

		Parking p = new Parking();
		p.setColor("White");
		p.setEntryDate(localDateTime);
		p.setExitDate(LocalDateTime.now());
		p.setLicense("AAAA-1234");
		p.setModel("Celta");
		p.setState("PE");

		Double tax = service.getBill(p);

		Assert.assertTrue(expectedTax.equals(tax));
	}

	@Test
	@DisplayName("calculate tax after one day success")
	
	void calcTaxAfterOneDaySuccess() {

		Double expectedTaxOneDay = 20D;
		
		LocalDateTime localDateTime = LocalDateTime.now().minusDays(1L);

		Parking p = new Parking();
		p.setColor("White");
		p.setEntryDate(localDateTime);
		p.setExitDate(LocalDateTime.now());
		p.setLicense("AAAA-1234");
		p.setModel("Celta");
		p.setState("PE");

		Double tax = service.getBill(p);
		
		Assert.assertTrue(expectedTaxOneDay.equals(tax));
	}
}
