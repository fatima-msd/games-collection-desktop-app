/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author mainmhl
 */
public class SnakeAndLaddersDice extends StackPane{
    public static final int MAX_VALUE = 6;
    public static final int MIN_VALUE = 1;
    public final SimpleIntegerProperty valueProperty = new SimpleIntegerProperty();
    
    public static int currentIntValue;
    
    public SnakeAndLaddersDice() {
        Rectangle rectangle = new Rectangle(50, 50);
        
        Text text = new Text();
        text.setFill(Color.WHITE);
        text.textProperty().bind(valueProperty.asString());
        
        this.setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle, text);
        
        this.setOnMouseClicked(e ->
        {
            roll();
            SnakeAndLaddersBoard.playSnakesAndLadders();
            //roll();
                });
    }
    
    public void roll() {
        RotateTransition rt = new RotateTransition(Duration.seconds(1), this);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setOnFinished(e -> {
            valueProperty.set(currentIntValue);
//            valueProperty.set((int)(Math.random() * (MAX_VALUE - MIN_VALUE + 1)) + MIN_VALUE);
        });
        rt.play();
    }
}
