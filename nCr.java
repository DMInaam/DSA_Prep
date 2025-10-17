public class nCr {
    public static int comb(int n,int r){
        if(r>n) return 0;
        if(r == 0 || r==n) return 1;
        double res = 0;
        for(int i = 0;i<r;i++) res+=Math.log(n-i)-Math.log(i+1);
        return (int)Math.round(Math.exp(res));
    }
    public static void main(String[] args) {
        System.out.println(comb(10, 3));
    }
}
