package com.example.snakeladder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.lang.String;

import java.io.IOException;

public class HelloApplication extends Application {
     private Button buttons[][] = new Button[3][3];
    private Label playerXScoreLabel, playerOScoreLabel;
    private int playerXScore = 0, playerOScore = 0;
    private boolean playerXTurn = true;
    private Pane createContent(){
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        //Title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setStyle("-fx-font-size : 35pt; -fx-font-weight : bold;");

        root.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        //Game Board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                  Button button = new Button();
                  button.setPrefSize(100,100);
                  button.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
                  button.setOnAction(actionEvent -> buttonClicked(button));
                  buttons[i][j] = button;
                  gridPane.add(button,j,i);
            }
        }
            root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane, Pos.CENTER);

        //Score
        HBox scoreBoard = new HBox(20);
        scoreBoard.setAlignment(Pos.CENTER);
        playerXScoreLabel = new Label("Player X: 0");
        playerXScoreLabel.setStyle("fx-fontSize:16pt; -fx-font-weight:bold;");
        playerOScoreLabel = new Label("Player O: 0");
        playerOScoreLabel.setStyle("fx-fontSize:16pt; -fx-font-weight:bold;");
        scoreBoard.getChildren().addAll(playerXScoreLabel,playerOScoreLabel);

        root.setBottom(scoreBoard);

        return root;
    }
    private void buttonClicked(Button button){
       if(button.getText().equals("")){
           if(playerXTurn){
               button.setText("X");
           } else {
               button.setText("O");
           }
           playerXTurn = !playerXTurn;

           checkWinner();
       }
        return;
    }

    private void checkWinner() {
        boolean tie = true;

        // Rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty()) {
                String winner = buttons[row][0].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // Columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()) {
                String winner = buttons[0][col].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        // Diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()) {
            String winner = buttons[0][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        if (buttons[2][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[0][2].getText())
                && !buttons[2][0].getText().isEmpty()) {
            String winner = buttons[2][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        // Tie
        for (Button[] row : buttons) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    tie = false;
                    break;
                }
            }
        }

        if (tie) {
            showTieDialog("");
            resetBoard();
        }
    }
        private void showWinnerDialog(String winner){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner");
            alert.setContentText("Congratulations " + winner+ " !You Won the Game ");
            alert.setHeaderText(" ");
            alert.showAndWait();
        }

    private void showTieDialog(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie !");
        alert.setContentText("Game Over ! It's a tie.");
        alert.setHeaderText(" ");
        alert.showAndWait();
    }

        private void updateScore(String winner){
         if(winner.equals("X")){
             playerXScore++;
             playerXScoreLabel.setText("Player X : " + playerXScore);
         }else{
             playerOScore++;
             playerOScoreLabel.setText("Player O : " + playerOScore);
         }
        }

        private void resetBoard(){
        for(Button row[] : buttons){
            for(Button button : row){
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}