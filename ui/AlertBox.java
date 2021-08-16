/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;



import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author fatima_msd
 */
public class AlertBox {
        static boolean answer;

    public static boolean display(String title, String message, String player1, String player2, int score1, int score2, int time) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(150);
        Label msg = new Label();
        HBox hboxtemp = new HBox();
        hboxtemp.getChildren().add(msg);
        hboxtemp.setAlignment(Pos.CENTER);
        msg.setAlignment(Pos.CENTER);
        msg.setText(message);
        Label lblScore1;
        Label lblScore2;
        if(score1 == 0 && score2 == 0) {
            lblScore1 = new Label();
            lblScore2 = new Label();
        }
        else{
            lblScore1 = new Label(Integer.toString(score1));
            lblScore2 = new Label(Integer.toString(score2));   
        }
        Label lblPlayer1 = new Label(player1);
        lblPlayer1.setAlignment(Pos.CENTER);
        Label lblPlayer2 = new Label(player2);
        lblPlayer2.setAlignment(Pos.CENTER);
        
        VBox vbox1 = new VBox(5);
        VBox vbox2 = new VBox(5);
        HBox hbox = new HBox(15); 
        Button okButton = new Button("OK");
        vbox1.getChildren().addAll(lblPlayer1, lblScore1);
        vbox2.getChildren().addAll(lblPlayer2, lblScore2);
        hbox.getChildren().addAll(vbox1 , vbox2 );
        hbox.setAlignment(Pos.CENTER);
        VBox vbox3 = new VBox(5);
        vbox3.getChildren().addAll(hboxtemp , hbox ,okButton);
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(vbox3, okButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 350 , 150);
        window.setScene(scene);
        window.show();        
        
//        long my_time = System.currentTimeMillis();
//        long t = time * 1000;
//        long end = my_time + t ;
//        if(time != 0){
//            while(my_time < end){
//                my_time = System.currentTimeMillis();
//            }
//            window.close();
//        }

//        try{
//            Thread.sleep(time*1000);
//        }
//        catch(InterruptedException ex){
//            Thread.currentThread().interrupt();
//        }
        PauseTransition delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished( event -> window.close() );
        delay.play();
        okButton.setOnAction(e -> {
            answer = true;
            window.close();
            });
            
       

        return answer;

    }
}
