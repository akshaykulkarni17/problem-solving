import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class dp {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        System.out.println(LengthOfLongestFibonacciSubsequence(a));
    }
    public int UniqueBinarySearchTreesII(int A) {
        int[] dp = new int[A+1];
        dp[0]=1;
        dp[1]=1;
        for (int level = 2; level <=A ; level++) {
            for (int root = 1; root <=level ; root++) {
                dp[level] += dp[level-root] * dp[root-1];
            }
        }
        return dp[A];
    }




    public static int LengthOfLongestFibonacciSubsequence(ArrayList<Integer> A) {
        TreeSet<Integer> ts = new TreeSet<>(A);
        int maxLength=0, x,y;
        for (int i = 0; i <A.size() ; i++) {
            for (int j = i+1; j <A.size() ; j++) {
                x=A.get(i)+A.get(j);
                y=A.get(j);
                int length = 3;
                while (ts.contains(x)&&ts.last()!=x){
                    int z=x+y;
                    y=x;
                    x=z;
                    maxLength=Math.max(maxLength,length+1);
                }
            }
        }
        return maxLength;
     }
}
