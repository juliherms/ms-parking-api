package com.github.juliherms.parking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.github.juliherms.parking.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

/**
 * Class responsible to test integration with testContainer's
 * @author jlv
 *
 */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest /* extends AbstractContainerBase */ {

	@Test
	void contextLoads() {
	}
//	@LocalServerPort
//	private int randomPort;
/**
	@BeforeEach
	public void setUpTest() {

		System.out.println(randomPort);
		RestAssured.port = randomPort;
	}

	@Test
	@DisplayName("Test list all parkings sucess")
	void whenFindAllThenCheckResult() {

		RestAssured
			.given()
			.auth()
			.basic("user","Test@123456")
			.when()
			.get("/parkings")
			.then()
			.statusCode(HttpStatus.OK.value());

	}

	@Test
	@DisplayName("Test Create Parking sucess")
	void whenCreateThenCheckIsCreated() {

		ParkingCreateDTO createDTO = new ParkingCreateDTO();
		createDTO.setColor("Amarelo");
		createDTO.setLicense("WRT-5555");
		createDTO.setModel("Brasilia");
		createDTO.setState("SP");

		RestAssured
			.given()
			.when()
			.auth().basic("user","Test@123456")
			.when()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(createDTO)
			.post("/parkings")
			.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("license", Matchers.equalTo("WRT-5555"));
	} **/
}
