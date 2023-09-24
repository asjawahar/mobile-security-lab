import java.io.*;
import java.net.*;
import java.util.Scanner;

public class server{


        public static int modInverse(int a, int m){
            a = a%m;
            for(int x = 1; x < m; x++){
                if((a*x)%m == 1){
                    return x;
                }
            }
            return 1;
        }

        public static String decrypt(String msg){
            String ans = "";
            int a = 9, b = 17 ,m = 26;
            int a_inv = modInverse(a, m);
            
            for(int i = 0; i < msg.length(); i++){
                char x = msg.charAt(i);
                int y;
                if(Character.isUpperCase(x)){
                    y = a_inv * (x - b - 65);
                    y = y % 26;
                    if(y < 0){
                        y += 26;
                    }
                    ans += (char)(y + 65);
                }
                else{
                    y = a_inv * (x - b - 97);
                    y = y % 26;
                    if(y < 0){
                        y += 26;
                    }
                    ans += (char)(y + 97);
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
