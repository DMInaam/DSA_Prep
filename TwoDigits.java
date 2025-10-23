public class TwoDigits {
/*
 * 3461. Check If Digits Are Equal in String After Operations I
 * Date: 23-10-2025
 * 
 * Question: You are given a string s consisting of digits. Perform the following operation repeatedly until the string has exactly two digits:For each pair of consecutive digits in s, starting from the first digit, calculate a new digit as the sum of the two digits modulo 10.Replace s with the sequence of newly calculated digits, maintaining the order in which they are computed. Return true if the final two digits in s are the same; otherwise, return false.
 */
    //using normal iterations
    public static boolean hasSameDigits(String s) {
        if(s.length()<2) return false;
        String current = s;
        StringBuilder bin = new StringBuilder();
        while(current.length()>2){ 
            for(int i = 0;i<current.length()-1;i++){
            bin.append(((current.charAt(i)-'0') + (current.charAt(i+1)-'0'))%10); 
        }
        current = bin.toString();
        bin.delete(0,bin.length());
        }
        return current.charAt(0) == current.charAt(1);
    }
    
    // using recursions
    // public static boolean hasSameDigits(String s) {
    //     StringBuilder bin = new StringBuilder();
    //     if(s.length()<2) return false;
    //     if(s.length()==2){
    //         if(s.charAt(0)==s.charAt(1))return true;
    //         return false;
    //     }
    //     for(int i = 0;i<s.length()-1;i++){
    //         int m = (int) s.charAt(i)+s.charAt(i+1);
    //         bin.append((char)m%10);
    //     }
    //     return hasSameDigits(bin.toString());
    // }

    
    public static void main(String[] args) {
        System.out.println(hasSameDigits("3902"));
        System.out.println(hasSameDigits("3"));
    }
}
