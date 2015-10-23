package com.chechel.domino.service;

import com.chechel.domino.entity.Node;
import com.chechel.domino.entity.Tile;

import java.util.Collection;

/**
 * Node Service
 */
public interface NodeService {
    /**
     * Search primary nodes with odd count related nodes
     * @param nodes all nodes
     * @return collections of primary nodes
     */
    Collection<Node> getPrimaryNodes(final Collection<Node> nodes);

    /**
     * Get nodes from tiles
     * @param tiles tiles
     * @return node collection from tiles
     */
    Collection<Node> getNodes(final Collection<Tile> tiles);
}
