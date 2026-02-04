public class trionic {
    public static boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 1;
        boolean p1 = false,p2 = false,p3 = false;
        while(i<n &&  nums[i-1]<nums[i]) {i++;p1=true;}
        while(i<n && nums[i-1]>nums[i]){i++;p2=true;}
        while(i<n && nums[i-1]<nums[i]){i++;p3 = true;}
        if(i!=n) return false;
        return (p1 && p2 && p3);
    }
    public static void main(String[] args) {
        int[] arr = {1,3,5,4,2,6};
        System.out.println(isTrionic(arr));
    }
}