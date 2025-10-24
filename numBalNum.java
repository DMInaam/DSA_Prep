/*
 * 2048. Next Greater Numerically Balanced Number
 * Date: 24-10-2025
 * 
 * Question: An integer x is numerically balanced if for every digit d in the number x, there are exactly d occurrences of that digit in x.Given an integer n, return the smallest numerically balanced number strictly greater than n.
 * Example: Input: n = 1000
Output: 1333
Explanation: 
1333 is numerically balanced since:
- The digit 1 occurs 1 time.
- The digit 3 occurs 3 times. 
It is also the smallest numerically balanced number strictly greater than 1000.
Note that 1022 cannot be the answer because 0 appeared more than 0 times.
 */



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class numBalNum {
    private static final List<Integer> balancedNumbers = new ArrayList<>();
    static {
        Set<Integer> nbnSet = new HashSet<>();
        findPartitions(1, new ArrayList<>(), nbnSet);
        balancedNumbers.addAll(nbnSet);
        Collections.sort(balancedNumbers);
    }
    private static void findPartitions(int startDigit, List<Integer> currentPartition, Set<Integer> nbnSet) {
        int totalLength = 0;
        for (int digit : currentPartition) {
            totalLength += digit;
        }
        if (totalLength > 7) {
            return;
        }
        if (!currentPartition.isEmpty()) {
            char[] digits = new char[totalLength];
            int index = 0;
            for (int digit : currentPartition) {
                for (int i = 0; i < digit; i++) {
                    digits[index++] = (char) (digit + '0');
                }
            }
            generatePermutations(digits, 0, nbnSet);
        }
        for (int i = startDigit; i <= 9; i++) {
            currentPartition.add(i);
            findPartitions(i + 1, currentPartition, nbnSet);
            currentPartition.remove(currentPartition.size() - 1);
        }
    }
    private static void generatePermutations(char[] digits, int startIndex, Set<Integer> nbnSet) {
        if (startIndex == digits.length - 1) {
            nbnSet.add(Integer.parseInt(new String(digits)));
            return;
        }
        Set<Character> usedInThisPosition = new HashSet<>();
        
        for (int i = startIndex; i < digits.length; i++) {
            if (usedInThisPosition.add(digits[i])) {
                swap(digits, startIndex, i);
                generatePermutations(digits, startIndex + 1, nbnSet);
                swap(digits, startIndex, i);
            }
        }
    }
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // public int nextBeautifulNumber(int n) {
    //     for (int num : balancedNumbers) {
    //         if (num > n) {
    //             return num;
    //         }
    //     }
    //     return -1;
    // }
    public static int nextBeautifulNumber(int n) {
    int low = 0;
    int high = balancedNumbers.size() - 1;
    int ans = -1; 
    while (low <= high) {
        int mid = low + (high - low) / 2;
        int midVal = balancedNumbers.get(mid);
        if (midVal > n) {
            ans = midVal;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return ans;
    }
    public static void main(String[] args) {
        // System.out.println(balancedNumbers);
        // System.out.println(balancedNumbers.size());
        System.out.println(nextBeautifulNumber(3096));

    }
}