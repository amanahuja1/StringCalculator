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

    private String delimiter;
    private static int counter;


    /**
     * @param numbers
     * @return sumOfNumbers
     */
    public int add(String numbers) {
        counter++;
        int sumOfNumbers = 0;
        if (Objects.isNull(numbers) || numbers.isEmpty()) {
            return sumOfNumbers;
        }
        String[] numberArray = handleDelimiter(numbers);
        int[] parsedNumberArray = Stream.of(numberArray).mapToInt(Integer::parseInt).toArray();
        areThereNegatives(parsedNumberArray);
        for (int number : parsedNumberArray) {
            if (number > 1000) {
                continue;
            }
            sumOfNumbers += number;
        }
        return sumOfNumbers;
    }

    private String[] handleDelimiter(String numbers) {
        String[] numberArray;
        if (numbers.matches("[\\/\\/][^0-9a-zA-Z]+[\n][0-9[^0-9a-zA-Z]]+")) {
            if (numbers.contains("[")) {
                numbers = numbers.replaceAll("[\\/\\/]+[\\[][^0-9a-zA-Z]+[\\]][\n]", "");
            } else {
                delimiter = String.valueOf(numbers.charAt(2));
                numbers = numbers.replaceAll(("[\\/\\/]+" + delimiter + "[\n]"), "");
            }

            numberArray = numbers.split("[^0-9a-zA-Z]+");
        } else {
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
        }
        return numberArray;

    }

    private void areThereNegatives(int[] parsedNumberArray) {
        int[] negativeNumberArray = Arrays.stream(parsedNumberArray).filter(input -> input < 0).toArray();
        if (Objects.nonNull(negativeNumberArray) && negativeNumberArray.length > 0) {
            String negativeNumbers = Arrays.toString(negativeNumberArray);
            throw new InvalidStringException(ApplicationConstants.NEG_NOT_ALLOWED + " and they are " + negativeNumbers);
        }
    }

    public static int getCalledCount() {
        return counter;
    }
}