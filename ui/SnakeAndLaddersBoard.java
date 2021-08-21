/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import static ui.SnakeAndLaddersSettings.playersNames;

/**
 *
 * @author mainmhl
 */
public class SnakeAndLaddersBoard extends GridPane {
    
    // cd ui
    // javac *.java
    // cd ..
    // javah ui.SnakeAndLaddersBoard
    // javah ui.ReversiBoard
    // cd ui 
    // g++ -std=c++11 -shared -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux  -fPIC -I$JAVA_INC -I$JAVA_INC/linux *.cpp -o Reversi.so
    // g++ -std=c++11 -shared -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux  -fPIC -I$JAVA_INC -I$JAVA_INC/linux *.cpp -o SnakesAndLadders.so
    // cd ..
    // java ui.UI
    static {
        System.load("/home/fatima_msd/NetBeansProjects/UI_TEST_B/src/ui/SnakesAndLadders.so"); // Load native library hello.dll (Windows) or libhello.so (Unixes)
                                     //  at runtime
                                     // This library contains a native method called sayHello()
    }

    public static native int GameSnakesAndLadders(int x , int d ,int turn);

    public static native int DiceSnakesAndLadders();

    static int rowCount;
    static int columnCount;
    //clear ....
    static int flag=0;
    static PairJava coordinatsArr[] =new PairJava[101];
    static TileSnakeAndLadders[][] tile;
    static int[][] boardArr = new int[10][10];
    static Status status;
    public SnakeAndLaddersBoard(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        tile=new TileSnakeAndLadders[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                tile[i][j] = new TileSnakeAndLadders();
                
                
            } 
        }
        SnakesAndLaddersCoordinates();

    }
    public static void playSnakesAndLadders(){
        SnakeAndLaddersDice.currentIntValue = SnakeAndLaddersBoard.DiceSnakesAndLadders();
        
        boolean result ;
        try{
            ReversiReadWriteControl.writeBoard(boardArr);
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        System.out.println("1");
        int fCoordinate = find(SnakeAndLaddersSettings.currentPlayer);
        System.out.println(fCoordinate + "   " + SnakeAndLaddersDice.currentIntValue + "   "+ SnakeAndLaddersSettings.currentPlayer);
        int lCoordiante = GameSnakesAndLadders(fCoordinate , SnakeAndLaddersDice.currentIntValue , SnakeAndLaddersSettings.currentPlayer - 1);
        SnakeAndLaddersSettings.togglePlayer();
        System.out.println("2");
        try{
            boardArr= ReversiReadWriteControl.readBoard();
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        System.out.println("3");
        try{
            status = ReversiReadWriteControl.readStatus();
            System.out.println(status);
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        System.out.println("4");
        
        if(status != Status.INVALID_MOVE && SnakeAndLaddersSettings.playaerType == -2){
            
            //if(SnakeAndLaddersSettings.playaerType == -2 ){
                System.out.println("naya");
                char[][]arr = new char[2][100];
                arr[0]=playersNames[0].toCharArray();
                arr[1]=playersNames[1].toCharArray();
                
                
                 result =AlertBox.display("Think","AI Thinking","","",0,0,1);
            
                //SnakeAndLaddersSettings.togglePlayer();
                
                status = Status.INVALID_MOVE;
                while(status == Status.INVALID_MOVE){
                    
                    SnakeAndLaddersDice.currentIntValue = SnakeAndLaddersBoard.DiceSnakesAndLadders();
                    SnakeAndLaddersDice.currentIntValue = (SnakeAndLaddersDice.currentIntValue*17 )% 6 + 1;
                    fCoordinate = find(SnakeAndLaddersSettings.currentPlayer);
                    lCoordiante = GameSnakesAndLadders(fCoordinate ,SnakeAndLaddersDice.currentIntValue , SnakeAndLaddersSettings.currentPlayer - 1);
                    //SnakeAndLaddersDice.currentIntValue = SnakeAndLaddersBoard.DiceSnakesAndLadders();
        

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


                    if(status == Status.INVALID_MOVE ){
                        System.out.println("INVALID_MOVE");
                        result =AlertBox.display("Invalid Move","Invalid move ... AI Try Again ...","","",0,0,2);
                    }
            }
                SnakeAndLaddersSettings.togglePlayer();
           // }
        }
        else if(status == Status.BITE){
                result =AlertBox.display("Bite","Bite :(","","",0,0,3);
            
        }
        else if(status == Status.CLIMB){
            result =AlertBox.display("Climb","Climb :)","","",0,0,3);
            
        }
        else if(status == Status.KILL){
            result =AlertBox.display("Kill","^^ Kill ^^","","",0,0,3);
            
        }
        else if(status == Status.WIN){
         result =AlertBox.display("End Game", SnakeAndLaddersSettings.playersNames[0] + "^^ Win ^^" ,SnakeAndLaddersSettings.playersNames[0],SnakeAndLaddersSettings.playersNames[1],0,0,20);   
        }       
        else if(status == Status.INVALID_MOVE){
            System.out.println("INVALID_MOVE");
            result =AlertBox.display("Invalid Move","Invalid move ... Please Try Again ...","","",0,0,2);
            
        }
        System.out.println("5");
        fillBoard(boardArr);
        System.out.println("6");
    }
    
     static public void fillBoard(int[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardArr[i][j]=board[i][j];
                if (board[i][j] == 0) {
                    tile[i][j].circle.setFill(null);
                }
                else if (board[i][j] == 1) {
                    tile[i][j].circle.setFill(SnakeAndLaddersSettings.player1Color);
                }
                else {
                    tile[i][j].circle.setFill(SnakeAndLaddersSettings.player2Color);

                }
            }
        }
    }
      static public int find(int c) {
        //int x = coordinatsArr[c].x;
        //int y = coordinatsArr[c].y;
        int indexX = 10;
        int indexY = 10;
        int out;
        int flag = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(boardArr[i][j] == c){
                    indexX = i;
                    indexY = j;
                    flag = 1;
                    break;
                }
                
            }
            if(flag == 1)
                break;
        }
        
        for(int i = 1; i < 101 ; i++){
            if(coordinatsArr[i].x == indexX && coordinatsArr[i].y == indexY){
                return i;
            }
        }
        return 0;
        
    }
    public static GridPane createBoard(int rowCount, int columnCount) {
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(500, 500);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {

                tile[i][j].setTranslateX(j * 50+39);
                tile[i][j].setTranslateY(i * 50+19);
                gridPane.getChildren().add(tile[i][j]);
            }
        }
        gridPane.setPadding(new Insets(0, 5, 0, 5));
        return gridPane;
        
    }
    static void SnakesAndLaddersCoordinates(){
    
        int len = 0;
        coordinatsArr[len++]= new PairJava(0,0);
        for(int i = 0 ; i <= 9 ;i++){
            coordinatsArr[len++]=new PairJava(9,i);
        }
        for(int i = 9 ; i >= 0 ;i--){
            coordinatsArr[len++]=new PairJava(8,i);
        }
        for(int i = 0 ; i <= 9 ;i++){
            coordinatsArr[len++]=new PairJava(7,i);
        }
        for(int i = 9 ; i >= 0 ;i--){
            coordinatsArr[len++]=new PairJava(6,i);
        }
        for(int i = 0 ; i <= 9 ;i++){
            coordinatsArr[len++]= new PairJava(5,i);
        }
        for(int i = 9 ; i >= 0 ;i--){
            coordinatsArr[len++]=new PairJava(4,i);
        }
        for(int i = 0 ; i <= 9 ;i++){
            coordinatsArr[len++]=new PairJava(3,i);
        }

            for(int i = 9 ; i >= 0 ;i--){
            coordinatsArr[len++]= new  PairJava(2,i);
        }
            for(int i = 0 ; i <= 9 ;i++){
            coordinatsArr[len++]= new PairJava(1,i);
        }
            for(int i = 9 ; i >= 0 ;i--){
            coordinatsArr[len++]= new PairJava(0,i);
        }

    }
}

class TileSnakeAndLadders extends StackPane {
    Circle circle = new Circle(16);

    public TileSnakeAndLadders() {
        Rectangle border = new Rectangle(50, 50);
        border.setFill(null);
        border.setStroke(null);
        setAlignment(Pos.CENTER);
        circle.setFill(null);
        getChildren().addAll(border, circle);
    }

    public void drawPlayer1() {
        circle.setFill(Color.BLACK);
    }

    public void drawPlayer2() {
        circle.setFill(Color.RED);
    }
}