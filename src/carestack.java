import java.util.*;

public class carestack {

    public static void main(String[] args) {
        List<Long> l = new ArrayList<Long>(Arrays.asList( 9L, 9L,3L, 3L, 5L));
        //System.out.println(uniqueNumbers(l));
        System.out.println(MaxSubArrayModM(l,7L));
    }

           static ArrayList<Integer> uniqueNumbers(ArrayList<Integer> list){
                int[] arr =new int[101];
                for (int x : list){
                    arr[x]++;
                }
                ArrayList<Integer> ans = new ArrayList<>();
                for (int i = 0; i < arr.length; i++) {
                    if(arr[i]>0){
                        ans.add(i);
                    }
                }
                return ans;
            }

    static long minTime(long[] machines, long goal) {
        long start=1;
        long end= (goal*Arrays.stream(machines).max().getAsLong());
        long ans=0;
        while(start<=end){
            long mid= start+(end-start)/2;
            long tot=0L;
            for (long x:machines){
                tot+=mid/x;
            }
            if(tot>=goal){
                ans=mid;
                end=mid-1;
            }
            else start=mid+1;
        }
        return ans;
    }

    static long MaxSubArrayModM(List<Long> arr, long m){
        TreeSet<Long> set = new TreeSet<>();
        long prefix=0L;
        long max=Long.MIN_VALUE;
        set.add(0L);
        for (long x: arr){
            prefix=(prefix+x)%m;
            max=Math.max(max,prefix);
            long start=set.higher(prefix)!=null?set.higher(prefix):-1;
            if (start!=-1){
                max=Math.max(max,prefix-start+m);
            }
            set.add(prefix);
        }
        return max;
    }
}

