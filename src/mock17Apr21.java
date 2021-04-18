import java.util.*;

public class mock17Apr21 {

    public static void main(String[] args) {
    List<Integer> list = new ArrayList<>(Arrays.asList(-2, -40, 0, -2, -3));
    System.out.println(maxProduct(list));
    }
    public static int maxSum(final List<Integer> A){
        int maxSum=Integer.MIN_VALUE;
        int sum=0;
        for (int x: A) {
            sum+=x;
            maxSum=Math.max(sum,maxSum);
            sum=Math.max(sum, 0);
        }
        return maxSum;
    }
    public static int maxProduct(final List<Integer> A) {
        int maxProduct=Integer.MIN_VALUE;
        int product=1;
        for (int x: A) {
                product*=x;
                maxProduct=Math.max(maxProduct,product);
                product=product==0?1:product;
        }
        return maxProduct;
    }
}
