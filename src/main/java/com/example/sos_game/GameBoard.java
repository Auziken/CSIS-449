package com.example.sos_game;

// import java.util.ArrayList;

public class GameBoard {
    int[][] boardState; // 2D array to represent game board

    int turnNum = 0;
    int gameState = 0;
    /*
    0 - Ongoing
    1 - Tied
    2 - Red win
    3 - Blue win
     */
    int boardSize; // Since the board needs to be a nxn grid, boardSize will be n.
    String gameType;
    boolean isBlueTurn;
    int redScore = 0, blueScore = 0;

    public void createBoard(int boardSize, String gameType){
        this.boardSize = boardSize;
        this.gameType = gameType;
        isBlueTurn = true;

        boardState = new int[boardSize][boardSize];
        for (int i=0; i < boardSize; i++){
            for (int j=0; j < boardSize; j++){
                boardState[i][j] = 0; // set every cell in the array to 0
            }
        }
    }



    public void playMove(int squareIndex, char playerChoice){
        int value; // value will either be 1 or -1 depending on if it's an S or O, respectively
        int x = squareIndex % boardSize;
        int y = squareIndex / boardSize;

        if (playerChoice == 'S'){
            value = 1;
            boardState[x][y] = value;
        }
        else{
            value = -1;
            boardState[x][y] = value;
        }

        turnNum += 1;
        updateGameState();
    }

    void updateGameState(){
        if (gameType == "Simple Game" && turnNum < boardSize * boardSize){
            if (blueScore > redScore){
                gameState = 3; // Blue wins
            }
            else if (redScore > blueScore){
                gameState = 2; // Red wins
            }
        } else if (turnNum >= boardSize * boardSize){
            if (blueScore > redScore){
                gameState = 3; // Blue wins
            }
            else if (redScore > blueScore){
                gameState = 2; // Red wins
            }
            else {
                gameState = 1; // Tie
            }
        }
    }

    // Getters and Setters
    public int[][] getBoardState(){
        return boardState;
    }
    public int getGameState(){
        return gameState;
    }
    public int getBoardSize(){
        return boardSize;
    }
    public String getGameType(){
        return gameType;
    }
    public boolean getBlueTurn(){
        return isBlueTurn;
    }
    public void setBlueTurn(boolean blueTurn){
        this.isBlueTurn = blueTurn;
    }
}
