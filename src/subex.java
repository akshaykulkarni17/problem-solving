import java.util.*;

public class subex {

    //[12,3,6,5,8,6,2,8,1] sum=6
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(12, 3, 6, 5, 8, 6, 2, 8, 1));
        System.out.println(threeSum(arr,6));
    }

    static ArrayList<Integer> threeSum(ArrayList<Integer> A,int sum){
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i)<sum){
                int tempSum=sum-A.get(i);
                Set<Integer> set = new HashSet<>();
                for (int j = i+1; j <A.size(); j++) {
                    if(set.contains(tempSum-A.get(j))){
                        ArrayList<Integer> ans= new ArrayList<>();
                        ans.add(A.get(i));
                        ans.add(A.get(j));
                        ans.add(tempSum-A.get(j));
                        return ans;
                    }
                    set.add(A.get(j));
                }
            }
        }
        return new ArrayList<>();
    }

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> q = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> freqCount = new HashMap<>();
        for (List<Integer> query : queries) {
            int op = query.get(0);
            int v = query.get(1);
            if (op == 1) {
                if (freq.containsKey(v)) {
                    freq.put(v, freq.get(v) + 1);
                } else {
                    freq.put(v, 1);
                }
                if (freqCount.containsKey(freq.get(v))){
                    freqCount.put(freq.get(v),freqCount.get(freq.get(v))+1);
                }
                else freqCount.put(freq.get(v),1);
            } else if (op == 2 && freq.containsKey(v)) {
                if (freqCount.get(freq.get(v))==1) freqCount.remove(freq.get(v));
                else freqCount.put(freq.get(v),freqCount.get(freq.get(v))-1);
                if (freq.get(v) == 1) {
                    freq.remove(v);
                } else {
                    freq.put(v, freq.get(v) - 1);
                }
            } else if (op == 3) {
                if (freqCount.containsKey(v)) {
                    q.add(1);
                } else q.add(0);
            }
        }
        return q;
    }
}
