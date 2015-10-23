package com.chechel.domino.service;

import com.chechel.domino.entity.Answer;
import com.chechel.domino.entity.Tile;

import java.util.Collection;

/**
 * Chain Service
 */
public interface ChainService {
    /**
     * Find longest chain
     * @param tiles tiles
     * @return answer result
     */
    Answer getLongestChain(final Collection<Tile> tiles);
}
