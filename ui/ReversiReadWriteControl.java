/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author mainmhl
 */
enum Status {
    SUCCESS, // valid move, piece successfully placed
    OUT_OF_BOUNDS, // invalid move, position is not on the board
    POSITION_FILLED, // invalid move, position is already filled
    INVALID_MOVE, // invalid move, position would not flip any of the opponent's pieces
    CANNOT_MOVE, // current player cannot move
    QUIT, // quit application
    FINDING_MOVE, // searching for a computer player's move
    WIN, // win
    KILL, // killed frenzy ! kills the other player
    AWARD, // if dice == 6
    BITE, // snakes
    CLIMB				// ladders
}

class PairJava{
    int x , y;

    PairJava(int x , int y){
        this.x = x;
        this.y = y;
    }
}

public class ReversiReadWriteControl {

    public static Status toStatus(char[] c) {
        String s = new String(c);
        
        if (s.equals("SUCCESS")) {
            return Status.SUCCESS;
        } else if (s.equals("OUT_OF_BOUNDS")) {
            return Status.OUT_OF_BOUNDS;
        } else if (s.equals( "POSITION_FILLED")) {
            return Status.POSITION_FILLED;
        } else if (s.equals("INVALID_MOVE")) {
            return Status.INVALID_MOVE;
        } else if (s.equals("CANNOT_MOVE")) {
            return Status.CANNOT_MOVE;
        } else if (s.equals("QUIT") ){
            return Status.QUIT;
        } else if (s.equals("FINDING_MOVE")) {
            return Status.FINDING_MOVE;
        } else if (s.equals("WIN")) {
            return Status.WIN;
        } else if (s.equals("KILL")) {
            return Status.KILL;
        } else if (s.equals("AWARD")) {
            return Status.AWARD;
        } else if (s.equals("BITE")) {
            return Status.BITE;
        } else if (s.equals("CLIMB") ){
            return Status.CLIMB;
        }
        return null;
    }

    public static char[] toStringStatus(Status s) {
        if (s == Status.SUCCESS) {
            return "SUCCESS".toCharArray();
        } else if (s == Status.OUT_OF_BOUNDS) {
            return "OUT_OF_BOUNDS".toCharArray();
        } else if (s == Status.POSITION_FILLED) {
            return "POSITION_FILLED".toCharArray();
        } else if (s == Status.INVALID_MOVE) {
            return "INVALID_MOVE".toCharArray();
        } else if (s == Status.CANNOT_MOVE) {
            return "CANNOT_MOVE".toCharArray();
        } else if (s == Status.QUIT) {
            return "QUIT".toCharArray();
        } else if (s == Status.FINDING_MOVE) {
            return "FINDING_MOVE".toCharArray();
        } else if (s == Status.WIN) {
            return "WIN".toCharArray();
        } else if (s == Status.KILL) {
            return "KILL".toCharArray();
        } else if (s == Status.AWARD) {
            return "AWARD".toCharArray();
        } else if (s == Status.BITE) {
            return "BITE".toCharArray();
        } else if (s == Status.CLIMB) {
            return "CLIMB".toCharArray();
        }
        return null;
    }

    public static void writeGameInfo( int y, int z, char[][] arr) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("gameInfo.txt");
            char tempY = Character.forDigit(y, 10);
            out.write(tempY);
            char tempZ = Character.forDigit(z, 10);
            out.write(tempZ);
            for (int i = 0; i < z; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    out.write(arr[i][j]);
                }
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static Status readStatus() throws FileNotFoundException, IOException {
        FileInputStream in = null;
        Status status = null;
        char[] str = new char[100];
        char s;
        int i = 0;
        try {
            
            in = new FileInputStream("status.txt");
            while (in.available() > 0) {
                s = (char) in.read();
                if(s!=0){
                    str[i++] = s;
                   
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        
        char [] str2 = new char[i];
        for(int j=0;j<i;j++){
            str2[j]=str[j];
        }
        
        status = toStatus(str2);
        
        return status;
    }

    public static Status readNextStatus() throws FileNotFoundException, IOException {
        FileInputStream in = null;
        Status status = null;
        char[] str = new char[100];
        char s;
        int i = 0;
        try {
            
            in = new FileInputStream("nextStatus.txt");
            while (in.available() > 0) {
                s = (char) in.read();
                if(s!=0){
                    str[i++] = s;
                   
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        
        char [] str2 = new char[i];
        for(int j=0;j<i;j++){
            str2[j]=str[j];
        }
        
        status = toStatus(str2);
        
        return status;
    }

    public static void writeStatus( Status status) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        char[] arr = new char[100];
        arr = toStringStatus(status);
        try {
            out = new FileOutputStream("status.txt");
            int len = arr.length;
            for (int i = 0; i < 100; i++) {
                if(i>=len){
                    out.write('\0');

                }
                else{
                    out.write(arr[i]);
                }

               
                
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
        
    }

    public static void writeBoard(int[][] board) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        try {
            char temp;
            out = new FileOutputStream("board.txt");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    temp = Character.forDigit(board[i][j], 10);
                    
                    out.write(temp);
                }
                

            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static int[][] readBoard() throws FileNotFoundException, IOException {
        FileInputStream in = null;
        int[][] board = new int[10][10];
        int num;
        try {
            char temp;
            in = new FileInputStream("board.txt");
            for (int i = 0; i < 100; i++) {
                temp = (char) in.read();
                board[i / 10][i % 10] = Character.getNumericValue(temp);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return board;
    }


    public static PairJava[] readFlip() throws FileNotFoundException, IOException {
        FileInputStream in = null;
        PairJava[] board ;
        int num;
        try {
            char temp, temp2;
            in = new FileInputStream("flip.txt");
            temp = (char) in.read();
            int len =   Character.getNumericValue(temp);
            board = new PairJava[len];
            for (int i = 0; i < len; i++) {
                temp = (char) in.read();
                temp2 = (char) in.read();
                board[i ] = new PairJava(Character.getNumericValue(temp) ,  Character.getNumericValue(temp2));
               // System.out.println(temp+ "  " + temp2);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return board;
    }
}
