import java.lang.reflect.Array;
import java.util.*;

class job{
    int st;
    int et;
    int prft;
}
public class test {

    int N;
    int[][] q = new int[N][N];
    int count=0;
    boolean[][] b = new boolean[N][N];
    void placequeen(){
        int qCount=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!b[i][j]){
                    q[i][j]=1;
                    b[i][j]=true;
                    qCount++;
                    if(isValid(i,j)) {
                        placequeen();
                        q[i][j]=0;
                        b[i][j]=false;
                        qCount--;
                    }
                    else{
                        q[i][j]=0;
                        b[i][j]=false;
                        qCount--;
                    }

                }
                if(i==N-1&&j==N-1){
                    if(qCount==N) count++;
                     return;
                }

            }
        }
    }

    private boolean isValid(int i, int j) {
        return true;
    }

    public static String largestNumber(int[] nums) {

        ArrayList<String> arr = new ArrayList<>();
        for(int x:nums){
            arr.add(String.valueOf(x));
        }
        arr.sort((a,b)->(a+b).compareTo(b+a));

        StringBuilder sb = new StringBuilder();
        Collections.reverse(arr);
        if(sb.charAt(0)=='0' ) return "0";
        for(String s: arr){
            sb.append(s);
        }
        return sb.toString();

    }

    static Set<List<Integer>> set ;
    public static List<List<Integer>> subsets(int[] nums) {
        boolean[] v = new boolean[nums.length];
        set=new HashSet<>();
        recur(nums,v,new ArrayList<>());
        return new ArrayList<>(set);
    }
    static void  recur(int[] nums, boolean[] v, List<Integer> l){
        List<Integer> temp = new ArrayList<>(l);
        Collections.sort(temp);
        set.add(temp);
        for(int i=0;i<nums.length;i++){
            if(!v[i]){
                l.add(nums[i]);
                v[i]=true;
                recur(nums,v,l);
                l.remove(l.size()-1);
                v[i]=false;

            }
        }
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        int [][]job  = new int[n][];
        for(int i=0; i<n; i++){
            job[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(job, (int[]a, int[]b)->a[0]!=b[0]?a[0]-b[0] : b[2]-a[2]);
        TreeMap<Integer,Integer> dp = new TreeMap<>();
        int maxProfit = 0;
        for(int i=n-1; i>=0; i-- ){

            Map.Entry<Integer, Integer> leaveEntry = dp.ceilingEntry(job[i][0]);
            Map.Entry<Integer, Integer> takeEntry = dp.ceilingEntry(job[i][1]);

            int profitIfLeft = leaveEntry == null? 0 : leaveEntry.getValue();
            int profitIfTaken =  job[i][2] + (takeEntry == null? 0 : takeEntry.getValue());

            int max = Math.max(profitIfLeft, profitIfTaken);

            dp.put(job[i][0], max);
            maxProfit = Math.max(maxProfit, max);
        }
        return maxProfit;
    }

    public static int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        ArrayList<job> jobs = new ArrayList<>();
        for(int i=0;i<profit.length;i++){
            job temp = new job();
            temp.st=startTime[i];
            temp.et=endTime[i];
            temp.prft=profit[i];
            jobs.add(temp);
        }
        jobs.sort((a,b)->b.st-a.st);
        TreeMap<Integer,Integer> maxProfit = new TreeMap<>();
        int prev=0;
        for (job j: jobs){
            Map.Entry<Integer, Integer> currMax = maxProfit.ceilingEntry(j.et);
            if (currMax!=null){
                j.prft+=currMax.getValue();
            }
            prev=Math.max(j.prft,prev);
            maxProfit.put(j.st,prev);
        }
        return prev;
    }

    static int LIS (int[] arr){
        int[] dp = new int[arr.length];
        dp[0]=1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] <= dp[j] )
                    dp[i] = dp[j] + 1;

            }
        }
        return dp[arr.length-1];
    }

    public static void main(String[] args) {
        int[] arr={1,2,3,3,5};
        int[] arr2={3,4,5,6,6};
        int[] arr3={0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15));
        Collections.shuffle(a);
        //System.out.println(largestNumber(arr));
        //System.out.println(subsets(arr));
        //System.out.println(jobScheduling2(arr,arr2,arr3));
        System.out.println(LIS(arr3));
        TreeMap<Integer,Integer> map = new TreeMap<>();
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(55);
        dq.remove(55);


    }

    static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }


}
