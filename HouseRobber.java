import java.util.Scanner;

public class HouseRobber {
    public static int maxNonAdjacentSum(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return arr[0];
        }
        int prev2 = arr[0];
        int prev1 = Math.max(arr[0], arr[1]); 
        for (int i = 2; i < n; i++) {
            int current = Math.max(prev1, prev2 + arr[i]);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter a string of digits: ");
            String s = sc.next();
            int n = s.length();

            if (n == 0) {
                System.out.println(0);
                return;
            }
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    arr[i] = c - '0';
                } else {
                    System.out.println("Invalid input: Please enter only digits.");
                    return;
                }
            }
            int result = maxNonAdjacentSum(arr);
            System.out.println("Maximum non-adjacent sum: " + result);
        }
    }
}
