package com.chechel.domino.service.impl;

import com.chechel.domino.entity.Node;
import com.chechel.domino.entity.Tile;
import com.chechel.domino.service.NodeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class NodeServiceImplTest {
    private static final String ERROR_MESSAGE = "Returned wrong nodes!";
    private static final String ERROR_MESSAGE_PRIMARY_NODES = "Returned wrong primary nodes!";

    @Autowired
    private NodeService nodeService;

    @Test
    public void shouldReturnPrimaryNodes() throws Exception {
        final Node node1 = new Node(1, Arrays.asList(2, 4));
        final Node node2 = new Node(2, Arrays.asList(1, 2));
        final Node node3 = new Node(3, Arrays.asList(3));
        final Node node4 = new Node(4, Arrays.asList(1));
        final Node node5 = new Node(5, Arrays.asList(3));
        final Collection<Node> nodes = Lists.newArrayList(node1, node2, node3, node4, node5);
        final Collection<Node> primaryNodes = nodeService.getPrimaryNodes(nodes);
        assertEquals(ERROR_MESSAGE_PRIMARY_NODES, 3, primaryNodes.size());
    }

    @Test
    public void shouldGetNodes() throws Exception {
        final Collection<Tile> tiles = Lists.newArrayList(new Tile(1, 2), new Tile(2, 2), new Tile(4, 1), new Tile(5, 3));
        final Collection<Integer> expectNodeNumbers = Sets.newHashSet();
        for (Tile tile : tiles) {
            expectNodeNumbers.add(tile.getLeftValue());
            expectNodeNumbers.add(tile.getRightValue());
        }
        final Collection<Node> nodes = nodeService.getNodes(tiles);
        assertEquals(ERROR_MESSAGE, expectNodeNumbers.size(), nodes.size());
    }
}