import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class maxFreq {

    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        Set<Integer> candidateSet = new HashSet<>();
        for (int num : nums) {
            candidateSet.add(num);
            candidateSet.add(num - k);
            candidateSet.add(num + k);
        }
        List<Integer> candidates = new ArrayList<>(candidateSet);
        Collections.sort(candidates);
        Arrays.sort(nums);
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        int maxF = 0;
        int leftPtr = 0;
        int rightPtr = 0;
        for (int t : candidates) {
            int L = t - k;
            int R = t + k;
            while (leftPtr < n && nums[leftPtr] < L) {
                leftPtr++;
            }
            while (rightPtr < n && nums[rightPtr] <= R) {
                rightPtr++;
            }
            int chin = rightPtr - leftPtr;
            int eq = counts.getOrDefault(t, 0);
            int chg = chin - eq;
            int current = eq + Math.min(chg, numOperations);
            maxF = Math.max(maxF, current);
        }
        
        return maxF;
    }


    public static void main(String[] args) {
        maxFreq sol = new maxFreq();
        int[] nums1 = {1, 2, 4};
        int k1 = 5;
        int numOperations1 = 1;
        System.out.println("Result 1: " + sol.maxFrequency(nums1, k1, numOperations1)); // Output: 2
    }
}