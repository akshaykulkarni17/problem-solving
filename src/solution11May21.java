import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class solution11May21 {
    public static void main(String[] args) {
        //System.out.println(singleElementInSortedArray(list));
        //System.out.println(peakElement(list));
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 4, 2, 3, 6, 8, 4, 5));
        System.out.println(activityNotifications(list,5));
    }
    public static int activityNotifications(List<Integer> expenditure, int d) {
       int median=0;
       int notice=0;
        for (int i = d; i < expenditure.size(); i++) {
            List<Integer> temp = expenditure.subList(i-d,i);
            Collections.sort(temp);
            median = d%2==1? temp.get(d/2):(temp.get(d/2)+temp.get((d/2)+1))/2;
            if(expenditure.get(i)>=median*2) notice++;
        }
    return notice;
    }
    public static int rotatedSortedArraySearch(ArrayList<Integer> A, int find){
        int pivot=0;
        if (A.get(0)<A.get(A.size()-1)) pivot=-1;
        else pivot=pivotElement(A,0,A.size()-1);
        if (pivot==-1){
            return binarySearch(A,find,0,A.size()-1);
        }
        else if (A.get(0)<=find&&find>A.get(pivot)){
            return binarySearch(A,find,0,pivot-1);
        }
        else return binarySearch(A,find,pivot,A.size()-1);
    }

    private static int binarySearch(ArrayList<Integer> A, int find, int start, int end) {
        if(start>end) return -1;
        int mid=start+(end-start)/2;
        if (A.get(mid)==find) return mid;
        if (A.get(mid)>find) return binarySearch(A,find,start,mid-1);
        if (A.get(mid)<find) return binarySearch(A,find,mid+1,end);
        return -1;
    }

    public static int pivotElement(ArrayList<Integer> A, int start, int end){
        int mid=start+(end-start)/2;
        if (A.get(mid+1)>A.get(mid)&&A.get(mid)<A.get(mid-1)) return mid;
        if (A.get(start)<A.get(mid)){
            return pivotElement(A,mid,end);
        }
        if (A.get(start)>A.get(mid)){
            return pivotElement(A,start,mid);
        }
        return -1;
    }
    public static int peakElement(ArrayList<Integer> A){
        if (A.size()<=3) return Collections.max(A);
        return findPeakElement(A,0,A.size()-1);
    }
    public static int findPeakElement(ArrayList<Integer> A,int start,int end){
        int mid = start + (end-start)/2;
        if (mid==0||start==end) return A.get(mid);
        if (A.get(mid-1)<A.get(mid)&&A.get(mid+1)<A.get(mid)) return A.get(mid);
        if (A.get(mid-1)>A.get(mid)) return findPeakElement(A,start,mid-1);
        if (A.get(mid+1)>A.get(mid)) return findPeakElement(A,mid+1,end);
        return -1;
    }
    public static int singleElementInSortedArray(ArrayList<Integer> A) {
        if (A.size()%2==0) return -1;
        return findSingleElementInSortedArray(A,0,A.size()-1);
    }
    public static int findSingleElementInSortedArray(ArrayList<Integer> A, int start, int end){
        int mid = start+(end-start)/2;
        if (mid==0||start==end) return A.get(mid);
        else if (mid%2==0){
            if (!A.get(mid-1).equals(A.get(mid)) &&!A.get(mid).equals(A.get(mid+1))) return A.get(mid);
            if (A.get(mid-1).equals(A.get(mid))) return findSingleElementInSortedArray(A,start,mid-1);
            if (A.get(mid+1).equals(A.get(mid))) return findSingleElementInSortedArray(A,mid+1,end);
        }
        else if (mid%2==1){
            if (A.get(mid+1).equals(A.get(mid))) return findSingleElementInSortedArray(A,start,mid-1);
            if (A.get(mid-1).equals(A.get(mid))) return findSingleElementInSortedArray(A,mid+1,end);
        }
        return -1;
    }
}
