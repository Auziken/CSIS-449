package com.example.sos_game;

import java.util.Random;

public class Computer {
    int playerVal;

    public void setPlayerVal(int playerVal) {
        this.playerVal = playerVal;
    }

    public int firstMove(int boardSize){
        Random random = new Random();
        int moveNum = random.nextInt(boardSize * boardSize);
        int randomLetter = random.nextInt(2);
        if (randomLetter == 0){
            return moveNum * -1;
        }
        else {
            return moveNum;
        }
    }

    public int chooseSquare(int squareIndex, int[][] boardState, int boardSize){
        int x = squareIndex % boardSize;
        int y = squareIndex / boardSize;
        boolean right = false, left = false, above = false, below = false;
        boolean nextRight = false, nextLeft = false, nextAbove = false, nextBelow = false;

        if (x + 1 < boardSize){
            right = true;
            if (x + 2 < boardSize){
                nextRight = true;
            }
        }
        if (x - 1 >= 0) {
            left = true;
            if (x - 2 >= 0) {
                nextLeft = true;
            }
        }
        if (y + 1 < boardSize) {
            below = true;
            if (y + 2 < boardSize) {
                nextBelow = true;
            }
        }
        if (y - 1 >= 0) {
            above = true;
            if (y - 2 >= 0) {
                nextAbove = true;
            }
        }

        if (boardState[x][y] == 1) {

            if (nextRight) {
                if (boardState[x + 1][y] == -1) {
                    if (sGood(x + 2, y, boardState, boardSize)) {
                        return pointToIndex(x + 2, y, boardSize);
                    }
                }
                else if (boardState[x + 1][y] == 0 && boardState[x + 2][y] == 1) {
                    if (oGood(x + 1, y, boardState, boardSize)) {
                        return pointToIndex(x + 1, y, boardSize) * -1;
                    }
                }
            }

            if (nextLeft) {
                if (boardState[x - 1][y] == -1) {
                    if (sGood(x - 2, y, boardState, boardSize)) {
                        return pointToIndex(x - 2, y, boardSize);
                    }
                }
                if (boardState[x - 1][y] == 0 && boardState[x - 2][y] == 1) {
                    if (oGood(x - 1, y, boardState, boardSize)) {
                        return pointToIndex(x - 1, y, boardSize) * -1;
                    }
                }
            }

            if (nextBelow) {
                if (boardState[x][y + 1] == -1) {
                    if (sGood(x, y + 2, boardState, boardSize)) {
                        return pointToIndex(x, y + 2, boardSize);
                    }
                }
                if (boardState[x][y + 1] == 0 && boardState[x][y + 2] == 1) {
                    if (oGood(x, y + 1, boardState, boardSize)) {
                        return pointToIndex(x, y + 1, boardSize) * -1;
                    }
                }
            }

            if (nextAbove) {
                if (boardState[x][y - 1] == -1) {
                    if (sGood(x, y - 2, boardState, boardSize)) {
                        return pointToIndex(x, y - 2, boardSize);
                    }
                }
                if (boardState[x][y - 1] == 0 && boardState[x][y - 2] == 1) {
                    if (oGood(x, y - 1, boardState, boardSize)) {
                        return pointToIndex(x, y - 1, boardSize) * -1;
                    }
                }
            }

            if (nextRight && nextBelow) {
                if (boardState[x + 1][y + 1] == -1) {
                    if (sGood(x + 2, y + 2, boardState, boardSize)) {
                        return pointToIndex(x + 2, y + 2, boardSize);
                    }
                }
                if (boardState[x + 1][y + 1] == 0 && boardState[x + 2][y + 2] == 1) {
                    if (oGood(x + 1, y + 1, boardState, boardSize)) {
                        return pointToIndex(x + 1, y + 1, boardSize) * -1;
                    }
                }
            }

            if (nextRight && nextAbove) {
                if (boardState[x + 1][y - 1] == -1) {
                    if (sGood(x + 2, y - 2, boardState, boardSize)) {
                        return pointToIndex(x + 2, y - 2, boardSize);
                    }
                }
                if (boardState[x + 1][y - 1] == 0 && boardState[x + 2][y - 2] == 1) {
                    if (oGood(x + 1, y - 1, boardState, boardSize)) {
                        return pointToIndex(x + 1, y - 1, boardSize) * -1;
                    }
                }
            }

            if (nextLeft && nextBelow) {
                if (boardState[x - 1][y + 1] == -1) {
                    if (sGood(x - 2, y + 2, boardState, boardSize)) {
                        return pointToIndex(x - 2, y + 2, boardSize);
                    }
                }
                if (boardState[x - 1][y + 1] == 0 && boardState[x - 2][y + 2] == 1) {
                    if (oGood(x - 1, y + 1, boardState, boardSize)) {
                        return pointToIndex(x - 1, y + 1, boardSize) * -1;
                    }
                }
            }

            if (nextLeft && nextAbove) {
                if (boardState[x - 1][y - 1] == -1) {
                    if (sGood(x - 2, y - 2, boardState, boardSize)) {
                        return pointToIndex(x - 2, y - 2, boardSize);
                    }
                }
                if (boardState[x - 1][y - 1] == 0 && boardState[x - 2][y - 2] == 1) {
                    if (oGood(x - 1, y - 1, boardState, boardSize)) {
                        return pointToIndex(x - 1, y - 1, boardSize) * -1;
                    }
                }
            }
        }

        else if (boardState[x][y] == -1) {
            if (left && right && above && below) {
                if (boardState[x - 1][y - 1] == 1) {
                    if (sGood(x + 1, y + 1, boardState, boardSize)) {
                        return pointToIndex(x + 1, y + 1, boardSize);
                    }
                }
                if (boardState[x + 1][y + 1] == 1) {
                    if (sGood(x - 1, y - 1, boardState, boardSize)) {
                        return pointToIndex(x - 1, y - 1, boardSize);
                    }
                }
                if (boardState[x + 1][y - 1] == 1) {
                    if (sGood(x - 1, y + 1, boardState, boardSize)) {
                        return pointToIndex(x - 1, y + 1, boardSize);
                    }
                }
                if (boardState[x - 1][y + 1] == 1) {
                    if (sGood(x + 1, y - 1, boardState, boardSize)) {
                        return pointToIndex(x + 1, y - 1, boardSize);
                    }
                }
            }
            if (left && right) {
                if (boardState[x - 1][y] == 1) {
                    if (sGood(x + 1, y, boardState, boardSize)) {
                        return pointToIndex(x + 1, y, boardSize);
                    }
                }
                if (boardState[x + 1][y] == 1) {
                    if (sGood(x - 1, y, boardState, boardSize)) {
                        return pointToIndex(x - 1, y, boardSize);
                    }
                }
            }
            if (above && below) {
                if (boardState[x][y - 1] == 1) {
                    if (sGood(x, y + 1, boardState, boardSize)) {
                        return pointToIndex(x, y + 1, boardSize);
                    }
                }
                if (boardState[x][y + 1] == 1) {
                    if (sGood(x, y - 1, boardState, boardSize)) {
                        return pointToIndex(x, y - 1, boardSize);
                    }
                }
            }
        }

        // :(
        for (int i = 0; i <= 1; i++){
            for (int j = 0; j < boardSize; j++) {
                for (int k = 0; k < boardSize; k++) {
                    if (boardState[j][k] == 0) {
                        if (sGood(j, k, boardState, boardSize) || i == 1) {
                            return pointToIndex(j, k, boardSize);
                        }
                        if (oGood(j, k, boardState, boardSize)) {
                            return pointToIndex(j, k, boardSize) * -1;
                        }
                    }
                }
            }
        }

        return -1;
    }
    
    // Computer attempts to place an S in a spot that would not let the opponent get a point.
    public boolean sGood(int x, int y, int[][] boardState, int boardSize) {

        if (boardState[x][y] != 0) {
            return false;
        }

        boolean nextRight = false;
        boolean nextLeft = false;
        boolean nextAbove = false;
        boolean nextBelow = false;

        if (x + 2 < boardSize) {
            nextRight = true;
        }
        if (x - 2 >= 0) {
            nextLeft = true;
        }
        if (y + 2 < boardSize) {
            nextBelow = true;
        }
        if (y - 2 >= 0) {
            nextAbove = true;
        }

        if (nextRight) {
            if (boardState[x + 1][y] == -1 && boardState[x + 2][y] == 0 || boardState[x + 1][y] == 0 && boardState[x + 2][y] == 1) {
                return false;
            }
        }
        if (nextLeft) {
            if (boardState[x - 1][y] == -1 && boardState[x - 2][y] == 0 || boardState[x - 1][y] == 0 && boardState[x - 2][y] == 1) {
                return false;
            }
        }
        if (nextBelow) {
            if (boardState[x][y + 1] == -1 && boardState[x][y + 2] == 0 || boardState[x][y + 1] == 0 && boardState[x][y + 2] == 1) {
                return false;
            }
        }
        if (nextAbove) {
            if (boardState[x][y - 1] == -1 && boardState[x][y - 2] == 0 || boardState[x][y - 1] == 0 && boardState[x][y - 2] == 1) {
                return false;
            }
        }
        if (nextRight && nextBelow) {
            if (boardState[x + 1][y + 1] == -1 && boardState[x + 2][y + 2] == 0 || boardState[x + 1][y + 1] == 0 && boardState[x + 2][y + 2] == 1) {
                return false;
            }
        }
        if (nextLeft && nextBelow) {
            if (boardState[x - 1][y + 1] == -1 && boardState[x - 2][y + 2] == 0 || boardState[x - 1][y + 1] == 0 && boardState[x - 2][y + 2] == 1) {
                return false;
            }
        }
        if (nextRight && nextAbove) {
            if (boardState[x + 1][y - 1] == -1 && boardState[x + 2][y - 2] == 0 || boardState[x + 1][y - 1] == 0 && boardState[x + 2][y - 2] == 1) {
                return false;
            }
        }
        if (nextLeft && nextAbove) {
            if (boardState[x - 1][y - 1] == -1 && boardState[x - 2][y - 2] == 0 || boardState[x - 1][y - 1] == 0 && boardState[x - 2][y - 2] == 1) {
                return false;
            }
        }

        return true;
    }

    // Computer attempts to place an O in a place where the opponent would not score.
    public boolean oGood(int x, int y, int[][] boardState, int boardSize) {

        if (boardState[x][y] != 0) {
            return false;
        }

        boolean right = false;
        boolean left = false;
        boolean above = false;
        boolean below = false;

        if (x + 1 < boardSize) {
            right = true;
        }
        if (x - 1 >= 0) {
            left = true;
        }
        if (y + 1 < boardSize) {
            below = true;
        }
        if (y - 1 >= 0) {
            above = true;
        }

        if (right && left && below && above) {
            if (boardState[x + 1][y + 1] == 1 && boardState[x - 1][y - 1] == 0 || boardState[x + 1][y + 1] == 0 && boardState[x - 1][y - 1] == 1) {
                return false;
            }
            if (boardState[x + 1][y - 1] == 1 && boardState[x - 1][y + 1] == 0 || boardState[x + 1][y - 1] == 0 && boardState[x - 1][y + 1] == 1) {
                return false;
            }
        }
        if (right && left) {
            if (boardState[x + 1][y] == 1 && boardState[x - 1][y] == 0 || boardState[x + 1][y] == 0 && boardState[x - 1][y] == 1) {
                return false;
            }
        }
        if (below && above) {
            if (boardState[x][y + 1] == 1 && boardState[x][y - 1] == 0 || boardState[x][y + 1] == 0 && boardState[x][y - 1] == 1) {
                return false;
            }
        }

        return true;
    }


    public int pointToIndex(int x, int y, int boardSize){ // Essentially allows an [x, y] coordinate to turn into a point that can be used on the button's array
        return (y * boardSize) + x + 1;
    }

    public int getPlayerVal(){
        return playerVal;
    }
}