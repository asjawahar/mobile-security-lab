/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hill;


import java.io.*;
import java.util.*;
import java.net.*;
import java.math.*;

/**
 *
 * @author student
 */
public class rsaServer {
    public static void main(String str[]) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Socket s = ss.accept();
        System.out.println("[INFO] : Client connected");
        DataInputStream is = new DataInputStream(s.getInputStream());
        
        Double line;
        
            line = is.readDouble();
            int n = is.readInt();
            int d = is.readInt();
            System.out.println("Encrypted message is : " + line);
 
            // converting int value of n to BigInteger
            BigInteger N = BigInteger.valueOf(n);

            // converting float value of c to BigInteger
            BigInteger C = BigDecimal.valueOf(line).toBigInteger();
            BigInteger dec = (C.pow(d)).mod(N);
            System.out.println("Decrypted message is : "
                               + dec);
            
        
        s.close();
        ss.close();
        
    }

}
