package com.incubyte.service;

import com.incubyte.constants.ApplicationConstants;
import com.incubyte.exception.InvalidStringException;
import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
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
    public void testAddUnknownNumbers() {
        String inputNumber = "1,2,3,4,5,6,7,8,9,10";
        int sumOfNumbers = stringCalculator.add(inputNumber);
        assertEquals("Sum is equal for unknown amount of numbers", 55, sumOfNumbers);
    }

    @Test
    public void testAddWithNewline() {
        String inputNumberNewLine = "1\n2,3";
        String inputNumberNewLineComma = "1,\n2,3";
        int sumOfNumbers = stringCalculator.add(inputNumberNewLine);
        assertEquals("Sum is equal", 6, sumOfNumbers);
        Throwable throwable = Assertions.catchThrowable(() -> stringCalculator.add(inputNumberNewLineComma));
        then(throwable).isExactlyInstanceOf(InvalidStringException.class).hasMessage(ApplicationConstants.INVALID_INPUT);
    }

    @Test
    public void testAddWithDifferentDelimiter() {
        String inputNumberOne = "//;\n1;2";
        int sumOfNumbers = stringCalculator.add(inputNumberOne);
        assertEquals("Sum is equal", 3, sumOfNumbers);
    }

    @Test
    public void testAddWithSingleNegativeNumber() {
        String inputNumber = "1,-2";
        Throwable throwable = Assertions.catchThrowable(() -> stringCalculator.add(inputNumber));
        then(throwable).isExactlyInstanceOf(InvalidStringException.class).hasMessage(ApplicationConstants.NEG_NOT_ALLOWED + " and they are [-2]");
    }

    @Test
    public void testAddWithMultipleNegativeNumber() {
        String inputNumber = "1,-2,-3,-4";
        Throwable throwable = Assertions.catchThrowable(() -> stringCalculator.add(inputNumber));
        then(throwable).isExactlyInstanceOf(InvalidStringException.class).hasMessage(ApplicationConstants.NEG_NOT_ALLOWED + " and they are [-2, -3, -4]");
    }

    @Test
    public void testAddWithNoGreaterThan1000() {
        String inputNumber = "2,1001";
        int sumOfNumbers = stringCalculator.add(inputNumber);
        assertEquals("Sum is equal", 2, sumOfNumbers);
    }

    @Test
    public void testAddWithDelimitersOfAnyLength() {
        String inputNumber = "//[***]\n1***2***3";
        int sumOfNumbers = stringCalculator.add(inputNumber);
        assertEquals("Sum is equal", 6, sumOfNumbers);
    }

    @AfterClass
    public static void howManyTimesMethodCalled() {
        int counter = StringCalculator.getCalledCount();
        assertEquals("Counter is equal", 11, counter);
    }


}
