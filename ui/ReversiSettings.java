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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static ui.MainScene.scene;

/**
 *
 * @author mainmhl
 */
public class ReversiSettings {

    static final int MAXPLAYERSNUM = 100;
    
    static int playaerType = -2;                                                // default (-2): computer && 0 --> human
    static String[] playersNames = new String[MAXPLAYERSNUM];
    static int currentPieceColor;                                               // current player color of piece (1 --> black && 0 --> white)
    
    static Color player1Color;
    static Color player2Color;
    static int currentPlayer = 1;                                               // 1 --> black, 0 --> white

    static Label turn = new Label();
    Label label;
    Button nextButton;
    Button cancelButton;
    ToggleGroup group;
    RadioButton computer;
    RadioButton human;
    HBox hBox1;
    static Scene scene;

    public ReversiSettings() {

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Create three buttons
        nextButton = new Button("Next");
        nextButton.setStyle("-fx-background-color : #ecc7d8");
        cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color : #ecc7d8");
        
        cancelButton.setOnAction(e -> {
            UI.window.setScene(UI.sceneArray.pop());
        });

        nextButton.setOnAction(e -> {                                           // computer player setting(2)
            String choice = group.getSelectedToggle().getUserData().toString();
            if (choice.equals("computer")) {
                playaerType = -2;
                UI.sceneArray.push(ReversiSettings.scene);
                ReversiComputerSettings reversiComputerSettings = new ReversiComputerSettings();
                UI.window.setScene(ReversiComputerSettings.scene);
            } else {
                playaerType = 0;
                UI.sceneArray.push(ReversiSettings.scene);
                ReversiHumanSettings reversiHumanSettings = new ReversiHumanSettings();
                UI.window.setScene(ReversiHumanSettings.scene);
            }
        });
        
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        label = new Label();
        label.setText("Choose your competitor type : ");
        label.setTextFill(Color.web("#ffffff"));

        // Create radio two buttons for computer player and human player
        group = new ToggleGroup();

        computer = new RadioButton("Computer");
        computer.setTextFill(Color.web("#fee902"));
        computer.setToggleGroup(group);
        computer.setUserData("computer");
        computer.setSelected(true);
        Image imageC = new Image(getClass().getResourceAsStream("computer.jpg"));
        ImageView imageViewC = new ImageView(imageC);
        imageViewC.setFitHeight(50);
        imageViewC.setFitWidth(50);
//        imageViewC.setX(200);
//        imageViewC.setY(200);
        computer.setGraphic(imageViewC);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        human = new RadioButton("Human");
        human.setUserData("human");
        human.setToggleGroup(group);
        human.setTextFill(Color.web("#fee902"));
        Image imageH = new Image(getClass().getResourceAsStream("human.jpg"));
        ImageView imageViewH = new ImageView(imageH);
        imageViewH.setFitHeight(50);
        imageViewH.setFitWidth(50);
//        imageViewH.setX(200);
//        imageViewH.setY(200);
        human.setGraphic(imageViewH);
        
        hBox1 = new HBox(5);
        hBox1.getChildren().add(nextButton);
        hBox1.getChildren().add(cancelButton);
        hBox1.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().add(label);
        layout.getChildren().add(computer);
        layout.getChildren().add(human);
        layout.getChildren().add(hBox1);

        layout.setAlignment(Pos.CENTER);
        layout.setId("firstStage");
        scene = new Scene(layout, 600, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
    }
    public static void togglePlayer(){
        if(currentPlayer == 1){
            currentPlayer = 2;
        }
        else{
            currentPlayer = 1 ;

        }
        
        turn.setText(playersNames[currentPlayer]);
        turn.setTextFill(Color.web("#ff0058"));
        turn.setPrefSize(20, 20);
        turn.setFont(Font.font("Cambria", 20));
    }
}
