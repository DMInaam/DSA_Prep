/*
 * 3354. Make Array Elements Equal to Zero
 * Date: 28-10-2025
 * 
 * Question: You are given an integer array nums. Start by selecting a starting position curr such that nums[curr] == 0, and choose a movement direction of either left or right.After that, you repeat the following process: If curr is out of the range [0, n - 1], this process ends. If nums[curr] == 0, move in the current direction by incrementing curr if you are moving right, or decrementing curr if you are moving left. Else if nums[curr] > 0:Decrement nums[curr] by 1. Reverse your movement direction (left becomes right and vice versa). Take a step in your new direction. A selection of the initial position curr and movement direction is considered valid if every element in nums becomes 0 by the end of the process.Return the number of possible valid selections.
 */

class pingPong {
    public static int countValidSelections(int[] nums) {
        int count = 0;
        long sum = 0;
        for(int i:nums){
            sum+=i;
        }
        long ls = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]== 0){
                long rs = sum - ls;
                if(ls == rs) count += 2;
                else if((ls+1)==rs || ls == (rs+1)) count++;
            }
            ls+=nums[i];
        }
        return count;
    }
    public static void main(String[] args) {
        int[] arr = {16,13,10,0,0,0,10,6,7,8,7};
        System.out.println(countValidSelections(arr));
    }
}