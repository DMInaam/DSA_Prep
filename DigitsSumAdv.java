class DigitsSumAdv{
    public static final int[][] PTMod_5 = {
            {1,0,0,0,0},
            {1,1,0,0,0},
            {1,2,1,0,0},
            {1,3,3,1,0},
            {1,4,1,4,1} //{1,4,6,4,1} % 5
        };
    public static final int[][] CRTMod_2 = {
            {0,6,2,8,4}, // remainder 0 for 0-10 arranged in for mod 5 => [0,1,2,3,4]
            {5,1,7,3,9} //emainder 1 for 0-10 arranged in for mod 5 => [0,1,2,3,4] 
        };
    public static int MOD5(int n,int k){
            if(k<0||k>n) return 0;
            long res = 1;
            while(n>0||k>0){
                int n_i = n%5;
                int k_i = k%5;
                res = (res* PTMod_5[n_i][k_i])%5;
                n/=5;
                k/=5;
            }
            return (int)res;  
        }
    public static int MOD2(int n,int k){
            if(k<0 || k>n) return 0;
            return ((n&k)==k)?1:0;
        }
    public boolean hasSameDigits(String s) {
        if(s.length()<2) return false;
        if(s.length()==2) return s.charAt(0) == s.charAt(1);

        int n = s.length();
        int[] digits = new int[n];
        for(int i = 0;i<n;i++) digits[i] = s.charAt(i)-'0';

        int N = n-2;
        long sum1 = 0;
        long sum2 = 0;
        for(int i=0;i<=N;i++){
            int m5 = MOD5(N,i);
            int m2 = MOD2(N,i);
            int coef = CRTMod_2[m2][m5];
            sum1= (sum1 + (long)coef*digits[i])%10;
            sum2= (sum2 + (long)coef*digits[i+1])%10;
        }
        return sum1 == sum2;
    }
}