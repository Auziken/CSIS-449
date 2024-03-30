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

    public void sValid(int x, int y){ // i wish there was a better way to do this
        boolean above = false, below = false, left = false, right = false;

        if (y - 1 >= 0 && y - 2 >= 0){
            above = true;
            if (boardState[x][y-1] == -1 && boardState[x][y-2] == 1){
                addPoint();
            }
            /*
            [S]
            [O]
            [S] <- HERE
             */
        }
        if (y + 1 < boardSize && y + 2 < boardSize) {
            below = true;
            if (boardState[x][y+1] == -1 && boardState[x][y+2] == 1) {
                addPoint();
            }
            /*
            [S] <- HERE
            [O]
            [S]
             */
        }
        if (x - 1 >= 0 && x - 2 >= 0) {
            left = true;
            if (boardState[x-1][y] == -1 && boardState[x-2][y] == 1) {
                addPoint();
            }
            /*
            [S][O][S] <- HERE
             */
        }
        if (x + 1 < boardSize && x + 2 < boardSize) {
            right = true;
            if (boardState[x+1][y] == -1 && boardState[x+2][y] == 1) {
                addPoint();
            }
            /*
            HERE -> [S][O][S]
             */
        }
        if (above && left) {
            if (boardState[x-1][y-1] == -1 && boardState[x-2][y-2] == 1)  {
                addPoint();
            }
            /*
            [S]
             [O]
              [S] <- HERE
             */
        }
        if (above && right) {
            if (boardState[x+1][y-1] == -1 && boardState[x+2][y-2] == 1) {
                addPoint();
            }
             /*
              [S]
             [O]
            [S] <- HERE
             */
        }
        if (below && left) {
            if (boardState[x-1][y+1] == -1 && boardState[x-2][y+2] == 1) {
                addPoint();
            }
             /*
              [S] <- HERE
             [O]
            [S]
             */
        }
        if (below && right) {
            if (boardState[x+1][y+1] == -1 && boardState[x+2][y+2] == 1) {
                addPoint();
            }
            /*
            [S] <- HERE
             [O]
              [S]
             */
        }
    }

    void oValid(int x, int y) {
        int horizontal = 0;
        int vertical = 0;
        int leftDiagonal = 0;
        int rightDiagonal = 0;

        if (x + 1 < boardSize && x - 1 >= 0 && y + 1 < boardSize && y - 1 >= 0) { // within the bounds of the game
            horizontal = boardState[x-1][y] + boardState[x][y] + boardState[x+1][y];
            vertical = boardState[x][y-1] + boardState[x][y] + boardState[x][y+1];
            leftDiagonal = boardState[x-1][y-1] + boardState[x][y] + boardState[x+1][y+1];
            rightDiagonal = boardState[x+1][y-1] + boardState[x][y] + boardState[x-1][y+1];
        }
        else if (x + 1 < boardSize && x - 1 >= 0) {
            horizontal = boardState[x-1][y] + boardState[x][y] + boardState[x+1][y];
        }
        else if (y + 1 < boardSize && y - 1 >= 0) {
            vertical = boardState[x][y-1] + boardState[x][y] + boardState[x][y+1];
        }

        // basically if any of these four alignments add up to one that means a successful SOS was created
        if (horizontal == 1) {
            addPoint();
        }
        if (vertical == 1) {
            addPoint();
        }
        if (leftDiagonal == 1) {
            addPoint();
        }
        if (rightDiagonal == 1) {
            addPoint();
        }
    }

    void addPoint(){
        if(isBlueTurn){
            blueScore += 1;
        }
        else{
            redScore += 1;
        }
    }

    public void playMove(int squareIndex, char playerChoice){
        int value; // value will either be 1 or -1 depending on if it's an S or O, respectively
        int x = squareIndex % boardSize;
        int y = squareIndex / boardSize;

        if (playerChoice == 'S'){
            value = 1;
            boardState[x][y] = value;
            sValid(x, y);
        }
        else{
            value = -1;
            boardState[x][y] = value;
            oValid(x, y);
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
    public int getBlueScore(){
        return blueScore;
    }
    public int getRedScore(){
        return redScore;
    }
    public void setBlueTurn(boolean blueTurn){
        this.isBlueTurn = blueTurn;
    }
}
