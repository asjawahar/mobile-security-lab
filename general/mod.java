import java.util.Scanner;

public class mod{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a and b: ");
        int a = sc.nextInt();
        int b = sc.nextInt();

        // find a mod b
        // handle all cases, a and b can be negative
        if(b<0){
            b = -b;
        }
        int ans = a%b;
        if(ans < 0)
            ans += b;
        System.out.println("a mod b: " + ans);
        
    }
}