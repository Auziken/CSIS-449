package com.example.sos_game;

public class DataContainer { // data containers are extremely useful in situations like these so you can pass data between scenes (specifically in javafx) without needing a third party like a text file
    private static final DataContainer instance = new DataContainer(); // final apparently isnt a static so im not sure what the difference is
    private int boardSize;
    private String gameType;
    private int computerPlayer;
    // private boolean recordGame;

    private DataContainer(){}

    public static DataContainer getInstance() {
        return instance;
    }

    public int getBoardSize() {
        return boardSize;
    }
    public String getGameType() {
        return gameType;
    }

    public int getComputerPlayer() { return computerPlayer; }

    // public boolean getRecordGame() { return recordGame; }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setComputerPlayer(int computerPlayer) { this.computerPlayer = computerPlayer; }

    // public void setRecordGame(boolean recordGame) { this.recordGame = recordGame; }
}