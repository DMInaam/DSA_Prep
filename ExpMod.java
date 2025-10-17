public class ExpMod {
    public int powMod(int x, int n, int M) {
        long res = 1;
        long b = x;
        while (n >= 1) {
            if ((n & 1) == 1) 
                res = ((res%M) * (b%M))%M;
            b = ((b%M) *(b%M)) % M;
            n >>=1;
            }
        return (int)res;
    }

    public static void main(String[] args) {
        int x = 105626009, n = 29250848, M = 36840944;
        ExpMod obj = new ExpMod();
        System.out.println(obj.powMod(x, n, M));
    }
}
