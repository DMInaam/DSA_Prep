import java.util.ArrayList;

class DigitsSum2 {
    private int[][] smallC = new int[5][5];

    private void precomputeSmallC() {
        for (int i = 0; i < 5; i++) {
            smallC[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                smallC[i][j] = (smallC[i - 1][j - 1] + smallC[i - 1][j]);
            }
        }
    }

    private long getCMod5(ArrayList<Integer> n_digits, ArrayList<Integer> k_digits) {
        long res = 1;
        for (int i = 0; i < n_digits.size(); i++) {
            int ni = n_digits.get(i);
            int ki = (i < k_digits.size()) ? k_digits.get(i) : 0;
            if (ki > ni) return 0;
            res = (res * smallC[ni][ki]);
        }
        return res % 5;
    }
    
    private ArrayList<Integer> toBase(int n, int base) {
        if (n == 0) {
            ArrayList<Integer> zero = new ArrayList<>();
            zero.add(0);
            return zero;
        }
        ArrayList<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % base);
            n /= base;
        }
        return digits;
    }

    public boolean hasSameDigits(String s) {
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
        }

        int m = n - 2;
        long sum0_mod2 = 0;
        long sum1_mod2 = 0;
        for (int j = 0; j <= m; j++) {
            if ((m & j) == j) { 
                sum0_mod2 = (sum0_mod2 + a[j]);
                sum1_mod2 = (sum1_mod2 + a[j + 1]);
            }
        }

        if (sum0_mod2 % 2 != sum1_mod2 % 2) {
            return false;
        }

        precomputeSmallC();
        long sum0_mod5 = 0;
        long sum1_mod5 = 0;

        ArrayList<Integer> m_digits = toBase(m, 5);
        ArrayList<Integer> j_digits = new ArrayList<>();
        j_digits.add(0);

        for (int j = 0; j <= m; j++) {
            long c_mod_5 = getCMod5(m_digits, j_digits);

            sum0_mod5 = (sum0_mod5 + c_mod_5 * a[j]);
            sum1_mod5 = (sum1_mod5 + c_mod_5 * a[j + 1]);
            
            int carry = 1;
            for (int i = 0; i < j_digits.size() && carry > 0; i++) {
                int val = j_digits.get(i) + carry;
                j_digits.set(i, val % 5);
                carry = val / 5;
            }
            if (carry > 0) {
                j_digits.add(carry);
            }
        }

        return (sum0_mod5 % 5 + 5) % 5 == (sum1_mod5 % 5 + 5) % 5;
    }
}