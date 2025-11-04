import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; // Only needed for Arrays.toString in main

public class XSum {

    // A small helper class to store the number and its frequency.
    // This is cleaner than using List<int[]>
    private class NumFreq {
        int num;
        int freq;

        NumFreq(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    }

    /**
     * This is the main function. It manages the sliding window.
     */
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] answer = new int[n - k + 1];

        // Loop from the first possible window start (i=0) to the last (i=n-k)
        for (int i = 0; i < n - k + 1; i++) {
            // OPTIMIZATION 1:
            // Instead of copying the array, we pass the original array
            // and the start/end bounds of the current window.
            // The window is from index i (inclusive) to i + k (exclusive).
            answer[i] = calculateXSum(nums, i, i + k, x);
        }
        return answer;
    }

    /**
     * A private helper method to calculate the x-sum for a single window
     * defined by [start, end) in the nums array.
     */
    private int calculateXSum(int[] nums, int start, int end, int x) {
        
        // OPTIMIZATION 2:
        // Use a simple array for frequency counting, since nums[i] <= 50.
        // Index 0 is unused, indices 1-50 store counts for numbers 1-50.
        int[] freqArray = new int[51];
        
        int totalSum = 0;
        int distinctCount = 0;

        // 1. Count frequencies and get total sum in one pass
        for (int i = start; i < end; i++) {
            int num = nums[i];
            if (freqArray[num] == 0) {
                distinctCount++; // Found a new distinct number
            }
            freqArray[num]++;
            totalSum += num;
        }

        // --- Check the Special Case ---
        if (distinctCount < x) {
            return totalSum;
        }

        // --- Normal Case: Find the Top x Elements ---

        // 2. Build a list of (num, freq) pairs from the frequency array
        List<NumFreq> list = new ArrayList<>();
        for (int num = 1; num <= 50; num++) {
            if (freqArray[num] > 0) {
                list.add(new NumFreq(num, freqArray[num]));
            }
        }

        // 3. Sort the list based on the problem's priority rules
        //    (This sort is very fast, as 'list.size()' is at most 50)
        list.sort((a, b) -> {
            if (a.freq != b.freq) {
                return Integer.compare(b.freq, a.freq); // Higher freq first
            } else {
                return Integer.compare(b.num, a.num);   // Bigger value first
            }
        });

        // OPTIMIZATION 3:
        // Use a boolean array (like a Set) to mark the top x elements.
        boolean[] isTopX = new boolean[51];
        for (int i = 0; i < x; i++) {
            isTopX[list.get(i).num] = true;
        }

        // 4. Calculate the final filtered sum
        int xSum = 0;
        for (int i = start; i < end; i++) {
            int num = nums[i];
            // Fast O(1) lookup
            if (isTopX[num]) {
                xSum += num;
            }
        }
        
        return xSum;
    }

    // --- Main method for testing ---
    public static void main(String[] args) {
        XSum sol = new XSum();
        int[] nums = {1, 1, 2, 2, 3, 4, 2, 3};
        int k = 6;
        int x = 2;
        int[] result = sol.findXSum(nums, k, x);
        // Expected: [6, 10, 12]
        System.out.println("Result: " + Arrays.toString(result));
    }
}