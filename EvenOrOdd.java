import java.util.Scanner;

public class EvenOrOdd {
    static boolean isEven(int n){
        if((n&1)==0)return true;
        return false;
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            if (isEven(n)) {
                System.out.println("The number is even");
            }
            else System.out.println("The number is Odd");
        }
    }
}
