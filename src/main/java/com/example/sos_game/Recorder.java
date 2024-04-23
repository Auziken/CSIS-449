package com.example.sos_game;

import java.io.*;
import java.util.*;

public class Recorder {

    public void writeMove (char playerSymbol, int buttonIndex, boolean bluePlayerTurn, int boardSize) {
        String player;
        int x = buttonIndex % boardSize;
        int y = buttonIndex / boardSize;

        if (bluePlayerTurn) {
            player = "Blue Player";
        }
        else {
            player = "Red Player";
        }

        try {
            FileWriter writer = new FileWriter("C:\\Users\\sammy\\Desktop\\college\\umkc\\spring 2024\\foundations of software\\sosgamereal\\recording.txt", true);
            String outText = player + " played " + playerSymbol + " at " + x + ", " + y + ".";
            writer.write(outText);
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void writeTime(){
        try {
            FileWriter writer = new FileWriter("C:\\Users\\sammy\\Desktop\\college\\umkc\\spring 2024\\foundations of software\\sosgamereal\\recording.txt", true);
            Date date = new Date();
            String now = "Game played at: " + date;
            writer.write(now);
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeWin (int gameState) {
        String outText;
        if (gameState == 3) {
            outText = "Blue Player Wins!";
        }
        else if (gameState == 2) {
            outText = "Red Player Wins!";
        }
        else {
            outText = "Red and Blue Player Tied!";
        }
        try {
            FileWriter writer = new FileWriter("C:\\Users\\sammy\\Desktop\\college\\umkc\\spring 2024\\foundations of software\\sosgamereal\\recording.txt", true);
            writer.write(outText);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void resetRecord() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\sammy\\Desktop\\college\\umkc\\spring 2024\\foundations of software\\sosgamereal\\recording.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\sammy\\Desktop\\college\\umkc\\spring 2024\\foundations of software\\sosgamereal\\recording.txt"))) {

            // Iterate through each line in the file
            String line;
            while ((line = reader.readLine()) != null) {
                // Set each line to empty
                writer.write("");
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error setting lines to empty: " + e.getMessage());
        }
    }
}
