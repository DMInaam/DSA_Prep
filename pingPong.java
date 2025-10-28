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