import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client{

    public static String encrypt(String msg){
        String ans = "";
        int key = 3;
        // remove space in msg
        
        msg = msg.replaceAll("\\s", "");
        for(int i = 0; i < msg.length(); i++){
            char x = msg.charAt(i);
            int y;
            if(Character.isUpperCase(x)){
                y = ((x - 65) + key)%26;
                ans += (char)(y + 65);
            }
            else{
                y = ((x - 97) + key)%26;
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