import java.io.*;
import java.net.*;
import java.util.Scanner;

public class server{

        public static String decrypt(String msg){
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
        ServerSocket ss = new ServerSocket(1234);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        Scanner sc = new Scanner(System.in);

        String msg = (String)din.readUTF();

        String ans = "";
        ans = decrypt(msg);

        System.out.println("Encrypted text: " + msg);
        System.out.println("Decrypted text: " + ans);

        din.close();
        s.close();
        ss.close();
    }
}
