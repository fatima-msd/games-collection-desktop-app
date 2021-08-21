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
public class SnakeAndLaddersComputerSettings {

    static final int MAXPLAYERSNUM = 100;
    
    static int playaerType = -2;                                                // -3 --> computer hard && -2 --> computer easy
    static String[] playersNames = new String[MAXPLAYERSNUM];

    static int currentPieceColor;                                               // current player color of piece (1 --> black && 0 --> white)

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Label colorLable;
    Label levelLable;
    Label nameLabel;
    TextField nameTextBox;
    Button nextButton;
    Button backButton;
    Button cancelButton;
    HBox hBox3;
    VBox layout;
    static Scene scene;

    public SnakeAndLaddersComputerSettings() {

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
        nameTextBox.setMaxWidth(200);
        
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Create three buttons
        nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color : #ecc7d8");
        nextButton.setOnAction(e -> {

            String username = nameTextBox.getText();
            if (username != null && !username.isEmpty()) {
                playersNames = new String[MAXPLAYERSNUM];
                playersNames[0] = username;
                playersNames[1] = "AI";
                
                SnakeAndLaddersSettings.playersNames[0] = username;
                SnakeAndLaddersSettings.playersNames[1] = "AI";
                
                // show the game window
                UI.sceneArray.push(SnakeAndLaddersComputerSettings.scene);
                SnakeAndLaddersComputerGame snakeAndLaddersComputerGame = new SnakeAndLaddersComputerGame();
                UI.window.setScene(snakeAndLaddersComputerGame.scene);

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
        layout.getChildren().addAll(nameLabel, nameTextBox, hBox3, alertLable);
        
        layout.setId("firstStage");
        scene = new Scene(layout, 600, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());        
    }

}
