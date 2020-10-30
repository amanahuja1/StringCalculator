package com.incubyte.service;

import com.incubyte.constants.ApplicationConstants;
import com.incubyte.exception.InvalidStringException;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
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
        String[] numberArray = handleDelimiter(numbers);
        int[] parsedNumberArray = Stream.of(numberArray).mapToInt(Integer::parseInt).toArray();
        for (int number : parsedNumberArray) {
            sumOfNumbers += number;
        }
        return sumOfNumbers;
    }

    private String[] handleDelimiter(String numbers) {
        String[] numberArray;
        if (numbers.contains(System.lineSeparator()) || numbers.contains("\n")) {
            numbers = numbers.replaceAll(System.lineSeparator(), ApplicationConstants.DEFAULT_DELIMITER);
            numbers = numbers.replace("\n", ApplicationConstants.DEFAULT_DELIMITER);
            numberArray = numbers.split(ApplicationConstants.DEFAULT_DELIMITER);
            Optional<String> emptyOptional = Arrays.stream(numberArray).filter(number -> (Objects.isNull(number) || number.isEmpty())).findFirst();
            if (emptyOptional.isPresent()) {
                throw new InvalidStringException(ApplicationConstants.INVALID_INPUT);
            }
        } else {
            numberArray = numbers.split(ApplicationConstants.DEFAULT_DELIMITER);
        }
        return numberArray;
    }
}