package com.chechel.domino.entity;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


public class TileTest {
    private static final String ERROR_MESSAGE_EQUALS = "Tiles aren't equals!";
    private static final String ERROR_MESSAGE = "Tiles are equals!";

    @Test
    public void shouldRetrurnTrueForEqualsTiles() throws Exception {
        Tile tile1 = new Tile(1, 1);
        Tile tile2 = new Tile(1, 1);
        assertTrue(ERROR_MESSAGE_EQUALS,tile1.equals(tile2));

        tile1 = new Tile(4, 6);
        tile2 = new Tile(6, 4);
        assertTrue(ERROR_MESSAGE_EQUALS,tile1.equals(tile2));
    }

    @Test
    public void shouldRetrurnFalseForNotEqualsTiles() throws Exception {
        Tile tile1 = new Tile(1, 2);
        Tile tile2 = new Tile(1, 1);
        assertFalse(ERROR_MESSAGE, tile1.equals(tile2));

        tile1 = new Tile(4, 6);
        tile2 = new Tile(6, 3);
        assertFalse(ERROR_MESSAGE, tile1.equals(tile2));
    }
}