package com.chechel.domino.generator.impl;

import com.chechel.domino.entity.Tile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chechel.domino.generator.TileGenerator;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/context.xml" })
public class TileGeneratorImplTest {

    private static final String ERROR_MESSAGE = "Return wrong count tiled";

    @Autowired
    private TileGenerator generator;

    @Test
    public void shouldGetInputCountTiles() throws Exception {
        int inputValues = 5;
        final Collection<Tile> tiles = generator.getInputCountTiles(inputValues);
        assertEquals(ERROR_MESSAGE,inputValues,tiles.size());
    }
}