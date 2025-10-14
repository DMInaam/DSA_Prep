import java.util.Scanner;

public class sumOfN {
    public static int findSum(int n){return (int)(n*(n+1))/2;}
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            System.out.println("the sum of first "+n+" natural number is "+findSum(n));
        }
    }
}
