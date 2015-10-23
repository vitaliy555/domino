package com.chechel.domino.service.impl;

import java.util.Collection;

import com.chechel.domino.entity.Answer;
import com.chechel.domino.entity.Node;
import com.chechel.domino.entity.Tile;
import com.chechel.domino.service.ChainService;
import com.chechel.domino.service.NodeService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Chain Service - provide operations for search longest chain
 */
public class ChainServiceImpl implements ChainService {
     @Autowired
     private NodeService nodeService;

    public Answer getLongestChain(final Collection<Tile> tiles) {
        final Collection<Node> allNodes = nodeService.getNodes(tiles);
        final Collection<Node> primaryNodes = nodeService.getPrimaryNodes(allNodes);
        Collection<Tile> longestChain = Lists.newArrayList();
        if (!primaryNodes.isEmpty()) {
            for (Node primaryNode : primaryNodes) {
                final Collection<Tile> lengthOfChain = findChainFromNode(primaryNode, allNodes);
                longestChain = lengthOfChain.size() > longestChain.size() ? lengthOfChain : longestChain;
            }
        } else {
            longestChain = tiles;
        }
        tiles.removeAll(longestChain);
        return new Answer(longestChain, tiles);
    }

    private Collection<Tile> findChainFromNode(final Node fromNode, final Collection<Node> nodes) {
        final Collection<Tile> chainOfTile = Lists.newArrayList();
        return findChainFromNode(fromNode.getNodeNumber(), convertToMultimap(nodes), chainOfTile);
    }

    private Collection<Tile> findChainFromNode(final Integer fromNodeNumber, final Multimap<Integer, Integer> nodeMap,final Collection<Tile> chainOfTile) {
        if (!nodeMap.isEmpty() && !nodeMap.get(fromNodeNumber).isEmpty()) {
            final Integer secondNodeNumber = getSecondNodeNumber(fromNodeNumber, nodeMap);
            chainOfTile.add(new Tile(fromNodeNumber,secondNodeNumber));
                removeAllPossibleLinks(fromNodeNumber, secondNodeNumber, nodeMap);
                return findChainFromNode(secondNodeNumber, nodeMap, chainOfTile);
        }
        return chainOfTile;
    }

    private int getSecondNodeNumber(final Integer fromNodeNumber, final Multimap<Integer, Integer> nodeMap) {
        int secondNodeNumber ;
        if (hasChainToHimself(fromNodeNumber, nodeMap)) {
            secondNodeNumber = fromNodeNumber;
        } else {
            if (nodeMap.get(fromNodeNumber).size() > 1) {
                final Node secondNode = getNodeWithLessRelatedNodes(nodeMap.get(fromNodeNumber), nodeMap);
                secondNodeNumber = secondNode.getNodeNumber();
            } else {
                secondNodeNumber = Iterables.get(nodeMap.get(fromNodeNumber), 0);
            }
        }
        return secondNodeNumber;
    }
    private Node getNodeWithLessRelatedNodes(final Collection<Integer> variantsOfSecondNode, final Multimap<Integer, Integer> nodeMap) {
        Node nodeWithLessRelatedNodes = null;
        for (Integer variant : variantsOfSecondNode) {
            if (nodeWithLessRelatedNodes != null) {
                if (nodeWithLessRelatedNodes.getRelatedNodes().size() > nodeMap.get(variant).size() && nodeMap.get(variant).size()>1) {
                    nodeWithLessRelatedNodes = new Node(variant, nodeMap.get(variant));
                }
            } else {
                nodeWithLessRelatedNodes = new Node(variant, nodeMap.get(variant));
            }
        }
        return nodeWithLessRelatedNodes;
    }

    private Multimap<Integer, Integer> convertToMultimap(final Collection<Node> nodes) {
        final Multimap<Integer, Integer> nodeMultimap = ArrayListMultimap.create();
        for (Node node : nodes) {
            nodeMultimap.putAll(node.getNodeNumber(), node.getRelatedNodes());
        }
        return nodeMultimap;
    }

    private boolean hasChainToHimself(final Integer fromNode, final Multimap<Integer, Integer> nodeMap) {
        return nodeMap.get(fromNode).contains(fromNode);
    }

    private void removeAllPossibleLinks(final Integer fromNodeNumber, final Integer secondNodeNumber, final Multimap<Integer, Integer> nodes) {
        nodes.get(fromNodeNumber).remove(secondNodeNumber);
        if (nodes.get(secondNodeNumber).contains(fromNodeNumber)) {
            nodes.get(secondNodeNumber).remove(fromNodeNumber);
        }
    }
}
