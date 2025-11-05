import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class TopXSum {

    // 1. HELPER CLASS FOR {VALUE, COUNT} PAIRS
    // Defined as a static nested class
    static class Pair {
        long value;
        int count;

        Pair(long value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return value == pair.value && count == pair.count;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, count);
        }
    }

    // 2. MEMBER VARIABLES (STATE) FOR THE SLIDING WINDOW
    // These will be accessed by the main method and helpers.
    
    // Custom comparator for the problem's sorting rules
    Comparator<Pair> comparator = (a, b) -> {
        if (a.count != b.count) {
            return Integer.compare(b.count, a.count); // Frequency descending
        }
        return Long.compare(b.value, a.value); // Value descending
    };

    // Data structures to maintain state
    Map<Long, Integer> freqMap;
    TreeSet<Pair> topX;
    TreeSet<Pair> others;
    long currentXSum;
    long totalWindowSum;
    int x; // The 'x' from the problem

    // 3. MAIN PUBLIC METHOD
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new long[0];
        }

        // Initialize all member variables
        this.x = x;
        this.freqMap = new HashMap<>();
        this.topX = new TreeSet<>(comparator);
        this.others = new TreeSet<>(comparator);
        this.currentXSum = 0L;
        this.totalWindowSum = 0L;

        // Note: The sum can exceed Integer.MAX_VALUE. We must use long.
        long[] answer = new long[n - k + 1];

        // --- Step 1: Initialize the first window ---
        for (int i = 0; i < k; i++) {
            long val = nums[i];
            totalWindowSum += val;
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        }

        // Add all initial elements to the TreeSets
        for (Map.Entry<Long, Integer> entry : freqMap.entrySet()) {
            addElement(entry.getKey(), entry.getValue());
        }

        // --- Step 2: Store the first answer ---
        answer[0] = (freqMap.size() < x) ? totalWindowSum : currentXSum;

        // --- Step 3: Slide the window ---
        for (int i = 1; i <= n - k; i++) {
            long leftElement = nums[i - 1];
            long rightElement = nums[i + k - 1];

            // Update total sum
            totalWindowSum = totalWindowSum - leftElement + rightElement;

            // --- Remove leftElement ---
            int oldLeftCount = freqMap.get(leftElement);
            removeElement(leftElement, oldLeftCount); // Remove old pair
            int newLeftCount = oldLeftCount - 1;

            if (newLeftCount > 0) {
                freqMap.put(leftElement, newLeftCount);
                addElement(leftElement, newLeftCount); // Add new pair
            } else {
                freqMap.remove(leftElement); // Fully remove
            }

            // --- Add rightElement ---
            int oldRightCount = freqMap.getOrDefault(rightElement, 0);
            if (oldRightCount > 0) {
                removeElement(rightElement, oldRightCount); // Remove old pair
            }
            int newRightCount = oldRightCount + 1;
            freqMap.put(rightElement, newRightCount);
            addElement(rightElement, newRightCount); // Add new pair

            // --- Step 4: Store the answer for the current window ---
            answer[i] = (freqMap.size() < x) ? totalWindowSum : currentXSum;
        }

        return answer;
    }

    // 4. PRIVATE HELPER METHODS

    /**
     * Adds a {value, count} pair to the sets and rebalances.
     */
    private void addElement(long value, int count) {
        Pair p = new Pair(value, count);
        // Add to topX if there's space, otherwise to others.
        // rebalance() will fix the placement.
        if (topX.size() < x) {
             topX.add(p);
             currentXSum += p.value * p.count;
        } else {
            others.add(p);
        }
        rebalance();
    }

    /**
     * Removes a {value, count} pair from the sets and rebalances.
     */
    private void removeElement(long value, int count) {
        Pair p = new Pair(value, count);
        if (topX.remove(p)) {
            // It was in the topX set
            currentXSum -= p.value * p.count;
        } else {
            // It was in the others set
            others.remove(p);
        }
        rebalance();
    }

    /**
     * Ensures topX has the correct x elements after any change.
     */
    private void rebalance() {
        // Case 1: topX has too many elements
        while (topX.size() > x) {
            Pair worstOfTop = topX.pollLast(); // Get the "worst" element
            currentXSum -= worstOfTop.value * worstOfTop.count;
            others.add(worstOfTop);
        }

        // Case 2: topX has space, and others has elements to fill it
        while (topX.size() < x && !others.isEmpty()) {
            Pair bestOfOthers = others.pollFirst(); // Get the "best" element
            currentXSum += bestOfOthers.value * bestOfOthers.count;
            topX.add(bestOfOthers);
        }

        // Case 3: Both sets are full. Check if a swap is needed.
        if (topX.size() == x && !others.isEmpty()) {
            // compare(a, b) > 0 means 'a' is "worse" than 'b'
            if (comparator.compare(topX.last(), others.first()) > 0) {
                // The worst of topX is "worse" than the best of others
                Pair worstOfTop = topX.pollLast();
                Pair bestOfOthers = others.pollFirst();

                // Update the sum
                currentXSum -= worstOfTop.value * worstOfTop.count;
                currentXSum += bestOfOthers.value * bestOfOthers.count;

                // Perform the swap
                topX.add(bestOfOthers);
                others.add(worstOfTop);
            }
        }
    }
}