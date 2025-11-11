public class OnesZeros {
    public static int[] countZO(String s){
        int[] arr = new int[2];
        for(char c:s.toCharArray()){
            if(c == '0') arr[0]++;
            else arr[1]++;
        }
        return arr;
    }
    public static int findMaxForm(String[] strs, int m, int n) {
       int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] counts = countZO(s);
            int zeros = counts[0];
            int ones = counts[1];
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j],1 + dp[i - zeros][j - ones]);
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        String[] s = {"01010", "0", "1", "00", "11","01"};
        System.out.println(findMaxForm(s, 4, 4));
    }
}
