public class sieve {
    public static int[] seiveOF(int n){
        boolean[] prime = new boolean[n+1];
        for(int i = 0;i<=n;i++) prime[i]=true;
        for(int i = 2;i<=Math.sqrt(n);i++){
            for(int j = i*i;j<=n;j+=i){prime[j]=false;}
        } 
        int count = 0;
        for(int i = 2;i<=n;i++){
            if(prime[i]==true) count++;
        }
        int[] ar = new int[count];
        int in=0;
        for(int i=2;i<=n;i++){
            if (prime[i]) {
                ar[in++]=i;
            }
        }
        return ar;
    }
    public static void main(String[] args) {
        int[] ar=seiveOF(9);
        for (int i : ar) {
            System.out.println(i);
        }
        
    }
}
