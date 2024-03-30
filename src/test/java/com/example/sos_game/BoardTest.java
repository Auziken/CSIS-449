package com.example.sos_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {


    @Test
    void test3Board() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(3, "Simple Game");
        int[][] testArray = testBoard.getBoardState();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                assertEquals(0, testArray[x][y]);
            }
        }
    }

    @Test
    void test10Board() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(10, "General Game");
        int[][] testArray = testBoard.getBoardState();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                assertEquals(0, testArray[x][y]);
            }
        }
    }

    @Test
    void testPlayS() {
        GameBoard testBoard = new GameBoard();
        Random random = new Random();
        int buttonIndex = random.nextInt(9);
        testBoard.createBoard(3, "Simple Game");
        testBoard.playMove(buttonIndex, 'S');
        int[][] testArray = testBoard.getBoardState();
        int x = buttonIndex % 3;
        int y = buttonIndex / 3;
        assertEquals(1, testArray[x][y]);
    }

    @Test
    void testPlayO() {
        GameBoard testBoard = new GameBoard();
        Random random = new Random();
        int buttonIndex = random.nextInt(100);
        testBoard.createBoard(10, "General Game");
        testBoard.playMove(buttonIndex, 'O');
        int[][] testArray = testBoard.getBoardState();
        int x = buttonIndex % 10;
        int y = buttonIndex / 10;
        assertEquals(-1, testArray[x][y]);
    }

    @Test
    void testGeneralWin() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(3, "General Game");
        testBoard.playMove(0, 'S');
        testBoard.playMove(1, 'O');
        testBoard.playMove(2, 'S');
        testBoard.playMove(3, 'S');
        testBoard.playMove(4, 'O');
        testBoard.playMove(5, 'S');
        testBoard.playMove(6, 'S');
        testBoard.playMove(7, 'O');
        testBoard.playMove(8, 'S');
        assertEquals(3, testBoard.getGameState());
    }

    @Test
    void testGeneralTie() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(3, "General Game");
        testBoard.playMove(0, 'S');
        testBoard.playMove(1, 'S');
        testBoard.playMove(2, 'S');
        testBoard.playMove(3, 'S');
        testBoard.playMove(4, 'S');
        testBoard.playMove(5, 'S');
        testBoard.playMove(6, 'S');
        testBoard.playMove(7, 'S');
        testBoard.playMove(8, 'S');
        assertEquals(1, testBoard.getGameState());
    }

    @Test
    void testSimpleWin() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(5, "Simple Game");
        testBoard.setBlueTurn(false);
        testBoard.playMove(6, 'S');
        testBoard.playMove(12, 'O');
        testBoard.playMove(18, 'S');
        assertEquals(2, testBoard.getGameState());
    }

    @Test
    void testSimpleTie() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(3, "Simple Game");
        testBoard.playMove(0, 'S');
        testBoard.playMove(1, 'S');
        testBoard.playMove(2, 'S');
        testBoard.playMove(3, 'S');
        testBoard.playMove(4, 'S');
        testBoard.playMove(5, 'S');
        testBoard.playMove(6, 'S');
        testBoard.playMove(7, 'S');
        testBoard.playMove(8, 'S');
    }

    @Test
    void test3GetSize() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(3, "Simple Game");
        int boardSize = testBoard.getBoardSize();
        assertEquals(3, boardSize);
    }

    @Test
    void test10GetSize() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(10, "General Game");
        int boardSize = testBoard.getBoardSize();
        assertEquals(10, boardSize);
    }

    @Test
    void testGetSimple() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(3, "Simple Game");
        String gameType = testBoard.getGameType();
        assertEquals("Simple Game", gameType);
    }

    @Test
    void testGetGeneral() {
        GameBoard testBoard = new GameBoard();
        testBoard.createBoard(10, "General Game");
        String gameType = testBoard.getGameType();
        assertEquals("General Game", gameType);
    }
}