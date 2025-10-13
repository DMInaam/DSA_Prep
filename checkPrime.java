import java.util.Scanner;

public class checkPrime {
    public static Boolean isPrime(int n){
        if(n<1) return false;
        if (n==2 || n==3) return true;
        if (n%2 == 0 || n%3 == 0) return false;
        for (int i=5;i<=Math.sqrt(n);i+=6){//iteration based on 6k-1 and 6k+1
            if(n%i == 0 || n%(i+2)==0) return false; // n%i is 6k-1 & n%i+2 is 6k+1 
        }
        return true;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            if (isPrime(n)) 
                System.out.println("The number is prime");
            else
                System.out.println("The number is not prime");
        }catch(Exception e){e.printStackTrace();}
    }
}
