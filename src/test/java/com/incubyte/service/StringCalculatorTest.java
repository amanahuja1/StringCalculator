package com.incubyte.service;

import com.incubyte.constants.ApplicationConstants;
import com.incubyte.exception.InvalidStringException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertEquals;

/**
 * @author AMAN AHUJA
 * Test Suite for testing Add functionality of a number string separated by a delimiter.
 */
public class StringCalculatorTest {

    private final StringCalculator stringCalculator = new StringCalculator();


    @Test
    public void testAddEmptyNumbersComma() {
        String inputEmpty = "";
        String inputOneNumber = "1";
        String inputTwoNumber = "1,2";
        int sumOfNumbers = stringCalculator.add(inputEmpty);
        assertEquals("Sum is 0 for empty String", 0, sumOfNumbers);
        sumOfNumbers = stringCalculator.add(inputOneNumber);
        assertEquals("Sum is equal for one number String", 1, sumOfNumbers);
        sumOfNumbers = stringCalculator.add(inputTwoNumber);
        assertEquals("Sum is equal for two number String", 3, sumOfNumbers);
    }

    @Test
    public void testAddMoreThanTwoNumbers() {
        String inputNumber = "1,2,3";
        Throwable throwable = Assertions.catchThrowable(() -> stringCalculator.add(inputNumber));
        then(throwable).isExactlyInstanceOf(InvalidStringException.class).hasMessage(ApplicationConstants.MSG_FOR_MORE_NO);
    }

}
