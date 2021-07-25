package com.mercateo.exercise.utils;

import java.util.Arrays;

public class Validator {

    private static final int MAX_WEIGHT = 100;
    private static final int MAX_NUMBER_OF_ITEMS = 15;
    private static final int MAX_VALUE = 100;

    public static void validateMaximumWeight(final int maxWeight){
        if(maxWeight <= 1 || maxWeight > MAX_WEIGHT){
            throw new IllegalArgumentException("Weight range a package can contain is 1 to 100!");
        }
    }

    public static void validateNumberOfItems(final int noOfItems){
        if(noOfItems < 1 || noOfItems > MAX_NUMBER_OF_ITEMS){
            throw new IllegalArgumentException("Items range you can choose from is 1 to 15!");
        }
    }

    public static void validateMaxValue(final int[] values){
        Arrays.stream(values).forEach(value -> {
            if(value < 0 || value > MAX_VALUE){
                throw new IllegalArgumentException("Value range a cost and weight can contain is 0 to 100!");
            }
        });

    }
}
