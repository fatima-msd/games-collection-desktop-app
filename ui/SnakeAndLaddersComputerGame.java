


package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import static ui.MainScene.scene;
import static ui.ReversiComputerGame.scene;

/**
 *
 * @author mainmhl
 */
public class SnakeAndLaddersComputerGame {

    static String[] playersNames = new String[3];
    static int playerType = 0;
    static int numberOfPlayers = 2;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Button backButton;
    
    Label firstPlayerName;
    Label secondPlayerName;

    Label firstScore;
    Label secondScore;
    
    SnakeAndLaddersDice dice = new SnakeAndLaddersDice();

    VBox firstInfo;
    VBox secondInfo;
    HBox hBox;

    Pane root;
    GridPane board;
    static Scene scene;

    static int currentPlayer = 1;                                               // 1 --> black, 0 --> white
    
    public SnakeAndLaddersComputerGame() {
        this.playersNames[0] = SnakeAndLaddersSettings.playersNames[0];
        this.playersNames[1] = SnakeAndLaddersSettings.playersNames[1];

        backButton = new Button("Back");
        backButton.setStyle("-fx-background-color : #ecc7d8");
        backButton.setOnAction(e -> {
            if (UI.sceneArray.isEmpty() == false) {
                UI.window.setScene(UI.sceneArray.pop());
            }
        });

        firstPlayerName = new Label(SnakeAndLaddersSettings.playersNames[0]);
        firstPlayerName.setFont(new Font("Arial", 25));
        firstPlayerName.setTextFill(Color.web("#000000"));
        firstScore = new Label("0");
        firstScore.setFont(new Font("Arial", 25));
        firstScore.setTextFill(Color.web("#000000"));
        firstInfo = new VBox(5);
        firstInfo.getChildren().addAll(firstPlayerName, firstScore);

        secondPlayerName = new Label(SnakeAndLaddersSettings.playersNames[1]);
        secondPlayerName.setFont(new Font("Arial", 25));
        secondPlayerName.setTextFill(Color.web("#000000"));
        secondScore = new Label("0");
        secondScore.setFont(new Font("Arial", 25));
        secondScore.setTextFill(Color.web("#000000"));
        secondInfo = new VBox(5);
        secondInfo.getChildren().addAll(secondPlayerName, secondScore);

        root = new Pane();

        SnakeAndLaddersBoard snakeAndLaddersBoard = new SnakeAndLaddersBoard(10, 10);

        board=SnakeAndLaddersBoard.createBoard(10,10);
        char[][]arr = new char[2][100];
        arr[0]=playersNames[0].toCharArray();
        arr[1]=playersNames[1].toCharArray();
        board=SnakeAndLaddersBoard.createBoard(10,10);
        try{
            ReversiReadWriteControl.writeGameInfo(-1 *SnakeAndLaddersSettings.playaerType, 2, arr);
        }
        catch(FileNotFoundException ex) {

        }
        catch(IOException ex){

        }
        
        
        try{
            ReversiReadWriteControl.writeBoard(SnakeAndLaddersBoard.boardArr);
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        SnakeAndLaddersDice.currentIntValue = SnakeAndLaddersBoard.DiceSnakesAndLadders();
        System.out.println(SnakeAndLaddersDice.currentIntValue);
        HBox tempHBox = new HBox(20);
        tempHBox.getChildren().addAll(backButton, dice);
        tempHBox.setAlignment(Pos.CENTER);

        VBox boardVBox = new VBox(60);
        boardVBox.getChildren().addAll(board, tempHBox);
        boardVBox.setAlignment(Pos.CENTER);

        board.setPadding(new Insets(35, 20, 0, 0));
        hBox = new HBox(10);
        hBox.getChildren().addAll(firstInfo, boardVBox, secondInfo);
        hBox.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:peru");
        root.setId("sankesAndLaddersBoard");
       
        root.getChildren().addAll(hBox);
        scene = new Scene(root, 620, 620);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }
    // call c++ function
}
