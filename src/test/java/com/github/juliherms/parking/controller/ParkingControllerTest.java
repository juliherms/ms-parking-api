package com.github.juliherms.parking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.github.juliherms.parking.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest {

	@LocalServerPort
	private int randomPort;

	@BeforeEach
	public void setUpTest() {
		
		System.out.println(randomPort);
		RestAssured.port = randomPort;
	}
	
	@Test
	void whenFindAllThenCheckResult() {
		
		RestAssured.given()
		.when()
		.get("/parkings")
		.then()
		.statusCode(HttpStatus.OK.value())
		.body("license[0]",Matchers.equalTo("PGP-1111"));
		
	}
	
	@Test
	void whenCreateThenCheckIsCreated() {
		
		ParkingCreateDTO createDTO = new ParkingCreateDTO();
		createDTO.setColor("Amarelo");
		createDTO.setLicense("WRT-5555");
		createDTO.setModel("Brasilia");
		createDTO.setState("SP");
		
		RestAssured.given()
			.when()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(createDTO)
			.post("/parkings")
			.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("license",Matchers.equalTo("WRT-5555"));
		
		
		
	}
}
