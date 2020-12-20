package com.github.juliherms.parking.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.juliherms.parking.exception.NotFoundExceptionDetails;
import com.github.juliherms.parking.exception.ParkingNotFoundException;
import com.github.juliherms.parking.exception.ValidationExceptionDetails;

import lombok.extern.log4j.Log4j2;

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
				NotFoundExceptionDetails.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.NOT_FOUND.value())
					.title("No Data Found")
					.details(parkingNotFoundException.getMessage())
					.developerMessage("Please check register in the database. Table Parking")
					.build(),
				HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException) {

		//capture errors
		List<FieldError> fieldErros =  methodArgumentNotValidException.getBindingResult().getFieldErrors();
		String fields = fieldErros.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String fieldsMessage = fieldErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		
		return new ResponseEntity<>(
				ValidationExceptionDetails.builder()
					.timestamp(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST.value())
					.title("Bad Request Exception. Invalid Fields")
					.details("Check the field(s) error(s)")
					.developerMessage(methodArgumentNotValidException.getClass().getName())
					.fields(fields)
					.fieldsMessage(fieldsMessage)
					.build(),
				HttpStatus.NOT_FOUND);


	}

}
