package com.github.juliherms.parking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

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
		.body("license[0]",Matchers.equalTo("PGP-1111"));
		
	}
}
