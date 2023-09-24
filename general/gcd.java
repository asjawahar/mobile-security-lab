import java.util.Scanner;
public class gcd{

    public static long gcd1(long a, long b){
        if(b == 0)
            return a;
        System.out.println("a: " + a + " b: " + b);
        return gcd1(b, a%b);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a and b: ");
        long a = sc.nextLong();
        long b = sc.nextLong();

        long gcd;

        gcd = gcd1(a, b);
        System.out.println("GCD of " + a + " and " + b + " is " + gcd);



    }
}