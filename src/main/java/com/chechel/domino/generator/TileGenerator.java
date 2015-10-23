package com.chechel.domino.generator;

import com.chechel.domino.entity.Tile;

import java.util.Collection;

/**
 * Generator unique tiles
 */
public interface TileGenerator {
    /**
     * Get count tiles from pool
     * @param inputValue input value
     * @return collection of tiles
     */
    Collection<Tile> getInputCountTiles(final int inputValue);
}
