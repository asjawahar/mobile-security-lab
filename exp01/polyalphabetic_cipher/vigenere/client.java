// vigener cipher
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client{

    public static String encrypt(String msg){
        String ans = "";    
        String key = "english";
        int n = msg.length();
        int m = key.length();

        // extend the keyword to match the length of the message
        int i = 0; 
        while(m < n){
            key += key.charAt(i);
            i++;
            m = key.length();
        }
        System.out.println("Key: " + key);

        for (i = 0; i < n; i++){
            int x = (int)msg.charAt(i) - 97;
            int y = (int)key.charAt(i) - 97;
            int z = (x + y) % 26;
            ans += (char)(z + 97);
        }
        
        return ans;
    }


    public static void main(String[] args) throws Exception{
        Socket s = new Socket("localhost", 1234);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the message: ");
        String msg = sc.nextLine();
        
        String ans = "";
        ans = encrypt(msg);

        System.out.println("Encrypted text: " + ans);

        dout.writeUTF(ans);
        dout.flush();
        dout.close();
        s.close();
    }
}