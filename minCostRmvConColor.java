public class minCostRmvConColor {
    public static int minCost(String colors, int[] neededTime) {
        int time = 0;
        int sum = neededTime[0];
        int mx = neededTime[0];
        char prev = colors.charAt(0);
        int n = neededTime.length;
        for(int i=1;i<n;i++){
            char c = colors.charAt(i);
            if(c == prev){
                int t = neededTime[i];
                sum += t;
                mx = Math.max(t,mx);
            }
            if(c!=prev){
                time += sum-mx;
                sum = neededTime[i];
                mx = neededTime[i];
                prev = c;
            }
            if(i == n-1) time+= sum - mx;
        }
        return time;
    }
    public static void main(String[] args) {
        String c = "aaabbbabbbb";
        int[] time = {3,5,10,7,5,3,5,5,4,8,1};
        System.out.println(minCost(c, time));
    }
}
