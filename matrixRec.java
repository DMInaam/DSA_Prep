import java.util.Stack;
public class matrixRec {
    private static int longRect(int[] height){
        Stack<Integer> s = new Stack<>();
        int n=height.length;
        int max =0;
        for(int h: height) System.out.print(h+" ");
        System.out.println();
        for(int i=0;i<=n;i++){
            System.out.println("i: "+i);
            int curHeight = (i==n)?0:height[i];
            System.out.println("stack content: "+s);
            while(!s.isEmpty() && height[s.peek()]>=curHeight){
                System.out.println("stack top index height: "+height[s.peek()]);
                int h = height[s.pop()];
                int w = s.isEmpty()?i:i-s.peek()-1;
                System.out.println("h: "+h+" w: "+w);
                max= Math.max(max,h*w);
            }
            s.push(i);
        }
        return max;
    }
    public static int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] height = new int[m];
        int maxArea = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]=='1') height[j] += 1;
                else height[j] = 0;
            }
            maxArea = Math.max(maxArea,longRect(height));
        }
        return maxArea;
    }
    public static void main(String[] args) {
        char[][] matrix = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println(maximalRectangle(matrix)); 
    }
}