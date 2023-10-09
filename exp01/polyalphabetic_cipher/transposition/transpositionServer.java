/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hill;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author student
 */
public class transpositionServer {
     public static void main(String args[]) throws IOException
     {
        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();
        System.out.println("[INFO] : Client connected");
        DataInputStream is = new DataInputStream(s.getInputStream());
        String cipher = is.readUTF();
        int key = 2;
        
        char[][] rail = new char[key][cipher.length()];
 
        // filling the rail matrix to distinguish filled
        // spaces from blank ones
        for (int i = 0; i < key; i++)
            Arrays.fill(rail[i], '\n');
 
        // to find the direction
        boolean dirDown = true;
 
        int row = 0, col = 0;
 
        // mark the places with '*'
        for (int i = 0; i < cipher.length(); i++) {
            // check the direction of flow
            if (row == 0)
                dirDown = true;
            if (row == key - 1)
                dirDown = false;
 
            // place the marker
            rail[row][col++] = '*';
 
            // find the next row using direction flag
            if (dirDown)
                row++;
            else
                row--;
        }
 
        // now we can construct the fill the rail matrix
        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (rail[i][j] == '*'
                    && index < cipher.length())
                    rail[i][j] = cipher.charAt(index++);
 
        StringBuilder result = new StringBuilder();
 
        row = 0;
        col = 0;
        for (int i = 0; i < cipher.length(); i++) {
            // check the direction of flow
            if (row == 0)
                dirDown = true;
            if (row == key - 1)
                dirDown = false;
 
            // place the marker
            if (rail[row][col] != '*')
                result.append(rail[row][col++]);
 
            // find the next row using direction flag
            if (dirDown)
                row++;
            else
                row--;
        }
        System.out.println("Decrypted text : "+  result.toString()) ;
        
        
     }
}
