import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class twoDimensionDP {
    public static void main(String[] args) {
        //System.out.println(DistinctSubsequences("banana","ban"));
        //System.out.println(flipArray(new ArrayList<>(Arrays.asList( 11, 10, 8, 6, 8, 11, 1, 10, 2, 3, 8, 3, 8, 12, 11, 1, 7, 5, 5, 12, 9, 4, 10, 3, 3, 3, 8, 8, 8, 6, 7, 7, 7, 6, 4, 2, 5, 8, 11, 10, 10, 10, 12, 9, 2, 3, 9, 12, 7, 6, 11, 8, 9, 9, 10, 3, 3, 5, 2, 10, 10, 9, 4, 9, 6, 11, 10, 2, 6, 1, 4, 7, 10, 3, 4, 3, 9, 4, 3, 8, 1, 1, 3 ))));
        //System.out.println(MatrixChainMultiplication(new ArrayList<>(Arrays.asList( 45, 17, 34, 27, 12, 22 ))));
        //System.out.println(BurstBalloons(new ArrayList<>(Arrays.asList(1,5,10,1))));
        System.out.println(EqualAveragePartition(new ArrayList<>(Arrays.asList(19, 5, 38, 22, 44, 12, 17, 35 ))));
    }

    public static int DistinctSubsequences(String A, String B) {
        int[][] dp = new int[B.length()+1][A.length()+1];
        for (int i = 0; i <=B.length() ; i++) {
            for (int j = 0; j <=A.length() ; j++) {
                if (i!=0&&j==0) dp[i][j]=0;
                else if (i==0) dp[i][j]=1;
                else if (B.charAt(i-1)!=A.charAt(j-1)){
                    dp[i][j]=dp[i][j-1];
                }
                else {
                    dp[i][j]=dp[i-1][j-1] + dp[i][j-1];
                }
            }
        }
        return dp[B.length()][A.length()];
    }


    public int RegularExpressionMatch(final String A, final String B) {
        boolean[][] dp = new boolean[A.length()+1][B.length()+1];
        for (int i = 0; i <= A.length(); i++) {
            for (int j = 0; j <= B.length() ; j++) {
                if (i==0&&j==0){
                    dp[i][j]=true;
                }
                else if (i==0 || j==0){
                    dp[i][j]=false;
                }
                else if (A.charAt(i-1)==B.charAt(j-1) || B.charAt(j)=='?'){
                    dp[i][j]=dp[i-1][j-1];
                }
                else if (B.charAt(j-1)=='*'){
                    dp[i][j]=dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        if (dp[A.length()][B.length()]) return 1;
        return 0;
    }




    public int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> A) {
        int[][] dp = new int[A.size()+1][A.get(0).size()+1];
        for(int i=1;i<=A.size();i++){
            for(int j=1;j<=A.get(0).size();j++){
                if(!A.get(i-1).get(j-1).equals(1)){
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[A.size()][A.get(0).size()];
    }

    public int dungeonPrincess(ArrayList<ArrayList<Integer>> A) {
        int[][] dp = new int[A.size()][A.get(0).size()];
        dp[0][0] = A.get(0).get(0);
        for (int i = 1; i < A.size(); i++) {
            dp[i][0] = dp[i-1][0] + A.get(i).get(0);
        }
        for (int i = 1; i <A.get(0).size() ; i++) {
            dp[0][i] = dp[0][i-1] + A.get(0).get(i);
        }
        for (int i = 1; i <A.size(); i++) {
            for (int j = 1; j <A.get(0).size() ; j++) {
                int temp = Math.abs(dp[i-1][j])<Math.abs(dp[i][j-1]) ? dp[i-1][j] : dp[i][j-1];
                dp[i][j]=temp+A.get(i).get(j);
            }
        }
        return dp[A.size()-1][A.get(0).size()-1];
    }

    public int minPathSum(ArrayList<ArrayList<Integer>> A) {
        int[][] dp = new int[A.size()][A.get(0).size()];
        dp[0][0] = A.get(0).get(0);
        for (int i = 1; i < A.size(); i++) {
            dp[i][0] = dp[i-1][0] + A.get(i).get(0);
        }
        for (int i = 1; i <A.get(0).size() ; i++) {
            dp[0][i] = dp[0][i-1] + A.get(0).get(i);
        }
        for (int i = 1; i <A.size(); i++) {
            for (int j = 1; j <A.get(0).size() ; j++) {
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+A.get(i).get(j);
            }
        }
        return dp[A.size()-1][A.get(0).size()-1];
    }


    public int longestPalindromicSubsequence(String A) {
        int[][] dp = new int[A.length()+1][A.length()+1];
        String B= new StringBuilder(A).reverse().toString();
        for (int i = 1; i <=A.length() ; i++) {
            for (int j = 1; j <=A.length() ; j++) {
                if (A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[A.length()][A.length()];
    }

    public int repeatingSubSequence(String A) {
        int[][] dp = new int[A.length()+1][A.length()+1];
        for (int i = 1; i <=A.length() ; i++) {
            for (int j = 1; j <=A.length() ; j++) {
                if (A.charAt(i)==A.charAt(j)&&i!=j){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        if (dp[A.length()][A.length()]>1) return 1;
        return 0;
    }

    public static String abbreviation(String a, String b) {
        // Write your code here
        boolean[][] dp=new boolean[a.length()+1][b.length()+1];
        dp[0][0]=true;
        for(int i=1;i<=a.length();i++){
            if (Character.isLowerCase(a.charAt(i-1))){
                dp[i][0]=true;
            }
        }
        for (int i = 1; i <=a.length() ; i++) {
            for (int j = 1; j <=b.length() ; j++) {
                if (a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
                else if (Character.toUpperCase(a.charAt(i-1))==b.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1] || dp[i-1][j];
                }
                else if (Character.isLowerCase(a.charAt(i-1))){
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[a.length()][b.length()]?"YES":"NO";
    }

    int longestPalindromicSubstring(String A){
        int N = A.length();
        StringBuilder sb = new StringBuilder(A).reverse();
        int dp[][] = new int[N+1][N+1];
        for (int i = 0; i <=N ; i++) {
            for (int j = 0; j <=N ; j++) {
                if (i==0&&j==0) dp[i][j]=0;
                else if (i==0||j==0) dp[i][j]=1;
                else if (A.charAt(i-1)==sb.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else dp[i][j]=0;
            }
        }
        return dp[N][N];
    }


    public int RectangleSum(int[][] A) {
        int N=A.length;
        int M=A[0].length;
        int[][] prefix = new int[N][M];
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j < M; j++) {
                if (j==0) prefix[i][0]=A[i][0];
                else{
                    prefix[i][j]=prefix[i][j-1]+A[i][j];
                }
            }
        }
        int maxSum=Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            for (int j = i; j <M ; j++) {
                ArrayList<Integer> sum = new ArrayList<>();
                for (int k = 0; k < N; k++) {
                    int exclude =0;
                    if(i==0) exclude = prefix[k][j];
                    else exclude = prefix[k][j]-prefix[k][i-1];
                    sum.add(exclude);
                }
                maxSum=Math.max(maxSum,maxKadaneSum(sum));
            }
        }
        return maxSum;
    }

    private int maxKadaneSum(ArrayList<Integer> sum) {
        char[] ch = {'w','r','t'};;
        int maxSum=Integer.MIN_VALUE;
        int s=0;
        for (int x : sum){
            s+=x;
            maxSum=Math.max(maxSum,s);
            if(s<0)s=0;
        }
        return maxSum;
    }

    static int flipSum;
    static int flips;
    static int sumG;
    public static int flipArray(final List<Integer> A) {
        sumG =0;
        for(int x:A){
            sumG+=x;
        }
        sumG=sumG/2;
        flips=Integer.MAX_VALUE;
        flipSum=Integer.MIN_VALUE;
        findFlips(A,0,0,new ArrayList<>());
        return flips;
    }

    private static void findFlips(List<Integer> a,int start, int sum,ArrayList<Integer> tmp) {
        for (int i = start; i < a.size(); i++) {
            if(a.get(i)+sum<=sumG){
                tmp.add(a.get(i));
                sum+=a.get(i);
                findFlips(a,i+1,sum,tmp);
                sum-=a.get(i);
                tmp.remove(tmp.size()-1);
            }
        }
        if(flipSum<sum) {
            flipSum=sum;
            flips=tmp.size();
        }
        else if (flipSum==sum){
            flips=Math.min(flips,tmp.size());
        }
    }


    public static int MatrixChainMultiplication(ArrayList<Integer> A) {
        int sum=0;
        while(A.size()>=3){
            int ijk = getIJK(A);
            sum+=A.get(ijk)*A.get(ijk+1)*A.get(ijk+2);
            A.remove(ijk+1);
        }
        return sum;
    }

    private static int getIJK(ArrayList<Integer> a) {
        int i=0;
        int j=2;
        int sum=Integer.MIN_VALUE;
        int start=-1;
        while(j<a.size()){
            if(sum<a.get(i)*a.get(i+1)*a.get(j)){
                sum=a.get(i)*a.get(i+1)*a.get(j);
                start=i;
            }
            i++;
            j++;
        }
        return start;
    }

    public static int BurstBalloons(ArrayList<Integer> arr) {
        int money=0;
        ArrayList<Integer> A = new ArrayList<>();
        A.add(1);
        A.addAll(arr);
        A.add(1);
        while (A.size()>=3){
            int min=Integer.MAX_VALUE;
            int index=-1;
            for (int i = 1; i < A.size()-1; i++) {
                if (min>A.get(i)){
                    min=A.get(i);
                    index=i;
                }
                else if(min==A.get(i)){
                    if(A.get(index-1)*A.get(index+1)<A.get(i-1)*A.get(i+1)){
                        index=i;
                    }
                }
            }
            money+=A.get(index-1)*A.get(index)*A.get(index+1);
            A.remove(index);
        }
        return money;
    }

    static ArrayList<Integer> eap;
    static double avg;
    public static ArrayList<ArrayList<Integer>> EqualAveragePartition(ArrayList<Integer> A) {
        int sum=0;
        for (int x: A){
            sum+=x;
        }
        Collections.sort(A);
        eap=new ArrayList<>();
        avg=(double) sum/A.size();
        equalAvgPart(A,0,0,new ArrayList<>());
        int i=0,j=0;
        while (i<eap.size()&&j<A.size()){
            if(eap.get(i).equals(A.get(j))){
                A.remove(j);
                i++;
            }
            else j++;
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ans.add(eap);
        ans.add(A);
        return ans;
    }

    private static void equalAvgPart(ArrayList<Integer> a, int start, int sum, ArrayList<Integer> list) {
        if (!list.isEmpty()&&(double) sum/list.size()==avg){
            if(eap.size()==0) eap=new ArrayList<>(list);
            else if (eap.size()>list.size()) eap=new ArrayList<>(list);
        }
        for (int i = start; i <a.size() ; i++) {
            sum+=a.get(i);
            list.add(a.get(i));
            equalAvgPart(a,i+1,sum,list);
            sum-=a.get(i);
            list.remove(list.size()-1);
        }
    }


}
