/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hill;
import java.math.*;
import java.io.*;
import java.util.*;
import java.net.*;
/**
 *
 * @author student
 */
public class rsaClient {
    
    static int gcd(int e, int z)
    {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }
    
    
    public static void main(String args[]) throws IOException
    {
        int p,q,n,z;
        
        int d = 0;
        
        int e,i;
        
        int msg = 17;
        double c;
        
        Socket soc = new Socket("localhost", 8080);
        DataOutputStream out = new DataOutputStream(soc.getOutputStream());
        
        // 1st prime number p
        p = 3;
 
        // 2nd prime number q
        q = 11;
        n = p * q;
        z = (p - 1) * (q - 1);
        System.out.println("the value of z = " + z);
 
        for (e = 2; e < z; e++) {
 
            // e is for public key exponent
            if (gcd(e, z) == 1) {
                break;
            }
        }
        System.out.println("the value of e = " + e);
        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * z);
 
            // d is for private key exponent
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("the value of d = " + d);
        c = (Math.pow(msg, e)) % n;
        System.out.println("Encrypted message is : " + c);
 
        // converting int value of n to BigInteger
        BigInteger N = BigInteger.valueOf(n);
        out.writeDouble(c);
        out.writeInt(n);
        out.writeInt(d);
        
    }
}
