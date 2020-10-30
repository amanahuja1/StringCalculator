package com.incubyte.service;

import com.incubyte.constants.ApplicationConstants;
import com.incubyte.exception.InvalidStringException;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author AMAN AHUJA
 * Add numbers separated by a delimiter
 */
public class StringCalculator {


    /**
     * @param numbers
     * @return sumOfNumbers
     */
    public int add(String numbers) {
        int sumOfNumbers = 0;
        if (Objects.isNull(numbers) || numbers.isEmpty()) {
            return sumOfNumbers;
        }
        String[] numberArray = numbers.split(ApplicationConstants.DEFAULT_DELIMITER);
        if (numberArray.length > 2)
            throw new InvalidStringException(ApplicationConstants.MSG_FOR_MORE_NO);
        int[] parsedNumberArray = Stream.of(numberArray).mapToInt(Integer::parseInt).toArray();
        for (int number : parsedNumberArray) {
            sumOfNumbers += number;
        }
        return sumOfNumbers;
    }
}
