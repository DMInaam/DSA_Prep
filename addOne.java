class addOne {
    public static int[] plusOne(int[] digits) {
        int n = digits.length - 1;
        int carry = 0;
        int i = n;
        while(i>=0){
            if(digits[i] == 9){
                carry++;
            }
            else{
                break;
            }
            i--;
        }
        if(carry == n+1){
            int[] arr = new int[n+2];
            arr[0] = 1;
            for(int j=1;j<=n;j++) arr[j] = 0;
            return arr;
        }
        digits[i]++;
        i++;
        while(i<=n){
           digits[i++] = 0; 
        }
        return digits;
    }
    public static void main(String[] args) {
        int[] digits = {9};
        int[] result = plusOne(digits);
        for(int i=0;i<result.length;i++) System.out.print(result[i]+" ");
    }
}