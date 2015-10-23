package com.chechel.domino.entity;

import java.util.Collection;
import java.util.Map;

/**
 * Node entity
 */
public class Node {
    private final int nodeNumber;
    private final Collection<Integer> relatedNodes;

    public Node(int nodeNumber, Collection<Integer> relatedNodes) {
        this.nodeNumber = nodeNumber;
        this.relatedNodes = relatedNodes;
    }

    public Node(Map.Entry<Integer, Collection<Integer>> nodeMap) {
        this.nodeNumber = nodeMap.getKey();
        this.relatedNodes = nodeMap.getValue();
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public Collection<Integer> getRelatedNodes() {
        return relatedNodes;
    }
}
