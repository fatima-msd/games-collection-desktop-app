/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;


/**
 *
 * @author mainmhl
 */
public class ReversiComputerGame {

    static String[] playersNames = new String[3];
    static int playerType = 0;
    static int numberOfPlayers = 2;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Button backButton;
    Button helpButton;
    
    Label firstPlayerName;
    Label secondPlayerName;

    Label firstScore;
    Label secondScore;

    VBox firstInfo;
    VBox secondInfo;
    HBox hBox;

    Pane root;
    GridPane board;
    static Scene scene;
                                              // 1 --> black, 0 --> white

    public ReversiComputerGame() {

        
        
        
        this.playersNames[0] = ReversiComputerSettings.playersNames[0];
        this.playersNames[1] = ReversiComputerSettings.playersNames[1];

        backButton = new Button("Back");
        backButton.setStyle("-fx-background-color : #ecc7d8");
        backButton.setOnAction(e -> {
            if (UI.sceneArray.isEmpty() == false) {
                UI.window.setScene(UI.sceneArray.pop());
            }
        });

        firstPlayerName = new Label(this.playersNames[0]);
        firstPlayerName.setTextFill(Color.web("#fee902"));
        firstScore = new Label("0");
        firstScore.setTextFill(Color.web("#fee902"));
        firstInfo = new VBox(5);
        firstInfo.getChildren().addAll(firstPlayerName, firstScore);

        secondPlayerName = new Label(this.playersNames[1]);
        secondPlayerName.setTextFill(Color.web("#fee902"));
        secondScore = new Label("0");
        secondScore.setTextFill(Color.web("#fee902"));
        secondInfo = new VBox(5);
        secondInfo.getChildren().addAll(secondPlayerName, secondScore);

        root = new Pane();

        char[][]arr = new char[2][100];
        arr[0]=playersNames[0].toCharArray();
        arr[1]=playersNames[1].toCharArray();
        ReversiBoard reversiBoard = new ReversiBoard(10, 10);
        
        int[][] boardArr= new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardArr[i][j]=0;
            }
        }
        boardArr[4][5]=2;
        boardArr[5][4]=2;
        boardArr[4][4]=1;
        boardArr[5][5]=1;
        
        ReversiBoard.fillBoard(boardArr);
        
        board=ReversiBoard.createBoard(10,10);
        
        try{
            ReversiReadWriteControl.writeGameInfo(0, 2, arr);
        }
        catch(FileNotFoundException ex) {

        }
        catch(IOException ex){

        }
        
        try{
            Status st = Status.SUCCESS;
            ReversiReadWriteControl.writeStatus(st);
        }
        catch(FileNotFoundException ex) {

        }
        catch(IOException ex){

        }
        try{
            ReversiReadWriteControl.writeBoard(boardArr);
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        ReversiBoard.StateReversi(ReversiSettings.currentPlayer-1);
        try{
            ReversiBoard.flip = ReversiReadWriteControl.readFlip();
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        try{
            ReversiBoard.nextStatus = ReversiReadWriteControl.readNextStatus();
            System.out.println(ReversiBoard.nextStatus);
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        
        helpButton = new Button("Help");
        helpButton.setStyle("-fx-background-color : #ecc7d8");
        helpButton.setOnAction(e -> {
            if (UI.sceneArray.isEmpty() == false) {
                ReversiBoard.mouseHelp();
                ///////
                ///////
                ///////
            }
        });
        VBox boardVBox = new VBox(50);
        HBox hbox = new HBox (10); 
        ReversiSettings.turn.setText(ReversiSettings.playersNames[ReversiSettings.currentPlayer]);
        ReversiSettings.turn.setTextFill(Color.web("#ff0058"));
        ReversiSettings.turn.setPrefSize(20, 20);
        ReversiSettings.turn.setFont(Font.font("Cambria", 20));
        hbox.getChildren().addAll( backButton, helpButton,ReversiSettings.turn);
        boardVBox.getChildren().addAll(board, hbox);
        boardVBox.setAlignment(Pos.CENTER);

        board.setPadding(new Insets(35, 20, 0, 0));
        hBox = new HBox(10);
        hBox.getChildren().addAll(firstInfo, boardVBox, secondInfo);
        hBox.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:white");
        root.setId("firstStage");
        root.getChildren().addAll(hBox);
        scene = new Scene(root, 600, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }

    // call c++ function
}