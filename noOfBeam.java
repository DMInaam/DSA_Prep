

public class noOfBeam {
    public static int noOf1s(String s){
        // int i = Collections.frequency(Arrays.asList(s.split("")),String.valueOf('1'));
        int i = (int) s.chars().filter(ch -> ch == '1').count();
        // System.out.println(i);
        return i;
    }
     public static int numberOfBeams(String[] bank) {
        int total = 0;
        int prev = 0;
        for(String s:bank){
            int current = noOf1s(s);
            // for(char c: s.toCharArray()){
            //     if(c == '1') current++;
            // }
            if(current>=1){
                total += prev*current;
                prev = current;
            }
        }
        return total;
    }
    // public static int numberOfBeams(String[] bank) {
    //     List<Integer> l=new ArrayList<Integer>();
    //     int beams = 0;
    //     for (String b:bank){ 
    //         int n = noOf1s(b);
    //         if(n>=1) 
    //             l.add(n);
    //     }
    //     if(l.size()>1){
    //         for(int i = 1;i<l.size();i++){
    //             int k = l.get(i)*l.get(i-1);
    //             beams+=k;
    //             System.out.println("k: "+k+"beams: "+beams);
    //         }
    //     }
    //     return beams;
    // }
    public static void main(String[] args) {
        String[] b = {"011001","000000","010100","001000"};
        System.out.println(numberOfBeams(b));
    }
}

