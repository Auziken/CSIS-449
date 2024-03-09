package com.example.sos_game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateController implements Initializable {


    DataContainer data = DataContainer.getInstance();

    // GUI garbage
    @FXML
    private Spinner<Integer> boardSize;
    SpinnerValueFactory<Integer> boardSizeSVF
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 10, 1);

    @FXML
    private RadioButton computerBlue;

    @FXML
    private RadioButton computerBoth;

    @FXML
    private RadioButton computerNone;

    @FXML
    private ToggleGroup computerPlayers;

    @FXML
    private RadioButton computerRed;

    @FXML
    private ToggleGroup gameType;

    @FXML
    private RadioButton generalGame;

    /*
    @FXML
    private RadioButton recordFalse;

    @FXML
    private ToggleGroup recordGame;

    @FXML
    private RadioButton recordTrue;
     */

    @FXML
    private RadioButton simpleGame;

    @FXML
    private Button startGameButton;

    @FXML
    void startGame(MouseEvent event) throws IOException {
        // board size into getters and setters
        int tempBoardSize = boardSize.getValue();
        data.setBoardSize(tempBoardSize);

        // game type into getters and setters
        if(gameType.getSelectedToggle().equals(generalGame)) {
            data.setGameType("General Game");
        }
        else if(gameType.getSelectedToggle().equals(simpleGame)) {
            data.setGameType("Simple Game");
        }


        if(computerPlayers.getSelectedToggle().equals(computerNone)) {
            data.setComputerPlayer(0);
        }
        else if(computerPlayers.getSelectedToggle().equals(computerRed)) {
            data.setComputerPlayer(1);
        }
        else if(computerPlayers.getSelectedToggle().equals(computerBlue)) {
            data.setComputerPlayer(2);
        }
        else if(computerPlayers.getSelectedToggle().equals(computerBoth)) {
            data.setComputerPlayer(3);
        }

        /*
        if(recordGame.getSelectedToggle().equals(recordTrue)) {
            data.setRecordGame(true);
        }
        else {
            data.setRecordGame(false);
        }
         */


        Stage gameStage = (Stage) startGameButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SOSGame.class.getResource("/com/example/sos_game/SOSGame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        gameStage.setTitle("SOS Game");
        gameStage.setScene(scene);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boardSize.setValueFactory(boardSizeSVF);
    }
}
