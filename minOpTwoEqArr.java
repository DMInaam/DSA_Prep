/*
 * 1526. Minimum Number of Increments on Subarrays to Form a Target Array
 * Date: 30-10-2025
 * 
 * Question: You are given an integer array target. You have an integer array initial of the same size as target with all elements initially zeros. In one operation you can choose any subarray from initial and increment each value by one. Return the minimum number of operations to form a target array from initial. The test cases are generated so that the answer fits in a 32-bit integer.
 */
public class minOpTwoEqArr {
    public int minNumberOperations(int[] target) {
        int steps = 0;
        int prev = 0;
        for(int cur:target){
            if(cur>prev){
                steps += (cur-prev);
            }
            prev = cur;
        }
        return steps;
    }
}
