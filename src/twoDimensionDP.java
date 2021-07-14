import java.util.ArrayList;

public class twoDimensionDP {

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
}
