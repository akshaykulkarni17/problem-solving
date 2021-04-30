import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class solution30Apr21 {
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(756898537, -1973594324, -2038664370, -184803526, 1424268980 ));
        System.out.println(MaxNonNegativeSubArray(l));
        int num=6;
        Integer.toHexString(num);
    }

    public static ArrayList<Integer> MaxNonNegativeSubArray(ArrayList<Integer> A) {
        ArrayList<Integer> maxArray = new ArrayList<>();
        long maxSum=Integer.MIN_VALUE;
        ArrayList<Integer> array = new ArrayList<>();
        long sum=0;
        for (int x:
                A) {
            if (x>=0){
                sum+=x;
                array.add(x);
            }
            else{
                if (sum>maxSum){
                    maxSum=sum;
                    maxArray.clear();
                    maxArray.addAll(array);
                }
                sum=0;
                array.clear();
            }
        }
        if (sum>maxSum){
            maxArray.clear();
            maxArray.addAll(array);
        }
        return maxArray;
    }

    public static ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        if (A.get(A.size()-1)<9){
            A.set(A.size()-1,A.get(A.size()-1)+1);
            while(A.get(0)==0){
                A.remove(0);
            }
            return A;
        }
        int index = A.size()-1;
        while(index>=0&&A.get(index)==9){
            A.set(index,0);
            index--;
        }
        if (index==-1){
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i <= A.size(); i++) {
                ans.add(0);
            }
            ans.set(0,1);
            return ans;
        }
        A.set(index,A.get(index)+1);
        while(A.get(0)==0){
            A.remove(0);
        }
        return A;
    }
//    |A[i] - A[j]| + |i - j|
//    A[i]>A[j]&&i>j    A[i]-A[j]+i-j = A[i]+i - (A[j]+j)   do A[i]+i > max-min
//    A[i]<A[j]&&i<j    -A[i]+A[j]-i+j =   (A[j]+j) - (A[i]+i)      do A[i]+i > max-min
//    A[i]<A[j]&&i>j    A[j]-A[i]+i-j =     A[j]-j - (A[i]-i)    do A[i]-i > max-min
//    A[i]>A[j]&&i<j    -A[j]+A[i]-i+j =      (A[i]-i) -  (A[j]-j )  do A[i]-i > max-min

    public int MaximumAbsoluteDifference(ArrayList<Integer> A) {
        ArrayList<Integer> plusIndex  = new ArrayList<>();
        ArrayList<Integer> minusIndex = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            plusIndex.add(A.get(i)+i);
        }
        for (int i = 0; i < A.size(); i++) {
            minusIndex.add(A.get(i)-i);
        }
        int plus= Collections.max(plusIndex)-Collections.min(plusIndex);
        int minus = Collections.max(minusIndex)-Collections.min(minusIndex);
        return Math.max(plus,minus);
    }
}
