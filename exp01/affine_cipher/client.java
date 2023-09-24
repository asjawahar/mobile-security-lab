import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client{

    public static String encrypt(String msg){
        String ans = "";
        int a = 9, b = 17;

        // affine cipher
        for(int i = 0; i < msg.length(); i++){
            char x = msg.charAt(i);
            int y;
            if(Character.isUpperCase(x)){
                y = ((a * (x - 65)) + b)%26;
                ans += (char)(y + 65);
            }
            else{
                y = ((a * (x - 97)) + b)%26;
                ans += (char)(y + 97);
            }
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