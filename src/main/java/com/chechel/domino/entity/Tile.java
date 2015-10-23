package com.chechel.domino.entity;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Tile entity
 */
public class Tile {

    private final Integer leftValue;
    private final Integer rightValue;

    public Tile(Integer leftValue, Integer rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public Integer getLeftValue() {
        return leftValue;
    }

    public Integer getRightValue() {
        return rightValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        List<Integer> values = Lists.newArrayList(leftValue, rightValue);
        values.removeAll(Arrays.asList(tile.leftValue, tile.rightValue));
        return values.isEmpty();
    }

    @Override
    public int hashCode() {
        int result = leftValue.hashCode();
        result = 31 * result + rightValue.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nTile[ " + leftValue +" | " + rightValue +" ]";
    }
}
