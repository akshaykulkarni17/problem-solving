import java.util.*;

public class oneDimensionDP {
    public static void main(String[] args) {

    }

    public int lengthOfLIS(int[] nums){
        List<Integer> piles = new ArrayList<>(nums.length);
        for (int num : nums) {
            int pile = Collections.binarySearch(piles, num);
            if (pile < 0) pile = ~pile;
            if (pile == piles.size()) {
                piles.add(num);
            } else {
                piles.set(pile, num);
            }
        }
        return piles.size();
    }

    public int longestIncreasingSubsequence(final List<Integer> A) {
        int[] lis = new int[A.size()+1];
        lis[0]=1;
        for (int i = 1; i <A.size() ; i++) {
            for (int j = i+1; j < A.size(); j++) {
                if (A.get(j)>A.get(i)){
                    lis[i]=lis[i-1]+1;
                }
            }
        }
        return Arrays.stream(lis).max().getAsInt();
    }

    public int LongestBalancedSubstring(final String a) {
        char[] str = a.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (str[i]=='{') str[i]='(';
            if (str[i]=='[') str[i]='(';
            if (str[i]=='}') str[i]=')';
            if (str[i]==']') str[i]=')';
        }

        Stack<Integer> stk = new Stack<>();
        stk.push(-1);
        int result=0;
        for (int i = 0; i < str.length; i++) {
            if (str[i]=='(') stk.push(i);
            else{
                if (!stk.isEmpty()) stk.pop();
                if (!stk.isEmpty()) result=Math.max(result,i-stk.peek());
                else stk.push(i);
            }
        }
        return result;
    }

    public int MaximumSum(ArrayList<Integer> A, int B, int C, int D) {
        int n= A.size();
        int[] left = new int[n];
        left[0]=(B*A.get(0));
        for(int i=1;i<n;i++){
            left[i]=Math.max(left[i-1],B*A.get(i));
        }
        int[] right = new int[n];
        right[n-1]=(D*A.get(n-1));
        for(int i=n-2;i>=0;i--){
            right[i]=Math.max(right[i-1],D*A.get(i));
        }
        int ans=0;
        for(int i=0;i<n;i++){
            ans=Math.max(ans, left[i]+C*A.get(i)+right[i]);
        }
        return ans;
    }


    public int maxProductSubArray(final List<Integer> A) {
        int product=1;
        int maxProduct=Integer.MIN_VALUE;
        for(int x:A){
            product=product*x;
            maxProduct=Math.max(maxProduct,product);
            if(product==0) product=1;
        }
        return maxProduct;
    }

    public int waysToDecode(String A) {
        int[] count = new int[A.length()];
        char[] ch = A.toCharArray();
        count[0]=1;
        count[1]=1;
        if(ch[0]=='0') return 0;
        for(int i=2;i<=ch.length;i++){
            if(ch[i-1]>'0') count[i]=count[i-1];
            if(ch[i-2]=='1' || (ch[i-2]=='2'&&ch[i-1]<'7')) count[i]+=count[i-2];
        }
        return count[ch.length];
    }

    public int climbStairs(int A) {
        int[] dp = new int[A+2];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=A;i++){
            dp[i]=dp[i-2]+dp[i-1];
        }
        return dp[A];
    }

    public int MaxSumWithoutAdjacentElements(ArrayList<ArrayList<Integer>> A) {
        int inclusive = Math.max(A.get(0).get(0),A.get(1).get(0));
        int exclusive=0;
        for(int i=1;i<A.get(0).size();i++){
            int temp=Math.max(inclusive,exclusive);
            inclusive=exclusive + Math.max(A.get(0).get(i),A.get(1).get(i));
            exclusive=temp;
        }
        return Math.max(inclusive,exclusive);
    }

    public int countMinSquares(int A) {
        int[] squares = new int[A+2];
        for(int i=1;i<=A;i++){
            squares[i]=i;
            for(int j=1;j*j<=i;j++){
                squares[i]=Math.min(squares[i],squares[i-j*j]+1);
            }
        }
        return squares[A];
    }

    public int letsParty(int A) {
        int[] party = new int[A+2];
        party[1]= 1;
        party[2]=2;
        for(int i=3;i<=A;i++){
            party[i]=party[i-1]+(i-1)*party[i-2];
            party[i]%=10003;
        }
        return party[A];
    }

    static int fibonacci(int A){
        int[] fib= new int[A+2];
        fib[0]=0;
        fib[1]=1;
        for(int i=2;i<=A;i++){
            fib[i]=fib[i-1]+fib[i-2];
        }
        return fib[A];
    }
}
