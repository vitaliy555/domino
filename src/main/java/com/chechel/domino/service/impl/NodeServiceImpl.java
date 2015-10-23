package com.chechel.domino.service.impl;

import java.util.Collection;
import java.util.Map;

import com.chechel.domino.entity.Node;
import com.chechel.domino.entity.Tile;
import com.chechel.domino.service.NodeService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * Node Service - provide operations with nodes
 */
public class NodeServiceImpl implements NodeService {

    @Override
    public Collection<Node> getPrimaryNodes(final Collection<Node> nodes) {
        final Collection<Node> primaryNodes = Lists.newArrayList();
        for (Node node : getNodesCanBeStart(nodes)) {
            if (primaryNodes.isEmpty()) {
                primaryNodes.add(node);
            } else {
                final Node nodeWithFewerRelatedNodes = Iterables.get(primaryNodes, 0);
                if (nodeWithFewerRelatedNodes.getRelatedNodes().size() > node.getRelatedNodes().size()) {
                    primaryNodes.clear();
                    primaryNodes.add(node);
                } else if (nodeWithFewerRelatedNodes.getRelatedNodes().size() == node.getRelatedNodes().size()) {
                    primaryNodes.add(node);
                }
            }
        }
        return primaryNodes;
    }

    @Override
    public Collection<Node> getNodes(final Collection<Tile> tiles) {
        final Multimap<Integer, Integer> nodeMap = ArrayListMultimap.create();
        for (Tile tile : tiles) {
            nodeMap.put(tile.getLeftValue(), tile.getRightValue());
            if (!isNodeContainRelatedNode(nodeMap, tile.getRightValue(), tile.getLeftValue())) {
                nodeMap.put(tile.getRightValue(), tile.getLeftValue());
            }
        }
        return convertToNode(nodeMap.asMap());
    }

    private boolean isNodeContainRelatedNode(final Multimap<Integer, Integer> nodeMap, final Integer nodeNumber,
                                             final Integer relatedNode) {
        return nodeMap.get(nodeNumber).contains(relatedNode);
    }

    private Collection<Node> convertToNode(final Map<Integer, Collection<Integer>> nodeMap) {
        final Collection<Node> nodes = Lists.newArrayList();
        for (Map.Entry<Integer, Collection<Integer>> entryNode : nodeMap.entrySet()) {
            nodes.add(new Node(entryNode));
        }
        return nodes;
    }

    private Collection<Node> getNodesCanBeStart(final Collection<Node> nodes) {
        final Collection<Node> nodesCanBeStart = Lists.newArrayList();
        for (Node node : nodes) {
            if (isNodeCanBeStart(node)) {
                nodesCanBeStart.add(node);
            }
        }
        return nodesCanBeStart;
    }

    private boolean isNodeCanBeStart(final Node node) {
        return relatedNodeReferToThemselves(node) && isNodeHasOddRelatedNodes(node) ? !isNodeHasReferOnlyToThemselves(node) : isNodeHasOddRelatedNodes(node);
    }

    private boolean isNodeHasOddRelatedNodes(final Node node) {
        return node.getRelatedNodes().size() % 2 != 0;
    }

    private boolean isNodeHasReferOnlyToThemselves(final Node node) {
        return node.getRelatedNodes().size() == 1 && relatedNodeReferToThemselves(node);
    }

    private boolean relatedNodeReferToThemselves(final Node node) {
        return node.getRelatedNodes().contains(node.getNodeNumber());
    }

}
