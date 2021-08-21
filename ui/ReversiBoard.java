/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import static ui.ReversiComputerGame.playersNames;

/**
 *
 * @author mainmhl
 */
public class ReversiBoard extends GridPane {

    static {
        System.load("/home/fatima_msd/NetBeansProjects/UI_TEST_B/src/ui/Reversi.so"); // Load native library hello.dll (Windows) or libhello.so (Unixes)
                                     //  at runtime
                                     // This library contains a native method called sayHello()
    }

    public static native int GameReversi(int x , int y ,int turn);

    public static native int StateReversi(int turn);

    static int rowCount;
    static int columnCount;
    //clear ....
    static int flag=0;
    static TileReversi[][] tile;
    static int[][] boardArr = new int[10][10];
    static Status status;
    static Status nextStatus;
    static PairJava[] flip = null ;
    public ReversiBoard(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        tile=new TileReversi[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                tile[i][j] = new TileReversi();
                
                
            }
        }

    }
    public static GridPane createBoard(int rowCount, int columnCount) {
        

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(500, 500);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {

                tile[i][j].setTranslateX(j * 50);
                tile[i][j].setTranslateY(i * 50);
                gridPane.getChildren().add(tile[i][j]);
            }
        }
        gridPane.setPadding(new Insets(0, 5, 0, 5));
        return gridPane;
        
    }

  
    public static void mouseEntered(MouseEvent e, TileReversi t) {
        int colIndex = 0;
        int rowIndex = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(tile[i][j].equals(t)){
                    rowIndex = i;
                    colIndex = j;
                    break;
                }
            }
        }
        try{
            ReversiReadWriteControl.writeBoard(boardArr);
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ class c++
//        ReversiSettings.othello.coordinate->setX(rowIndex-1);
//	ReversiSettings.othello.coordinate->setY(colIndex-1);
//	Status s=ReversiSettings.othello.runGameLoop()
//        if(s==Status::INVALID_MOVE){
//		cout<<"Invalid Move .... Please Try Again"<<endl;
//	}
        

        //clear ....
        
        // if(flag == 0){
        //     for (int i = 0; i < 10; i++) {
        //         for (int j = 0; j < 10; j++) {
        //             boardArr[i][j]=0;
        //         }
        //     }
        //     boardArr[4][5]=-2;
        //     boardArr[5][4]=-2;
        //     boardArr[4][4]=1;
        //     boardArr[5][5]=1;
        //     fillBoard(boardArr);
        //     flag = 1;
        // }
        // else if(flag == 1){
        //     for (int i = 0; i < 10; i++) {
        //         for (int j = 0; j < 10; j++) {
        //             boardArr[i][j]=0;
        //         }
        //     }
        //     boardArr[3][5]=-2;
        //     boardArr[1][4]=-2;
        //     boardArr[9][4]=1;
        //     boardArr[5][0]=1;
        //     fillBoard(boardArr);
        //     flag = 0;
            
        // }
        System.out.println(ReversiSettings.currentPlayer);
        GameReversi(rowIndex,colIndex , ReversiSettings.currentPlayer-1);
        try{
            boardArr= ReversiReadWriteControl.readBoard();
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        try{
            status = ReversiReadWriteControl.readStatus();
            System.out.println(status);
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        fillBoard(boardArr);
       
        if(status == Status.SUCCESS ){
            
            if(ReversiSettings.playaerType == -2 || ReversiSettings.playaerType == -3){
                char[][]arr = new char[2][100];
                arr[0]=playersNames[0].toCharArray();
                arr[1]=playersNames[1].toCharArray();
                
                try{
                    ReversiReadWriteControl.writeGameInfo(-1*ReversiSettings.playaerType , 2, arr);
                }
                catch(FileNotFoundException ex) {

                }
                catch(IOException ex){

                }
                 boolean result =AlertBox.display("Think","AI Thinking","","",0,0,1);
            
                ReversiSettings.togglePlayer();
                
                try{
                    ReversiReadWriteControl.writeBoard(boardArr);
                }
                catch(FileNotFoundException ex){

                }
                catch(IOException ex){

                }
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(ReversiBoard.class.getName()).log(Level.SEVERE, null, ex);
//                }
                //PauseTransition delay = new PauseTransition(Duration.seconds(2));
                //delay.setOnFinished( event -> window.close() );
                //delay.play();
                GameReversi(0 , 0 , ReversiSettings.currentPlayer-1);
                try{
                    boardArr= ReversiReadWriteControl.readBoard();
                }
                catch(FileNotFoundException ex){

                }
                catch(IOException ex){

                }
                try{
                    status = ReversiReadWriteControl.readStatus();
                }
                catch(FileNotFoundException ex){

                }
                catch(IOException ex){

                }
                fillBoard(boardArr);
                try{
                    ReversiReadWriteControl.writeGameInfo(0, 2, arr);
                }
                catch(FileNotFoundException ex) {

                }
                catch(IOException ex){

                }
            }
            
            ReversiSettings.togglePlayer();
            StateReversi(ReversiSettings.currentPlayer-1);
            try{
                nextStatus = ReversiReadWriteControl.readNextStatus();
            }
            catch(FileNotFoundException ex){
    
            }
            catch(IOException ex){
    
            }
            
           
            
            if(nextStatus == Status.CANNOT_MOVE ){
               
                ReversiSettings.togglePlayer();
                StateReversi(ReversiSettings.currentPlayer-1);
            
                try{
                    flip = ReversiReadWriteControl.readFlip();
                }
                catch(FileNotFoundException ex){

                }
                catch(IOException ex){

                }
                try{
                    nextStatus = ReversiReadWriteControl.readNextStatus();
                }
                catch(FileNotFoundException ex){

                }
                catch(IOException ex){

                }
                if(nextStatus == Status.CANNOT_MOVE ){
                    int score1 = 0;
                    int score2 = 0;
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            
                            if (boardArr[i][j] == 0) {
                                
                            }
                            else if (boardArr[i][j] == 1) {
                                ++score1;
                            }
                            else {
                                ++score2;

                            }
                        }
                    }
                    if(score1 > score2){
                        System.out.println("End Game");
                        boolean result =AlertBox.display("End Game","Player 1 : "+ ReversiSettings.playersNames[0] + "^^ Win ^^" ,ReversiSettings.playersNames[0],ReversiSettings.playersNames[1],score1,score2,20);
                        
                    }
                    else if(score1 < score2){
                        System.out.println("End Game");
                        boolean result =AlertBox.display("End Game","Player 2 : "+ ReversiSettings.playersNames[1] + "^^ Win ^^" ,ReversiSettings.playersNames[0],ReversiSettings.playersNames[1],score1,score2,20);
                        
                    }
                    else{
                        System.out.println("End Game");
                        boolean result =AlertBox.display("End Game","^^ Draw ^^" ,ReversiSettings.playersNames[0],ReversiSettings.playersNames[1],score1,score2,20);
                        
                        
                    }
                    
                    
                }
                else{
                    System.out.println("CANNOT_MOVE");
                    boolean result =AlertBox.display("Cannot Move","Cannot move","","",0,0,2);
                    
                }
            
            }
            else{
                 try{
                flip = ReversiReadWriteControl.readFlip();
            }
            catch(FileNotFoundException ex){

            }
            catch(IOException ex){

            }
            }
        }
        else if(status == Status.WIN){
            
            int score1 = 0;
            int score2 = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {

                    if (boardArr[i][j] == 0) {

                    }
                    else if (boardArr[i][j] == 1) {
                        ++score1;
                    }
                    else {
                        ++score2;

                    }
                }
            }
            if(score1 > score2){
                System.out.println("End Game");
                boolean result =AlertBox.display("End Game","Player 1 : "+ ReversiSettings.playersNames[0] + "^^ Win ^^" ,ReversiSettings.playersNames[0],ReversiSettings.playersNames[1],score1,score2,20);

            }
            else if(score1 < score2){
                System.out.println("End Game");
                boolean result =AlertBox.display("End Game","Player 2 : "+ ReversiSettings.playersNames[1] + "^^ Win ^^" ,ReversiSettings.playersNames[0],ReversiSettings.playersNames[1],score1,score2,20);

            }
            else{
                System.out.println("End Game");
                boolean result =AlertBox.display("End Game","^^ Draw ^^" ,ReversiSettings.playersNames[0],ReversiSettings.playersNames[1],score1,score2,20);


            }

        }
        else if(status == Status.INVALID_MOVE){
            System.out.println("INVALID_MOVE");
            boolean result =AlertBox.display("Invalid Move","Invalid move ... Please Try Again ...","","",0,0,2);
            
        }
        
//        else if(status == Status.WIN){
//            System.out.println("WIIIIIIIn");
//        }

        //fillBoard(boardArr);
        //clear ....
       // System.out.println(colIndex + "  " + rowIndex);
    }
    
    public static void mouseHelp() {
        int len = flip.length;
       // int yy=flip[0].x;
        for(int i = 0; i < len ; i++){
            int x=flip[i].x;
            int y=flip[i].y;
            tile[x][y].circle.setFill(Color.YELLOW);
        }

    }
    
    

    static public void fillBoard(int[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardArr[i][j]=board[i][j];
                if (board[i][j] == 0) {
                    tile[i][j].circle.setFill(Color.GREEN);
                }
                else if (board[i][j] == 1) {
                    tile[i][j].circle.setFill(ReversiSettings.player1Color);
                }
                else {
                    tile[i][j].circle.setFill(ReversiSettings.player2Color);

                }
            }
        }
    }
}

class TileReversi extends StackPane {

    Circle circle = new Circle(20);

    public TileReversi() {
        Rectangle border = new Rectangle(50, 50);
        border.setFill(Color.GREEN);
        border.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        circle.setFill(Color.GREEN);
        getChildren().addAll(border, circle);

        setOnMouseClicked(event -> {
            ReversiBoard.mouseEntered(event, this);

        });
    }

    public void drawPlayer1() {
        circle.setFill(Color.BLACK);
    }

    public void drawPlayer2() {
        circle.setFill(Color.WHITE);

    }
}
