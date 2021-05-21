import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class solution {
    public static void main(String[] args) {

    }

    public int longestSubarrayDivisibleByK(ArrayList<Integer> A, int k){
        ArrayList<Integer> prefix = new ArrayList<>();
        int sum=0;
        for (int x:A) {
            sum+=x;
            prefix.add(sum);
        }
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < prefix.size(); i++) {
            if (map.containsKey(prefix.get(i)%k)){
                ArrayList<Integer> list = map.get(prefix.get(i)%k);
                list.add(i);
            }
            else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(prefix.get(i)%k,list);
            }
        }
        int ans=Integer.MIN_VALUE;
        int sumArr=0;
        for (int i = 0; i < prefix.size(); i++) {
            if (prefix.get(i)%k==0){
                sumArr=prefix.get(i);
                ans=Math.max(ans,i+1);
            }
        }
        for (int x : map.keySet()){
            ArrayList<Integer> indexes = map.get(x);
            int temp = prefix.get(indexes.get(indexes.size()-1)) - prefix.get(indexes.get(0));
            if (temp>sumArr){
                sumArr=temp;
                ans= 0;
            }
        }
        return ans;
    }
}
