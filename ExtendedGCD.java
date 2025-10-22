public class ExtendedGCD {
    public static int gcdExt(int a,int b,int[] x,int[] y){
        if(a==0){
            x[0]=0;
            y[0]=1;
            return b;
        }
        int[] x1 ={0},y1={0};
        int gcd = gcdExt(b%a, a, x1, y1);
        x[0]=y1[0]-(b/a)*x1[0];
        y[0]=x1[0];
        return gcd;
    }
    public static int gcd(int a,int b){
        int[] x={1},y={1};
        int g = gcdExt(a, b, x, y);
        System.out.println(x[0]+" "+y[0]);
        return g;
    }
    public static void main(String[] args) {
        System.out.println(gcd(30, 20));
    }
}
