import java.util.ArrayList;
import java.util.Scanner;

public class FloorCeil {
    public static int floor(int a,int b){
        int q=a/b;
        if((a^b)<0 && (a%b)!=0) q--;
        return q;
    }
    public static int ceil(int a,int b){
        int q=a/b;
        if((a^b)>0 && (a%b)!=0) q++;
        return q;
    }
    public static ArrayList<Integer> divFloorCeil(int a,int b){
         ArrayList<Integer> ar = new ArrayList<>();
         ar.add(floor(a, b));
         ar.add(ceil(a, b));
         return ar;
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(divFloorCeil(a, b));
        }
    }
}
