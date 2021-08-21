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
public class SnakeAndLaddersHumanSettings {

    static final int MAXPLAYERSNUM = 100;
    static int playerNum;
    
    static int playaerType = -2;                                                // -3 --> computer hard && -2 --> computer easy
    static String[] playersNames = new String[MAXPLAYERSNUM];

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Label nameLabel1;
    TextField nameTextBox1;
    
    Label nameLabel2;
    TextField nameTextBox2;
    
    HBox hBox3;
    
    Button nextButton;
    Button backButton;
    Button cancelButton;
    VBox layout;
    static Scene scene;

    public SnakeAndLaddersHumanSettings() {

        Label alertLable = new Label();
        alertLable.setText("");
        alertLable.setTextFill(Color.web("#ff0058"));
        
        nameLabel1 = new Label();
        nameLabel1.setText("*Enetr first player's username : ");
        nameLabel1.setTextFill(Color.web("#ffffff"));

        nameTextBox1 = new TextField();
        nameTextBox1.setMaxWidth(200);
        nameTextBox1.setPromptText("Eneter first player's username : ");
        nameTextBox1.setPrefColumnCount(10);
        
        nameLabel2 = new Label();
        nameLabel2.setText("*Enetr second player's username : ");
        nameLabel2.setTextFill(Color.web("#ffffff"));

        nameTextBox2 = new TextField();
        nameTextBox2.setMaxWidth(200);
        nameTextBox2.setPromptText("Eneter second player's username : ");
        nameTextBox2.setPrefColumnCount(10);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Create three buttons
        nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color : #ecc7d8");
        nextButton.setOnAction(e -> {

            String username1 = nameTextBox1.getText();
            String username2 = nameTextBox2.getText();

            if(username1.isEmpty() && username2.isEmpty()) {
                alertLable.setText("You must fill at least two username text field.");
            } else {
                playersNames[0] = username1;
                playersNames[1] = username2;
                
                this.playerNum = 2;
                SnakeAndLaddersSettings.playerNum = this.playerNum;
                SnakeAndLaddersSettings.playersNames[0] = username1;
                SnakeAndLaddersSettings.playersNames[1] = username2;
                
                // show the game window                
                UI.sceneArray.push(SnakeAndLaddersHumanSettings.scene);
                SnakeAndLaddersHumanGame snakeAndLaddersHumanGame = new SnakeAndLaddersHumanGame();
                UI.window.setScene(SnakeAndLaddersHumanGame.scene);
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
        layout.getChildren().addAll(nameLabel1, nameTextBox1,nameLabel2, nameTextBox2, hBox3,  alertLable);
        
        layout.setId("firstStage");
        scene = new Scene(layout, 600, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());        
    }

}
