/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillcipher;

/**
 *
 * @author student
 */
import java.io.*;  
import java.net.*;  
import java.util.Scanner;

public class Hillcipher_client {
    public static void main(String[] args) {  
    try{      
    Socket s=new Socket("localhost",6666);  
    DataOutputStream dout=new DataOutputStream(s.getOutputStream());
    Scanner sc=new Scanner(System.in);
    int size = 2;
    int arr[][] = new int[2][2];
//    for(int i=0; i<size; i++)
//    {
//        for(int j=0; j<size; j++)
//        {
//            arr[i][j]=sc.nextInt();
//        }
//    }
    arr[0][0]=5;
    arr[0][1]=8;
    arr[1][0]=17;
    arr[1][1]=3;
     
    String str = new String();
    str = sc.nextLine();
    
    while(str.length()%size!=0)
    {
        str+='a';
    }
    
    System.out.println(str);
    int p[][]=new int[str.length()/size][size]; 
    int c[][]=new int[str.length()/size][size];
    String temp = "";
    int t=0;
    for(int i=0; i<str.length()/size; i++)
    {
        for(int j=0; j<size; j++)
        {
            int a=(str.charAt(t)-97)%26;
            p[i][j] = a;
            System.out.println(p[i][j]);
            t++;
        }
            
    }
//    System.out.println(str.length()/size);
    
    
    
    
    
    for(int i=0;i<str.length()/size;i++){    
    for(int j=0;j<size;j++){    
    c[i][j]=0;      
    for(int k=0;k<size;k++)      
    {      
    c[i][j]+=p[i][k]*arr[k][j];
    }//end of k loop  
    temp+=(char)((c[i][j])%26+97);
    System.out.print(c[i][j]+" ");
    //printing matrix element  
    }//end of j loop  
    System.out.println();//new line    
    }
    System.out.println(temp);
    
    
    dout.writeUTF(temp);  
    dout.flush();  
    dout.close();  
    s.close();  
    }catch(Exception e){System.out.println(e);}  
    }  
}  

 
