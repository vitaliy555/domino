package com.chechel.domino.entity;

import java.util.Collection;
import java.util.List;

/**
 * Answer after successful completion of the application
 */
public class Answer {
    private static final String answerTemplate = "The largest chain include tiles : %s \nExtra tiles : %s";

    private final Collection<Tile> tilesInChain;
    private final Collection<Tile> extraTitles;

    public Answer(Collection<Tile> tilesInChain, Collection<Tile> extraTitles) {
        this.tilesInChain = tilesInChain;
        this.extraTitles = extraTitles;
    }

    public Collection<Tile> getExtraTitles() {
        return extraTitles;
    }

    public Collection<Tile> getTilesInChain() {
        return tilesInChain;
    }

    public void printAnswer() {
        System.out.println(String.format(answerTemplate, tilesInChain, extraTitles));
    }
}
