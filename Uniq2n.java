import java.util.HashSet;
public class Uniq2n {
    public static int repeatedNTimes(int[] nums) {
        int n = -1;
        HashSet<Integer> uq = new HashSet<>();
        for(int i:nums){
            if(uq.contains(i)){
                n = i;
                break;
            }
            uq.add(i);
        }
        return n;
    }
    public static void main(String[] args) {
        int[] nums = {5,1,5,2,5,3,5,4};
        int result = repeatedNTimes(nums);
        System.out.println(result);
    }
}