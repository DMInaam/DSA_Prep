import java.util.Arrays;
import java.util.Collections;
public class apples {
    public static int minimumBoxes(int[] apple, int[] capacity) {
        int sum = 0;
        int m = capacity.length;
        for(int a : apple) sum += a;
        Integer[] cap = new Integer[m];
        for(int i = 0;i<m;i++) cap[i] = capacity[i];
        Arrays.sort(cap, Collections.reverseOrder());
        int i = 0;
        while(sum>0){
            sum -= cap[i++];            
        }
        return i;
    }
    public static void main(String[] args) {
        int[] apple = {1,8,3,3,5};
        int[] capacity = {3,9,5,1,9};
        System.out.println(minimumBoxes(apple, capacity));
    }
}