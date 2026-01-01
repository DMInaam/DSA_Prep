class bestTime {
    public static long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long baseProfit = 0;
        for (int i = 0; i < n; i++) {
            baseProfit += (long) prices[i] * strategy[i];
        }

        int half = k / 2;
        long currentGain = 0;
        for (int i = 0; i < k; i++) {
            int modifiedAction = (i < half) ? 0 : 1;
            currentGain += (long) (modifiedAction - strategy[i]) * prices[i];
        }
        
        long maxGain = Math.max(0, currentGain);

        for (int i = 1; i <= n - k; i++) {
            currentGain -= (long) (0 - strategy[i - 1]) * prices[i - 1];
            int midIdx = i + half - 1;
            currentGain -= (long) (1 - strategy[midIdx]) * prices[midIdx]; // Remove 'Sell' gain
            currentGain += (long) (0 - strategy[midIdx]) * prices[midIdx]; // Add 'Hold' gain

            
            int rightIdx = i + k - 1;
            currentGain += (long) (1 - strategy[rightIdx]) * prices[rightIdx];

            maxGain = Math.max(maxGain, currentGain);
        }

        return baseProfit + maxGain;
    }
    public static void main(String[] args) {
        System.err.println(maxProfit(new int[]{4,7,13},new int[]{-1,-1,0},2));
    }
}