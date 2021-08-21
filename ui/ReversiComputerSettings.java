/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static ui.MainScene.scene;

/**
 *
 * @author mainmhl
 */
public class ReversiComputerSettings {

    static final int MAXPLAYERSNUM = 100;
    
    static int playaerType = -2;                                                // -3 --> computer hard && -2 --> computer easy
    static String[] playersNames = new String[MAXPLAYERSNUM];

    static int currentPieceColor;                                               // current player color of piece (1 --> black && 0 --> white)

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Label colorLable;
    Label levelLable;
    Label nameLabel;
    TextField nameTextBox;
    ToggleGroup group1;
    RadioButton hard;
    RadioButton easy;
    RadioButton white;
    RadioButton black;
    HBox hBox1;
    ToggleGroup group2;
    Button nextButton;
    Button backButton;
    Button cancelButton;
    HBox hBox3;
    VBox layout;
    static Scene scene;

    public ReversiComputerSettings() {

        colorLable = new Label();
        colorLable.setText("Choose your color : ");
        colorLable.setTextFill(Color.web("#ffffff"));

        levelLable = new Label();
        levelLable.setText("Choose hardness level of the game : ");
        levelLable.setTextFill(Color.web("#ffffff"));

        nameLabel = new Label();
        nameLabel.setText("*Enetr your player's username : ");
        nameLabel.setTextFill(Color.web("#ffffff"));

        Label alertLable = new Label();
        alertLable.setText("");
        alertLable.setTextFill(Color.web("#ff0058"));

        nameTextBox = new TextField();
        nameTextBox.setPromptText("Eneter your player's username : ");
        nameTextBox.setPrefColumnCount(10);

        group1 = new ToggleGroup();
        hard = new RadioButton("Hard");
        hard.setToggleGroup(group1);
        hard.setUserData("hard");
        hard.setTextFill(Color.web("#fee902"));

        easy = new RadioButton("Easy");
        easy.setToggleGroup(group1);
        easy.setUserData("easy");
        easy.setSelected(true);
        easy.setTextFill(Color.web("#fee902"));

        hBox1 = new HBox(5);
        hBox1.getChildren().add(easy);
        hBox1.getChildren().add(hard);
        hBox1.setAlignment(Pos.CENTER);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        group2 = new ToggleGroup();
        white = new RadioButton("White");
        white.setToggleGroup(group2);
        white.setUserData("white");
        white.setSelected(true);
        white.setTextFill(Color.web("#fee902"));

        black = new RadioButton("Black");
        black.setToggleGroup(group2);
        black.setUserData("black");
        black.setTextFill(Color.web("#fee902"));
        HBox hBox2 = new HBox(5);
        hBox2.getChildren().add(white);
        hBox2.getChildren().add(black);
        hBox2.setAlignment(Pos.CENTER);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Create three buttons
        nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color : #ecc7d8");
        nextButton.setOnAction(e -> {
            String levelChoice = group1.getSelectedToggle().getUserData().toString();
            if (levelChoice.equals("hard")) {
                playaerType = -3;
                 ReversiSettings.playaerType = -3;
            } else {
                playaerType = -2;
                ReversiSettings.playaerType = -2;
            }

            String colorChoice = group2.getSelectedToggle().getUserData().toString();
            if (colorChoice.equals("white")) {
                currentPieceColor = 0;
                ReversiSettings.player2Color = Color.BLACK;
                ReversiSettings.player1Color = Color.WHITE;
            } else {
                currentPieceColor = 1;
                ReversiSettings.player1Color = Color.BLACK;
                ReversiSettings.player2Color = Color.WHITE;
            }

            String username = nameTextBox.getText();
            if (username != null && !username.isEmpty()) {
                playersNames = new String[MAXPLAYERSNUM];
                playersNames[0] = username;
                playersNames[1] = new String("AI");
                ReversiSettings.playersNames[0] = playersNames[0];
                ReversiSettings.playersNames[1] = playersNames[1];
                // show the game window
                UI.sceneArray.push(ReversiComputerSettings.scene);
                ReversiComputerGame reversiComputerGame = new ReversiComputerGame();
                UI.window.setScene(reversiComputerGame.scene);


            } else {
                alertLable.setText("You must fill username text field.");
            }
        });

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        backButton = new Button("Back");
        backButton.setStyle("-fx-background-color : #ecc7d8");
        backButton.setOnAction(e -> {
            if (UI.sceneArray.isEmpty() == false) {
                UI.window.setScene(UI.sceneArray.pop());
            }

        });

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color : #ecc7d8");
        cancelButton.setOnAction(e -> {
            UI.sceneArray.pop();
            UI.window.setScene(UI.sceneArray.pop());
        });

        hBox3 = new HBox(5);
        hBox3.getChildren().addAll(nextButton, backButton, cancelButton);
        hBox3.setAlignment(Pos.CENTER);

        layout = new VBox(10);

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(nameLabel, nameTextBox, levelLable, hBox1, colorLable, hBox2, hBox3, alertLable);
        
        layout.setId("firstStage");
        scene = new Scene(layout, 600, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());        
    }

}