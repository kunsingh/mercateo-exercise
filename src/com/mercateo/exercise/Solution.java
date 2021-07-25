package com.mercateo.exercise;


import com.mercateo.exercise.utils.ItemParser;
import com.mercateo.exercise.utils.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This problem is a variation of 0-1 KNAPSACK problem.
 *
 * The solution uses Dynamic programming concept of memoization of intermediate result
 * and backtracking to find the index of item selected.
 *
 * To RUN
 * ------
 * - Go to src and compile the files
 * src> javac com/mercateo/exercise/*.java
 *
 * - then run java command
 * src> java com/mercateo/exercise/Solution  <<filePath>>
 */
public class Solution {

    public void getMaximumCostPackage(int[] costs, int[] weights, int maxWeight) {

        //Basic validations
        if (costs == null || costs.length == 0 || weights == null || weights.length == 0) {
            throw new IllegalArgumentException("Either argument is null or have no value to process!");
        }
        int n = costs.length;

        //Constraints validations
        Validator.validateMaximumWeight(maxWeight);
        Validator.validateNumberOfItems(n);
        Validator.validateMaxValue(costs);
        Validator.validateMaxValue(weights);

        //Memoization array
        int[][] F = new int[n + 1][maxWeight + 1];
        F[0][0] = 0;

        for (int p = 1; p <= costs.length; p++) {
            for (int w = 0; w <= maxWeight; w++) {
                if (weights[p - 1] <= w) {
                    //Get Max - Either we can include the item or not, if included then add the cost of that item
                    F[p][w] = Math.max(F[p - 1][w - weights[p - 1]] + costs[p - 1], F[p - 1][w]);
                } else {
                    //we will not include the item...just copy previous one
                    F[p][w] = F[p - 1][w];
                }
            }
        }

        // backtracking to get the item index
        int[] selected = new int[n + 1];
        int weight = maxWeight;
        int j = 0;
        for (int i = weights.length; i > 0; i--) {
            if ((weight - weights[i - 1] >= 0) && (F[i][weight] - F[i - 1][(weight - weights[i - 1])] == costs[i - 1])) {
                selected[j++] = i - 1;
                weight -= weights[i - 1];
            }
        }

        //Output builder
        StringBuilder result = new StringBuilder();
        for (int k = j - 1; k >= 0; k--) {
            result.append(selected[k] + 1).append(",");
        }
        if (result.length() > 0) {
            System.out.println(result.substring(0, result.lastIndexOf(",")));
        } else {
            System.out.println("-");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        final File file = new File(args[0]);
        final Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (!line.isEmpty()) {
                final ItemParser itemParser = new ItemParser(line);
                new Solution().getMaximumCostPackage(itemParser.getCosts(), itemParser.getWeight(), itemParser.getMaxWeight());
            }
        }
    }
}
