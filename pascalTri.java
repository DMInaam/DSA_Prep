import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class pascalTri {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> arr = new ArrayList<>();
        if(numRows<=0) return arr;
        arr.add(new ArrayList<Integer>(Arrays.asList(1)));
        for(int n = 1;n<numRows;n++){
            List<Integer> row = new ArrayList<Integer>();
            List<Integer> prow = arr.get(n-1);
            row.add(1);
            for(int i=1;i<n;i++){
                int sum = prow.get(i-1) + prow.get(i);
                row.add(sum);
            }
            row.add(1);
            arr.add(row);
        }
        return arr;
    }
}