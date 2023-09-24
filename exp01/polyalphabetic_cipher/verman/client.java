// auto key system cipher
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client{

    public static String encrypt(String msg){
        String ans = "";
        String key = "english";

        // xor the letters
        for(int i = 0; i < msg.length(); i++){
            int x = (msg.charAt(i) - 'a') ^ (key.charAt(i) - 'a');
            x = x % 26;
            ans += (char)(x + 'a');
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