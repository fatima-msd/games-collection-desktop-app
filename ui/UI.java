/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.Stack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mainmhl
 */
public class UI extends Application {

    /**
     * @param args the command line arguments
     */
    public static Stack<Scene> sceneArray = new Stack<>(); 
    static Stage window;
    
    public static void main(String[] args) {
        // TODO code application logic here 
        launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setResizable(false);                                             // disable the maximize button        
        window.setTitle("Games Collection");
        MainScene scene = new MainScene();
        window.setScene(MainScene.scene);
        window.show();

    }
    
}
