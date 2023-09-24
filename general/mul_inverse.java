import java.util.Scanner;

public class mul_inverse{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a and b: ");  
        int a = sc.nextInt();
        int b = sc.nextInt();

        int ans = 0;
        for(int i = 1; i < b; i++){
            if((a*i)%b == 1){
                ans = i;
                break;
            }
        }
        System.out.println("Multiplicative Inverse of " + a + " mod " + b + " is " + ans);
    }
}