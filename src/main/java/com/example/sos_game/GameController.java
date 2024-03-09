package com.example.sos_game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    DataContainer data = DataContainer.getInstance();

    
    ArrayList<Button> buttonArray;

   
    GameBoard board;

    
    Computer computerPlayer;


    @FXML
    private RadioButton blueO;

    @FXML
    private RadioButton blueS;

    @FXML
    private ToggleGroup blueToggle;

    @FXML
    private GridPane gameBoard;

    @FXML
    private Text gameText;

    @FXML
    private Button newGameButton;

    @FXML
    private RadioButton redO;

    @FXML
    private RadioButton redS;

    @FXML
    private ToggleGroup redToggle;

    @FXML
    private Text testTextBoardSize;

    @FXML
    private Text testTextGameType;



    @FXML
    void newGame(MouseEvent event) throws IOException {
        //Setting up the "New Game" button to send user back to the "Create Game" Scene
        Stage gameStage = (Stage) newGameButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SOSGame.class.getResource("/com/example/sos_game/CreateGame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        gameStage.setTitle("Game test test?");
        gameStage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initializing the Game Board
        testTextGameType.setText(data.getGameType());

        //Setting the initial turn to start with the Blue Player
        gameText.setText("Blue Player's Turn");

        //Creating the button array to be filled by (Board Size)^2 many buttons
        buttonArray = new ArrayList<>();
        board  = new GameBoard();

        //Creating the Game Board
        createBoard(buttonArray);

        //Creating the Computer Players
        computerPlayer = new Computer();
        computerPlayer.setPlayerVal(data.getComputerPlayer());

        //If Computer Players are used, this is a needed edge case to get them to start playing
        // as normally they only react to the other player's moves
        if (computerPlayer.getPlayerVal() == 2 || computerPlayer.getPlayerVal() == 3) {
            int moveIndex = computerPlayer.firstMove(board.getBoardSize());
            if (moveIndex < 0) {
                blueO.setSelected(true);
                moveIndex = moveIndex * -1;
            }
            else {
                blueS.setSelected(true);
            }
            System.out.println(moveIndex);
            Button moveButton = buttonArray.get(moveIndex);
            handleMouseClickedEvent(moveButton);
        }

    }



    public void createBoard(ArrayList<Button> buttonArray) {

        //Filling the button array and the game board with buttons and set the board state to all 0 (empty)
        for (int i = 0; i < (data.getBoardSize() * data.getBoardSize()); i++) {
            Button tempButton = new Button();
            buttonArray.add(tempButton);
            GridPane.setConstraints(tempButton, i % data.getBoardSize(), i / data.getBoardSize());
            gameBoard.getChildren().add(tempButton);
        }


        buttonArray.forEach(this::setupButton);


        board.createBoard(data.getBoardSize(), data.getGameType());
    }

    private void setupButton(Button button) {
        button.setPrefHeight((double) 500 / data.getBoardSize());
        button.setPrefWidth ((double) 500 / data.getBoardSize());
        button.setAlignment(Pos.CENTER);
        // button.setStyle("-fx-background-color: transparent;");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 20));


        // Colored outline on each cell, depending on who's turn it is.
        button.setOnMouseEntered(event -> {
            if (board.getBlueTurn()) {
                button.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
            } else {
                button.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            }
        });

        button.setOnMouseExited(event -> {
            // Reset the style when mouse exits
            button.setStyle("");
        });



        button.setOnMouseClicked(mouseEvent -> {
            handleMouseClickedEvent(button);
        });
    }

    public void handleMouseClickedEvent(Button button) {
        setPlayerSymbol(button);
        if (!checkWin()) {
            changeTurn();
            computerTurn(button);
        }
    }

    public void setPlayerSymbol(Button button) {

        int buttonIndex = buttonArray.indexOf(button);

        // Sets an S or an O in the clicked Cell
        if ((board.getBlueTurn() && blueToggle.getSelectedToggle().equals(blueS)) || !board.getBlueTurn() && redToggle.getSelectedToggle().equals(redS)) {
            button.setText("S");
            board.playMove(buttonIndex, 'S');
        }
        else {
            button.setText("O");
            board.playMove(buttonIndex, '0');
        }
        button.setDisable(true);
        button.setOpacity(1.0);
    }

    public boolean checkWin() {
        int tempState = board.getGameState();
        if (tempState == 1) {
            gameEnd();
            gameText.setText("Red wins!");
            return true;
        }
        else if (tempState == 2) {
            gameEnd();
            gameText.setText("Blue wins!");
            return true;
        }
        else if (tempState == -1) {
            gameEnd();
            gameText.setText("Tie!");
            return true;
        }
        else {
            return false;
        }
    }

    public void changeTurn() { // Change boolean depending on player turn
        if (board.getBlueTurn()) {
            board.setBlueTurn(false);
            gameText.setText("Player Red's turn");
        }
        else {
            board.setBlueTurn(true);
            gameText.setText("Player Blue's turn");
        }
    }

    public void gameEnd() {
        for (Button button : buttonArray) {
            button.setDisable(true);
        }
    }

    public void computerTurn(Button button) {
        if (computerPlayer.getPlayerVal() == 1 && !board.getBlueTurn() || computerPlayer.getPlayerVal() == 2 && board.getBlueTurn() || computerPlayer.getPlayerVal() == 3) {
            int buttonIndex = buttonArray.indexOf(button);
            int[][] tempBoardState = board.getBoardState();
            int tempBoardSize = board.getBoardSize();

            int moveIndex = computerPlayer.chooseSquare(buttonIndex, tempBoardState, tempBoardSize);

            if (moveIndex < 0) {
                if (board.getBlueTurn()) {
                    blueO.setSelected(true);
                }
                else {
                    redO.setSelected(true);
                }
                moveIndex = (moveIndex * -1) - 1;
            }
            else {
                if (board.getBlueTurn()) {
                    blueS.setSelected(true);
                }
                else {
                    redS.setSelected(true);
                }
                moveIndex = moveIndex - 1;
            }



            Button moveButton = buttonArray.get(moveIndex);
            handleMouseClickedEvent(moveButton);
        }

    }

}
