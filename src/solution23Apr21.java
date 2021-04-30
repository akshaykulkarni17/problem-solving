import java.util.*;

public class solution23Apr21 {

    public static void main(String[] args) {
    ArrayList<Integer> A = new ArrayList<>(Arrays.asList(5,3,2));
    ArrayList<Integer> B = new ArrayList<>(Arrays.asList(1, 5, 7, 8));
    //System.out.println(MaximumPathInArrays(A,B));
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1,2));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(2,3));
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(1, 3));
        ArrayList<ArrayList<Integer>> l = new ArrayList<>();
        l.add(l1);l.add(l2);l.add(l3);
        System.out.println(LittleGirlAndMaximumSum(A,l));
    }
    public static int LittleGirlAndMaximumSum(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        //ArrayList<Integer> list = new ArrayList<>();
        Collections.sort(A);
        int mod=1000000007;
//        Map<Integer,Integer> map = new HashMap<>();
//        for (ArrayList<Integer> a:
//             B) {
//            for (int i = a.get(0)-1; i < a.get(1); i++) {
//                if (map.containsKey(i)){
//                    map.put(i,map.get(i)+1);
//                }
//                else{
//                    map.put(i,1);
//                }
//            }
//        }
//        for (int i = 0; i < A.size(); i++) {
//            if (!map.containsKey(i)){
//                map.put(i,0);
//            }
//        }
//        ArrayList<ArrayList<Integer>> frequency = new ArrayList<>();
//        for (int x:
//             map.keySet()) {
//            frequency.add(new ArrayList<>(Arrays.asList(map.get(x),x)));
//        }
        ArrayList<ArrayList<Integer>> frequency = new ArrayList<>();
        int[] freq = new int[A.size()];
        for (ArrayList<Integer> q:
             B) {
            freq[q.get(0)-1] = freq[q.get(0)-1]+1;
            if (q.get(1)<freq.length) freq[q.get(1)] = freq[q.get(1)] -1;
        }
        int sum=0;
        for (int i = 0; i < freq.length; i++) {
            sum+=freq[i];
            freq[i]=sum;
        }
        for (int i = 0; i < freq.length; i++) {
            frequency.add(new ArrayList<>(Arrays.asList(freq[i],i)));
        }
        Collections.sort(frequency, Comparator.comparing(a -> a.get(0)));
//        for (int i = 0; i < A.size(); i++) {
//            list.add(0);
//        }
        int[] list = new int[frequency.size()];
        for (int i = 0; i < frequency.size(); i++) {
            list[frequency.get(i).get(1)] =A.get(i);
        }
        sum=0;
        ArrayList<Integer> prefix = new ArrayList<>();
        for (int x:
             list) {
            sum+=x;
            sum%=mod;
            prefix.add(sum);
        }
        sum=0;
        for (ArrayList<Integer> b:
                B) {
            int temp =  b.get(0)==1 ? prefix.get(b.get(1)-1) : prefix.get(b.get(1)-1)-prefix.get(b.get(0)-2);
            sum+=temp%mod;
            sum%=mod;
        }
        return sum;
    }
    public static int MaximumPathInArrays(ArrayList<Integer> A, ArrayList<Integer> B) {
        int a =0;
        int b=0;
        int aSum=0;
        int bSum=0;
        int sum=0;
        while(a<A.size()&&b<B.size()){
            if(A.get(a)<B.get(b)){
                aSum+=A.get(a);
                a++;
            }
            else if(B.get(b)<A.get(a)){
                bSum+=B.get(b);
                b++;
            }
            else if(A.get(a).equals(B.get(b))){
                sum+=A.get(a);
                sum+=Math.max(aSum,bSum);
                a++;b++;
                aSum=0;bSum=0;
            }
        }
        while(a<A.size()){
            aSum+=A.get(a);
            a++;
        }
        while(b<B.size()){
            bSum+=B.get(b);
            b++;
        }
        sum+=Math.max(aSum,bSum);
        return sum;
    }
}
