import java.util.ArrayDeque;
import java.util.ArrayList;
public class maxOfSlide {
    public static ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        // code here
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> win = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            if(!win.isEmpty() && win.peekFirst() == i-k)
                win.pollFirst();
            while(!win.isEmpty() && arr[win.peekLast()] <= arr[i])
                win.pollLast();
            win.offerLast(i);
            if(i>=k-1)
                result.add(arr[win.peekFirst()]);
        }
        return result;
    }  
    public static void main(String[] args) {
        int[] arr = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        ArrayList<Integer> res = maxOfSubarrays(arr, k);
        for(int num: res)
            System.out.print(num+" ");
    }  
}
