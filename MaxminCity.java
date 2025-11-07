class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;

        // 1. Calculate initial power for each city using a sliding window
        long[] initial_power = new long[n];
        long current_window_sum = 0;

        // Initialize window for city 0
        for (int i = 0; i <= r && i < n; i++) {
            current_window_sum += stations[i];
        }
        initial_power[0] = current_window_sum;

        // Slide the window for remaining cities
        for (int i = 1; i < n; i++) {
            int add_idx = i + r;
            int rem_idx = i - r - 1;

            if (add_idx < n) {
                current_window_sum += stations[add_idx];
            }
            if (rem_idx >= 0) {
                current_window_sum -= stations[rem_idx];
            }
            initial_power[i] = current_window_sum;
        }

        // 2. Binary search for the maximum possible minimum power
        long low = 0;
        long high = 0; // A safe upper bound is sum of all stations + k
        long min_initial = Long.MAX_VALUE;

        for (long p : initial_power) {
             min_initial = Math.min(min_initial, p);
        }
        for (int s : stations) {
            high += s;
        }
        high += k;
        
        // We can use the minimum initial power as a tighter lower bound
        low = min_initial; 

        long ans = 0;

        while (low <= high) {
            long target_min_power = low + (high - low) / 2;
            
            // Pass initial_power, r, k, and the target to check
            if (check(initial_power, r, k, target_min_power)) {
                ans = target_min_power;      // This target is achievable
                low = target_min_power + 1; // Try for an even higher minimum
            } else {
                high = target_min_power - 1; // This target is too high
            }
        }
        return ans;
    }

    /**
     * Checks if it's possible to make all cities have at least `target` power
     * using at most `k` stations. This is done greedily in O(n).
     */
    private boolean check(long[] initial_power, int r, long k, long target) {
        int n = initial_power.length;
        
        // `added_power` is a difference array. 
        // `added_power[i]` stores the change in *additional* power starting at city `i`.
        long[] added_power = new long[n + 1]; 
        long k_used = 0;
        long current_power_delta = 0; // This is the running sum of `added_power`

        for (int i = 0; i < n; i++) {
            // 1. Update the running sum of additions based on previous steps
            current_power_delta += added_power[i];

            // 2. Calculate the total power of the current city
            long current_city_power = initial_power[i] + current_power_delta;

            // 3. If power is insufficient, we must add stations
            if (current_city_power < target) {
                // 4. Calculate how many stations are needed
                long needed = target - current_city_power;

                // 5. Check if we have enough budget
                k_used += needed;
                if (k_used > k) {
                    return false; // Exceeded budget
                }

                // 6. Apply the effect of adding `needed` stations.
                // We add them at city `j = i + r`. This affects the range `[i, i + 2*r]`.
                
                // Update our running delta *immediately* since the effect starts at `i`
                current_power_delta += needed;

                // Schedule the subtraction at the end of the affected range
                int end_range_exclusive = Math.min(n - 1, i + 2 * r) + 1;
                
                // We only need to schedule the subtraction if it's within bounds
                // (The `added_power` array has size n+1, so index `n` is valid)
                if (end_range_exclusive <= n) { 
                    added_power[end_range_exclusive] -= needed;
                }
            }
        }
        
        // If we got through the whole loop without exceeding k, it's possible
        return true;
    }
    public static void main(String[] args) {
        // Create an instance of the Solution class
        Solution sol = new Solution();

        // Example 1:
        int[] stations1 = {1, 2, 4, 5, 0};
        int r1 = 1;
        int k1 = 2;
        long result1 = sol.maxPower(stations1, r1, k1);
        System.out.println("Example 1 Output: " + result1); // Expected: 5

        // Example 2 (Another test case):
        // 4 cities, range 0, add 10 stations
        // Initial state: [1, 2, 3, 4]
        // We can add 10 to city 0. Powers: [11, 2, 3, 4]. Min: 2
        // We can add 5 to city 0, 5 to city 1. Powers: [6, 7, 3, 4]. Min: 3
        // Optimal: Add 2 to city 0, 1 to city 1, 0 to city 2, 7 to city 3
        // Powers: [3, 3, 3, 11]. Min: 3.
        // Let's try for 3.
        // i=0, power=1, need 2. k_used=2. (add at i+r = 0)
        // i=1, power=2, need 1. k_used=3. (add at i+r = 1)
        // i=2, power=3, need 0. k_used=3.
        // i=3, power=4, need 0. k_used=3.
        // So 3 is possible.
        // Let's try for 4.
        // i=0, power=1, need 3. k_used=3.
        // i=1, power=2, need 2. k_used=5.
        // i=2, power=3, need 1. k_used=6.
        // i=3, power=4, need 0. k_used=6.
        // 6 <= 10. So 4 is possible.
        // Let's try for 7.
        // i=0, need 6. k_used=6.
        // i=1, need 5. k_used=11. -> Fails.
        // So answer should be 6.
        int[] stations2 = {1, 2, 3, 4};
        int r2 = 0;
        int k2 = 10;
        long result2 = sol.maxPower(stations2, r2, k2);
        System.out.println("Example 2 Output: " + result2); // Expected: 6
    }
}