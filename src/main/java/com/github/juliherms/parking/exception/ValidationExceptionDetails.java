package com.github.juliherms.parking.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Class responsible to capture javax validation exceptions
 * @author jlv
 *
 */
@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {

	private final String fields;
	private final String fieldsMessage;
}
