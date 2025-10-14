import java.util.Scanner;

public class DigitsSum {
    static int sumOfDigits(int n){
        int result = 0;
        while(n>0){
            result+=n%10;
            n/=10;
        }
        return result;
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            System.out.println("The sum of the digits is "+ sumOfDigits(n));
        }
    }
}
