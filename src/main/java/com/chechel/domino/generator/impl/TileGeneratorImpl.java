package com.chechel.domino.generator.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import com.chechel.domino.entity.Tile;
import com.chechel.domino.generator.TileGenerator;
import com.google.common.collect.Lists;

/**
 * Tile Generator
 */
public class TileGeneratorImpl implements TileGenerator {
    private final List<Tile> poolOfTiles = Lists.newArrayList();
    private static final int MIN = 0;
    private static final int MAX = 6;

    @PostConstruct
    private void createPoolOfTiles() {
        for (int i = MIN; i <= MAX; i++) {
            for (int j = MIN; j <= MAX; j++) {
                if (i <= j) {
                    poolOfTiles.add(new Tile(i, j));
                }
            }
        }
        Collections.shuffle(poolOfTiles);
    }

    @Override
    public Collection<Tile> getInputCountTiles(final int inputValue) {
        return poolOfTiles.subList(0, inputValue);
    }
}
