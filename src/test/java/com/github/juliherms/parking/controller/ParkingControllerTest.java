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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest extends AbstractContainerBase  {

	@LocalServerPort
	private int randomPort;

	@BeforeEach
	public void setUpTest() {

		RestAssured.port = randomPort;
	}

	@Test
	@DisplayName("Test list all parkings success")
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
	@DisplayName("Test Find Parking By Id Error")
	void whenFindParkingByIdThenCheckResultError() {

		RestAssured
			.given()
			.auth()
			.basic("user","Test@123456")
			.when()
			.get("/parkings/0ae5daf51a02444294bf2b9f5c231822")
			.then()
			.statusCode(HttpStatus.NOT_FOUND.value());

	}
	
	@Test
	@DisplayName("Test lists all parkings and car by model error")
	void whenFindCarWithoutModelError() {
		
		RestAssured
		.given()
		.auth()
		.basic("user","Test@123456")
		.when()
		.get("/parkings/car")
		.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
		
	}
	
	@Test
	@DisplayName("Test lists all parkings and car by license error")
	void whenFindCarWithoutLicenseError() {
		
		RestAssured
		.given()
		.auth()
		.basic("user","Test@123456")
		.when()
		.get("/parkings/find")
		.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
		
	}
	
	@Test
	@DisplayName("Test lists all parkings and car by license success")
	void whenFindCarWithLicenseSuccess() {
		
		RestAssured
		.given()
		.auth()
		.basic("user","Test@123456")
		.when()
		.get("/parkings/find?license=PZH5451")
		.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
		
	}
	
	@Test
	@DisplayName("Test lists all parkings and car by model success")
	void whenFindCarWithModelSuccess() {
		
		RestAssured
		.given()
		.auth()
		.basic("user","Test@123456")
		.when()
		.get("/parkings/car?model=celta")
		.then()
		.statusCode(HttpStatus.OK.value());
		
	}
	
	
	@Test
	@DisplayName("Test Create Parking success")
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
	}
	
	@Test
	@DisplayName("Test Create Parking Error")
	void whenCreateThenCheckIsCreatedError() {

		ParkingCreateDTO createDTO = new ParkingCreateDTO();
		createDTO.setColor("Amarelo");
		createDTO.setModel("Brasilia");
		createDTO.setLicense(null);
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
			.statusCode(HttpStatus.BAD_REQUEST.value());
			
	}
}
