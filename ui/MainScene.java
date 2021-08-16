/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author mainmhl
 */
public class MainScene {
    
    static Scene scene;
    VBox vBox;
    Button snakeAndLaddersBtn;
    int snakeAndLaddersBtnState = 0;
    Button reversiBtn;
    int reversiBtnState = 0;
    Button exitPrimaryStageBtn;
    int exitprimaryStageState = 0;
    Button settingsBtn;
    int settingsBtnState = 0;
    
    public MainScene() {
        
        final java.net.URL resource = getClass().getResource("1.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        
        vBox = new VBox();
        vBox.setSpacing(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(200);

        snakeAndLaddersBtn = new Button("Snakes & Ladders");
        snakeAndLaddersBtn.setAlignment(Pos.CENTER);
        snakeAndLaddersBtn.setMinWidth(vBox.getPrefWidth());
        snakeAndLaddersBtn.setMinHeight(50);
        snakeAndLaddersBtn.setStyle("-fx-background-color : #ecc7d8");
        snakeAndLaddersBtn.setOnAction(e -> {
            System.out.println("Snakes and Ladders");
            snakeAndLaddersBtnState = 1;
            UI.sceneArray.push(this.scene);
            SnakeAndLaddersSettings snakeAndLaddersSettings = new SnakeAndLaddersSettings();
            UI.window.setScene(SnakeAndLaddersSettings.scene);
        });

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        reversiBtn = new Button("Reversi");
        reversiBtn.setAlignment(Pos.CENTER);
        reversiBtn.setMinWidth(vBox.getPrefWidth());
        reversiBtn.setMinHeight(50);
        reversiBtn.setStyle("-fx-background-color : #ecc7d8");
        reversiBtn.setOnAction(e -> {
            
            reversiBtnState = 1;
            UI.sceneArray.push(this.scene);
            ReversiSettings reversi = new ReversiSettings();
            UI.window.setScene(ReversiSettings.scene);
        });

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        settingsBtn = new Button("Settings");
        settingsBtn.setAlignment(Pos.CENTER);
        settingsBtn.setMinWidth(vBox.getPrefWidth());
        settingsBtn.setMinHeight(50);
        settingsBtn.setStyle("-fx-background-color : #ecc7d8");
        settingsBtn.setOnAction(e -> {
            settingsBtnState = 1;
            UI.sceneArray.push(this.scene);
            Settings settings = new Settings();
            UI.window.setScene(Settings.scene);
        });
        
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~        
        exitPrimaryStageBtn = new Button("Exit");
        exitPrimaryStageBtn.setAlignment(Pos.CENTER);
        exitPrimaryStageBtn.setMinWidth(vBox.getPrefWidth());
        exitPrimaryStageBtn.setMinHeight(50);
        exitPrimaryStageBtn.setStyle("-fx-background-color : #ecc7d8");
        exitPrimaryStageBtn.setOnAction(e -> {
            boolean result = ConfirmBox.display("Games Collection", "Are you sure you want to quit without saving?");
            if (result == true) {
                System.out.println("Goodbye!");
                UI.window.close();
            } else {
                // saves the game
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                System.out.println("Saving in file...");
            }
        });

        vBox.getChildren().addAll(snakeAndLaddersBtn, reversiBtn, settingsBtn, exitPrimaryStageBtn);
        vBox.setAlignment(Pos.CENTER);
        vBox.setId("firstStage");
        
        scene = new Scene(vBox, 600, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }
}
