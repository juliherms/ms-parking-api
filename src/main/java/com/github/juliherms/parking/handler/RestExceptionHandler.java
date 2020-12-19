package com.github.juliherms.parking.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.juliherms.parking.exception.NotFoundExceptionDetails;
import com.github.juliherms.parking.exception.ParkingNotFoundException;

/**
 * Class responsible to intercept all Controllers
 * 
 * @author jlv
 *
 */
@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ParkingNotFoundException.class)
	public ResponseEntity<NotFoundExceptionDetails> handlerNotFoundException(
			ParkingNotFoundException parkingNotFoundException) {

		return new ResponseEntity<>(
				NotFoundExceptionDetails.builder().timestamp(LocalDateTime.now()).status(HttpStatus.NOT_FOUND.value())
						.title("No Data Found").details(parkingNotFoundException.getMessage())
						.developerMessage("Please check register in the database. Table Parking").build(),
				HttpStatus.NOT_FOUND);

	}

}
