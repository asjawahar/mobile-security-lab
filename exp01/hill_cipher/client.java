import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client{

    // print matrix
    public static void print_mat(int[][] mat){
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

    // matrix multiplication
    public static int[][] mat_mul(int[][] mat1, int[][] mat2){
        int[][] ans = new int[mat1.length][mat2[0].length];
        for(int i = 0; i < mat1.length; i++){
            for(int j = 0; j < mat2[0].length; j++){
                ans[i][j] = 0;
                for(int k = 0; k < mat1[0].length; k++){
                    ans[i][j] += mat1[i][k] * mat2[k][j];
                }
                ans[i][j] = ans[i][j]%26;
            }
        }
        return ans;
    }

    public static String encrypt(String msg){
        String ans = "";

        int[][] key = {{5, 8}, {17, 3}};
        int n = msg.length();
        
        if(n%2 != 0){
            msg += "a";
            n+=1;
        }
        int[][] msg_mat = new int[n/2][2];

        int k = 0;
        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < 2; j++){
                msg_mat[i][j] = (int)msg.charAt(k) - 97;
                k++;
            }
        }

        System.out.println("Message matrix: ");  
        print_mat(msg_mat);

        int[][] enc_mat = mat_mul(msg_mat, key);
        System.out.println("Encrypted matrix: ");
        print_mat(enc_mat);

        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < 2; j++){
                ans += (char)(enc_mat[i][j] + 97);
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