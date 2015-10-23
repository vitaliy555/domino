package com.chechel.domino.service.impl;

import com.chechel.domino.entity.Answer;
import com.chechel.domino.entity.Tile;
import com.chechel.domino.service.ChainService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class ChainServiceImplTest {
    private static final String ERROR_MESSAGE = "Actual result is wrong!";

    @Autowired
    private ChainService chainService;

    @Test
    public void shouldReturnLongestChain() throws Exception {
        Collection<Tile> tiles = Lists.newArrayList(new Tile(1, 2), new Tile(2, 2), new Tile(4, 1), new Tile(5, 3));
        Answer longestChain = chainService.getLongestChain(tiles);
        assertEquals(ERROR_MESSAGE,3, longestChain.getTilesInChain().size());

        tiles = Lists.newArrayList(new Tile(3, 5), new Tile(0, 0), new Tile(2, 5), new Tile(0, 6), new Tile(4, 5), new Tile(3, 4), new Tile(4, 6), new Tile(0, 1), new Tile(3, 6));
        longestChain = chainService.getLongestChain(tiles);
        assertEquals(ERROR_MESSAGE,7, longestChain.getTilesInChain().size());
    }
}