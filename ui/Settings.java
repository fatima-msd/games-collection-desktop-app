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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static ui.ReversiComputerSettings.scene;

/**
 *
 * @author mainmhl
 */
public class Settings {
    static int soundState = 1;
    static int musicState = 1;
    static Scene scene;
    
    Label soundLbl;
    Button soundBtn;
    Label musicLbl;
    Button musicBtn;
    Button backBtn;
    
    Image soundOnImage;
    ImageView soundOnImageView;
    Image soundOffImage;
    ImageView soundOffImageView;
    Image musicOnImage;
    ImageView musicOnImageView;
    Image musicOffImage;
    ImageView musicOffImageView;
    VBox vBox;

    public Settings() {
        soundLbl = new Label();
        soundLbl.setText("Sound");
        soundLbl.setTextFill(Color.web("#ffffff"));
        soundOnImage = new Image("file:\\soundOn.png");
        soundOnImageView = new ImageView(soundOnImage);
        soundOnImageView.setFitHeight(70);
        soundOnImageView.setFitWidth(70);
        soundBtn = new Button("", soundOnImageView);
        soundBtn.setStyle(
            "-fx-background-radius: 5em; " +
            "-fx-min-width: 80px; " +
            "-fx-min-height: 80px; " +
            "-fx-max-width: 80px; " +
            "-fx-max-height: 80px; " +
            "-fx-background-color: -fx-body-color;" +
            "-fx-background-insets: 0px; " +
            "-fx-padding: 0px;"
        );
        soundBtn.setOnAction(e -> {
            
        });
        
        soundOffImage = new Image("file:\\soundOff.png");
        soundOffImageView = new ImageView(soundOffImage);
        soundOffImageView.setFitHeight(70);
        soundOffImageView.setFitWidth(70);
        
        musicLbl = new Label();
        musicLbl.setText("music");
        musicLbl.setTextFill(Color.web("#ffffff"));
        musicOnImage = new Image("file:\\musicOn.png");
        musicOnImageView = new ImageView(musicOnImage);
        musicOnImageView.setFitHeight(70);
        musicOnImageView.setFitWidth(70);
        musicBtn = new Button("", musicOnImageView);
        musicBtn.setStyle(
            "-fx-background-radius: 5em; " +
            "-fx-min-width: 80px; " +
            "-fx-min-height: 80px; " +
            "-fx-max-width: 80px; " +
            "-fx-max-height: 80px; " +
            "-fx-background-color: -fx-body-color;" +
            "-fx-background-insets: 0px; " +
            "-fx-padding: 0px;"
        );
        musicBtn.setOnAction(e -> {
            
        });
        
        musicOffImage = new Image("file:\\musicOff.png");
        musicOffImageView = new ImageView(musicOffImage);
        musicOffImageView.setFitHeight(70);
        musicOffImageView.setFitWidth(70);
        
        backBtn = new Button("Back");
        backBtn.setAlignment(Pos.CENTER);
        backBtn.setMinWidth(100);
        backBtn.setMinHeight(50);
        backBtn.setStyle("-fx-background-color : #ecc7d8");
        backBtn.setOnAction(e -> {
            UI.window.setScene(UI.sceneArray.pop());
        });
        
        vBox = new VBox(25);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(musicLbl, musicBtn, soundLbl, soundBtn, backBtn);
        vBox.setId("firstStage");
        scene = new Scene(vBox, 600, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());        
    }
}
