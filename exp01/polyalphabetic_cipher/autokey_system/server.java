import java.io.*;
import java.net.*;
import java.util.Scanner;

public class server{

        public static String decrypt(String msg){
        String ans = "";
        String key = "english";
        int n = msg.length();
        int m = key.length();

        int i;

        for (i = 0; i < n-m; i++){
            int x = (int)msg.charAt(i) - 97;
            int y = (int)key.charAt(i) - 97;
            int z = (x - y + 26) % 26;
            ans += (char)(z + 97);
        }
        key += ans;
        for (i = n-m; i < n; i++){
            int x = (int)msg.charAt(i) - 97;
            int y = (int)key.charAt(i) - 97;
            int z = (x - y + 26) % 26;
            ans += (char)(z + 97);
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
