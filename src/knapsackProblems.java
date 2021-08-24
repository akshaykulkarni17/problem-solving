import java.net.http.HttpRequest;
import java.util.*;

public class knapsackProblems {

    public static void main(String[] args) {
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(60,100,120));
        ArrayList<Integer> weights = new ArrayList<>(Arrays.asList(10,20,30));
        //System.out.println(maxWeight(values,weights,50));
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,1,2,3));
        //System.out.println(subsetSum(list,28));
        int[] arr = {1,2,3};
        //System.out.println(equalPartition(list));
        //System.out.println(countSubsetsTargetSum(list,10));
        //System.out.println(minSubsetSumDiff(list));
        //System.out.println(countSubsetSumDiff(list,1));
        System.out.println(longestCommonSubsequences("abbcdeef","bcd"));
        //System.out.println(longestCommonSubstring("abbcdeef","bcd"));
        System.out.println(printLongestCommonSubsequences("abbcdeef","bcd"));
        //System.out.println(shortestSuperSequence("abbcdeef","bcde"));
        //System.out.println(permute(arr));
    }
    public static String shortestSuperSequence(String a, String b){
        {
            int aLength=a.length(), bLength=b.length();
            int[][] ans = new int[aLength+1][bLength+1];
            for (int i = 0; i <=  aLength ; i++) {
                for (int j = 0; j <= bLength ; j++) {
                    if (i==0||j==0){
                        ans[i][j]=0;
                    }
                    else if (a.charAt(i-1)==b.charAt(j-1)){
                        ans[i][j] = 1+ans[i-1][j-1];
                    }
                    else {
                        ans[i][j]=Math.max(ans[i-1][j],ans[i][j-1]);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            int i=aLength,j=bLength;
            while (i>0&&j>0){
                if (a.charAt(i-1)==b.charAt(j-1)){
                    sb.append(a.charAt(i-1));
                    i--;j--;
                }
                else if(ans[i-1][j]>ans[i][j-1]){
                    sb.append(b.charAt(j-1));
                    sb.append(a.charAt(i-1));
                    i--;
                }
                else if (ans[i-1][j]<ans[i][j-1]){
                    sb.append(a.charAt(i-1));
                    sb.append(b.charAt(j-1));
                    j--;
                }

            }
            while(i>0){
                sb.append(a.charAt(i-1));
                i--;
            }
            while(j>0){
                sb.append(b.charAt(j-1));
                j--;
            }
            return sb.reverse().toString();
        }
    }
    public static int longestCommonSubstring(String a, String b){
        int aLen=a.length(),  bLen=b.length();
        int[][] ans = new int[aLen+1][bLen+1];
        for (int i = 0; i <=aLen ; i++) {
            for (int j = 0; j <=bLen ; j++) {
                if (i==0||j==0){
                    ans[i][j] =0;
                }
                else if (a.charAt(i-1)==a.charAt(j-1)){
                    ans[i][j]=1+ans[i-1][j-1];
                }
                else {
                    ans[i][j]=0;
                }
            }
        }
        int max=0;
        for (int i = 0; i <= aLen; i++) {
            for (int j = 0; j <=bLen ; j++) {
                max=Math.max(max,ans[i][j]);
            }
        }
        return max;
    }
    public static String printLongestCommonSubsequences(String a, String b){
        int aLength=a.length(), bLength=b.length();
        int[][] ans = new int[aLength+1][bLength+1];
        for (int i = 0; i <=  aLength ; i++) {
            for (int j = 0; j <= bLength ; j++) {
                if (i==0||j==0){
                    ans[i][j]=0;
                }
                else if (a.charAt(i-1)==b.charAt(j-1)){
                    ans[i][j] = 1+ans[i-1][j-1];
                }
                else {
                    ans[i][j]=Math.max(ans[i-1][j],ans[i][j-1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i=aLength,j=bLength;
        while (i>0&&j>0){
            if (ans[i-1][j]==ans[i][j-1]){
                sb.append(a.charAt(i-1));
                i--;
                j--;
            }
            else if(ans[i-1][j]>ans[i][j-1]){
                sb.append(a.charAt(i-1));
                sb.append(b.charAt(j-1));
                i--;
            }
            else if (ans[i-1][j]<ans[i][j-1]){
                sb.append(a.charAt(i-1));
                sb.append(b.charAt(j-1));
                j--;
            }
        }
        return sb.reverse().toString();
    }
    public static int longestCommonSubsequences(String a, String b){
        //return longestCommonSubsequencesRecursive(a,b,a.length()-1,b.length()-1);
        return longestCommonSubsequencesDP(a,b,a.length(),b.length());
    }
    public static int longestCommonSubsequencesDP(String a, String b, int aLength, int bLength){
        int[][] ans = new int[aLength+1][bLength+1];
        for (int i = 0; i <=  aLength ; i++) {
            for (int j = 0; j <= bLength ; j++) {
                if (i==0||j==0){
                    ans[i][j]=0;
                }
                else if (a.charAt(i-1)==b.charAt(j-1)){
                    ans[i][j] = 1+ans[i-1][j-1];
                }
                else {
                    ans[i][j]=Math.max(ans[i-1][j],ans[i][j-1]);
                }
            }
        }
        return ans[aLength][bLength];
    }
    public static int longestCommonSubsequencesRecursive(String a, String b, int aLength, int bLength){
        if (aLength==0||bLength==0){
            return 0;
        }
        else if (a.charAt(aLength)==b.charAt(bLength)){
            return 1+ longestCommonSubsequencesRecursive(a,b,aLength-1,bLength-1);
        }
        else {
                return Math.max(longestCommonSubsequencesRecursive(a,b,aLength,bLength-1),longestCommonSubsequencesRecursive(a,b,aLength-1,bLength));
            }
        }

    public static int countSubsetSumDiff(ArrayList<Integer>list, int diff){
        int sum=0;
        for (int x:list){
            sum+=x;
        }
        int s1= (sum-diff)/2;
        return countSubsetsTargetSum(list,s1);
    }
    public static int minSubsetSumDiff(ArrayList<Integer>list){
        int sum=0;
        for (int x:list){
            sum+=x;
        }
        ArrayList<Integer> sumPossible = new ArrayList<>();
        for (int i = 0; i <=sum ; i++) {
            if(subsetSum(list,i))sumPossible.add(i);
        }
        return sum-2*sumPossible.get((sumPossible.size()-1)/2);
    }
    public static int countSubsetsTargetSum(ArrayList<Integer>list,int target){
        int[][] ans = new  int[list.size()+1][target+1];
        for (int i = 0; i <= list.size(); i++) {
            for (int j = 0; j <= target ; j++) {
                if (j==0){
                    ans[i][j]=1;
                }
                else if (i==0){
                    ans[i][j]=0;
                }
                else if (list.get(i-1)<=j){
                    ans[i][j]=ans[i-1][j-list.get(i-1)]+ans[i-1][j];
                }
                else {
                    ans[i][j]=ans[i-1][j];
                }
            }
        }
        return ans[list.size()][target];
    }
    public static boolean equalPartition(ArrayList<Integer> list){
        int sum=0;
        for (int x:list){
            sum+=x;
        }
        if (sum%2==0){
            return subsetSum(list,sum/2);
        }
        return false;
    }
    public static boolean subsetSum(ArrayList<Integer> list,int target){
        boolean[][] ans = new boolean[list.size()+1][target+1];
        for (int i = 0; i <=list.size(); i++) {
            for (int j = 0; j <= target; j++) {
                if (j==0){
                    ans[i][j]=true;
                }
                else if(i==0){
                    ans[i][j]=false;
                }
                else if (list.get(i-1)<=j){
                    ans[i][j]= ans[i-1][j-list.get(i-1)] || ans[i-1][j];
                }
                else {
                    ans[i][j]=ans[i-1][j];
                }
            }
        }
        return ans[list.size()][target];
    }
    public static int maxWeight(ArrayList<Integer> values, ArrayList<Integer> weights, int capacity){
        int n=weights.size();
        //return maxWeightRecursive(values,weights,capacity,n);
        return maxWeightDP(values,weights,capacity);
    }
    public static int maxWeightRecursive(ArrayList<Integer> values, ArrayList<Integer> weights, int capacity,int n){
        if (n==0||capacity==0) return 0;
        if (weights.get(n-1)<=capacity){
            return Math.max(values.get(n-1)+maxWeightRecursive(values,weights,capacity-weights.get(n-1),n-1),
                                maxWeightRecursive(values,weights,capacity,n-1));
        }
        else {
            return maxWeightRecursive(values,weights,capacity,n-1);
        }
    }
    public static int maxWeightDP(ArrayList<Integer> values, ArrayList<Integer> weights, int capacity){
        int[][] ans = new int[weights.size()+1][capacity+1];
        for (int i = 0; i <= weights.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i==0||j==0){
                    ans[i][j]=0;
                }
                else if (weights.get(i-1)<=j){
                    ans[i][j]=Math.max(values.get(i-1)+ans[i-1][j-weights.get(i-1)], ans[i-1][j]);
                }
                else {
                    ans[i][j] = ans[i-1][j];
                }
            }
        }
        return ans[weights.size()][capacity];
    }

    public int TusharsBirthdayParty(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
        int maxSum = Collections.max(A);  //maximum capacity
        int[] dp = new int[maxSum+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=maxSum;i++)  //check for each capacity
            for(int j=0;j<C.size();j++){
                if(i-B.get(j) >=0 ){
                    dp[i] = Math.min(dp[i], C.get(j) + dp[i-B.get(j)]);
                }
            }
        int ans = 0;
        for (int x: A){
            ans+=dp[x];
        }
        return ans;
    }

    static List<List<Integer>> list;
    public static List<List<Integer>> permute(int[] nums) {
        list=new ArrayList<>();
        boolean[] v = new boolean[nums.length];
        permute(nums,v,new ArrayList<>());
        return list;

    }
    static void permute(int[] n, boolean[] v,List<Integer> a){
        if(a.size()==n.length) {
            list.add(new ArrayList<>(a));
            return;
        }
        for(int i=0;i<n.length;i++){
            if(!v[i]){
                a.add(n[i]);
                v[i]=true;
                permute(n,v,a);
                a.remove(a.size()-1);
                v[i]=false;
            }

        }
    }

}
