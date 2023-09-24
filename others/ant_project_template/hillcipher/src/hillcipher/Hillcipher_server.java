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
public class Hillcipher_server {
    public static void main(String[] args){  
        try{  
        ServerSocket ss=new ServerSocket(6666);  
        Socket s=ss.accept();//establishes connection   
        DataInputStream dis=new DataInputStream(s.getInputStream());
        int size=2;
        int arr[][]=new int[2][2];
        arr[0][0]=9;
        arr[0][1]=54;
        arr[1][0]=27;
        arr[1][1]=15;
        
//        for(int i=0; i<2; i++)
//        {
//            for(int j=0; j<2; j++)
//            {
//                arr[i][j]=arr[i][j]%26;
//                if(arr[i][j]<0) 
//                {
//                    arr[i][j]+=27;
//                }
//                    
//                System.out.println(arr[i][j]);
//            }
//        }
        
        
        
        
        String  str=(String)dis.readUTF();
        int p[][]=new int[str.length()/size][size]; 
        int c[][]=new int[str.length()/size][size];
        
        int t=0;
        for(int i=0; i<str.length()/size; i++)
        {
            for(int j=0; j<size; j++)
            {
                int a=(str.charAt(t)-97)%26;
                c[i][j] = a;
                System.out.println(c[i][j]); 
                t++;
            }

        }
    String temp="";
    for(int i=0;i<str.length()/size;i++){    
    for(int j=0;j<size;j++){    
    p[i][j]=0;      
    for(int k=0;k<size;k++)      
    {      
    p[i][j]+=c[i][k]*arr[k][j];
    }//end of k loop  
    temp+=(char)((p[i][j])%26+97);
    System.out.print(p[i][j]+" ");
    //printing matrix element  
    }//end of j loop  
    System.out.println();//new line    
    }
        
        System.out.println("CipherText= "+str);  
        System.out.println("PlainText= "+temp);
        ss.close();  
        }catch(Exception e){System.out.println(e);}  
     }  
}
