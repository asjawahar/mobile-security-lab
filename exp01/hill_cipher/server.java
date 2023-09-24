import java.io.*;
import java.net.*;
import java.util.Scanner;

public class server{

    public static void print_mat(int[][] mat){
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

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

    // inverse of a number
    public static int inverse_no(int a){
        int ans = 0;
        for(int i = 0; i < 26; i++){
            if((a*i)%26 == 1){
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static int[][] check_mat(int[][] mat){
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                mat[i][j] = mat[i][j]%26;
                if(mat[i][j] < 0)
                    mat[i][j] += 26;
            }
        }
        return mat;
    }

    // inverse
    public static int[][] inverse(int[][] mat){
        int[][] ans = new int[mat.length][mat[0].length];
        int det = mat[0][0]*mat[1][1] - mat[0][1]*mat[1][0];
        det = det%26;
        if(det < 0)
            det += 26;

        System.out.println("det: " + det);
        int det_inv = inverse_no(det);
        System.out.println("det_inv: " + det_inv);

        ans[0][0] = mat[1][1]*det_inv;
        ans[0][1] = -mat[0][1]*det_inv;
        ans[1][0] = -mat[1][0]*det_inv;
        ans[1][1] = mat[0][0]*det_inv;
        print_mat(ans);
        ans=check_mat(ans);
        print_mat(ans);
        return ans;
    }



    public static String decrypt(String msg){
        String ans = "";
        // hill cipher
        int[][] key = {{5, 8}, {17, 3}};
        int n = msg.length();
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

        int[][] key_inv = inverse(key);
        int[][] dec_mat = mat_mul(msg_mat, key_inv);
        System.out.println("Decrypted matrix: ");
        print_mat(dec_mat);

        for(int i = 0; i < n/2; i++){
            for(int j = 0; j < 2; j++){
                ans += (char)(dec_mat[i][j] + 97);
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
