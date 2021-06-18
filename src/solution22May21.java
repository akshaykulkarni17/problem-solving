

import java.util.*;


public class solution22May21 {

    public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 6, 13, 15, 16, 17, 13, 13, 15, 17, 17, 17, 17, 17, 19, 19));
    //System.out.println(solveGameOfBottles(list));
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(26, 41));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(40,47));
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(47, 7));
        ArrayList<Integer> l4 = new ArrayList<>(Arrays.asList(50,34));
        ArrayList<Integer> l5 = new ArrayList<>(Arrays.asList(18,28));
        ArrayList<ArrayList<Integer>> l = new ArrayList<>();
        l.add(l1);l.add(l2);l.add(l3);l.add(l4);l.add(l5);
        //System.out.println(solveBClosestPointsToOrigin(l,5));
        //System.out.println(solveInversionCount(list));
        System.out.println(MaximumUnsortedSubArray(list));
    }


    public static int solveInversionCount(ArrayList<Integer> A) {
        return mergeCount(A,0,A.size()-1);
    }

    private static int mergeCount(ArrayList<Integer> a, int low, int high) {
        long count=0;
        if (low<high){
            int mid=low+(high-low)/2;
            count+=mergeCount(a,low,mid);
            count%=1000000007;
            count+=mergeCount(a,mid+1,high);
            count%=1000000007;
            count+=mergeSortCount(a,low,mid,high);
            count%=1000000007;
        }
        return (int) count;
    }

    private static long mergeSortCount(ArrayList<Integer> a, int low, int mid, int high) {
        ArrayList<Integer> left = new ArrayList<>(a.subList(low,mid+1));
        ArrayList<Integer> right = new ArrayList<>(a.subList(mid+1,high+1));
        int i=0;
        int j=0;
        int k=low;
        long swaps=0;
        while(i<left.size()&&j< right.size()){
            if (left.get(i)<=right.get(j)){
                    a.set(k,left.get(i));
                    k++;i++;
            }
            else {
                swaps+=(mid+1)-(low+i);
                swaps%=1000000007;
                a.set(k, right.get(j));
                k++;j++;
            }
        }
        while (i<left.size()){
            a.set(k,left.get(i));
            k++;i++;
        }
        while (j<right.size()){
            a.set(k, right.get(j));
            k++;j++;
        }
        return swaps;
    }

    public static ArrayList<Integer> solveMaximumAndMinimumMagic(ArrayList<Integer> A) {
        int i=0;
        int j=A.size()-1;
        Collections.sort(A);
        long mod=1000000007;
        ArrayList<Integer> maxSet1=new ArrayList<>();
        ArrayList<Integer> maxSet2=new ArrayList<>();
        while(i<j){
            maxSet1.add(A.get(i));
            maxSet2.add(A.get(j));
            i++;
            j--;
        }
        long maxSum =0;
        for (int k = 0; k < maxSet1.size(); k++) {
            maxSum+=(Math.abs(maxSet2.get(k)%mod-maxSet1.get(k)%mod))%mod;
            maxSum%=mod;
        }
        ArrayList<Integer> minSet1=new ArrayList<>();
        ArrayList<Integer> minSet2=new ArrayList<>();
        i=0;
        j=i+1;
        while (j<A.size()){
            minSet1.add(A.get(i));
            minSet2.add(A.get(j));
            i+=2;j+=2;
        }
        long minSum=0;
        for (int k = 0; k < minSet1.size(); k++) {
            minSum+=(Math.abs(minSet2.get(k)%mod-minSet1.get(k)%mod))%mod;
            minSum%=mod;
        }
        return new ArrayList<>(Arrays.asList((int)maxSum,(int)minSum));
    }




    public ArrayList<ArrayList<Integer>> BClosestPointsToOrigin(ArrayList<ArrayList<Integer>> A, int B) {
        A.sort((p1, p2) -> (p1.get(0) * p1.get(0) + p1.get(1) * p1.get(1) - p2.get(0) * p2.get(0) - p2.get(1) * p2.get(1)));
        return new ArrayList<>(A.subList(0,B));
    }

    public ArrayList<ArrayList<Integer>> solveBClosestPointsToOrigin(ArrayList<ArrayList<Integer>> A, int B) {
        int index=0;
        ArrayList<ArrayList<Integer>> distance = new ArrayList<>();
        for (ArrayList<Integer> points : A){
            long temp = (long) points.get(0)*(long)points.get(0) + (long) points.get(1)*(long)points.get(1);
            int dist = (int) Math.sqrt(temp);
            distance.add(new ArrayList<Integer>(Arrays.asList(index,dist)));
            index++;
        }
        distance.sort(Comparator.comparing(a->a.get(1)));
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < B; i++) {
            answer.add(A.get(distance.get(i).get(0)));
        }
        return answer;
    }




    public static int solveGameOfBottles(ArrayList<Integer> A) {
        Collections.sort(A);
        int i=0;
        int j=i+1;
        while (j<A.size()){
            if (A.get(i)<A.get(j)){
                i++;
            }
            j++;
        }
        return j-i;
    }


    public int solveSumTheDifference(ArrayList<Integer> A) {
        long minSum=0;
        long maxSum=0;
        long mod=1000000007;
        Collections.sort(A);
        int power=1;
        for (int i = A.size()-1; i >=0 ; i--) {
            minSum+= (A.get(i)%mod * power%mod)%mod;
            minSum%=mod;
            power<<=1;
        }
        power=1;
        for (Integer integer : A) {
            maxSum += (integer % mod * power % mod) % mod;
            maxSum %= mod;
            power <<= 1;
        }
        return (int) (maxSum-minSum);
    }
    public static ArrayList<Integer> MaximumUnsortedSubArray(ArrayList<Integer> A) {
        int left=0;
        int right=0;
        ArrayList<Integer> temp = new ArrayList<>(A);
        Collections.sort(temp);
        if(temp.equals(A)) return new ArrayList<>(Collections.singleton(-1));
        for (int i = 0; i < A.size()-1; i++) {
            if (A.get(i)>A.get(i+1)){
                left=i;
                break;
            }
        }
        for (int i = A.size()-1; i >0 ; i--) {
            if (A.get(i)<A.get(i-1)){
                right=i;
                break;
            }
        }
        int min = Collections.min(A.subList(left,right+1));
        int max = Collections.max(A.subList(left,right+1));
        for (int i = 0; i < left; i++) {
            if(A.get(i)>min) {
                left=i;
                break;
            }
        }
        for (int i = right+1; i < A.size(); i++) {
            if (A.get(i)<max){
                right=i;
            }
        }
        return new ArrayList<Integer>(Arrays.asList(left,right));
    }
    public int kthsmallestElementInArray(final List<Integer> A, int B) {
        List<Integer> array = new ArrayList<>(A);
        int index=0;
        int k=B;
        while(B!=0){
            int min=Integer.MAX_VALUE;
            int pos=0;
            for (int i = index; i <A.size() ; i++) {
                if (array.get(i)<min){
                    min= array.get(i);
                    pos=i;
                }
            }
            Collections.swap(array,index,pos);
            index++;
            B--;
        }
        return array.get(k-1);
    }
}
