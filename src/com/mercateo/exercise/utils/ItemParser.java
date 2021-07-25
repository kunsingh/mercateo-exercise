package com.mercateo.exercise.utils;


public class ItemParser {

    int[] weight;
    int[] costs;
    private int maxWeight;

    public ItemParser(final String line){
        this.maxWeight = Integer.parseInt(line.substring(0, line.indexOf(":")).trim());
        String[] data = line.substring(line.indexOf(":") + 1).trim().split(" ");
        this.weight = new int[data.length];
        this.costs = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            String[] values = data[i].split(",");
            weight[i] = (int)Double.parseDouble(values[1]);
            costs[i] = Integer.parseInt(values[2].substring(1, values[2].length() - 1));
        }
    }

    public int[] getWeight() {
        return weight;
    }

    public void setWeight(int[] weight) {
        this.weight = weight;
    }

    public int[] getCosts() {
        return costs;
    }

    public void setCosts(int[] costs) {
        this.costs = costs;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}
