package com.github.juliherms.parking.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotFoundExceptionDetails {

	private String title;
	private int status;
	private String details;
	private String developerMessage;
	private LocalDateTime timestamp;
}
