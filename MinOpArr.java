public class MinOpArr {
    public static int minOperations(int[] nums) {
        var stack = new int[nums.length + 1];
        var top = 0;
        var ans = 0;
        for (var i = 0; i < nums.length; i++) {
            while (stack[top] > nums[i]) {
                top--;
                ans++;
            }
            if (stack[top] != nums[i])
                stack[++top] = nums[i];
        }
        return ans + top;
    }
    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{6,3,6,0,1,5,8,4,748,3,0}));
    }
}
