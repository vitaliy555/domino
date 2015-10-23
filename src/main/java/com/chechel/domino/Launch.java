package com.chechel.domino;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chechel.domino.entity.Answer;
import com.chechel.domino.entity.Tile;
import com.chechel.domino.generator.TileGenerator;
import com.chechel.domino.service.ChainService;
import com.chechel.domino.util.AppConst;

/**
 * Application launcher
 */
public class Launch {

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        final ChainService chainService = (ChainService) context.getBean("chainService");
        final TileGenerator generator = (TileGenerator) context.getBean("tileGenerator");
        new Launch().startAppDomini(chainService, generator);
    }

    private void startAppDomini(final ChainService chainService, final TileGenerator generator) {
        System.err.println(AppConst.FIRST_LABEL);
        final Integer enteredValue = readInput();
        final Collection<Tile> generatedTiles = generator.getInputCountTiles(enteredValue);
        System.err.println(AppConst.INPUT_TILES + generatedTiles);
        final Answer longestChain = chainService.getLongestChain(generatedTiles);
        longestChain.printAnswer();

    }

    private int readInput() {
        String input = EMPTY;
        try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in))) {
            input = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(AppConst.ERROR_MESSAGE);
        }
        if (!input.matches(AppConst.DIGIT_REGEX)) {
            System.err.println(AppConst.INCORRECT_INPUT);
            System.exit(0);
        }
        return Integer.parseInt(input.trim());
    }
}
