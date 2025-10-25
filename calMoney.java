/*
 * 1716. Calculate Money in Leetcode Bank
 * Date: 25-10-2025
 * 
 * Question: Hercy wants to save money for his first car. He puts money in the Leetcode bank every day. He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday. Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.
 */

class calMoney {
    public static int totalMoney(int n) {
        int r = n%7;
        int q = n/7;
        int sum_W = (q * (2*28 + (q-1)*7) )/2; 
        int sum_D = (r * (q+1+q+r) )/2;
        return sum_D + sum_W ;
    }
    // public static int totalMoney(int n) {
    //     int r = n%7;
    //     int q = n/7;
    //     int s=7;
    //     int sum = 0;
    //     int a = 0;
    //     for(int i= 0;i<=q;i++){
    //         if(q==0 || i == q )
    //         {
    //             sum += ((r+i)*(r+i+1)/2)-a;
    //             return sum;
    //         }
    //         sum+= (s*(s+1)/2)-a;
    //         a+=i+1;
    //         s++;
    //     }
    //     return sum;
    // }
    public static void main(String[] args) {
        System.out.println(totalMoney(8));
        System.out.println(totalMoney(9));
        System.out.println(totalMoney(10));
    }
}