/*
 * 3370. Smallest Number With All Set Bits
 * Date: 29-10-2025
 * 
 * Question: You are given a positive number n. Return the smallest number x greater than or equal to n, such that the binary representation of x contains only set bits
 */

public class numWSetBits {
    public static int count(int n){
        return (int)(Math.log(n)/Math.log(2)+1);
    }
    public static int smallestNumber(int n) {
        return (int)Math.pow(2,count(n))-1;
    }
    public static void main(String[] args) {
        System.out.println(smallestNumber(116));
    }
}
