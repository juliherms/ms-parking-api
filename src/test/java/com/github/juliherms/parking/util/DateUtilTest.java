package com.github.juliherms.parking.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class responsible to test Date Utils
 * 
 * @author jlv
 *
 */
@SpringBootTest
public class DateUtilTest {

	@Autowired
	private DateUtil dateUtil;

	@Test
	@DisplayName("isEqualOrFutureDate successful")
	public void isEqualOrFutureDate_Successful() {

		LocalDate date = LocalDate.of(2050, 01, 01);
		Assert.assertTrue(dateUtil.isEqualOrFutureDate(date));

	}

	@Test
	@DisplayName("isEqualOrFutureDate Error")
	public void isEqualOrFutureDate_Error() {

		LocalDate date = LocalDate.of(2000, 01, 01);
		Assert.assertFalse(dateUtil.isEqualOrFutureDate(date));

	}

	@Test
	@DisplayName("isEqualOrFutureDate ActualDate_Success")
	public void isEqualOrFutureDate_ActualDate_Success() {

		LocalDate date = LocalDate.now();
		Assert.assertTrue(dateUtil.isEqualOrFutureDate(date));

	}

	@Test
	@DisplayName("formatLocalDateTimeToDatabaseStyle_Success")
	public void formatLocalDateTimeToDatabaseStyle_Success() {

		String returnExpected = "2020-12-28 08:21:00";

		LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 28, 8, 21);
		String format = dateUtil.formatLocalDateTimeToDatabaseStyle(localDateTime);

		Assert.assertTrue(format.equals(returnExpected));
	}
	
	@Test
	@DisplayName("formatLocalDateTimeToDatabaseStyle_Error")
	public void formatLocalDateTimeToDatabaseStyle_Error() {

		String returnExpected = "2020-12-28-08:21:00";

		LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 28, 8, 21);
		String format = dateUtil.formatLocalDateTimeToDatabaseStyle(localDateTime);

		Assert.assertFalse(format.equals(returnExpected));
	}
}
