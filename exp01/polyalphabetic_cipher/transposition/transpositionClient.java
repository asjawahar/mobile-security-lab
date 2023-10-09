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
public class transpositionClient {
    
    public static String encrypt(String text, int key)
	{

		// create the matrix to cipher plain text
		// key = rows , length(text) = columns
		char[][] rail = new char[key][text.length()];

		// filling the rail matrix to distinguish filled
		// spaces from blank ones
		for (int i = 0; i < key; i++)
			Arrays.fill(rail[i], '\n');

		boolean dirDown = false;
		int row = 0, col = 0;

		for (int i = 0; i < text.length(); i++) {

			// check the direction of flow
			// reverse the direction if we've just
			// filled the top or bottom rail
			if (row == 0 || row == key - 1)
				dirDown = !dirDown;

			// fill the corresponding alphabet
			rail[row][col++] = text.charAt(i);

			// find the next row using direction flag
			if (dirDown)
				row++;
			else
				row--;
		}

		// now we can construct the cipher using the rail
		// matrix
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < key; i++)
			for (int j = 0; j < text.length(); j++)
				if (rail[i][j] != '\n')
					result.append(rail[i][j]);

		return result.toString();
	}

    
     public static void main(String args[]) throws IOException
     {
        Socket soc = new Socket("localhost", 8080);
        DataOutputStream out = new DataOutputStream(soc.getOutputStream());
        Scanner in = new Scanner(System.in);
        System.out.print("Message : ");
        String plain = in.nextLine();
        String enc = encrypt(plain, 2);
        System.out.println("Encrypted Message : " + enc);
        out.writeUTF(enc);
        
     }
}
