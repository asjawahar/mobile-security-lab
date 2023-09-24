import java.io.*;
import java.net.*;
import java.util.Scanner;

public class server{

        public static String decrypt(String msg){
        String ans = "";
        int key = 3;
        for(int i = 0; i < msg.length(); i++){
            char x = msg.charAt(i);
            int y;
            if(Character.isUpperCase(x)){
                y = ((x - 65) - key)%26;
                if(y < 0){
                    ans += (char)(y + 26 + 65);
                }else{
                    ans += (char)(y + 65);
                }
            }
            else{
                y = ((x - 97) - key)%26;
                if(y < 0){
                    ans += (char)(y + 26 + 97);
                }else{
                    ans += (char)(y + 97);
                }
            }
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
