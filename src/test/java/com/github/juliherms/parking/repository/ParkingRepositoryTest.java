package com.github.juliherms.parking.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.juliherms.parking.model.Parking;
/**
 * Class responsible to test ParkingRepository
 * @author jlv
 *
 */
@DataJpaTest
@DisplayName("Tests for Parking Repository")
public class ParkingRepositoryTest {

	@Autowired
	private ParkingRepository repo;
	
	@Test
	@DisplayName("Save updates parking when successful")
	public void save_UpdatesParking_WhenSuccessful() {
		
		Parking p = createParking();
		Parking savedParking = this.repo.save(p);
		
		savedParking.setLicense("AAAA-1234");
		Parking parkingUpdated = this.repo.save(savedParking);
		
		Assertions.assertThat(savedParking.getLicense()).isEqualTo(parkingUpdated.getLicense());
	}
	
	
	@Test
	@DisplayName("Save create parking when successful")
	public void save_PersistParking_WhenSuccessful() {
		
		Parking p = createParking();
		Parking savedParking = this.repo.save(p);
		
		Assertions.assertThat(savedParking).isNotNull();
		Assertions.assertThat(savedParking.getId()).isNotNull();
		Assertions.assertThat(savedParking.getLicense()).isEqualTo(p.getLicense());
	}
	
	private Parking createParking() {
		
		Parking p = new Parking();
		p.setId(getUUID());
		p.setColor("Branco");
		p.setEntryDate(LocalDateTime.now());
		p.setLicense("ABC-1234");
		p.setModel("Chronos");
		p.setState("PE");
		
		return p;
		
	}
	
	private String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
