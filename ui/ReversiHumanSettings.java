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
public class ReversiHumanSettings {

    static final int MAXPLAYERSNUM = 100;

    static int playerType = 0;
    static String[] playersNames = new String[MAXPLAYERSNUM];
    static int currentPieceColor;                                               // current player color of piece (1 --> black && 0 --> white) 
    
    
    Label colorLable;
    Label nameLabel1;
    Label nameLabel2;
    Label alertLable;
    TextField nameTextBox1;
    TextField nameTextBox2;
    ToggleGroup group2;
    RadioButton white;
    RadioButton black;
    HBox hBox2;
    Button nextButton;
    Button backButton;
    Button cancelButton;
    HBox hBox3;
    VBox layout;
    static Scene scene;

    public ReversiHumanSettings() {

        colorLable = new Label();
        colorLable.setText("Choose first player's color : ");
        colorLable.setTextFill(Color.web("#ffffff"));

        nameLabel1 = new Label();
        nameLabel1.setText("Enetr first player's username : ");
        nameLabel1.setTextFill(Color.web("#ffffff"));

        nameLabel2 = new Label();
        nameLabel2.setText("Enetr second player's username : ");
        nameLabel2.setTextFill(Color.web("#ffffff"));

        alertLable = new Label();
        alertLable.setText("");
        alertLable.setTextFill(Color.web("#ff0058"));

        nameTextBox1 = new TextField();
        nameTextBox1.setPromptText("Eneter first player's username : ");
        nameTextBox1.setPrefColumnCount(10);

        nameTextBox2 = new TextField();
        nameTextBox2.setPromptText("Eneter second player's username : ");
        nameTextBox2.setPrefColumnCount(10);

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

        hBox2 = new HBox(5);
        hBox2.getChildren().add(white);
        hBox2.getChildren().add(black);
        hBox2.setAlignment(Pos.CENTER);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Create three buttons
        nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color : #ecc7d8");
        nextButton.setOnAction(e -> {
            String colorChoice = group2.getSelectedToggle().getUserData().toString();
            if (colorChoice.equals("white")) {
                currentPieceColor = 0;
                ReversiSettings.player1Color = Color.WHITE;
                ReversiSettings.player2Color = Color.BLACK;
            } else {
                currentPieceColor = 1;
                ReversiSettings.player1Color = Color.BLACK;
                ReversiSettings.player2Color = Color.WHITE;
            }

            String username1 = nameTextBox1.getText();
            String username2 = nameTextBox2.getText();
            if (username1 != null && !username1.isEmpty() && username2 != null && !username2.isEmpty()) {
                playersNames = new String[MAXPLAYERSNUM];
                playersNames[0] = username1;
                playersNames[1] = username2;
                ReversiSettings.playersNames[0] = username1;
                ReversiSettings.playersNames[1] = username2;

                // show the game window
                UI.sceneArray.push(ReversiHumanSettings.scene);
                ReversiHumanGame reversiHumanGame = new ReversiHumanGame();
                UI.window.setScene(ReversiHumanGame.scene);
            } else {
                alertLable.setText("You must fill all username text fields.");
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
        layout.getChildren().addAll(nameLabel1, nameTextBox1, nameLabel2, nameTextBox2, colorLable, hBox2, hBox3, alertLable);
        layout.setId("firstStage");

        scene = new Scene(layout, 600, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }
}
