/*
 * 3289. The Two Sneaky Numbers of Digitville
 * Date: 31-10-2025
 * 
 * Question: In the town of Digitville, there was a list of numbers called nums containing integers from 0 to n - 1. Each number was supposed to appear exactly once in the list, however, two mischievous numbers sneaked in an additional time, making the list longer than usual. As the town detective, your task is to find these two sneaky numbers. Return an array of size two containing the two numbers (in any order), so peace can return to Digitville.
 */

public class SneakyNum {
    public static int[] getSneakyNumbers(int[] nums) {
        int count = 0;
        int n = nums.length;
        int[] arr = new int[2];
        for(int i:nums){
            int ex = i%n;
            nums[ex]+=n;
        }
        for(int j=0;j<n-2;j++){
            if((nums[j]/n) == 2) arr[count++] = j;
            if(count == 2) break;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] n = {0,5,3,3,5,2,1,6,4};
        n = getSneakyNumbers(n);
        System.out.print("[ ");
        for(int i:n){System.out.print(i+" ");}
        System.out.print("]");
    }
}
